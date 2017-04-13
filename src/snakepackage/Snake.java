package snakepackage;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Daniel on 11.04.2017.
 */
public class Snake {

    SnakeGame game;
    LinkedList<Points> snake;
    int xKoordHead = 3;
    int yKoordHead = 3;
    int direction = 0;
    boolean top = false, bot = false, right = false, left= false;

    Snake(SnakeGame game) {
        this.game = game;
        snake = new LinkedList();
        snake.add(new Points(xKoordHead,yKoordHead));
        snakeMoves();
    }

    public void drawSnake(Graphics2D g) {
        g.setColor(Color.yellow);
        g.fillRect(xKoordHead*game.gui.area.einheitenWidth, yKoordHead*game.gui.area.einheitenHeight, game.gui.area.einheitenWidth, game.gui.area.einheitenHeight);
    }

    public void snakeMoves() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(right){
                        xKoordHead++;
                    }
                    else if(left){
                        xKoordHead--;
                    }
                    else if(top){
                        yKoordHead--;
                    }
                    else if(bot){
                        yKoordHead++;
                    }
                    //System.out.println(right+"  "+left+"  "+top+"  "+bot);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
