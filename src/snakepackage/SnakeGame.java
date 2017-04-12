package snakepackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Daniel on 06.04.2017.
 */
public class SnakeGame {

    int fenster = 0;
    JFrame frame;
    Gui gui;
    Window window;
    public int screen = 0;
    public int screenWidth = 600;
    public int screenHight = 600;

    public static void main(String[] args){
        new SnakeGame().start();
    }

    void start() {
        frame = new JFrame("Snake - Game");
        frame.addKeyListener(new KeyListener(this));
        screen = 0;
        startGame();
        startMenu();
        gui.startMenu();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void startMenu(){
        gui = new Gui(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                gui.updateGui();
            }
        }).start();
        frame.add(gui);
    }
    public void startGame(){
        window = new Window(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                window.updateWindow();
            }
        }).start();
        frame.add(window);
    }

    public void getScreen(){
          screenWidth =  frame.getWidth();
          screenHight = frame.getHeight();
    }

}
