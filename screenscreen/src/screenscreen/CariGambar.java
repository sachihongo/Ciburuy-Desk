/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screenscreen;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
public class CariGambar extends javax.swing.JFrame implements ActionListener {
    int[] tilemap = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12};
    JToggleButton[] cards = new JToggleButton[24];
    Object card1Object = null;
    Object card2Object = null;
    String card1Image = "";
    String card2Image = "";
    int card1Num = 0;
    int card2Num = 0;
    int cardsSelected = 0;
    int cardsLeft = 24;
    int numTries = 0;
    public CariGambar() {
        initComponents();
        shuffleCards();
    }
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(800,700);
        setTitle("Game Cocok Gambar Aksara Sunda");
        JPanel buttonPane = new JPanel();
        JPanel cardPane = new JPanel();
        buttonPane.setLayout(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        b.fill = GridBagConstraints.HORIZONTAL;
        cardPane.setLayout(new GridLayout(6,6));
        numTriesLabel = new JLabel();
        b.gridx = 0;
        b.gridy = 1;
        buttonPane.add(numTriesLabel,b);
        replayButton = new JButton();
        b.gridx = 0;
        b.gridy = 0;
        buttonPane.add(replayButton,b);
        exitButton = new JButton();
        b.gridx = 1;
        b.gridy = 0;
        buttonPane.add(exitButton,b);
        numTriesLabel.setText("Total Percobaan: " + numTries +" x");
        replayButton.setMnemonic('S');
        replayButton.setText("Reset");
        replayButton.setToolTipText("Reset");
        replayButton.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    replayButtonActionPerformed(evt);
                }
            }
        );
        exitButton.setMnemonic('x');
        exitButton.setText("Keluar");
        exitButton.setToolTipText("Keluar");
        exitButton.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    //exitButtonActionPerformed(evt);
                    new home().setVisible(true);
                    //this.setVisible(false);
                }
            }
        );
        for (int i = 0; i < cards.length; i++)
        {
            cards[i] = new JToggleButton("Card: " + i);
            cards[i].addActionListener(this);
            cards[i].setSize(100,100);
            cards[i].setIcon(new ImageIcon(getClass().getResource("/screenscreen/CariGambar/background.PNG")));
            cards[i].setText("");
            cards[i].setFont(new java.awt.Font("Lucida Grande", 1, 0));
            cardPane.add(cards[i]);
        }
        getContentPane().add(buttonPane,
        BorderLayout.SOUTH);
        getContentPane().add(cardPane,
        BorderLayout.NORTH);
    }
    private void replayButtonActionPerformed(java.awt.event.ActionEvent evt) {
        shuffleCards();
    }
    private void shuffleCards() {
        int[] temparray = new int[3];
        for (int shuffle = 0; shuffle < 5; shuffle++){
            for (int x = 0; x < tilemap.length; x++) {
                temparray[0] = (int)Math.floor
                (Math.random()*24);
                temparray[1] = tilemap[temparray[0]];
                temparray[2] = tilemap[x];
                tilemap[x] = temparray[1];
                tilemap[temparray[0]] = temparray[2];
            }
        }
        for (int i = 0; i < cards.length; i++) {
            cards[i].setSelectedIcon(new ImageIcon(getClass().getResource("/screenscreen/CariGambar/image"+tilemap[i]+".png")));
            cards[i].setText("" + i);
            cards[i].setEnabled(true);
            cards[i].setVisible(true);
            cards[i].setSelected(false);
        }
        cardsLeft = 24;
        numTries = 0;
        numTriesLabel.setText("Total Percobaan: " + numTries+" x");
    }
    public void actionPerformed(java.awt.event.ActionEvent e){
        int iconMarker = 0;
        int nameMarker = 0;
        String card1String = "";
        String card2String = "";
        String nameText = "";
        cardsSelected++;
        if (cardsSelected == 3) {
            card1Object = null;
            cards[card1Num].setSelected(false);
            card2Object = null;
            cards[card2Num].setSelected(false);
            cardsSelected = 1;
        }
        if (card1Object == null) {
            card1Object = e.getSource();
            card1String = card1Object.toString();
            iconMarker = card1String.lastIndexOf(",selectedIcon=");
            nameMarker = card1String.lastIndexOf(",text=");
            card1Image = card1String.substring(iconMarker+14,nameMarker);
            nameText = card1String.substring(nameMarker+6,card1String.length()-1);
            card1Num = Integer.parseInt(nameText);
        }
        else if (card2Object == null) {
            card2Object = e.getSource();
            card2String = card2Object.toString();
            iconMarker = card2String.lastIndexOf(",selectedIcon=");
            nameMarker = card2String.lastIndexOf(",text=");
            card2Image = card2String.substring(iconMarker+14,nameMarker);
            nameText = card2String.substring(nameMarker+6,card2String.length()-1);
            card2Num = Integer.parseInt(nameText);
        }
        if (card1Object != null && card2Object != null) {
            numTries++;
            numTriesLabel.setText("Total Percobaan: " + numTries+" x");
            if (card1Image.equals(card2Image) &&
                (card1Num != card2Num)) {
                cards[card1Num].setVisible(false);
                cards[card2Num].setVisible(false);
                card1Object = null;
                card2Object = null;
                cardsLeft -= 2;
                cardsSelected = 0;
            }
        }
        if (cardsLeft == 0) {
            getRootPane().setDefaultButton(replayButton);
            numTriesLabel.setText("Selamat!  Anda Berhasil Dalam : " + numTries+"x Percobaan");
        }
    }
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(
            new Runnable() {
                public void run() {
                    new CariGambar().setVisible(true);
                }
            }
        );
    }
    private javax.swing.JPanel cardPane;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton replayButton;
    private javax.swing.JLabel numTriesLabel;
}