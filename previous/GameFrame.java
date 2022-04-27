import javax.swing.*;
import java.awt.*;
import java.time.Clock;

public class GameFrame extends JFrame {
    public GamePanel gamePanel;

    public GameFrame(){
        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.setTitle("Arkanoid");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.add(gamePanel);
        this.pack();
        this.setLocationRelativeTo(null);
    }




}