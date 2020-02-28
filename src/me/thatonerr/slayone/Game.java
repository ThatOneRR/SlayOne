package me.thatonerr.slayone;

import me.thatonerr.slayone.listeners.KeyInput;
import me.thatonerr.slayone.listeners.MouseInput;
import me.thatonerr.slayone.managers.SPManager;
import me.thatonerr.slayone.managers.ScreenManager;
import me.thatonerr.slayone.values.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private String title;
    private int width, height, tps = 0, fps = 0;
    private boolean running;
    private Thread thread = new Thread(this);
    private JFrame frame;
    private Canvas canvas;
    private BufferStrategy buffer;
    private Graphics2D graphics;

    private MouseInput mouseInput = new MouseInput();
    private KeyInput keyInput = new KeyInput();

    private ScreenManager screenManager;
    private SPManager spManager;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setSize(width, height);
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();

/*        frame.addMouseListener(mouseInput);
        frame.addMouseMotionListener(mouseInput);
        frame.addMouseWheelListener(mouseInput);*/
        canvas.addMouseListener(mouseInput);
        canvas.addMouseMotionListener(mouseInput);
        canvas.addMouseWheelListener(mouseInput);
        frame.addKeyListener(keyInput);

        screenManager = new ScreenManager(this);
        spManager = new SPManager(this);
    }

    private void tick() {
        screenManager.tick();
        if (screenManager.getGameState() == GameState.SINGLEPLAYER) spManager.tick();

        tps++;
    }

    private void render() {
        buffer = canvas.getBufferStrategy();
        if (buffer == null) {
            canvas.createBufferStrategy(2);
            return;
        }
        graphics = (Graphics2D) buffer.getDrawGraphics();
        graphics.clearRect(0, 0, width, height);

        screenManager.render(graphics);
        if (screenManager.getGameState() == GameState.SINGLEPLAYER) spManager.render(graphics);

        graphics.dispose();
        buffer.show();
        fps++;
    }

    @Override
    public void run() {
        double tickRatio = 1e9 / 60.0, delta = 0;
        long previous = System.nanoTime(), current, timer = System.currentTimeMillis();

        while (running) {
            current = System.nanoTime();
            delta += (current - previous) / tickRatio;
            previous = current;
            while (delta >= 1) {
                tick();
                delta--;
            }
            render();
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(title + " | Tps: " + tps + " | Fps: " + fps);
                tps = 0;
                fps = 0;
            }
        }

        stop();
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        Thread.currentThread().interrupt();
        System.exit(0);
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }

    public SPManager getSpManager() {
        return spManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public MouseInput getMouseInput() {
        return mouseInput;
    }

    public KeyInput getKeyInput() {
        return keyInput;
    }

}
