package view;
import acount.ChooseGame;
import acount.Store;
import model.hero.Mario;
import model.prize.Coin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static model.hero.Mario.PR;

public class CheckpointCheck extends JFrame implements ActionListener, KeyListener,Runnable {

        static JFrame checkpoint = new JFrame("checkPoint");
        JButton yes = new JButton();
        JButton no = new JButton();
        Label label = new Label();
        private int coins;
        public CheckpointCheck() {
                this.setVisible(true);
                this.setSize(500, 500);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.add(yes);
                this.add(no);
                this.add(label);

                yes.setBounds(150, 200, 150, 50);
                no.setBounds(150, 300, 150, 50);

                yes.setText("do you want to pay " + PR + " coin and save your time and location");
                yes.setText("Ignore and continue");

        }

        @Override
        public void actionPerformed(ActionEvent e) {
                 if (e.getSource() == yes){
                          coins = (int) (coins - PR);
                 }
                 if (e.getSource() == no){
                         checkpoint.dispose();

                 }



        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }


        @Override
        public void run() {

        }
}