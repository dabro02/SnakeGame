package snakepackage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Daniel on 06.04.2017.
 */
public class KeyListener implements java.awt.event.KeyListener{
    SnakeGame game;
    KeyListener(SnakeGame game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        /*System.out.println(e.getKeyCode());
        System.out.println(e.getKeyChar());*/
        if(game.screen == 1){
        if(e.getKeyCode() == 0){
            if(game.window.paused){
                game.window.paused = false;
            }
            else{
            game.window.paused = true;}
        }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
