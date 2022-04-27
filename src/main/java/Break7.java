public class Break7 {

    public boolean run;
public boolean check8;
    public Break7(boolean run , boolean check8) {
        this.run = run;
        this.check8=check8;
    }

    public void Down() {
        GamePanel.blocksPresent[7].y += 1;
        if (GamePanel.blocksPresent[7].intersects(GamePanel.paddle)) {
            run = true;
            GamePanel.score+=20;
            if(GamePanel.break8.run)check8=true;
            return;
        }
        if (GamePanel.blocksPresent[7].y > GamePanel.GAME_HEIGHT) {
            GamePanel.is7= false;
        }

    }

    public void FastTime() {
            GamePanel.break7timeLeft--;
            for (Ball ball : GamePanel.balls) {
                ball.speed = 3;
            }
            if (GamePanel.break7timeLeft < 0) {
                for (Ball ball : GamePanel.balls) {
                    ball.speed = 2;
                }
                GamePanel.break8.check7=false;
                GamePanel.is7 = false;
                run = false;
                return;
            }

    }
}
