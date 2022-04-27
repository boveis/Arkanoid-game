import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Break9 {
    public boolean run;

    public Break9() {
        this.run = false;

    }

    public void Down() {
            GamePanel.blocksPresent[9].y += 1;
            if (GamePanel.blocksPresent[9].intersects(GamePanel.paddle)) {
                run = true;
                return;
            }
            if (GamePanel.blocksPresent[9].y > GamePanel.GAME_HEIGHT) {
                GamePanel.is9 = false;
            }

    }

    public void FireBallTime() {
        GamePanel.break9timeLeft--;

        for (Ball ball:GamePanel.balls){
            try {
                ball.pic = ImageIO.read(new File("aks/fire ball.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (GamePanel.break9timeLeft < 0) {
            GamePanel.is9 = false;
            run = false;
            for (Ball ball : GamePanel.balls) {
                try {
                    ball.pic = ImageIO.read(new File("aks/ball.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return;
        }

    }
}
