package com.sdr.rpg;

import com.sdr.rpg.entity.mob.Player;
import com.sdr.rpg.graphics.Renderer;
import com.sdr.rpg.graphics.ScreenRenderer;
import com.sdr.rpg.graphics.SpriteSheet;
import com.sdr.rpg.input.KeyboardInput;
import com.sdr.rpg.level.JsonLevel;
import com.sdr.rpg.level.Level;
import com.sdr.rpg.level.RandomLevel;
import com.sdr.rpg.level.SpawnLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Main class of the game.
 * Starts the main game loop
 * <p>
 * Created by Konstantin on 24.09.2017.
 */
public class Game extends Canvas implements Runnable {

    private static final String THREAD_NAME = "Display-Thread";

    // 1 second
    private static final int ONE_SECOND = 1000;

    private static int customWidth = 550;
    private static int customHeight = customWidth / 16 * 9;
    private static int customScale = 2;

    private Thread thread;
    private JFrame frame;
    private boolean running = false;
    private Level level;

    private BufferedImage image = new BufferedImage(customWidth, customHeight, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    private Renderer screenRenderer;
    private Player player;

    private KeyboardInput keyboardInput;


    public Game() {
        // Define scale of window
        Dimension windowSize = new Dimension(customWidth * customScale, customHeight * customScale);
        setPreferredSize(windowSize);
        frame = new JFrame();
        screenRenderer = new ScreenRenderer(customWidth, customHeight);
        // add listener to canvas
        keyboardInput = new KeyboardInput();
        addKeyListener(keyboardInput);
//        level = new RandomLevel(64, 64);
//        level = new SpawnLevel("/textures/tiles/level_0.png");
        level = new JsonLevel("/maps/level_alpha.json", SpriteSheet.world);
        player = new Player(300,400,keyboardInput);
    }

    public static int getCustomWidth() {
        return customWidth;
    }

    public static int getCustomHeight() {
        return customHeight;
    }

    public static int getCustomScale() {
        return customScale;
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double nanoSeconds = 1000000000.0 / 60.0;
        double delta = 0;

        int frames = 0;
        int updates = 0;

        requestFocus();
        while (running) {
            long nowTime = System.nanoTime();
            delta += (nowTime - lastTime) / nanoSeconds;
            lastTime = nowTime;

            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            // reset frames and updates
            if (System.currentTimeMillis() - timer > ONE_SECOND) {
                timer += ONE_SECOND;
                printMessage("ups = " + updates + ", fps = " + frames);
                frame.setTitle(Settings.GAME_TITLE + " | " + updates + " ups, " + frames + " fps");
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    private void update() {
        keyboardInput.update();
        player.update();
    }

    private void render() {
        // get strategy from canvas
        BufferStrategy bufferStrategy = getBufferStrategy();
        // create strategy if it doesn't exit
        if (bufferStrategy == null) {
            createBufferStrategy(Settings.BUFFER_STRATEGY_NUMBER);
            return;
        }
        screenRenderer.clear();
        // Player location
        int xScroll = player.getX() - screenRenderer.getWidth()/2;
        int yScroll = player.getY() - screenRenderer.getHeight()/2;

        level.render(xScroll, yScroll, screenRenderer);

        player.render(screenRenderer);

        System.arraycopy(screenRenderer.getPixels(), 0, pixels, 0, pixels.length);

        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Verdana", Font.BOLD, 15));
        graphics.drawString("Debug. X: " + player.getX() + " , Y: " + player.getY() + ", direction : " + player.getDirection(), 10, 30);

        graphics.dispose();
        bufferStrategy.show();
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, THREAD_NAME);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println("[" + Thread.currentThread().getName() + "] catch an InterruptedException.");
            e.printStackTrace();
        }
    }

    private void printMessage(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }
}
