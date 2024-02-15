package acount;

import com.google.gson.Gson;
import manager.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;

public class ChooseGame extends JFrame implements ActionListener, KeyListener,Runnable {
    static JFrame chooseGame = new JFrame();
    JButton fristGame = new JButton("first game");
    JButton secondGame = new JButton("second game");
    JButton thirdGame = new JButton("third game");
    JButton NewGame = new JButton("new game");

    Label label = new Label();
    private User user;

    ChooseGame(User user){
        super("GameSetting");
        this.user = user;
        this.setVisible(true);
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(fristGame);
        this.add(secondGame);
        this.add(thirdGame);
        this.add(NewGame);
        this.add(label);
        this.setBackground(Color.DARK_GRAY);

        fristGame.setBounds(250,300,300,50);
        secondGame.setBounds(250,400,300,50);
        thirdGame.setBounds(250,500,300,50);
        NewGame.setBounds(250,150,300,50);

        NewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == NewGame){
                    GameEngine gameEngine = new GameEngine(user);
                }
            }
        });



        fristGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == fristGame){
                    ChooseGame.this.user.getRunningGames().add(new GameState( 4, 5, 6, 7));
                    FileWriter file = null;
                    try {
                        file = new FileWriter("./src/data/"+ ChooseGame.this.user.getName()+".json");
                        new Gson().toJson(ChooseGame.this.user, User.class,  file);
                        file.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }



                }


            }
        });
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

    @Override
    public void run() {

    }
}
