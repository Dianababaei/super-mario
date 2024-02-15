package acount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//import static model.hero.supers.*;

public class Store extends JFrame implements ActionListener, KeyListener {
    JFrame store = new JFrame();
    JButton frist = new JButton("higher jump");
    JButton second = new JButton("faster run");
    JButton third = new JButton("coin collector");
    JButton forth = new JButton("faster shot");
    JButton exit = new JButton("previous");
    Label label = new Label();


    Store(){
        super("Store");
        this.setVisible(true);
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(frist);
        this.add(second);
        this.add(third);
        this.add(forth);
        this.add(exit);
        this.add(label);
        this.setBackground(Color.darkGray);

        label.setBackground(Color.DARK_GRAY);
        label.setVisible(true);

        frist.setBounds(250,200,300,50);
        second.setBounds(250,300,300,50);
        third.setBounds(250,400,300,50);
        forth.setBounds(250,500,300,50);
        exit.setBounds(350,700,100,40);

        frist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == frist) {
                    setHigherJump(true);
                    }
                }
        });

        second.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == second) {
                    setFasterRun(true);
                }
            }

            private void setFasterRun(boolean b) {
            }
        });

        third.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == frist) {
                    setCoinCollector(true);
                }
            }

            private void setCoinCollector(boolean b) {
                
            }
        });

        forth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == frist) {
                    setFasterShot(true);
                }
            }

            private void setFasterShot(boolean b) {
                
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                store.dispose();
            }
        });


    }

    private void setHigherJump(boolean b) {
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {

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



    }



