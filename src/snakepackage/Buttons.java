package snakepackage;

import java.awt.*;

/**
 * Created by Daniel on 10.04.2017.
 */
public class Buttons {
    int x,y,w,h;
    String title;
    float rForeground, gForeground, bForeground;
    Color  foreground,foreground1, surround;
    int titleLength =0 ;
    boolean point =false;
    boolean pressed;

    Buttons(int x, int y,int w, int h, Color surround, float rForeground, float gForeground, float bForeground, String name){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.surround = surround;
        this.rForeground = rForeground;
        this.gForeground = gForeground;
        this.bForeground = bForeground;
        this.foreground = new Color(rForeground,gForeground,bForeground);
        for(float i = 0; rForeground <1; i = i+0.1f){
            rForeground = rForeground+i;
        }
        for(float o = 0; gForeground <1; o = o+0.1f){
            gForeground = gForeground+o;
        }
        for(float p = 0; bForeground <1; p = p+0.1f){
            bForeground = bForeground+p;
        }
        if(rForeground >1){
            rForeground =  1;
        }
        if(gForeground >1){
            gForeground = 1;
        }
        if(bForeground >1){
            bForeground = 1;
        }
        foreground1 = new Color(rForeground,gForeground,bForeground);
        this.title = name;
        titleLength = title.length();
    }

    void render(Graphics2D g) {

        if(!point) {
            g.setColor(foreground);
        }
        else {
            g.setColor(foreground1);
        }
        g.fillRect(x,y,w,h);
        g.setColor(surround);
        g.drawRect(x-1,y-1,w+1,h+1);
        if(pressed){
            g.setFont(new Font("Calibri", 1, 22));
        }
        else {
            g.setFont(new Font("Calibri", 1, 20));
        }
        g.drawString(title, x+w/(titleLength+2)*2, y+(h/3*2));
        if(point){
            g.setColor(new Color(0.6f,0.6f,0.6f,0.8f));
            g.fillRect(x-1,y+h+1,w+4, 1);
            g.fillRect(x,       y+h+2,w+3, 2);
            g.fillRect(x+w+2, y-1, 1,h+2);

        }
    }

    public void buttonPressed() {
            x = x-5;
            y = y-5;
            w = w+10;
            h = h+10;
            pressed = true;
    }
    public void buttonReleased() {
            x = x+5;
            y = y+5;
            w = w-10;
            h = h-10;
    }

    public boolean buttonPointed(int mouseX, int mouseY) {


        if (mouseX >= x && mouseX <= x + w && mouseY >= y + 25 && mouseY <= y + 25 + h) {
            point = true;
            return point;
        }
        point = false;
        return point;
    }

    public void koordsUpdate(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

}
