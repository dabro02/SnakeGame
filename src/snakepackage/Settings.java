package snakepackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Created by Daniel on 13.04.2017.
 */
public class Settings extends JPanel {

    File Size;
    SnakeGame game;
    int actualSize = 0;
    Buttons size1,size2,size3,size4,size5, size6, back;


    Settings(SnakeGame game) {
        this.game = game;
        Size = new File("size.ultimativedatei");
        if (!Size.exists()) {
            try {
                Size.createNewFile();
            } catch (Exception e) {

            }
        }
        readFromTxt();
        size1 = new Buttons(100,150,150,50,Color.BLACK, 0.3f,0.5f,0.7f, "854 x 480", 20);
        size2 = new Buttons(100,250,150,50,Color.BLACK, 0.3f,0.5f,0.7f, "1280 x 720", 20);
        size3 = new Buttons(100,350,150,50,Color.BLACK, 0.3f,0.5f,0.7f, "1366 x 768", 20);
        size4 = new Buttons(350,150,150,50,Color.BLACK, 0.3f,0.5f,0.7f, "1600 x 900", 20);
        size5 = new Buttons(350,250,150,50,Color.BLACK, 0.3f,0.5f,0.7f, "1920 x 1080", 20);
        size6 = new Buttons(350,350,150,50,Color.BLACK, 0.3f,0.5f,0.7f, "3840 x 2160", 20);
        back = new Buttons( 225,450,150,50,Color.BLACK, 0.3f,0.5f,0.7f, "    Fertig", 20);

    }

    public void startSettings() {
        game.screen = 2;
        game.frame.setBounds(700,200, 600,600);
    }

    public void drawSettings(Graphics2D g){

        size1.render(g);
        size2.render(g);
        size3.render(g);
        size4.render(g);
        size5.render(g);
        size6.render(g);
        back.render(g);
        g.setColor(Color.lightGray);
        switch(actualSize) {
            case 0:
                g.fillRoundRect(60,167,15,15, 25,25);
                break;
            case 1:
                g.fillRoundRect(60,267,15,15, 25,25);
                break;
            case 2:
                g.fillRoundRect(60,367,15,15, 25,25);
                break;
            case 3:
                g.fillRoundRect(310,167,15,15, 25,25);
                break;
            case 4:
                g.fillRoundRect(310,267,15,15, 25,25);
                break;
            case 5:
                g.fillRoundRect(310,367,15,15, 25,25);
                break;

            default:
                break;
        }
    }

    public void settingsUpdate(int x, int y) {
        size1.buttonPointed(x,y);
        size2.buttonPointed(x,y);
        size3.buttonPointed(x,y);
        size4.buttonPointed(x,y);
        size5.buttonPointed(x,y);
        size6.buttonPointed(x,y);
        back.buttonPointed(x,y);
    }

    public void mousePressed(MouseEvent e) {
        if(size1.buttonPointed(e.getX(),e.getY())){
            size1.buttonPressed();
            repaint();
        }
        if(size2.buttonPointed(e.getX(),e.getY())){
            size2.buttonPressed();
            repaint();
        }
        if(size3.buttonPointed(e.getX(),e.getY())){
            size3.buttonPressed();
            repaint();
        }
        if(size4.buttonPointed(e.getX(),e.getY())){
            size4.buttonPressed();
            repaint();
        }
        if(size5.buttonPointed(e.getX(),e.getY())){
            size5.buttonPressed();
            repaint();
        }
        if(size6.buttonPointed(e.getX(),e.getY())){
            size6.buttonPressed();
            repaint();
        }
        if(back.buttonPointed(e.getX(),e.getY())) {
            back.buttonPressed();
            repaint();
        }
    }

    public void mouseReleased() {
        if(size1.pressed){
            size1.buttonReleased();
            size1.pressed = false;
            actualSize = 0;
            repaint();
        }
        if(size2.pressed){
            size2.buttonReleased();
            size2.pressed = false;
            actualSize = 1;
            repaint();
        }
        if(size3.pressed){
            size3.buttonReleased();
            size3.pressed = false;
            actualSize = 2;
            repaint();
        }
        if(size4.pressed){
            size4.buttonReleased();
            size4.pressed = false;
            actualSize = 3;
            repaint();
        }
        if(size5.pressed){
            size5.buttonReleased();
            size5.pressed = false;
            actualSize = 4;
            repaint();
        }
        if(size6.pressed){
            size6.buttonReleased();
            size6.pressed = false;
            actualSize = 5;
            repaint();
        }
        if(back.pressed){
            back.buttonReleased();
            back.pressed = false;
            saveAsTxt();
            if(game.fenster == 0){
                game.gui.startMenu();
            }
            if(game.fenster == 1){
                game.gui.startWindow();
            }
            repaint();
        }
    }

    public void saveAsTxt() {
        try {
            PrintWriter fw = new PrintWriter(Size);
            fw.write(actualSize);
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void readFromTxt() {
        try {
            FileReader fr = new FileReader("size.ultimativedatei");
            actualSize = fr.read();
        }
        catch(Exception e){

        }
    }
}
