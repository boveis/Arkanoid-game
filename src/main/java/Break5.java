public class Break5 {
    public boolean run;
    public boolean check6;

    public Break5(boolean run , boolean check6) {
        this.run = run;
        this.check6=check6;
    }

    public void Down() {
        GamePanel.blocksPresent[5].y += 1;
        if (GamePanel.blocksPresent[5].intersects(GamePanel.paddle)) {
            run = true;
            GamePanel.score-=50;
            if(GamePanel.break6.run)check6=true;
            return;
        }
        if (GamePanel.blocksPresent[5].y > GamePanel.GAME_HEIGHT) {
            GamePanel.is5= false;
        }

    }

    public void BigPaddle() {
        GamePanel.break5timeLeft--;
        GamePanel.paddle.width=180;
        if (GamePanel.break5timeLeft < 0) {
            GamePanel.paddle.width=150;
            GamePanel.is5 = false;
            if (GamePanel.break6.check5)GamePanel.break6.check5=false;
            run = false;
            return;
        }

    }
}
