public class Break8 {
    public boolean run;
    public boolean check7;

    public Break8() {
        this.run = false;
        check7=false;
    }

    public void Down() {
        GamePanel.blocksPresent[8].y += 1;
        if (GamePanel.blocksPresent[8].intersects(GamePanel.paddle)) {
            run = true;
            if(GamePanel.break7.run)check7=true;
            return;
        }
        if (GamePanel.blocksPresent[8].y > GamePanel.GAME_HEIGHT) {
            GamePanel.is8= false;
        }

    }

    public void SlowTime() {
        GamePanel.break8timeLeft--;
        for(Ball ball:GamePanel.balls){
            ball.speed=1;
        }
        if (GamePanel.break8timeLeft < 0) {
            for(Ball ball:GamePanel.balls){
                ball.speed=2;
            }
            GamePanel.is8 = false;
            run = false;
            GamePanel.break7.check8=false;
            return;
        }
    }
}
