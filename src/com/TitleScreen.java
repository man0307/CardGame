package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame implements ActionListener{
    JButton newGame;
    JButton Bexit;

    public TitleScreen(){
        setTitle("Concentration Card Game");
        setSize(560, 370);
        setLocation(500, 200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.lightGray);
        contentPane.setLayout(null);

        ImageIcon homeImage = new ImageIcon(TitleScreen.class.getResource("\\images\\MainMenuTitle.png"));
        JLabel imageHolder = new JLabel(homeImage);
        imageHolder.setBounds(10, 10, homeImage.getIconWidth() ,homeImage.getIconHeight());

        newGame = new JButton(new ImageIcon(TitleScreen.class.getResource("\\images\\newgame.png")));
        newGame.setBounds(20, 250, 221, 61);
        newGame.addActionListener(this);
        Bexit = new JButton(new ImageIcon(TitleScreen.class.getResource("\\images\\exit.png")));
        Bexit.setBounds(300, 250, 221, 61);
        Bexit.addActionListener(this);

        contentPane.add(imageHolder);
        contentPane.add(newGame);
        contentPane.add(Bexit);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == newGame){
            AddPlayer player1 = new AddPlayer();
            player1.setVisible(true);
            super.dispose();
        }else if(e.getSource() == Bexit){
           super.dispose();
        }
    }
}
