

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class fly extends JPanel implements ActionListener, KeyListener {

    int birdY = 250, birdVel = 0, score = 0;
    boolean gameOver = false;
    Timer timer;
    ArrayList<Rectangle> pipes = new ArrayList<>();
    Random rand = new Random();

    public fly() {
        setPreferredSize(new Dimension(500,500));
        setBackground(Color.cyan);
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(20, this);
        timer.start();

        for(int i=0;i<3;i++) addPipe(600+i*200);
    }

    void addPipe(int x) {
        int h = 50 + rand.nextInt(200);
        pipes.add(new Rectangle(x,0,50,h));
        pipes.add(new Rectangle(x,h+150,50,500-h-150));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red); g.fillOval(100,birdY,30,30);
        g.setColor(Color.green);
        for(Rectangle p:pipes) g.fillRect(p.x,p.y,p.width,p.height);
        g.setColor(Color.black); g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString("Score: "+score,10,30);
        if(gameOver){ g.setFont(new Font("Arial",Font.BOLD,40));
            g.drawString("Game Over - Press ENTER",50,250); timer.stop(); }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameOver){
            birdVel+=1; birdY+=birdVel;
            ArrayList<Rectangle> remove = new ArrayList<>();
            for(Rectangle p:pipes){ p.x-=5; if(p.x+50<0) remove.add(p); }
            pipes.removeAll(remove);
            if(pipes.size()<6) addPipe(600);

            Rectangle bird = new Rectangle(100,birdY,30,30);
            for(Rectangle p:pipes) if(p.intersects(bird)) gameOver=true;
            if(birdY<0||birdY>470) gameOver=true;

            for(Rectangle p:pipes) if(p.x+50==100) score++;
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE) birdVel=-10;
        if(e.getKeyCode()==KeyEvent.VK_ENTER && gameOver){
            birdY=250; birdVel=0; score=0; gameOver=false; pipes.clear();
            for(int i=0;i<3;i++) addPipe(600+i*200); timer.start();
        }
    }
    @Override public void keyReleased(KeyEvent e){}
    @Override public void keyTyped(KeyEvent e){}

    public static void main(String[] args){
        JFrame f = new JFrame("Flying Bird Game");
        f.add(new FlyingBirdGame()); f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); f.setVisible(true);
    }
}