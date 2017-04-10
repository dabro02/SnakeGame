package snakepackage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 06.04.2017.
 */
public class Window extends JPanel {
    SnakeGame game;
    Window(SnakeGame game){
        if(game.screen == 1) {
            this.game = game;
            game.frame.setBounds(200, 100, 1500, 800);

        }
    }

    @Override
    protected void paintComponent(Graphics g2){
        if(game.screen == 1) {
            Graphics2D g = (Graphics2D) g2;
            super.paintComponent(g);
        }
    }

    public void updateWindow(){
        while(true){
            if(game.screen == 1){
                repaint();
            }
        }
    }
}
