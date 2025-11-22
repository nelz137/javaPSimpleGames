import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BallGameInput extends JPanel implements KeyListener {

    int x, y;           // Ball position
    int diameter = 40;  // Ball size

    public BallGameInput(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.black);

        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        g.fillOval(x, y, diameter, diameter);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)  x -= 10;
        if (key == KeyEvent.VK_RIGHT) x += 10;
        if (key == KeyEvent.VK_UP)    y -= 10;
        if (key == KeyEvent.VK_DOWN)  y += 10;

        repaint();
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {

        // DEFAULT VALUES
        int startX = 100;
        int startY = 100;

        // INPUT FOR X POSITION
        String sx = JOptionPane.showInputDialog("Enter starting X position (default 100):");

        if (sx != null && !sx.trim().equals("")) {
            try {
                startX = Integer.parseInt(sx);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Using default X = 100.");
            }
        }

        // INPUT FOR Y POSITION
        String sy = JOptionPane.showInputDialog("Enter starting Y position (default 100):");

        if (sy != null && !sy.trim().equals("")) {
            try {
                startY = Integer.parseInt(sy);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Using default Y = 100.");
            }
        }

        JFrame frame = new JFrame("Ball Game (With Safe User Input)");

        BallGameInput game = new BallGameInput(startX, startY);

        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}