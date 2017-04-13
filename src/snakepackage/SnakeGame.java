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
        startMenu();
        gui.startMenu();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void startMenu(){
        gui = new Gui(this);
        frame.add(gui);
    }

    public void getScreen(){
          screenWidth =  frame.getWidth();
          screenHight = frame.getHeight();
    }

}
