import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: mrproper
 * Date: 08.10.13
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
public class Player {


    public static final int MAX_V=100;
    public static final int MAX_TOP=50;
    public static final int MAX_BOTTOM=400;

    Image img_c = new ImageIcon("res/player.png").getImage();
    Image img_l = new ImageIcon("res/left.png").getImage();
    Image img_r = new ImageIcon("res/right.png").getImage();
    Image img=img_c;

    Rectangle getRect(){
        return new Rectangle(x,y,213,96);
    }


    int v=0;
    int dv=0;
    int s=0;
    int layer1=0;
    int layer2=1200;

    int x=30;
    int y=200;
    int dy=0;


    public void move(){
        s+=v;
        v+=dv;
        if(v<=0)v=0;
        if(v>=MAX_V)v=MAX_V;
        y-=dy;
        if(y<=MAX_TOP)y=MAX_TOP;
        if(y>=MAX_BOTTOM)y=MAX_BOTTOM;
        if(layer2-v<=0){
            layer1=0;
            layer2=1200;
        }
        else{
        layer1-=v;
        layer2-=v;
        }
    }

    public void keyPressed(KeyEvent e){
        int key=e.getKeyCode();
        if(key==KeyEvent.VK_RIGHT){
            dv=5;
        }
        if(key==KeyEvent.VK_LEFT){
            dv=-5;
        }
        if(key==KeyEvent.VK_UP){
            dy=10;
             img=img_l;
        }
        if(key==KeyEvent.VK_DOWN){
            dy=-10;
            img=img_r;
        }
    }
    public void keyReleased(KeyEvent e){
        int key=e.getKeyCode();
        if(key==KeyEvent.VK_RIGHT){
            dv=0;
        }
        if(key==KeyEvent.VK_LEFT){
            dv=0;
        }
        if(key==KeyEvent.VK_UP){
            dy=0;
            img=img_c;
        }
        if(key==KeyEvent.VK_DOWN){
            dy=0;
            img=img_c;
        }
    }

}
