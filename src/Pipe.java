import javax.swing.*;
import java.awt.*;

public class Pipe extends JPanel {
    int x;
    int y;
    int width = 50;
    int height;
    Image img;
    int speed = 2;
    boolean passed = false;

    public Pipe(int x, int y, int height, Image img) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.img = img;
    }

    public void update() {
        x -= speed;
    }

}
