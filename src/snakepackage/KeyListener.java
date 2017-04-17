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
        /*System.out.println(e.getExtendedKeyCode());
        System.out.println(e.getKeyLocation());*/
                //System.out.println(e.getKeyChar());
                if(game.screen == 1){
                    if(e.getKeyChar() == '') {
                        if(game.gui.paused){
                            game.gui.paused = false;
                        }
                        else {
                            game.gui.paused = true;
                        }
                    }
                    else if(e.getKeyChar() == 'd'){
                        if(!game.gui.paused){
                            game.gui.snake.right = true;
                            game.gui.snake.left = false;
                            game.gui.snake.top = false;
                            game.gui.snake.bot = false;
                            game.gui.snake.direction = 0;
                        }
                    }
                    else if(e.getKeyChar() == 'a'){
                        if(!game.gui.paused){
                            game.gui.snake.left = true;
                            game.gui.snake.right = false;
                            game.gui.snake.top = false;
                            game.gui.snake.bot = false;
                            game.gui.snake.direction = 1;
                        }
                    }
                    else if(e.getKeyChar() == 'w'){
                        if(!game.gui.paused){
                            game.gui.snake.top = true;
                            game.gui.snake.right = false;
                            game.gui.snake.left = false;
                            game.gui.snake.bot = false;
                            game.gui.snake.direction = 2;
                        }
                    }
                    else if(e.getKeyChar() == 's'){
                        if(!game.gui.paused){
                            game.gui.snake.bot = true;
                            game.gui.snake.right =false;
                            game.gui.snake.left = false;
                            game.gui.snake.top = false;
                            game.gui.snake.direction = 3;
                        }
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
