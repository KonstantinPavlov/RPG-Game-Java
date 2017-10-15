package com.sdr.rpg.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Keyboard Listener
 * Created by Konstantin on 24.09.2017.
 */
public class KeyboardInput implements KeyListener {

    private boolean[] keys = new boolean[120];
    public boolean up, down, left, right;

    public void update() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    private void printMessage(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }
}
