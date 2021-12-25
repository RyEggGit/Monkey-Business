/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonkeyBusiness;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class EndScreen extends javax.swing.JFrame {

    MainMenu firstWindow;
    GameLoop game;
    
    int t;
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    // width will store the width of the screen
    int screenWidth = (int) size.getWidth();
    // height will store the height of the screen
    int screenHeight = (int) size.getHeight();
    
    public EndScreen(MainMenu m, GameLoop g, int time) {   
        
        firstWindow = m;
        setSize(screenWidth, screenHeight);
        //starting position
        setLocationRelativeTo(null);
        //exits when closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //draws the JLabel of the game
       
        //Sets JFrame to Full screen
        setUndecorated(true);
        setResizable(false);
        //sets JFrame to visible
        setVisible(true);
        
        btnBack = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblSubTitle = new javax.swing.JLabel();
        lblScroll = new javax.swing.JLabel();
        btnBackGround = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        btnBack.setFont(new java.awt.Font("Tempus Sans ITC", 0, 48)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 0));
        btnBack.setText("Menu");
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack);
        btnBack.setBounds(530, 710, 250, 140);

        lblTitle.setFont(new java.awt.Font("Tempus Sans ITC", 0, 150)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 0));
        lblTitle.setText("You Win!");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(600, 90, 615, 197);

        lblTime.setFont(new java.awt.Font("Tempus Sans ITC", 0, 96)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 0));
        lblTime.setText("0:00");
        getContentPane().add(lblTime);
        lblTime.setBounds(760, 400, 380, 270);

        lblSubTitle.setFont(new java.awt.Font("Tempus Sans ITC", 0, 96)); // NOI18N
        lblSubTitle.setForeground(new java.awt.Color(255, 255, 0));
        lblSubTitle.setText("Your time was");
        getContentPane().add(lblSubTitle);
        lblSubTitle.setBounds(620, 250, 850, 200);

        lblScroll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MonkeyBusiness/output-onlinepngtools.png"))); // NOI18N
        getContentPane().add(lblScroll);
        lblScroll.setBounds(370, 40, 1100, 916);

        btnBackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MonkeyBusiness/backgroundRoom0.png"))); // NOI18N
        btnBackGround.setText("jLabel3");
        getContentPane().add(btnBackGround);
        btnBackGround.setBounds(0, 0, 1920, 1080);
        
        setSize(1920, 1080);
        firstWindow = m;
        game = g;
        t = time;
        
        ArrayList<Integer> times = new ArrayList<Integer>();

        /*
        try {
            //create scanner for file
            System.out.println("started reading scores");
            Scanner s = new Scanner(EndScreen.class.getResourceAsStream("scores.txt"));
            System.out.println("ended reading scores");
            //while file has another line add contents to an array list
            while (s.hasNextInt()) {
                times.add(s.nextInt());
            }
            
            OutputStream out = new FileOutputStream("scores.txt");

            ObjectOutput sc = new ObjectOutputStream(out);
            sc.writeObject(times + "" + t);
            /*
            try (FileWriter fw = new FileWriter("scores.txt")) {
                fw.write(times + "");
            }
             
            System.out.println("Success!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        */
        changeTime();
    }
        

    //method for changing time from seconds to minutes
    public void changeTime(){
        System.out.println("Time: "+ t %60);
        int minutes = Math.floorDiv(t, 60);
        System.out.println("Minutes: "+ minutes);
        //if times if more than 10 run
        if (t < 10){
            lblTime.setText(minutes + ":0" + t %60);
        }
        //otherwise run this
        else{
            lblTime.setText(minutes + ":" + t %60);
        }  
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblSubTitle = new javax.swing.JLabel();
        lblScroll = new javax.swing.JLabel();
        btnBackGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        btnBack.setFont(new java.awt.Font("Tempus Sans ITC", 0, 48)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 0));
        btnBack.setText("Menu");
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack);
        btnBack.setBounds(530, 710, 250, 140);

        lblTitle.setFont(new java.awt.Font("Tempus Sans ITC", 0, 150)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 0));
        lblTitle.setText("You Win!");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(600, 90, 615, 197);

        lblTime.setFont(new java.awt.Font("Tempus Sans ITC", 0, 96)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 0));
        lblTime.setText("0:00");
        lblTime.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblTime);
        lblTime.setBounds(760, 400, 340, 270);

        lblSubTitle.setFont(new java.awt.Font("Tempus Sans ITC", 0, 96)); // NOI18N
        lblSubTitle.setForeground(new java.awt.Color(255, 255, 0));
        lblSubTitle.setText("Your time was");
        getContentPane().add(lblSubTitle);
        lblSubTitle.setBounds(620, 250, 850, 200);

        lblScroll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MonkeyBusiness/output-onlinepngtools.png"))); // NOI18N
        getContentPane().add(lblScroll);
        lblScroll.setBounds(370, 40, 1100, 916);

        btnBackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MonkeyBusiness/backgroundRoom0.png"))); // NOI18N
        btnBackGround.setText("jLabel3");
        getContentPane().add(btnBackGround);
        btnBackGround.setBounds(0, 0, 1920, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        firstWindow.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel btnBackGround;
    private javax.swing.JLabel lblScroll;
    private javax.swing.JLabel lblSubTitle;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}
