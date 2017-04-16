package snakepackage;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Daniel on 11.04.2017.
 */
public class Snake {

    SnakeGame game;
    ArrayList<Points> snake;
    int xKoordHead = 3;
    int yKoordHead = 3;
    int direction = 0;
    boolean top = false, bot = false, right = false, left= false;

    Snake(SnakeGame game) {
        this.game = game;
        snake = new ArrayList<>();
        snake.add(new Points(xKoordHead,yKoordHead));
        snake.add(new Points(xKoordHead,yKoordHead));
        snake.add(new Points(xKoordHead,yKoordHead));
        snakeMoves();
    }

    public void drawSnake(Graphics2D g) {
        g.setColor(Color.yellow);
        for(Points point : snake) {
            //System.out.println(point.getXKoordinate()+"  "+point.getYKoordinate() + "   "+ snake.size());
            g.fillRect(point.getXKoordinate() * game.gui.area.einheitenWidth, point.getYKoordinate() * game.gui.area.einheitenHeight, game.gui.area.einheitenWidth, game.gui.area.einheitenHeight);
        }
    }

    public void snakeMoves() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int previousX;
                int previousY;
                int prevX2;
                int prevY2;
                while(true){

                    previousX = xKoordHead;
                    previousY = yKoordHead;

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

                    for( Points point : snake){
                        if(previousX == xKoordHead && previousY == yKoordHead) {
                        }
                        else {
                            //System.out.println("new Koords:       " + point.xKoordinate + "  " + point.yKoordinate);
                            prevX2 = point.xKoordinate;
                            prevY2 = point.yKoordinate;
                            point.xKoordinate = previousX;
                            point.yKoordinate = previousY;
                            previousX = prevX2;
                            previousY = prevY2;
                            /*
                            if(point.xKoordinate == xKoordHead || point.yKoordinate == yKoordHead ){
                                System.out.println(true);
                                game.gui.losed = true;
                            }*/
                        }
                        if(point.getXKoordinate() <= 1 || point.getXKoordinate() >= 70 || point.getYKoordinate() <= 1 || point.getYKoordinate() >= 40){
                            game.gui.losed = true;
                        }

                    }
                    game.gui.repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    int xNewPoint;
    int yNewPoint;

    public void snakeEats() {

        for(Points point : snake){
            xNewPoint = point.getXKoordinate();
            yNewPoint = point.getYKoordinate();
        }
        snake.add(new Points(xNewPoint,yNewPoint));
        snake.add(new Points(xNewPoint,yNewPoint));
    }
}
