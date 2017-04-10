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
                        if (settings.buttonPointed(e.getX(), e.getY())) {
                            settings.buttonPressed();
                            repaint();
                        }
                        if (info.buttonPointed(e.getX(), e.getY())) {
                            info.buttonPressed();
                            repaint();
                        }
                        if (exit.buttonPointed(e.getX(), e.getY())) {
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
                            repaint();
                            game.startGame();
                        }
                        if (settings.pressed) {
                            settings.buttonReleased();
                            settings.pressed = false;
                            repaint();
                        }
                        if (info.pressed) {
                            info.buttonReleased();
                            info.pressed = false;
                            repaint();
                        }
                        if (exit.pressed) {
                            exit.buttonReleased();
                            exit.pressed = false;
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
                game.frame.setBounds(700, 200, 600, 600);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        updateGui();

                    }
                }).start();
        }
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
                repaint();
            }
            repaint();
        }
    }

}
