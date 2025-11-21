
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PongGame extends JPanel implements ActionListener, KeyListener {
    // Panel size
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    // Ball and paddle sizes
    private static final int BALL_SIZE = 20;
    private static final int PADDLE_W = 10;
    private static final int PADDLE_H = 80;
    private static final int PADDLE_MARGIN = 10;

    // Positions and velocities
    private int ballX, ballY;
    private int ballDX, ballDY;
    private int playerY, aiY;

    // Controls
    private boolean upPressed = false;
    private boolean downPressed = false;

    // Scores
    private int playerScore = 0;
    private int aiScore = 0;

    private final Timer timer;
    private final Random rand = new Random();

    public PongGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        playerY = aiY = (HEIGHT - PADDLE_H) / 2;
        resetBall();

        timer = new Timer(10, this);
        timer.start();
    }

    private void resetBall() {
        ballX = WIDTH / 2 - BALL_SIZE / 2;
        ballY = HEIGHT / 2 - BALL_SIZE / 2;
        // Give ball an initial speed and random vertical direction
        ballDX = rand.nextBoolean() ? 3 : -3;
        ballDY = rand.nextInt(3) + 1; // 1..3
        if (rand.nextBoolean()) ballDY = -ballDY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Center dashed line
        g.setColor(Color.DARK_GRAY);
        for (int y = 0; y < HEIGHT; y += 20) {
            g.fillRect(WIDTH / 2 - 1, y, 2, 12);
        }

        // Ball and paddles
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
        g.fillRect(PADDLE_MARGIN, playerY, PADDLE_W, PADDLE_H);
        g.fillRect(WIDTH - PADDLE_MARGIN - PADDLE_W, aiY, PADDLE_W, PADDLE_H);

        // Scores
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString(String.valueOf(playerScore), WIDTH / 2 - 40, 30);
        g.drawString(String.valueOf(aiScore), WIDTH / 2 + 20, 30);
    }

    public void startGame(){
        // Create a JFrame and add this PongGame panel to it so the hub
        // can launch the game without relying on the static main method.
        JFrame frame = new JFrame("Simple Pong");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Ensure key listener receives focus
        this.requestFocusInWindow();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move player paddle smoothly based on booleans
        if (upPressed && playerY > 0) playerY -= 6;
        if (downPressed && playerY + PADDLE_H < HEIGHT) playerY += 6;

        // Move ball
        ballX += ballDX;
        ballY += ballDY;

        // Top/bottom bounce (use panel bounds, not hard-coded numbers)
        if (ballY <= 0) {
            ballY = 0;
            ballDY = -ballDY;
        } else if (ballY + BALL_SIZE >= HEIGHT) {
            ballY = HEIGHT - BALL_SIZE;
            ballDY = -ballDY;
        }

        // Simple AI: follow ball's vertical center with a max speed, and clamp
        int aiCenter = aiY + PADDLE_H / 2;
        int ballCenter = ballY + BALL_SIZE / 2;
        if (aiCenter < ballCenter) aiY += Math.min(4, ballCenter - aiCenter);
        else aiY -= Math.min(4, aiCenter - ballCenter);
        if (aiY < 0) aiY = 0;
        if (aiY + PADDLE_H > HEIGHT) aiY = HEIGHT - PADDLE_H;

        // Collision detection using rectangles (reliable)
        Rectangle ballRect = new Rectangle(ballX, ballY, BALL_SIZE, BALL_SIZE);
        Rectangle playerRect = new Rectangle(PADDLE_MARGIN, playerY, PADDLE_W, PADDLE_H);
        Rectangle aiRect = new Rectangle(WIDTH - PADDLE_MARGIN - PADDLE_W, aiY, PADDLE_W, PADDLE_H);

        // Only reflect if ball is moving toward the paddle (prevents multiple flips)
        if (ballRect.intersects(playerRect) && ballDX < 0) {
            ballDX = -ballDX;
            // Adjust vertical speed depending on where it hit the paddle
            int hitPos = (ballY + BALL_SIZE / 2) - (playerY + PADDLE_H / 2);
            ballDY = Math.max(-5, Math.min(5, hitPos / 8));
            if (ballDY == 0) ballDY = rand.nextBoolean() ? 1 : -1;
            // move ball out of paddle to avoid sticking
            ballX = playerRect.x + playerRect.width;
        }

        if (ballRect.intersects(aiRect) && ballDX > 0) {
            ballDX = -ballDX;
            int hitPos = (ballY + BALL_SIZE / 2) - (aiY + PADDLE_H / 2);
            ballDY = Math.max(-5, Math.min(5, hitPos / 8));
            if (ballDY == 0) ballDY = rand.nextBoolean() ? 1 : -1;
            ballX = aiRect.x - BALL_SIZE;
        }

        // Score and reset when ball passes left or right
        if (ballX + BALL_SIZE < 0) { // AI scores
            aiScore++;
            resetBall();
        } else if (ballX > WIDTH) { // Player scores
            playerScore++;
            resetBall();
        }

        repaint();
    }

    // KeyListener implementations: set booleans for smooth movement
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) upPressed = true;
        if (code == KeyEvent.VK_DOWN) downPressed = true;
        if (code == KeyEvent.VK_SPACE) { // reset scores and ball
            playerScore = 0;
            aiScore = 0;
            resetBall();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) upPressed = false;
        if (code == KeyEvent.VK_DOWN) downPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simple Pong");
            PongGame pong = new PongGame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(pong);
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            // Ensure key listener receives focus
            pong.requestFocusInWindow();
        });
    }
}
