import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    int boardWidth = 360;
    int boardHeight = 640;

    Image Backgound;
    Image BirdImg;
    Image TopPipeImg;
    Image bottomPipeImg;
    Bird bird;
    Pipe pipe;

    public void paintComponent (Graphics g){
            super.paintComponent(g);
            draw(g);
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(Backgound, 0,0, boardWidth, boardHeight, null);
        graphics.drawImage(BirdImg, bird.x, bird.y, bird.width,bird.height, null);
    }

    public Game() {
        Backgound = new ImageIcon(getClass().getResource("/images/flappybirdbg.png")).getImage();
        BirdImg = new ImageIcon(getClass().getResource("/images/flappybird.png")).getImage();
        TopPipeImg = new ImageIcon(getClass().getResource("/images/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("/images/bottompipe.png")).getImage();
        bird = new Bird(BirdImg);
    }
}
