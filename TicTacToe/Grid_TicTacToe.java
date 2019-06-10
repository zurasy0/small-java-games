package com.zuras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid_TicTacToe {
    private JPanel panel_tictactoe;
    private JButton button_top_left;
    private JButton button_mid_left;
    private JButton button_bottom_left;
    private JButton button_middle;
    private JButton button_bottom_mid;
    private JButton button_top_mid;
    private JButton button_top_right;
    private JButton button_mid_right;
    private JButton button_bottom_right;
    private JToolBar toolbar_main_menu;
    private JButton button_toolbar_newgame;
    private JButton button_toolbar_quitgame;

    private static final int[][] field_tictactoe = new int[3][3];
    private static boolean spielzug_x = true;
    private static boolean game_is_over = false;

    private Grid_TicTacToe() {
        button_top_left.addActionListener(e -> {
            button_top_left.setText(spielzug(0, 0, button_top_left.getText()));
            //button_top_left.setBackground(Color.GREEN);
        });
        button_top_mid.addActionListener(e -> button_top_mid.setText(spielzug(0,1, button_top_mid.getText())));
        button_top_right.addActionListener(e -> button_top_right.setText(spielzug(0,2, button_top_right.getText())));
        button_mid_left.addActionListener(e -> button_mid_left.setText(spielzug(1,0, button_mid_left.getText())));
        button_middle.addActionListener(e -> button_middle.setText(spielzug(1,1, button_middle.getText())));
        button_mid_right.addActionListener(e -> button_mid_right.setText(spielzug(1,2, button_mid_right.getText())));
        button_bottom_left.addActionListener(e -> button_bottom_left.setText(spielzug(2,0, button_bottom_left.getText())));
        button_bottom_mid.addActionListener(e -> button_bottom_mid.setText(spielzug(2,1, button_bottom_mid.getText())));
        button_bottom_right.addActionListener(e -> button_bottom_right.setText(spielzug(2,2, button_bottom_right.getText())));
        button_toolbar_newgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spielzug_x = true;
                game_is_over = false;
                game_init_field();
                button_top_left.setText("");
                button_top_mid.setText("");
                button_top_right.setText("");
                button_mid_left.setText("");
                button_middle.setText("");
                button_mid_right.setText("");
                button_bottom_left.setText("");
                button_bottom_mid.setText("");
                button_bottom_right.setText("");
            }
        });
        button_toolbar_quitgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });
    }

    public static void main(String[] args) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame("TIC TAC TOE");
        frame.setContentPane(new Grid_TicTacToe().panel_tictactoe);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);

        JOptionPane.showMessageDialog(null,"Player X begins!");

        game_init_field();
        gameloop();
    }

    private static String spielzug(int index_field_y, int index_field_x, String button_text_old) {
        if (game_is_over) {
            JOptionPane.showMessageDialog(null,"You need to start a new game!");
            return button_text_old;
        }
        if (field_tictactoe[index_field_y][index_field_x] != 0) {
            return button_text_old;
        }
        if (spielzug_x) {
            field_tictactoe[index_field_y][index_field_x] = 1;
            spielzug_x = false;
            return "X";
        }
        else {
            field_tictactoe[index_field_y][index_field_x] = 2;
            spielzug_x = true;
            return "O";
        }
    }

    private static void gameloop() {
        // only checks if there is a winner, then prompts dialogue and exits the program
        String text_winner="";

        while (true) {
            try {
                Thread.sleep(50);
            }
            catch (InterruptedException ignored) {
                quit("Error in function gameloop! Error with Thread.sleep(50). Game will be closed.");
            }
            for (int i=1; i<3; i++) {
                if (i==1) text_winner="Player X wins!";
                if (i==2) text_winner="Player O wins!";

                if ((field_tictactoe[0][0] == i && field_tictactoe[0][1] == i && field_tictactoe[0][2] == i)
                        || (field_tictactoe[1][0] == i && field_tictactoe[1][1] == i && field_tictactoe[1][2] == i)
                        || (field_tictactoe[2][0] == i && field_tictactoe[2][1] == i && field_tictactoe[2][2] == i)
                        || (field_tictactoe[0][0] == i && field_tictactoe[1][0] == i && field_tictactoe[2][0] == i)
                        || (field_tictactoe[0][1] == i && field_tictactoe[1][1] == i && field_tictactoe[2][1] == i)
                        || (field_tictactoe[0][2] == i && field_tictactoe[1][2] == i && field_tictactoe[2][2] == i)
                        || (field_tictactoe[0][0] == i && field_tictactoe[1][1] == i && field_tictactoe[2][2] == i)
                        || (field_tictactoe[0][2] == i && field_tictactoe[1][1] == i && field_tictactoe[2][0] == i)) {
                    game_over(text_winner);
                }
            }
        }
    }

    private static void quit(String message) {
        JOptionPane.showMessageDialog(null, message);
        System.exit(0);
    }

    private static void quit() {
        System.exit(0);
    }

    private static void game_init_field() {
        for (int i=0; i<3; i++) {
            for (int k = 0; k < 3; k++) {
                field_tictactoe[i][k] = 0;
            }
        }
    }

    private static void game_over(String text) {
        JOptionPane.showMessageDialog(null, text);
        game_is_over = true;
        // prepare next game
        game_init_field();
    }
}