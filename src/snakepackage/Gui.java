package snakepackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Daniel on 06.04.2017.
 */
public class Gui extends JPanel{

    SnakeGame game;
    //Gui
    Buttons startGame, exit, info, settings;
    //Game
    Buttons restart, backToMenu;
    boolean paused = false;
    GameArea area;
    //Snake


    Gui(SnakeGame game){
            this.game = game;
            //Gui
            this.startGame = new Buttons(225, 100, 150, 50, Color.BLACK, 0, 0, 1, " Start");
            this.settings = new Buttons(225, 185, 150, 50, Color.BLACK, 0, 0, 1, " Einstellungen");
            this.info = new Buttons(225, 270, 150, 50, Color.BLACK, 0, 0, 1, " Info");
            this.exit = new Buttons(225, 355, 150, 50, Color.BLACK, 0, 0, 1, " Beenden");
            //Game
            this.restart = new Buttons((game.screenWidth/2*1)-75, game.screenHight/5, 150,50, Color.BLACK, 0.5f,0.6f,0, "Restart");
            this.backToMenu = new Buttons((game.screenWidth/2*1)-75, game.screenHight/5*2, 150,50,Color.BLACK, 0.5f,0.6f,0, "Menü");
            area = new GameArea(game);
            game.frame.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    //Gui
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
                    //game
                    else if(game.screen == 1){
                        if(restart.buttonPointed(e.getX(),e.getY())){
                            restart.buttonPressed();
                            repaint();
                        }
                        else if(backToMenu.buttonPointed(e.getX(),e.getY())){
                            backToMenu.buttonPressed();
                            repaint();
                        }
                    }

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    //Gui
                    if (game.screen == 0) {
                        if (startGame.pressed) {
                            startGame.buttonReleased();
                            startGame.pressed = false;
                            game.gui.startWindow();
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
                    //Game
                    else if(game.screen == 1) {
                        if(restart.pressed){
                            restart.buttonReleased();
                            restart.pressed = false;
                            game.gui.startWindow();
                            repaint();
                        }
                        else if(backToMenu.pressed){
                            backToMenu.buttonReleased();
                            backToMenu.pressed = false;
                            game.gui.startMenu();
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

    public void startMenu() {

        game.screen = 0;
        game.frame.setBounds(700, 200, 600, 600);
        game.frame.setResizable(false);

    }
    public void startWindow() {
        paused = false;
        game.screen = 1;
        game.frame.setBounds(200, 100, 1500, 900);
        game.frame.setResizable(true);
    }

    @Override
    protected void paintComponent(Graphics g2){
        //Gui
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
        //Game
        else if(game.screen == 1) {
            Graphics2D g = (Graphics2D) g2;
            super.paintComponent(g);
            game.gui.setBackground(Color.DARK_GRAY);
            g.setColor(new Color(0.44313726f, 0f, 0f));
            g.fillRect(0, 0, game.screenWidth, 20);
            g.fillRect(0, 0, 20, game.screenHight);
            g.fillRect(0, game.screenHight - 57, game.screenWidth, 20);
            g.fillRect(game.screenWidth - 35, 0, 20, game.screenHight);

            if(paused){
                g.setColor(new Color(0.5f,0.5f,0.5f,0.5f));
                g.fillRect(0,0,game.screenWidth,game.screenHight);
                restart.render(g);
                backToMenu.render(g);
            }
        }
    }


    public void updateGui(){
        //for Game
        int oldscreenwidth =  game.screenWidth;
        int oldscreenheight = game.screenHight;

        while(true){
            //Gui
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
            //game
            else if(game.screen == 1){
                game.getScreen();
                if(paused) {
                    int y = 0;
                    int x = 0;
                    try {
                        y = game.frame.getMousePosition().y;
                        x = game.frame.getMousePosition().x;
                    } catch (Exception e) {
                    }
                    if(oldscreenheight != game.screenHight || oldscreenwidth != game.screenWidth){

                        restart.koordsUpdate((game.screenWidth/2*1)-75, game.screenHight/5, 150,50);
                        backToMenu.koordsUpdate((game.screenWidth/2*1)-75, game.screenHight/5*2, 150,50);
                        oldscreenwidth = game.screenWidth;
                        oldscreenheight = game.screenHight;
                    }
                    restart.buttonPointed(x,y);
                    backToMenu.buttonPointed(x,y);
                }
            }
            repaint();
        }
    }

}
