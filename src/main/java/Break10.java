public class Break10 {
    public boolean run;

    public Break10(boolean run ) {
        this.run = run;
    }

    public void Down() {
            GamePanel.blocksPresent[10].y += 1;
            if (GamePanel.blocksPresent[10].intersects(GamePanel.paddle)) {
                this.run = true;
                GamePanel.score-=50;
                return;
            }
            if (GamePanel.blocksPresent[10].y > GamePanel.GAME_HEIGHT) {
                GamePanel.is10 = false;
                return;
            }
    }

    public void addBall() {

        if (GamePanel.break10ballCount == 3) {
            GamePanel.break10ballCount = 0;
            this.run = false;
            GamePanel.is10 = false;
            return;
        }
        if (GamePanel.break10timeLeft > 300) {
            GamePanel.break10timeLeft = 0;
            GamePanel.break10ballCount++;
            GamePanel.balls.add(new Ball(237, 555, GamePanel.BALL_DIAMETER, GamePanel.BALL_DIAMETER, 1, -1, 2, "aks/ball.png"));
        }
        GamePanel.break10timeLeft++;
        return;

    }
}