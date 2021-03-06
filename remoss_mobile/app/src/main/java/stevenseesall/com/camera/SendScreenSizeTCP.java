package stevenseesall.com.camera;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SendScreenSizeTCP {
    String mServerIP;
    int mHeight = 0;
    int mWidth = 0;
    int mPort = 0;

    public SendScreenSizeTCP(int height, int width, String serverIP){
        mServerIP = serverIP;
        mHeight = height;
        mWidth = width;
    }

    public void GetCam() {
        Socket AskSocket = null;
        try {
            AskSocket = new Socket(mServerIP, 44444);
            DataOutputStream AskSocketout = new DataOutputStream(AskSocket.getOutputStream());
            String addr = Utils.getIPAddress(true);
            AskSocketout.writeBytes(addr);
            AskSocketout.close();
            AskSocket.close();

            ServerSocket serverSocket = new ServerSocket(44444);
            final Socket PortSocket = serverSocket.accept();
            readInts(PortSocket);
            serverSocket.close();
            PortSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readInts(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        DataInputStream dis = new DataInputStream(in);

        mPort = dis.readInt();
    }
}
