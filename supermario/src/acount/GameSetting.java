package acount;

import manager.ResumeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameSetting extends JFrame implements ActionListener, KeyListener,Runnable{

    //TODO
    private User user;
    static JFrame gameSetting = new JFrame();
    static JFrame chooseGame;
    JButton starting = new JButton();
    JButton continueing = new JButton();
    JButton checkCoins = new JButton();
    JButton store = new JButton();
    JButton checkProfile = new JButton();
    JButton firstGame = new JButton("first game");
    JButton secondGame = new JButton("second game");
    JButton thirdGame = new JButton("third game");
    Label label = new Label();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    GameSetting(User user) {
        super("GameSetting");
        this.user = user;
        this.setVisible(true);
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(starting);
        this.add(continueing);
        this.add(checkCoins);
        this.add(checkProfile);
        this.add(store);
        this.add(label);



        label.setBackground(Color.DARK_GRAY);
        label.setVisible(true);


        starting.setText("start new game");
        continueing.setText("previous game");
        checkCoins.setText("coins");
        store.setText("store");
        checkProfile.setText("check profile");

        starting.setBounds(300,100,150,50);
        continueing.setBounds(300,200,150,50);
        checkCoins.setBounds(300,300,150,50);
        checkProfile.setBounds(300,400,150,50);
        store.setBounds(300,500,150,50);

        store.setFont(new Font("Comic.Sans",Font.BOLD,14));
        starting.setFont(new Font("Comic.Sans",Font.BOLD,14));
        continueing.setFont(new Font("Comic.Sans",Font.BOLD,14));
        checkCoins.setFont(new Font("Comic.Sans",Font.BOLD,14));
        checkProfile.setFont(new Font("Comic.Sans",Font.BOLD,14));
        checkProfile.setFont(new Font("Comic.Sans",Font.BOLD,14));


        starting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == starting){
                    ChooseGame chooseGame = new ChooseGame(user);


                }
                gameSetting.dispose();

            }
        });

        store.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == store){
                    Store store = new Store();
                }
                gameSetting.dispose();
            }
        });

        continueing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == continueing){
                    GameState gameState = new ResumeGame(user).getPreviousGame();

                }
            }
        });


        checkProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == checkProfile){
                    JFrame profile = new JFrame();
                    profile.setVisible(true);
                    profile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    profile.setSize(400,400);
                    JButton b1;
                    JPanel newPanel;
                    JLabel userLabel, scoreLabel, characterLabel;
                    userLabel = new JLabel();
                    userLabel.setText("         username:  " + user.getName());
                    scoreLabel = new JLabel();
                    characterLabel = new JLabel();
                    scoreLabel.setText("        highest score:  " + user.getHighestScore());
                    characterLabel.setText("        characters");
                    b1 = new JButton("previous");

                    newPanel = new JPanel(new GridLayout(4, 1));
                    newPanel.add(userLabel);
                    newPanel.add(scoreLabel);
                    newPanel.add(characterLabel);
                    newPanel.add(b1);

                    //set border to panel
                    add(newPanel, BorderLayout.CENTER);
                    setTitle("Profile");         //set title to the login form
                    profile.add(newPanel);
                    b1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            profile.dispose();
                        }
                    });




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
