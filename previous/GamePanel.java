import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GamePanel extends JPanel implements KeyListener {
    public static boolean inGame = false;
    static final int GAME_WIDTH = 500;
    static final int GAME_HEIGHT = 700;
    static final int BALL_DIAMETER=14;
    static int paddle_width=150;
    static int paddle_height=20;
    public int life=2;
    public static ArrayList<Ball> balls = new ArrayList<Ball>();
    public Thread thread ;
    public static ArrayList<Block> blocks = new ArrayList<Block>();
    public static Block paddle;
    public static Ball ball;
    public NewLine newLine;
    public WinkBreak winkBreak;
    public static Block[] blocksPresent;
    public int score;
    public static boolean is10;
    public static int break10timeLeft;
    public static int break10ballCount;
    public Break10 break10;
    public HashMap<Integer , String> hashMap;
    public static boolean is9;
    public static int break9timeLeft;
    public Break9 break9;

    public static boolean is11;
    public static int break11timeLeft;
    public Break11 break11;


    public static boolean is8;
    public static int break8timeLeft;
    public static Break8 break8;


    public static boolean is7;
    public static int break7timeLeft;
    public static Break7 break7;


    public static boolean is6;
    public static int break6timeLeft;
    public static Break6 break6;

    public static boolean is5;
    public static int break5timeLeft;
    public static Break5 break5;


    public GamePanel() {

        hashMap= new HashMap<Integer, String>();
        hashMap.put(4,"aks/white.jpg");
        hashMap.put(3,"aks/normal break.jpg");
        hashMap.put(2,"aks/dark wood.jpg");
        hashMap.put(1,"aks/normal break.jpg");
        hashMap.put(5,"aks/large paddle break.jpg");
        hashMap.put(6,"aks/small paddle break.jpg");
        hashMap.put(7,"aks/high speed break.jpg");
        hashMap.put(8,"aks/low speed break.jpg");
        hashMap.put(9,"aks/fire ball break.jpg");
        hashMap.put(10,"aks/multi ball break.jpg");
        hashMap.put(11,"aks/dizzy paddle break.jpg");



        blocksPresent = new Block[12];
        Block tmp;
        {
        for (int j = 0; j < 8; j++) {
            tmp = new Block((30 + (j * 55)), (30 + (0 * 35)), 40, 20, 4, "aks/white.jpg");
            blocks.add(tmp);
        }
        for (int i = 1; i < 5; i++) {
            int j = 1;
            tmp = new Block((30 + (j * 55)), (30 + (i * 35)), 40, 20, 3, "aks/normal break.jpg");
            blocks.add(tmp);
            j++;
            tmp = new Block((30 + (j * 55)), (30 + (i * 35)), 40, 20, 2, "aks/dark wood.jpg");
            blocks.add(tmp);
            j++;
            tmp = new Block((30 + (j * 55)), (30 + (i * 35)), 40, 20, 1, "aks/normal break.jpg");
            blocks.add(tmp);
            j++;
            tmp = new Block((30 + (j * 55)), (30 + (i * 35)), 40, 20, 1, "aks/normal break.jpg");
            blocks.add(tmp);
            j++;
            tmp = new Block((30 + (j * 55)), (30 + (i * 35)), 40, 20, 2, "aks/dark wood.jpg");
            blocks.add(tmp);
            j++;
            tmp = new Block((30 + (j * 55)), (30 + (i * 35)), 40, 20, 3, "aks/normal break.jpg");
            blocks.add(tmp);
            j++;
        }
        blocksPresent[6] = new Block((30 + (0 * 55)), (30 + (1 * 35)), 40, 20, 6, "aks/small paddle break.jpg");
        blocks.add(blocksPresent[6]);
        blocksPresent[7] = new Block((30 + (7 * 55)), (30 + (1 * 35)), 40, 20, 7, "aks/high speed break.jpg");
        blocks.add(blocksPresent[7]);
        blocksPresent[10] = new Block((30 + (0 * 55)), (30 + (2 * 35)), 40, 20, 10, "aks/multi ball break.jpg");
        blocks.add(blocksPresent[10]);
        blocksPresent[5] = new Block((30 + (7 * 55)), (30 + (2 * 35)), 40, 20, 5, "aks/large paddle break.jpg");
        blocks.add(blocksPresent[5]);
        blocksPresent[8] = new Block((30 + (0 * 55)), (30 + (3 * 35)), 40, 20, 8, "aks/low speed break.jpg");
        blocks.add(blocksPresent[8]);
        tmp = new Block((30 + (7 * 55)), (30 + (3 * 35)), 40, 20, 4, "aks/white.jpg");
        blocks.add(tmp);
        blocksPresent[9] = new Block((30 + (0 * 55)), (30 + (4 * 35)), 40, 20, 9, "aks/fire ball break.jpg");
        blocks.add(blocksPresent[9]);
        blocksPresent[11]= new Block((30 + (7 * 55)), (30 + (4 * 35)), 40, 20, 11, "aks/dizzy paddle break.jpg");
        blocks.add(blocksPresent[11]);
    }
        this.setPreferredSize(new Dimension(GAME_WIDTH , GAME_HEIGHT));
        this.setFocusable(true);
        this.setBackground(Color.white);
        paddle = new Block(175,600 ,paddle_width , paddle_height , 2, "aks/paddle.jpg");
        ball = new Ball(237 , 555 , BALL_DIAMETER , BALL_DIAMETER , 1.0 ,-1.0 , 2.0 ,"aks/ball.png");
        balls.add(ball);
        this.addKeyListener(this);

        score=0;
        winkBreak = new WinkBreak(0);
        newLine = new NewLine(0);

        break10 = new Break10();
        break10timeLeft=0;
        break10ballCount=0;

        break9 = new Break9();
        break9timeLeft=2000;

        break11 = new Break11();
        break11timeLeft=2000;

        break8 = new Break8();
        break8timeLeft=2000;


        break7 = new Break7();
        break7timeLeft=2000;


        break6 = new Break6();
        break6timeLeft=2000;


        break5 = new Break5();
        break5timeLeft=2000;
        SavedDate();
    }
    public void init(String s){

    }


    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0 , 0 , GAME_WIDTH , GAME_HEIGHT);
        for (int i=0 ; i<blocks.size() ; i++){
            blocks.get(i).draw(g , this);
        }
        paddle.draw( g, this);
        for (Ball ball:balls){
            ball.draw(g,this);
        }

        PaintPresent(g);
    }

    public void PaintPresent(Graphics g){
        if (is10){
              if(!break10.run){
                break10.Down();
                blocksPresent[10].draw(g,this);
            }
            else{

                break10.addBall();
            }
        }
        if(is9){
            if(!break9.run){
                break9.Down();
                blocksPresent[9].draw(g,this);
            }
            else{
                break9.FireBallTime();
            }
        }
        if(is11){
            if(!break11.run){
                break11.Down();
                blocksPresent[11].draw(g,this);
            }
            else{
                break11.DizzyTime();
            }
        }

        if(is8){
            if(!break8.run){
                break8.Down();
                blocksPresent[8].draw(g,this);
            }
            else{
                if(!break8.check7)
                 break8.SlowTime();
            }
        }
        if(is7){
            if(!break7.run){
                break7.Down();
                blocksPresent[7].draw(g,this);
            }
            else{
                if(!break7.check8)
                    break7.FastTime();
            }
        }

        if(is6){
            if(!break6.run){
                break6.Down();
                blocksPresent[6].draw(g,this);
            }
            else{
                if(!break6.check5)
                    break6.SmallPaddle();

            }
        }
        if(is5){
            if(!break5.run){
                break5.Down();
                blocksPresent[5].draw(g,this);
            }
            else{
                if(!break5.check6)
                    break5.BigPaddle();
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            thread=new Thread(()->{
                while (inGame){
                    update();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                }

            });
            thread.start();
            if (inGame)inGame=false;
            else inGame=true;

        }
        if  (e.getKeyCode()==KeyEvent.VK_RIGHT){
            if (is11 && break11.run){
                paddle.paddleMov=-2;
            }
            else paddle.paddleMov=2;
        }

        if  (e.getKeyCode()==KeyEvent.VK_LEFT){
            if (is11 && break11.run){
                paddle.paddleMov=2;
            }
            else paddle.paddleMov=-2;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

        if  (e.getKeyCode()==KeyEvent.VK_RIGHT){
            paddle.paddleMov=0;
        }

        if  (e.getKeyCode()==KeyEvent.VK_LEFT){
            paddle.paddleMov=0;
        }
    }


     public void update(){

        paddle.PaddleMove();
        newLine.MakeNewLine();
        winkBreak.MakeWink();
         for (Ball ball:balls) {
             ball.move();
             if ((ball.x > (int) (GAME_WIDTH) - (BALL_DIAMETER)) || ball.x < 0) ball.movX *= -1;
             if ((ball.y < 0)) ball.movY *= -1;
             if (/*(ball.x+BALL_DIAMETER)>paddle.x && ball.x<(paddle.width+paddle.x) && (ball.y+BALL_DIAMETER)>=paddle.y && (ball.y+BALL_DIAMETER)<=paddle.y+paddle_height*/
                     ball.intersects(paddle)) {
                 int tmp = (ball.x - paddle.x);
                 int tmpwidth = paddle.width / 3;
                 int[] pp = new int[]{tmpwidth * 1, tmpwidth * 2};
                 if (tmp < pp[0]) {
                     ball.movX *= -1.0;
                     ball.movY *= -1.0;
                 }
                 if (tmp < pp[1] && tmp >= pp[0]) {
                     ball.movY *= -1;
                 }
                 if (tmp >= pp[1]) {
                     ball.movY *= -1.0;
                     ball.movX *= -1.0;
                 }
             }
             int count = 0;
             for (int i = 0; i < (blocks.size() - count); i++) {

                 if (!blocks.get(i).destroyed && blocks.get(i).intersects(ball)) {

                     int typeOfBreak = blocks.get(i).type;
                     switch (typeOfBreak) {
                         case 1:
                             score += 10;
                             FindDirection(ball, blocks.get(i));
                             blocks.get(i).destroyed = true;
                             blocks.remove(i);
                             i--;
                             break;
                         case 2:
                             if (is9 && break9.run){
                                 score+=20;
                                 FindDirection(ball, blocks.get(i));
                                 blocks.get(i).destroyed = true;
                                 blocks.remove(i);
                                 i--;
                             }else {
                                 score += 10;
                                 FindDirection(ball, blocks.get(i));
                                 blocks.get(i).destroyed = true;
                                 blocks.add(new Block(blocks.get(i).x, blocks.get(i).y, blocks.get(i).width, blocks.get(i).height, 1, "aks/normal break.jpg"));
                                 blocks.remove(i);
                                 i--;
                                 count++;
                             }
                             break;
                         case 3:
                             if (blocks.get(i).active) {
                                 score += 40;
                                 FindDirection(ball, blocks.get(i));
                                 blocks.get(i).destroyed = true;
                                 blocks.remove(i);
                                 i--;
                             }
                             break;
                         case 4:
                             score += 30;
                             FindDirection(ball, blocks.get(i));
                             blocks.get(i).destroyed = true;
                             blocks.remove(i);
                             i--;
                             break;
                         case 5:
                             is5 = true;
                             score += 5;
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;

                         break;
                         case 6:
                             is6 = true;
                             score += 50;
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;
                             break;
                         case 7:
                             is7 = true;
                             score += 5;
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;
                             break;
                         case 8:
                             is8 = true;
                             score += 60;
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;
                         break;

                         case 9:
                             is9 = true;
                             score += 20;
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;
                             break;
                         case 10:
                             is10 = true;
                             score += 20;
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;
                             break;
                         case 11:
                             is11 = true;
                             score += 30;
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;
                             break;

                         default:
                     }
                 }
             }
         }
         if(blocks.size()==0)Victory();
         for(Block block:blocks){
             if((block.y+block.height)>paddle.y)EndGame();
         }
         for(int i=0 ; i<balls.size() ; i++){
             Ball ball=balls.get(i);

             if (ball.y > GAME_HEIGHT) {
                 balls.remove(i);
                 if (balls.size() == 0) {
                     if (life == 0) {
                         EndGame();
                     } else {
                         life--;
                         balls.add(new Ball(237, 555, GamePanel.BALL_DIAMETER, GamePanel.BALL_DIAMETER, 1.0, -1.0, 2.0, "aks/ball.png"));
                     }
                 }
             }
         }
        repaint();
     }
     public void Victory(){
         inGame=false;
         thread.stop();
     }
     public void EndGame(){
        inGame=false;
        thread.stop();
     }

     public void FindDirection(Ball ball , Block block){
        if (ball.x<block.x || ball.x>=(block.x+block.width)){
            ball.movX*=-1;
            return;
        }
        ball.movY*=-1;
        return;
     }

     public void SavedDate(){
         try {
             FileWriter fl=new FileWriter("tex/StartGame.txt");
             fl.write(score+"\n");
             fl.write(life+"\n");
             fl.write(balls.size()+"\n");
             for(int i=0 ; i<balls.size() ; i++){
                 fl.write(balls.get(i).x+"\n");
                 fl.write(balls.get(i).y+"\n");
                 fl.write(balls.get(i).movX+"\n");
                 fl.write(balls.get(i).movY+"\n");
                 fl.write(balls.get(i).speed+"\n");
             }
             fl.write(paddle.x+"\n");
             fl.write(paddle.y+"\n");
             fl.write(blocks.size()+"\n");
             for(int i=0 ; i<blocks.size() ; i++){
                 fl.write(blocks.get(i).x+"\n");
                 fl.write(blocks.get(i).y+"\n");
                 fl.write(blocks.get(i).type+"\n");
             }
             for (int i=5 ; i<12 ; i++){
                 fl.write(blocksPresent[i].x+"\n");
                 fl.write(blocksPresent[i].y+"\n");
                 fl.write(blocksPresent[i].type+"\n");
             }

             fl.write(newLine.newLinetime+"\n");
             fl.write(winkBreak.winkTime+"\n");

             fl.write(is11+"\n");
             fl.write(break11.run+"\n");
             fl.write(break11timeLeft+"\n");


             fl.write(is10+"\n");
             fl.write(break10.run+"\n");
             fl.write(break10timeLeft+"\n");
             fl.write(break10ballCount+"\n");


             fl.write(is9+"\n");
             fl.write(break9.run+"\n");
             fl.write(break9timeLeft+"\n");


             fl.write(is8+"\n");
             fl.write(break8.run+"\n");
             fl.write(break8.check7+"\n");
             fl.write(break8timeLeft+"\n");


             fl.write(is7+"\n");
             fl.write(break7.run+"\n");
             fl.write(break7.check8+"\n");
             fl.write(break7timeLeft+"\n");



             fl.write(is6+"\n");
             fl.write(break6.run+"\n");
             fl.write(break6.check5+"\n");
             fl.write(break6timeLeft+"\n");





             fl.write(is5+"\n");
             fl.write(break5.run+"\n");
             fl.write(break5.check6+"\n");
             fl.write(break5timeLeft+"\n");



             fl.close();










         } catch (IOException e) {
             e.printStackTrace();
         }
     }



}
