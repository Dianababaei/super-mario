package acount;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class LogIn extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
    static HashMap<String, String> userManager = new HashMap<>();
    private JLabel res;


    LogIn() {
        this.setTitle("Login Form");
        this.setVisible(true);
        setBounds(300, 90, 900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        this.add(res);


    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {

                if(new File("./src/", userTextField.getText()+ ".json").exists()){
                    FileReader file = null;
                    try {
                        file = new FileReader("./src/" + userTextField.getText() + ".json");
                        Gson gson = new Gson();
                        User user = gson.fromJson(file, User.class);
                        //TODO you add this again
                        user.setPathJsonFile("./src/"+ passwordField.getText()+".json");
                        if(Objects.equals(user.getPassword(), passwordField.getText())) {
                            JOptionPane.showMessageDialog(null, "Password matched");
                            new GameSetting(user);
                        }else {
                            JOptionPane.showMessageDialog(null, "Password Not matched");
                        }
                        file.close();
                    } catch (FileNotFoundException ae) {
                        JOptionPane.showMessageDialog(null, "Error Occured While fetching");
                    } catch (IOException ae) {
                        throw new RuntimeException(ae);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "User not found!");
                }

            }

        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }

        }
    }

}

