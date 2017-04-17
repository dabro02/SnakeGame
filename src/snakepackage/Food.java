package snakepackage;

import java.awt.*;
import java.util.Random;

/**
 * Created by Daniel on 17.04.2017.
 */
public class Food {

    Random random = new Random();
    SnakeGame game;
    Points food;
    int xKoordinateFood = 0;
    int yKoordinateFood = 0;

    Food(SnakeGame game) {
        this.game = game;
        xKoordinateFood = random.nextInt(68);
        yKoordinateFood =  random.nextInt(48);
    }

    public void drawFood() {
        Graphics2D g = (Graphics2D) game.gui.getGraphics();
        g.setColor(Color.BLACK);
        g.drawRect(xKoordinateFood*game.gui.area.einheitenWidth, yKoordinateFood*game.gui.area.einheitenHeight, game.gui.area.einheitenWidth, game.gui.area.einheitenHeight);
        g.setColor(Color.red);
        g.fillRect(xKoordinateFood*game.gui.area.einheitenWidth, yKoordinateFood*game.gui.area.einheitenHeight, game.gui.area.einheitenWidth, game.gui.area.einheitenHeight);
    }

    public void eaten() {
        if(game.gui.snake.xKoordHead == food.getXKoordinate() && game.gui.snake.yKoordHead == food.getYKoordinate()){
            game.gui.snake.snakeEats();
            xKoordinateFood= random.nextInt(68);
            yKoordinateFood = random.nextInt(48);
        }
    }

}
