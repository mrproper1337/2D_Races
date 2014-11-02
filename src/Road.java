import javax.swing.*;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;


public class Road extends JPanel implements ActionListener,Runnable{
    Timer mainTimer=new Timer(20,this);
    Image img = new ImageIcon("res/bg_road.jpg").getImage();
    Player p=new Player();

    Thread enemiesFactory=new Thread(this);
    List<Enemy> enemies = new ArrayList<Enemy>();

    public Road(){
        mainTimer.start();
        enemiesFactory.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    @Override
    public void run() {
        while(true){
            Random rand=new Random();
            try {
                Thread.sleep(rand.nextInt(20000));
                enemies.add(new Enemy(1200,
                        rand.nextInt(600),
                        rand.nextInt(60),this));


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private  class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            p.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            p.keyReleased(e);
        }
    }

    public void paint(Graphics G){
        G = (Graphics2D)G;
        G.drawImage(img, p.layer1, 0, null);
        G.drawImage(img, p.layer2, 0, null);
        G.drawImage(p.img,p.x,p.y,null);

        Iterator<Enemy> i=enemies.iterator();
        while (i.hasNext()){
            Enemy e=i.next();
            if(e.x>=2400||e.x<=-2400){
                i.remove();
            }else{
                e.move();
                G.drawImage(e.img,e.x,e.y,null);
        }         }
    }

    public void actionPerformed(ActionEvent e){
        p.move();
        repaint();
        testCollisionWithEnemies();
    }
    private void testCollisionWithEnemies(){
        Iterator<Enemy> i=enemies.iterator();
        Iterator<Enemy> j=enemies.iterator();
        while(i.hasNext()){
            Enemy e=i.next();
                if(p.getRect().intersects(e.getRect())){
                JOptionPane.showMessageDialog(null,"YOLO#1");
                System.exit(1);
            }
        }
    }

}
