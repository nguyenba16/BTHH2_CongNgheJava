import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        JFrame mainWindown = new JFrame();
        mainWindown.setTitle("Flappy Bird");

        int boardWidth = 340;
        int boardHeight = 640;
        mainWindown.setSize(boardWidth, boardHeight);

        mainWindown.setLocationRelativeTo(null);
        mainWindown.setResizable(false);

        Game game = new Game();
        mainWindown.add(game);

        mainWindown.setVisible(true);

        mainWindown.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}