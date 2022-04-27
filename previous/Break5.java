public class Break5 {
    public boolean run;
    public boolean check6;

    public Break5() {
        this.run = false;
        this.check6=false;
    }

    public void Down() {
        GamePanel.blocksPresent[5].y += 1;
        if (GamePanel.blocksPresent[5].intersects(GamePanel.paddle)) {
            run = true;
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
