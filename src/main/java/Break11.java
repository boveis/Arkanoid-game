import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Break11 {
    public boolean run;

    public Break11(boolean run ) {
        this.run = run;

    }

    public void Down() {
        GamePanel.blocksPresent[11].y += 1;
        if (GamePanel.blocksPresent[11].intersects(GamePanel.paddle)) {
            run = true;
            GamePanel.score+=100;
            return;
        }
        if (GamePanel.blocksPresent[11].y > GamePanel.GAME_HEIGHT) {
            GamePanel.is11= false;
        }

    }

    public void DizzyTime() {
        GamePanel.break11timeLeft--;

        if (GamePanel.break11timeLeft < 0) {
            GamePanel.is11 = false;
            run = false;
            return;
        }
    }
}


