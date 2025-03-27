import javax.swing.*;
        import java.awt.*;

public class Bird extends JPanel {
    int x = 10;
    int y = 10;
    int width = 29;
    int height = 34;
    Image img;
    int velocityY = 0;
    int gravity = 1;

    public Bird(Image img) {
        this.img = img;
    }

    public void update(int boardHeight) {
        velocityY += gravity;
        y += velocityY;
        if (y >= boardHeight - height) {
            y = boardHeight - height;
            velocityY = 0;
        }
    }

    public void jump() {
        velocityY = -10;
    }

}
