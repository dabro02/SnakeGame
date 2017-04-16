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
    Buttons restart,settingsInGame, backToMenu;
    boolean paused = false;
    boolean losed = false;
    GameArea area;
    //Einstellungen
    Settings settingsMenu;
    //Snake
    Snake snake;


    Gui(SnakeGame game){
            this.game = game;
            //Gui
            this.startGame = new Buttons(225, 100, 150, 50, Color.BLACK, 0, 0, 1, "Start  ", 20);
            this.settings = new Buttons(225, 185, 150, 50, Color.BLACK, 0, 0, 1, "Einstellungen", 20);
            this.info = new Buttons(225, 270, 150, 50, Color.BLACK, 0, 0, 1, "  Info  ", 20);
            this.exit = new Buttons(225, 355, 150, 50, Color.BLACK, 0, 0, 1, "Beenden  ", 20);
            //Game
            this.restart = new Buttons((game.screenWidth/2)-75, game.screenHight/9*2, 150,50, Color.BLACK, 0.5f,0.6f,0, "Restart    ", 20);
            this.backToMenu = new Buttons((game.screenWidth/2)-75, game.screenHight/9*3, 150,50,Color.BLACK, 0.5f,0.6f,0, "Menü   ", 20);
            this.settingsInGame = new Buttons((game.screenWidth/2)-75, game.screenHight/9*4, 150,50,Color.BLACK, 0.5f,0.6f,0,"Einstellungen ", 20);
            area = new GameArea(game);
            //Einstellungen
            settingsMenu = new Settings(game);
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
                        else if(settingsInGame.buttonPointed(e.getX(),e.getY())){
                            settingsInGame.buttonPressed();
                            repaint();
                        }
                        else if(backToMenu.buttonPointed(e.getX(),e.getY())){
                            backToMenu.buttonPressed();
                            repaint();
                        }
                    }
                    //Einstellungen
                    else if(game.screen == 2){
                        settingsMenu.mousePressed(e);
                    }

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    //Gui
                    if (game.screen == 0) {
                        if (startGame.pressed) {
                            startGame.buttonReleased();
                            startGame.pressed = false;
                            startWindow();
                            repaint();
                        }
                        else if (settings.pressed) {
                            settings.buttonReleased();
                            settings.pressed = false;
                            settingsMenu.startSettings();
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
                            settingsMenu.saveAsTxt();
                            System.exit(0);
                            repaint();
                        }
                    }
                    //Game
                    else if(game.screen == 1) {
                        if(restart.pressed){
                            restart.buttonReleased();
                            restart.pressed = false;
                            losed = false;
                            startWindow();
                            repaint();
                        }
                        else if(settingsInGame.pressed){
                            settingsInGame.buttonReleased();
                            settingsInGame.pressed = false;
                            settingsMenu.startSettings();
                            repaint();
                        }
                        else if(backToMenu.pressed){
                            backToMenu.buttonReleased();
                            backToMenu.pressed = false;
                            startMenu();
                            repaint();
                        }
                    }
                    //Einstellungen
                    else if(game.screen == 2) {
                        settingsMenu.mouseReleased();
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
        game.fenster = 0;
        game.screen = 0;
        game.frame.setBounds(700, 200, 600, 600);

    }
    public void startWindow() {
        paused = false;
        losed = false;
        game.fenster = 1;
        game.screen = 1;
        switch(settingsMenu.actualSize) {
            case 0:
                game.frame.setBounds(0, 0, 854,480);
                break;
            case 1:
                game.frame.setBounds(0, 0, 1280,720);
                break;
            case 2:
                game.frame.setBounds( 0, 0, 1366,768);
                break;
            case 3:
                game.frame.setBounds(0, 0, 1600, 900);
                break;
            case 4:
                game.frame.setBounds(0,0,1920,1080);
                break;
            case 5:
                game.frame.setBounds(0,0,3840 , 2160);
                break;

                default:
                    game.frame.setBounds(0, 0, 854,480);
                    break;
        }
        snake = new Snake(game);


    }

    @Override
    protected void paintComponent(Graphics g2){
        Graphics2D g = (Graphics2D) g2;
        super.paintComponent(g);
        game.gui.setBackground(Color.DARK_GRAY);
        //Gui
        if(game.screen == 0) {
            g.setColor(Color.BLACK);
            startGame.render(g);
            settings.render(g);
            info.render(g);
            exit.render(g);
        }
        //Game
        else if(game.screen == 1) {
            g.setColor(new Color(0.44313726f, 0f, 0f));
            g.fillRect(0, 0, game.screenWidth, area.einheitenHeight);
            g.fillRect(0, 0, area.einheitenWidth, game.screenHight);
            g.fillRect(0, game.screenHight - 47+20-area.einheitenHeight, game.screenWidth, area.einheitenHeight);
            g.fillRect(game.screenWidth - 23+20-area.einheitenWidth, 0, area.einheitenWidth, game.screenHight);
            snake.drawSnake(g);

            if(paused){
                g.setColor(new Color(0.5f,0.5f,0.5f,0.5f));
                g.fillRect(0,0,game.screenWidth,game.screenHight);
                restart.render(g);
                settingsInGame.render(g);
                backToMenu.render(g);
            }
            if(losed){
                g.setColor(new Color(0.5f,0.5f,0.5f,0.5f));
                g.fillRect(0,0,game.screenWidth,game.screenHight);
                backToMenu.render(g);
                restart.render(g);
            }
            //System.out.println(area.einheitenWidth+ "  "+area.einheitenHeight);
        }
        //Einstellungen
        else if(game.screen == 2){
            settingsMenu.drawSettings(g);
        }
    }


    public void updateGui(){

        while(true){
            int y = 0;
            int x = 0;
            try {
                y = game.frame.getMousePosition().y;
                x = game.frame.getMousePosition().x;
            }
            catch(Exception e){}
            //Gui
            if(game.screen == 0){

                startGame.buttonPointed(x,y);
                settings.buttonPointed(x,y);
                info.buttonPointed(x,y);
                exit.buttonPointed(x,y);
            }
            //game
            else if(game.screen == 1){
                game.getScreen();
                if(paused) {
                    snake.right = false;
                    snake.left = false;
                    snake.top = false;
                    snake.bot = false;
                    restart.koordsUpdate((game.screenWidth/2*1)-113, game.screenHight/9*2, 225,75, 25);
                    settingsInGame.koordsUpdate((game.screenWidth/2*1)-113, game.screenHight/9*3, 225,75, 25);
                    backToMenu.koordsUpdate((game.screenWidth/2*1)-113, game.screenHight/9*4, 225,75, 25);
                    restart.buttonPointed(x,y);
                    settingsInGame.buttonPointed(x,y);
                    backToMenu.buttonPointed(x,y);
                }
                else if(losed) {
                    snake.right = false;
                    snake.left = false;
                    snake.top = false;
                    snake.bot = false;
                    backToMenu.koordsUpdate((game.screenWidth/2*1)-150, game.screenHight/2-300, 300,100, 30);
                    restart.koordsUpdate((game.screenWidth/2*1)-150, game.screenHight/2, 300,100, 30);
                    backToMenu.buttonPointed(x,y);
                    restart.buttonPointed(x,y);
                }
                else {
                    restart.koordsUpdate((game.screenWidth/2*1)-113, game.screenHight/9*2, 225,75, 20);
                    settingsInGame.koordsUpdate((game.screenWidth/2*1)-113, game.screenHight/9*3, 225,75, 20);
                    backToMenu.koordsUpdate((game.screenWidth/2*1)-113, game.screenHight/9*4, 225,75, 20);
                }
                //Snake:
                area.gameAreaUpdate();

            }
            else if(game.screen == 2) {
                settingsMenu.settingsUpdate(x,y);
            }
            repaint();
        }
    }

}
