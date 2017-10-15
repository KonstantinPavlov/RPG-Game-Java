package com.sdr.rpg;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        setUpFrame(game);
        game.start();
    }

    private static void setUpFrame(Game game) {
        game.getFrame().setResizable(false);
        game.getFrame().setTitle(Settings.GAME_TITLE);
        game.getFrame().add(game);
        game.getFrame().pack();
        game.getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.getFrame().setLocationRelativeTo(null);
        game.getFrame().setVisible(true);
    }
}
