package com;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddPlayer extends JFrame implements ActionListener {

    JButton startGame, goBack;
    JTextField nameField;

    public AddPlayer(){
        setTitle("Welcome");
        setSize(600, 500);
        setLocation(500, 200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.lightGray);
        contentPane.setLayout(null);

        ImageIcon homeImage = new ImageIcon(AddPlayer.class.getResource("\\images\\AddPlayerImage.png"));
        JLabel imageHolder = new JLabel(homeImage);
        imageHolder.setBounds(25, 10, homeImage.getIconWidth() ,homeImage.getIconHeight());


        JLabel nameLabel = new JLabel("Player Name:");
        Font font1 = new Font("SansSerif", Font.BOLD, 25);
        nameLabel.setFont(font1);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBounds(40, 240, 250, 50);

        nameField = new JTextField();
        nameField.setFont(font1);
        nameField.setBounds(310, 240, 221, 61);

        JLabel mainMenuLabel = new JLabel("Back To Main Menu");
        mainMenuLabel.setFont(font1);
        mainMenuLabel.setForeground(Color.black);
        mainMenuLabel.setBounds(40, 310, 250, 50);

        goBack = new JButton(new ImageIcon(AddPlayer.class.getResource("\\images\\menu.png")));
        goBack.setBounds(310, 310, 221, 61);
        goBack.addActionListener(this);

        JLabel startGameLabel = new JLabel("Start Game");
        startGameLabel.setFont(font1);
        startGameLabel.setForeground(Color.black);
        startGameLabel.setBounds(40, 380, 250, 50);

        startGame = new JButton(new ImageIcon(AddPlayer.class.getResource("\\images\\begin.png")));
        startGame.setBounds(310, 380, 221, 61);
        startGame.addActionListener(this);

        contentPane.add(imageHolder);
        contentPane.add(nameLabel);
        contentPane.add(nameField);
        contentPane.add(mainMenuLabel);
        contentPane.add(goBack);
        contentPane.add(startGameLabel);
        contentPane.add(startGame);
    }
    public void saveName(String name) throws IOException{
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("players.txt", true));
            writer.write("\n" + name + "\n");
            writer.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "File not created");
            e.printStackTrace();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == goBack){
            super.dispose();   //Close addPlayer window
            TitleScreen game1 =  new TitleScreen();
            game1.setVisible(true);
        }else if(e.getSource() == startGame){
            super.dispose();
            GameBoard gameBoard1 = new GameBoard(nameField.getText());
            gameBoard1.setVisible(true);
        }
    }
}
