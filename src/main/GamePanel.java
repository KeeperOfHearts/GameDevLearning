import javax.swing.JPanel;
import java.awt.*;
import java.sql.SQLOutput;

public class GamePanel extends JPanel implements Runnable {
    final int originalRileSize = 16;
    final int scale = 3;
    final int tileSize = originalRileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreeRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreeRow;

    // FPS
    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    //player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;


            if (delta > 1) {
                // UPDATE
                update();
                // DRAW
                repaint();
                delta--;
            }
        }
    }

    public void update() {
       if (keyHandler.upPressed == true) {
           playerY -= playerSpeed;
       } else if (keyHandler.downPressed == true) {
           playerY += playerSpeed;
       } else if (keyHandler.leftPressed == true) {
           playerX -= playerSpeed;
       } else if (keyHandler.rightPressed == true) {
           playerX += playerSpeed;
       }
    }

    public void paintComponent(Graphics graphics) {
      super.paintComponent(graphics);

      Graphics2D graphics2D = (Graphics2D) graphics;

      graphics2D.setColor(Color.WHITE);

      graphics2D.fillRect(playerX, playerY, tileSize, tileSize);

      graphics2D.dispose();
    }
}
