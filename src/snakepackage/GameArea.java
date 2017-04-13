package snakepackage;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Daniel on 13.04.2017.
 */
public class GameArea {

    SnakeGame game;
    final int spielfeldbreite = 70;
    final int spielfeldhöhe = 40;
    int einheitenWidth = 0;
    int einheitenHeight = 0;

    GameArea(SnakeGame game){
        this.game=game;
    }

    public void gameAreaUpdate(){

        einheitenWidth = game.screenWidth/spielfeldbreite;
        einheitenHeight = game.screenHight/spielfeldhöhe;
    }
}
