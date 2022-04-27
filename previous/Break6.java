public class Break6 {
    public boolean run;
    public boolean check5;
    public Break6() {
        this.run = false;
        check5=false;
    }

    public void Down() {
        GamePanel.blocksPresent[6].y += 1;
        if (GamePanel.blocksPresent[6].intersects(GamePanel.paddle)) {

            if (GamePanel.break5.run){
                check5=true;
            }
            run=true;

            return;
        }
        if (GamePanel.blocksPresent[6].y > GamePanel.GAME_HEIGHT) {
            GamePanel.is6= false;
        }

    }

    public void SmallPaddle() {
        GamePanel.break6timeLeft--;
        GamePanel.paddle.width=90;
        if (GamePanel.break6timeLeft < 0) {
            GamePanel.paddle.width=150;
            GamePanel.is6 = false;
            GamePanel.break5.check6=false;
            run = false;
            return;
        }

    }
}
