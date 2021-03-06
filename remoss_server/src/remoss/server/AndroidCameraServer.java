/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package remoss.server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author Administrateur
 */
public class AndroidCameraServer extends javax.swing.JFrame {
    boolean isCam1Used = false; 
    boolean isCam2Used = false;
    boolean isCam3Used = false;
    boolean isCam4Used = false;
    boolean isCam5Used = false;
    boolean isCam6Used = false;
    boolean isShuffle = false;
    boolean isSaveOnMov = false;
    Thread mShuffleThread = new Thread();
    
    public AndroidCameraServer() throws IOException {
        Image i = ImageIO.read(getClass().getResource("Camera.gif"));
        setIconImage(i);
        initComponents();
        ConnectionString ipCipher = new ConnectionString();
        String encryptedIPAddress = ipCipher.encrypt();
        this.lblAddress.setText(encryptedIPAddress);
        startServer();
        
        AndroidCameraServer.this.chkAutoShuffle.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(!isShuffle){
                    isShuffle = true;
                    mShuffleThread = new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                               while(true){
                                   for(int i = 1; i <= 6; i++){
                                       AndroidCameraServer.this.MainCameraNumber.setText(Integer.toString(i));
                                       try {
                                           int sleep = (int)AndroidCameraServer.this.spnShuffle.getValue();
                                           Thread.sleep(sleep * 1000);
                                       } catch (InterruptedException ex) {
                                           Logger.getLogger(AndroidCameraServer.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                   }
                               }
                            }
                      });
                    mShuffleThread.start();
                }
                else{
                    mShuffleThread.stop();
                    isShuffle = false;
                }
            }
      });
        
        AndroidCameraServer.this.chk_SaveMovement.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(!isSaveOnMov){
                    isSaveOnMov = true;
                }
                else{
                    isSaveOnMov = false;
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Camera1 = new javax.swing.JLabel();
        Camera2 = new javax.swing.JLabel();
        Camera4 = new javax.swing.JLabel();
        Camera5 = new javax.swing.JLabel();
        Camera3 = new javax.swing.JLabel();
        Camera6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        MainCamera = new javax.swing.JLabel();
        MainCameraNumber = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        txt_Path = new javax.swing.JTextField();
        btn_Path = new javax.swing.JButton();
        spnShuffle = new javax.swing.JSpinner();
        chkAutoShuffle = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        chk_SaveMovement = new javax.swing.JCheckBox();
        spn_trigger = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        chk_diff = new javax.swing.JCheckBox();
        Options = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblAddress = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MultiCam-Server");
        setBackground(new java.awt.Color(255, 255, 255));

        Camera1.setBackground(new java.awt.Color(0, 0, 0));
        Camera1.setOpaque(true);
        Camera1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Camera1MouseClicked(evt);
            }
        });

        Camera2.setBackground(new java.awt.Color(0, 0, 0));
        Camera2.setOpaque(true);
        Camera2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Camera2MouseClicked(evt);
            }
        });

        Camera4.setBackground(new java.awt.Color(0, 0, 0));
        Camera4.setOpaque(true);
        Camera4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Camera4MouseClicked(evt);
            }
        });

        Camera5.setBackground(new java.awt.Color(0, 0, 0));
        Camera5.setOpaque(true);
        Camera5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Camera5MouseClicked(evt);
            }
        });

        Camera3.setBackground(new java.awt.Color(0, 0, 0));
        Camera3.setOpaque(true);
        Camera3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Camera3MouseClicked(evt);
            }
        });

        Camera6.setBackground(new java.awt.Color(0, 0, 0));
        Camera6.setOpaque(true);
        Camera6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Camera6MouseClicked(evt);
            }
        });

        jLabel8.setText("Caméra #1");

        jLabel9.setText("Caméra #2");

        jLabel10.setText("Caméra #3");

        jLabel11.setText("Caméra #4");

        jLabel12.setText("Caméra #5");

        jLabel13.setText("Caméra #6");

        MainCamera.setBackground(new java.awt.Color(0, 0, 0));
        MainCamera.setOpaque(true);

        MainCameraNumber.setText("1");

        jLabel1.setText("Caméra Principale: #");

        txt_Path.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_PathActionPerformed(evt);
            }
        });

        btn_Path.setText("Save Pictures to...");
        btn_Path.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PathActionPerformed(evt);
            }
        });

        spnShuffle.setModel(new javax.swing.SpinnerNumberModel(1, 1, 15, 1));

        chkAutoShuffle.setText("Auto Shuffle Cameras every");

        jLabel3.setText("seconds");

        chk_SaveMovement.setText("Save pictures when ");

        spn_trigger.setModel(new javax.swing.SpinnerNumberModel(10, 10, 100, 5));

        jLabel5.setText("% movement detected");

        chk_diff.setText("Show differences");
        chk_diff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_diffActionPerformed(evt);
            }
        });

        Options.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Options.setText("Options");

        jButton1.setText("Take a picture of the main camera");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblAddress.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAddress.setText("j");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Connexion string");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(txt_Path)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Path))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chk_diff)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(chkAutoShuffle)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnShuffle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(chk_SaveMovement)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spn_trigger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(38, 38, 38))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(Options)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(lblAddress))
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Options)))
                .addGap(4, 4, 4)
                .addComponent(chk_diff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkAutoShuffle)
                    .addComponent(spnShuffle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_SaveMovement)
                    .addComponent(jLabel5)
                    .addComponent(spn_trigger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Path)
                    .addComponent(txt_Path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(MainCamera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(MainCameraNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 892, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(Camera1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(Camera2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(Camera3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(Camera4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(Camera6, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Camera5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(MainCameraNumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MainCamera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(4, 4, 4)
                                .addComponent(Camera1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(4, 4, 4)
                                .addComponent(Camera4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jLabel12)))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Camera5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addGap(6, 6, 6)
                                .addComponent(Camera6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Camera2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel10)
                                .addGap(6, 6, 6)
                                .addComponent(Camera3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(200, 200, 200))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Camera1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Camera1MouseClicked
        AndroidCameraServer.this.MainCameraNumber.setText("1");
    }//GEN-LAST:event_Camera1MouseClicked

    private void Camera2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Camera2MouseClicked
        AndroidCameraServer.this.MainCameraNumber.setText("2");
    }//GEN-LAST:event_Camera2MouseClicked

    private void Camera3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Camera3MouseClicked
        AndroidCameraServer.this.MainCameraNumber.setText("3");
    }//GEN-LAST:event_Camera3MouseClicked

    private void Camera4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Camera4MouseClicked
        AndroidCameraServer.this.MainCameraNumber.setText("4");
    }//GEN-LAST:event_Camera4MouseClicked

    private void Camera5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Camera5MouseClicked
        AndroidCameraServer.this.MainCameraNumber.setText("5");
    }//GEN-LAST:event_Camera5MouseClicked

    private void Camera6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Camera6MouseClicked
        AndroidCameraServer.this.MainCameraNumber.setText("6");
    }//GEN-LAST:event_Camera6MouseClicked

    private void chk_diffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_diffActionPerformed

    }//GEN-LAST:event_chk_diffActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new JFrameImageSaver(AndroidCameraServer.this, AndroidCameraServer.this.MainCamera);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_PathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PathActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            AndroidCameraServer.this.txt_Path.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btn_PathActionPerformed

    private void txt_PathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PathActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AndroidCameraServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AndroidCameraServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AndroidCameraServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AndroidCameraServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AndroidCameraServer n = new AndroidCameraServer();
                    
                    n.panel1.setBackground(Color.lightGray);
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    n.setMinimumSize(screenSize);
                    n.setExtendedState(n.getExtendedState() | AndroidCameraServer.MAXIMIZED_BOTH);
                    n.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(AndroidCameraServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Camera1;
    public javax.swing.JLabel Camera2;
    public javax.swing.JLabel Camera3;
    public javax.swing.JLabel Camera4;
    public javax.swing.JLabel Camera5;
    public javax.swing.JLabel Camera6;
    public javax.swing.JLabel MainCamera;
    public javax.swing.JLabel MainCameraNumber;
    public javax.swing.JLabel Options;
    private javax.swing.JButton btn_Path;
    public javax.swing.JCheckBox chkAutoShuffle;
    public javax.swing.JCheckBox chk_SaveMovement;
    public javax.swing.JCheckBox chk_diff;
    public javax.swing.JButton jButton1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JLabel lblAddress;
    public java.awt.Panel panel1;
    public javax.swing.JSpinner spnShuffle;
    public javax.swing.JSpinner spn_trigger;
    public javax.swing.JTextField txt_Path;
    // End of variables declaration//GEN-END:variables
 public void startServer() {
        (new Thread(new AssignCamera(AndroidCameraServer.this))).start();
    }
}


