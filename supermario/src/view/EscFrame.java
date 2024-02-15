package view;

import manager.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EscFrame extends JFrame implements KeyListener,Runnable {

    JFrame jFrame = new JFrame();
    JButton StartButton = new JButton("continue");
    JButton Sound = new JButton("mute/unmute");
    JButton Exit = new JButton("Exit");
    Label label = new Label();
    private GameStatus gameStatus;

    public EscFrame(){
        super();
        this.setVisible(true);
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(StartButton);
        this.add(Sound);
        this.add(Exit);
        this.add(label);
        this.setBackground(Color.DARK_GRAY);

        StartButton.setBounds(75,50,150,30);
        Sound.setBounds(75,125,150,30);
        Exit.setBounds(75,200,150,30);

        StartButton.setFont(new Font("Comic.Sans",Font.BOLD,15));
        Sound.setFont(new Font("Comic.Sans",Font.BOLD,15));
        Exit.setFont(new Font("Comic.Sans",Font.BOLD,15));

        StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == StartButton) {
                    gameStatus = GameStatus.RUNNING;
                    jFrame.dispose();
                }


            }
        });

        Sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == Sound) {
                }


            }
        });

        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == Exit) {
                    gameStatus = GameStatus.START_SCREEN;

                }


            }
        });



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
