package snakepackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
            game.frame.setBounds(200, 100, 1500, 900);
            game.getScreen();
            System.out.println(game.screenWidth+ "  "+ game.screenWidth/2);
            this.restart = new Buttons((game.screenWidth/2*1)-75, game.screenHight/5, 150,50, Color.BLACK, 0.5f,0.6f,0, "Restart");
            this.backToMenu = new Buttons((game.screenWidth/2*1)-75, game.screenHight/5*2, 150,50,Color.BLACK, 0.5f,0.6f,0, "Menü");
            game.frame.setResizable(true);
            game.frame.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if(game.screen == 1){
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
                    if(game.screen == 1) {
                        if(restart.pressed){
                            restart.buttonReleased();
                            restart.pressed = false;
                            repaint();
                        }
                        else if(backToMenu.pressed){
                            backToMenu.buttonReleased();
                            backToMenu.pressed = false;
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
        int oldscreenwidth = game.screenWidth;
        int oldscreenheight = game.screenHight;
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
