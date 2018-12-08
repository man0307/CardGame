package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class GameBoard extends JFrame implements ActionListener {
    JPanel gamePanel = new JPanel();
    JPanel start = new JPanel();
    JButton buttons[];
    JButton restart;
    static int numButtons;
    ImageIcon turnedOver;
    ImageIcon images[] = new ImageIcon[52];
    int numClicks = 0;
    int cardHolder1 = 0;
    int cardHolder2 = 0;
    JLabel scoreCounterLabel;
    int score = 0;
    String playerName;

    public GameBoard(String now_player) {
        this.playerName = now_player;
        setTitle("Concentration Card Game");
        setPreferredSize(new Dimension(1500, 900));
        setLocation(100, 25);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*
         * Building an array of images, Using each 1 twice so they can match.
         * Cheated and hard coded the values, I was originally using a for loop to generate this but I was
         */
        for (int i = 0; i < 26; i++) {
            String imagePath = "\\images\\veryhuo.com_pkp_" + (i + 1) + ".jpg";
            images[i * 2] = new ImageIcon(GameBoard.class.getResource(imagePath));
            images[i * 2 + 1] = new ImageIcon(GameBoard.class.getResource(imagePath));

        }
        /*
         *sets the start image to the back of the card.
         */
        turnedOver = new ImageIcon(GameBoard.class.getResource("\\images\\cardImage.png"));
        gamePanel.setPreferredSize(new Dimension(600, 600));
        gamePanel.setLayout(new GridLayout(4, images.length));
        numButtons = images.length;
        buttons = new JButton[numButtons];

        for (int i = 0; i < images.length; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            buttons[i].setIcon(turnedOver);
            gamePanel.add(buttons[i]);
        }

        JLabel nameLabel = new JLabel("Player Name:");
        Font font1 = new Font("SansSerif", Font.BOLD, 25);
        nameLabel.setFont(font1);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBounds(20, 0, 250, 50);


        JLabel nameFromMemory = new JLabel(playerName);
        font1 = new Font("SansSerif", Font.BOLD, 25);
        nameFromMemory.setFont(font1);
        nameFromMemory.setForeground(Color.RED);
        nameFromMemory.setBounds(270, 0, 221, 61);

        /**
         * JLabel used to display instructions to the player
         */
        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("Score:");
        font1 = new Font("SansSerif", Font.BOLD, 25);
        scoreLabel.setFont(font1);
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setBounds(20, 60, 221, 61);

        /**
         * JLabel used to store and display the score.
         */
        scoreCounterLabel = new JLabel("" + score);
        font1 = new Font("SansSerif", Font.BOLD, 25);
        scoreCounterLabel.setFont(font1);
        scoreCounterLabel.setForeground(Color.RED);
        scoreCounterLabel.setBounds(270, 60, 221, 61);

        /**
         * JButton used to restart the game, resets the game board.
         */
        restart = new JButton(new ImageIcon(GameBoard.class.getResource("\\images\\restart.png")));
        restart.addActionListener(this);
        restart.setBounds(1250, 120, 221, 61);

        start.setPreferredSize(new Dimension(100, 200));
        start.setLayout(null);
        start.add(nameLabel);
        start.add(nameFromMemory);
        start.add(scoreLabel);
        start.add(scoreCounterLabel);
        start.add(restart);


        /*
         * shuffleCards() called statically to rearrange the deck.
         */
        shuffleCards(images);

        BorderLayout cardLayout = new BorderLayout(10, 10);

        add(gamePanel, BorderLayout.NORTH);
        add(start, BorderLayout.SOUTH);
        pack();
        setVisible(true);

    }


    public static void shuffleCards(ImageIcon[] images) {
        Random randomNumber = new Random();
        for (int i = 0; i < numButtons; i++) {
            int rand = randomNumber.nextInt(numButtons);
            ImageIcon tempHolder = images[i];
            images[i] = images[rand];
            images[rand] = tempHolder;
        }
    }


    public static boolean checkMatch(JButton button1, JButton button2) {

        if(button1.getIcon()==null || button2.getIcon()==null){
            return false;
        }
        String s1 = button1.getIcon().toString();
        String s2 = button2.getIcon().toString();
        if (s1.equals(s2)) {
            return true;
        }
        return false;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < numButtons; i++) {
            if (e.getSource() == buttons[i]) {
                if(buttons[i].getIcon()==null){
                    JOptionPane.showMessageDialog(null, "Cannot select card that have been removed", "Attention", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                numClicks++;
                if (numClicks == 1) {
                    buttons[i].setIcon(images[i]);
                    cardHolder1 = i;
                    System.out.println(cardHolder1);
                } else if (numClicks == 2) {
                	if(i==cardHolder1){
                		numClicks--;
                		JOptionPane.showMessageDialog(null, "Please choose a different card to compare", "Attention", JOptionPane.ERROR_MESSAGE);
                	}else{
                		buttons[i].setIcon(images[i]);
                        cardHolder2 = i;
                        System.out.println(cardHolder2);
                	}
                } else if (numClicks == 3) {
                    if (checkMatch(buttons[cardHolder1], buttons[cardHolder2])) {
                        buttons[cardHolder1].setIcon(null);
                        buttons[cardHolder2].setIcon(null);
                        score++;
                        scoreCounterLabel.setText("" + score);
                        numClicks = 0;
                    } else {
                        buttons[cardHolder1].setIcon(turnedOver);
                        buttons[cardHolder2].setIcon(turnedOver);
                        numClicks = 0;
                    }
                }
                if (Integer.parseInt(scoreCounterLabel.getText()) == 4) {
                    JOptionPane.showMessageDialog(null, "Congratulations, You Won");
                }
            } else if (e.getSource() == restart) {
                super.dispose();
                GameBoard gameBoard1 = new GameBoard(playerName);
                gameBoard1.setVisible(true);
                break;
            }
        }
    }

}
