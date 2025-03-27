import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game extends JPanel {
    int boardWidth = 360;
    int boardHeight = 640;
    Image Backgound;
    Image BirdImg;
    Image TopPipeImg;
    Image bottomPipeImg;
    Bird bird;
    Pipe pipe;
    int score = 0;
    boolean isGameOver = false;
    Timer timer, timerPipe;
    ArrayList<Pipe> pipeArr = new ArrayList<Pipe>();
    public void paintComponent (Graphics g){
            super.paintComponent(g);
            draw(g);
    }

    public boolean hasCollision(Bird bird, Pipe pipe){
        return bird.x < pipe.x+ pipe.width && bird.x + bird.width > pipe.x && bird.y<pipe.y+pipe.height && bird.y+bird.height>pipe.y;
    }
    public void checkGameOver() {
        for (Pipe pipe : pipeArr) {
            if (hasCollision(bird, pipe) || bird.y >= boardHeight - bird.height) {
                isGameOver = true;
                ((Timer) timer).stop(); // Dừng trò chơi
                ((Timer) timerPipe).stop();
                break;
            }
        }
    }

    public void restartGame() {
        isGameOver = false;
        score = 0;
        bird = new Bird(BirdImg);
        pipeArr.clear();
        timer.start();
        timerPipe.start();
        repaint();
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(Backgound, 0,0, boardWidth, boardHeight, null);
        graphics.drawImage(BirdImg, bird.x, bird.y, bird.width,bird.height, null);
        for (Pipe pipe : pipeArr) {
            graphics.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 24));
        graphics.drawString("Score: " + score, 20, 40);
        if (isGameOver) {
            graphics.setFont(new Font("Arial", Font.BOLD, 36));
            graphics.drawString("GAME OVER", boardWidth / 2 - 110, boardHeight / 2);
            graphics.drawString("Nhấn R", boardWidth / 2 - 70, boardHeight / 2 + 50);
        }
    }

    public Game() {
        super();
        Backgound = new ImageIcon(getClass().getResource("/images/flappybirdbg.png")).getImage();
        BirdImg = new ImageIcon(getClass().getResource("/images/flappybird.png")).getImage();
        TopPipeImg = new ImageIcon(getClass().getResource("/images/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("/images/bottompipe.png")).getImage();
        bird = new Bird(BirdImg);
        timer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < pipeArr.size(); i++) {
                    pipeArr.get(i).update();
                    if (hasCollision(bird, pipeArr.get(i))) {
                        System.out.println("Đụng");
                        ((Timer) e.getSource()).stop();
                        break;
                    }

                }
                for (int i = 0; i < pipeArr.size(); i += 2) {
                    if (!pipeArr.get(i).passed && bird.x > pipeArr.get(i).x + pipeArr.get(i).width) {
                        score++;
                        pipeArr.get(i).passed = true;
                    }
                }

                bird.update(boardHeight);
                checkGameOver();
                repaint();
            }

        });
        timer.start();

        timerPipe = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int gap = 150;
                int pipeHeight = 100 + (int)(Math.random() * 200);
                repaint();
                pipeArr.add(new Pipe(boardWidth, 0, pipeHeight, TopPipeImg));
                pipeArr.add(new Pipe(boardWidth, pipeHeight + gap, boardHeight - pipeHeight - gap, bottomPipeImg)); // Ống dưới
            }
        });
        timerPipe.start();

        setFocusable(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    bird.jump();
                } else if (isGameOver && e.getKeyCode() == KeyEvent.VK_R) {
                    restartGame();
                }
            }
        });

    };
}
