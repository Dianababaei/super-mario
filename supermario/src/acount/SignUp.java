package acount;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Objects;


public class SignUp extends JFrame
        implements ActionListener {


    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel name;
    private JLabel pass;
    private  JTextField tname;
    private  JTextField pname;
    private JTextField tmno;
    private JLabel gender;
    private JRadioButton male;
    private JRadioButton female;
    private ButtonGroup gengp;
    private JTextArea tadd;
    private JCheckBox term;
    private JButton sub;
    private JButton log;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;


    // constructor, to initialize the components
    // with default values.
    public SignUp()
    {
        setTitle("Registration Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);

        pass = new JLabel("password");
        pass.setFont(new Font("Arial", Font.PLAIN, 20));
        pass.setSize(100, 20);
        pass.setLocation(100, 300);
        c.add(pass);

        pname = new JTextField();
        pname.setFont(new Font("Arial", Font.PLAIN, 15));
        pname.setSize(190, 20);
        pname.setLocation(200, 300);
        c.add(pname);


        gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.PLAIN, 20));
        gender.setSize(100, 20);
        gender.setLocation(100, 200);
        c.add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.PLAIN, 15));
        male.setSelected(true);
        male.setSize(75, 20);
        male.setLocation(200, 200);
        c.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.PLAIN, 15));
        female.setSelected(false);
        female.setSize(80, 20);
        female.setLocation(275, 200);
        c.add(female);

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);


        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(100, 450);
        sub.addActionListener(this);
        c.add(sub);


        log = new JButton("log in");
        log.setFont(new Font("Arial", Font.PLAIN, 15));
        log.setSize(100, 20);
        log.setLocation(340, 450);
        log.addActionListener(this);
        c.add(log);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(220, 450);
        reset.addActionListener(this);
        c.add(reset);

        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(500, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 175);
        resadd.setLineWrap(true);
        c.add(resadd);

        setVisible(true);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == sub) {
            String data1;
            String data2 = "password : " + pname.getText() ;
            String data
                    = "Name : "
                    + tname.getText() + "/n";
            if (male.isSelected())
                data1 = "Gender : Male"
                        + "\n";
            else
                data1 = "Gender : Female"
                        + "\n";
            if(new File("./src/", tname.getText()+ ".json").exists()){
                JOptionPane.showMessageDialog(null, "this username is already taken");
                String def = "";
                tname.setText(def);
                pname.setText(def);
                res.setText(def);
                tout.setText(def);
                resadd.setText(def);
            }
            else {
            tout.setText(data + data1 + data2);
            tout.setEditable(false);
            res.setText("Registration Successfully..");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try {
                FileWriter file = new FileWriter("./src/"+ tname.getText()+".json", true);
                gson.toJson(new User(tname.getText(), pname.getText()), User.class,  file);
                file.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error occured");
            }}
        }

        else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            pname.setText(def);
            tadd.setText(def);
            res.setText(def);
            tout.setText(def);
            term.setSelected(false);
            resadd.setText(def);
        }
        else if (e.getSource() == log) {
            LogIn login = new LogIn();
        }
    }}








