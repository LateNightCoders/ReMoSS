package stevenseesall.com.camera;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Fred on 2/7/2015.
 */
public class ThreadCamera extends Thread {
    Context mContext;
    Camera mCamera;
    Activity mActivity;
    SeekBar mSeekBar;
    FrameLayout mFrameLayout;
    int mSensibility = 0;
    private SurfaceHolder mHolder;
    boolean Libre = true;
    int[] mImageAvant;
    TextView mTextView;
    TextView mMouvementTextView;
    ToggleButton mToggleButton;
    Boolean isAlarmOn = false;
    String ServerIP = "192.168.1.100";
    int mSkippedFrameHorizontal = 3;
    int mSkippedFrameVertical = 3;

    public ThreadCamera(final Context context, Activity activity, SeekBar seekBar, FrameLayout frameLayout, TextView textView,
                        TextView textViewMouvement, ToggleButton toggleButton) throws IOException {
        mContext = context;
        mActivity = activity;
        mSeekBar = seekBar;
        mFrameLayout = frameLayout;
        mTextView = textView;
        mMouvementTextView = textViewMouvement;
        mToggleButton = toggleButton;

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    isAlarmOn = true;
                } else {
                    isAlarmOn = false;
                }
            }
        });
    }


    public void run() {
        boolean hasCamera = checkCameraHardware(mContext);

        if (!hasCamera) {
            throw new RuntimeException("No camera found on device");
        }
        mCamera = getCameraInstance();
        Looper.prepare();
        final CameraPreview preview = new CameraPreview(mContext);
        final FrameLayout previewFrame = mFrameLayout;

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                previewFrame.addView(preview);
            }
        });
        Looper.loop();

    }

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    public static Camera getCameraInstance() {
        Camera camera;
        try {
            camera = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            throw new RuntimeException("No camera found on device");
        }

        return camera; // returns null if camera is unavailable

    }

    private class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

        public CameraPreview(final Context context) {
            super(context);

            mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    mSensibility = progress;
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(Integer.toString(mSensibility));
                        }
                    });

                    //Toast.makeText(context, "Sensibilité: " + progress, Toast.LENGTH_SHORT).show();
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                }
            });
            // Install a SurfaceHolder.Callback so we get notified when the
            // underlying surface is created and destroyed.
            mHolder = getHolder();
            mHolder.addCallback(this);
            // deprecated setting, but required on Android versions prior to 3.0
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // The Surface has been created, now tell the camera where to draw the preview.
            try {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();
            } catch (IOException e) {
                Log.d("Error", "Error setting camera preview: " + e.getMessage());
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            // If your preview can change or rotate, take care of those events here.
            // Make sure to stop the preview before resizing or reformatting it.

            if (mHolder.getSurface() == null) {
                // preview surface does not exist
                return;
            }

            try {
                mCamera.stopPreview();
            } catch (Exception e) {

            }

            Camera.Parameters parameters = mCamera.getParameters();
            List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
            Camera.Size cameraSize = sizes.get(0);
            parameters.setPreviewSize(cameraSize.width, cameraSize.height);
            parameters.setRotation(90);
            parameters.setPreviewFrameRate(8);

            mCamera.setDisplayOrientation(90);
            mCamera.setParameters(parameters);

            // start preview with new settings
            try {
                mCamera.setPreviewDisplay(mHolder);
                mCamera.setPreviewCallback(new Camera.PreviewCallback() {
                    public void onPreviewFrame(final byte[] data, final Camera camera) {
                        final int frameHeight = camera.getParameters().getPreviewSize().height;
                        final int frameWidth = camera.getParameters().getPreviewSize().width;

                        (new Thread(new ThreadSendUDPFeed(data, ServerIP, 666, frameHeight, frameWidth))).start();

                        if (Libre) {
                            int rgb[] = new int[(frameWidth * frameHeight) / mSkippedFrameHorizontal / mSkippedFrameVertical];
                            int[] image = decodeYUV420SP(rgb, data, frameWidth, frameHeight, mSkippedFrameHorizontal, mSkippedFrameVertical);
                            if (mImageAvant == null) {
                                mImageAvant = image;
                            }

                            Libre = false;
                            ThreadCheckMovement checkMovement = new ThreadCheckMovement(image, frameWidth, frameHeight, mSkippedFrameHorizontal, mSkippedFrameVertical,
                                    mSensibility, mImageAvant, mActivity, mMouvementTextView, mToggleButton, mContext, isAlarmOn);
                            (new Thread(checkMovement)).start();
                            mImageAvant = checkMovement.getmImage();
                            Libre = true;
                        }
                    }
                });
                mCamera.startPreview();

            } catch (Exception e) {
                Log.d("Error", "Error starting camera preview: " + e.getMessage());
            }

        }

        int[] decodeYUV420SP(int[] rgb, byte[] yuv420sp, int width, int height, int skippedFrameHorizontal, int skippedFrameVertical) {
            final int frameSize = (width * height / skippedFrameHorizontal) / skippedFrameVertical;
            for (int j = 0, yp = 0; j < height; j += skippedFrameHorizontal) {
                int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
                for (int i = 0; i < width; i += skippedFrameVertical, yp++) {
                    int y = (0xff & ((int) yuv420sp[yp])) - 16;
                    if (y < 0)
                        y = 0;
                    if ((i & 1) == 0) {
                        v = (0xff & yuv420sp[uvp++]) - 128;
                        u = (0xff & yuv420sp[uvp++]) - 128;
                    }

                    int y1192 = 1192 * y;
                    int r = (y1192 + 1634 * v);
                    int g = (y1192 - 833 * v - 400 * u);
                    int b = (y1192 + 2066 * u);

                    if (r < 0) {
                        r = 0;
                    } else if (r > 262143)
                        r = 262143;
                    if (g < 0)
                        g = 0;
                    else if (g > 262143)
                        g = 262143;
                    if (b < 0)
                        b = 0;
                    else if (b > 262143)
                        b = 262143;
                    rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
                }
            }
            return rgb;
        }
    }
}