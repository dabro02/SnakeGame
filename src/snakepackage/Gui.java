package snakepackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Daniel on 06.04.2017.
 */
public class Gui extends JPanel{

    Buttons startGame, exit, info, settings;
    SnakeGame game;

    Gui(SnakeGame game){
        if(game.screen == 0) {
            this.game = game;
            this.startGame = new Buttons(225, 100, 150, 50, Color.BLACK, 0, 0, 1, " Start");
            this.settings = new Buttons(225, 185, 150, 50, Color.BLACK, 0, 0, 1, " Einstellungen");
            this.info = new Buttons(225, 270, 150, 50, Color.BLACK, 0, 0, 1, " Info");
            this.exit = new Buttons(225, 355, 150, 50, Color.BLACK, 0, 0, 1, " Beenden");
            game.frame.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (game.screen == 0) {
                        if (startGame.buttonPointed(e.getX(), e.getY())) {
                            startGame.buttonPressed();
                            repaint();
                        }
                        else if (settings.buttonPointed(e.getX(), e.getY())) {
                            settings.buttonPressed();
                            repaint();
                        }
                        else if (info.buttonPointed(e.getX(), e.getY())) {
                            info.buttonPressed();
                            repaint();
                        }
                        else if (exit.buttonPointed(e.getX(), e.getY())) {
                            exit.buttonPressed();
                            repaint();
                        }
                    }

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (game.screen == 0) {
                        if (startGame.pressed) {
                            startGame.buttonReleased();
                            startGame.pressed = false;
                            game.window.startWindow();
                            repaint();
                        }
                        else if (settings.pressed) {
                            settings.buttonReleased();
                            settings.pressed = false;
                            repaint();
                        }
                        else if (info.pressed) {
                            info.buttonReleased();
                            info.pressed = false;
                            repaint();
                        }
                        else if (exit.pressed) {
                            exit.buttonReleased();
                            exit.pressed = false;
                            System.exit(0);
                            repaint();
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        updateGui();

                    }
                }).start();
        }
    }

    public void startMenu() {

        game.screen = 0;
        game.frame.setBounds(700, 200, 600, 600);
        game.frame.setResizable(false);
    }

    @Override
    protected void paintComponent(Graphics g2){
        if(game.screen == 0) {
            Graphics2D g = (Graphics2D) g2;
            super.paintComponent(g);
            game.gui.setBackground(Color.DARK_GRAY);
            g.setColor(Color.BLACK);
            startGame.render(g);
            settings.render(g);
            info.render(g);
            exit.render(g);
        }
    }


    public void updateGui(){
        while(true){
            if(game.screen == 0){
                int y = 0;
                int x = 0;
                try {
                    y = game.frame.getMousePosition().y;
                    x = game.frame.getMousePosition().x;
                }
                catch(Exception e){}
                startGame.buttonPointed(x,y);
                settings.buttonPointed(x,y);
                info.buttonPointed(x,y);
                exit.buttonPointed(x,y);
            }
            repaint();
        }
    }

}
