package snakepackage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 06.04.2017.
 */
public class Window extends JPanel {

    SnakeGame game;
    Buttons restart, backToMenu;
    boolean paused = false;

    Window(SnakeGame game){
        if(game.screen == 1) {
            this.game = game;
            this.restart = new Buttons((game.screenWidth/2)-75, game.screenHight/3, 150,50, Color.BLACK, 0.5f,0.6f,0, "Restart");
            this.backToMenu = new Buttons((game.screenWidth/2)-75, game.screenHight/6*4, 150,50,Color.BLACK, 0.5f,0.6f,0, "Menü");
            game.frame.setResizable(true);
            game.frame.setBounds(200, 100, 1500, 900);

        }
    }

    @Override
    protected void paintComponent(Graphics g2){
        if(game.screen == 1) {
            Graphics2D g = (Graphics2D) g2;
            super.paintComponent(g);
            game.window.setBackground(Color.DARK_GRAY);
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

    public void updateWindow(){
        while(true){
            if(game.screen == 1){

                game.getScreen();
                if(paused) {
                    int y = 0;
                    int x = 0;
                    try {
                        y = game.frame.getMousePosition().y;
                        x = game.frame.getMousePosition().x;
                    } catch (Exception e) {
                    }
                    restart.buttonPointed(x,y);
                    backToMenu.buttonPointed(x,y);
                }

                repaint();
            }
            repaint();
        }
    }
}
