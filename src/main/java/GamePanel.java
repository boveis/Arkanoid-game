import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GamePanel extends JPanel implements KeyListener {
    public boolean inGame = false;
    public static boolean endGame;
    static final int GAME_WIDTH = 500;
    static final int GAME_HEIGHT = 700;
    static final int BALL_DIAMETER=14;
    static int paddle_width=150;
    static int paddle_height=20;
    public static int life;
    public Thread thread ;
    public static ArrayList<Block> blocks;
    public HashMap<Integer , String> hashMap;
    public static ArrayList<Ball> balls;
    public JLabel label;
    public JLabel jlabel;
    public String path;


    public static Block paddle;
    public NewLine newLine;
    public WinkBreak winkBreak;
    public static Block[] blocksPresent;
    public static int score;
    public static boolean is10;
    public static int break10timeLeft;
    public static int break10ballCount;
    public Break10 break10;


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


    public GamePanel(String s) {
        endGame=false;
        this.path=s;

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


        balls=new ArrayList<Ball>();
        blocks= new ArrayList<Block>();
        blocksPresent = new Block[12];

        this.setPreferredSize(new Dimension(GAME_WIDTH , GAME_HEIGHT));
        this.setFocusable(true);
        this.setBackground(Color.white);
        this.addKeyListener(this);
        init();
        label = new JLabel();
        label.setText("score:"+score+"        ball left:" + life);

        label.setFont(new Font("MV Boli" , Font.BOLD , 20 ));
        label.setForeground(Color.BLACK);
        this.setLayout(null);
        label.setBounds(0, 600, 500 , 100);
        this.add(label);

        jlabel = new JLabel("GAME OVER!");
        jlabel.setBounds(0 ,0 , 500 , 700);
        jlabel.setBackground(Color.white);
        jlabel.setForeground(Color.black);
        jlabel.setVisible(false);
        jlabel.setFont(new Font("MV Boli" , Font.BOLD , 60 ));
        jlabel.setVerticalTextPosition(JLabel.CENTER);
        jlabel.setHorizontalTextPosition(JLabel.CENTER);
        this.add(jlabel);



    }
    public void SavedDate(String pathOfFile){
        try {
            FileWriter fl = new FileWriter(pathOfFile);
            fl.write(endGame+"\n");
            fl.write(score + "\n");
            fl.write(life + "\n");
            fl.write(balls.size() + "\n");
            for (int i = 0; i < balls.size(); i++) {
                fl.write(balls.get(i).x + "\n");
                fl.write(balls.get(i).y + "\n");
                fl.write(balls.get(i).movX + "\n");
                fl.write(balls.get(i).movY + "\n");
                fl.write(balls.get(i).speed + "\n");
            }
            fl.write(paddle.x + "\n");
            fl.write(paddle.y + "\n");

            fl.write(blocks.size() + "\n");
            for (int i = 0; i < blocks.size(); i++) {
                fl.write(blocks.get(i).x + "\n");
                fl.write(blocks.get(i).y + "\n");
                fl.write(blocks.get(i).type + "\n");
            }

            for (int i = 5; i < 12; i++) {
                fl.write(blocksPresent[i].x + "\n");
                fl.write(blocksPresent[i].y + "\n");
                fl.write(blocksPresent[i].type + "\n");
            }

            fl.write(newLine.newLinetime + "\n");
            fl.write(winkBreak.winkTime + "\n");

            fl.write(is11 + "\n");
            fl.write(break11.run + "\n");
            fl.write(break11timeLeft + "\n");


            fl.write(is10 + "\n");
            fl.write(break10.run + "\n");
            fl.write(break10timeLeft + "\n");
            fl.write(break10ballCount + "\n");


            fl.write(is9 + "\n");
            fl.write(break9.run + "\n");
            fl.write(break9timeLeft + "\n");


            fl.write(is8 + "\n");
            fl.write(break8.run + "\n");
            fl.write(break8.check7 + "\n");
            fl.write(break8timeLeft + "\n");


            fl.write(is7 + "\n");
            fl.write(break7.run + "\n");
            fl.write(break7.check8 + "\n");
            fl.write(break7timeLeft + "\n");


            fl.write(is6 + "\n");
            fl.write(break6.run + "\n");
            fl.write(break6.check5 + "\n");
            fl.write(break6timeLeft + "\n");


            fl.write(is5 + "\n");
            fl.write(break5.run + "\n");
            fl.write(break5.check6 + "\n");
            fl.write(break5timeLeft + "\n");



            fl.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        File fl= new File(path);
        try {
            Scanner sc=new Scanner(fl);
            endGame=sc.nextBoolean();
            score=sc.nextInt();
            life=sc.nextInt();

            int tmpballsize = sc.nextInt();
            for (int i=0 ; i<tmpballsize ; i++){
                int tmpx;
                tmpx = sc.nextInt();
                int tmpy;
                tmpy = sc.nextInt();
                int tmpmovx;
                tmpmovx = sc.nextInt();
                int tmpmovy=sc.nextInt();
                int tmpspeed=sc.nextInt();
                balls.add(new Ball(tmpx , tmpy , BALL_DIAMETER , BALL_DIAMETER , tmpmovx , tmpmovy , tmpspeed , "aks/ball.png"));
            }

            int tmppaddlex=sc.nextInt();
            int tmppaddley=sc.nextInt();
            paddle = new Block(tmppaddlex , tmppaddley , paddle_width , paddle_height , 20 , "aks/paddle.jpg");


            int tmpblocksize=sc.nextInt();
            for (int  i=0 ; i<tmpblocksize ; i++){
                int tmpx =sc.nextInt();
                int tmpy=sc.nextInt();
                int tmptype = sc.nextInt();
                blocks.add(new Block(tmpx , tmpy , 40 , 20  , tmptype , hashMap.get(tmptype)));
            }

            for (int i=5 ; i<12 ; i++){
                int tmpx =sc.nextInt();
                int tmpy=sc.nextInt();
                int tmptype = sc.nextInt();
                blocksPresent[i]=new Block(tmpx , tmpy , 40 , 20  , tmptype , hashMap.get(tmptype));
            }
            int tmpnewlinetime=sc.nextInt();
            int tmpWinktime=sc.nextInt();

            winkBreak = new WinkBreak(tmpWinktime);
            newLine = new NewLine(tmpnewlinetime);


            is11=sc.nextBoolean();
            boolean tmp11run=sc.nextBoolean();
            break11timeLeft=sc.nextInt();
            break11 = new Break11(tmp11run);


            is10=sc.nextBoolean();
            boolean tmp10run=sc.nextBoolean();
            break10timeLeft=sc.nextInt();
            break10ballCount=sc.nextInt();
            break10 = new Break10(tmp10run);

            is9=sc.nextBoolean();
            boolean tmp9run=sc.nextBoolean();
            break9timeLeft=sc.nextInt();
            break9 = new Break9(tmp9run);

            is8=sc.nextBoolean();
            boolean tmp8run=sc.nextBoolean();
            boolean tmp8check7=sc.nextBoolean();
            break8timeLeft=sc.nextInt();
            break8 = new Break8(tmp8run , tmp8check7);

            is7=sc.nextBoolean();
            boolean tmp7run=sc.nextBoolean();
            boolean tmp7check8=sc.nextBoolean();
            break7timeLeft=sc.nextInt();
            break7 = new Break7(tmp7run , tmp7check8);


            is6=sc.nextBoolean();
            boolean tmp6run=sc.nextBoolean();
            boolean tmp6check5=sc.nextBoolean();
            break6timeLeft=sc.nextInt();
            break6 = new Break6(tmp6run , tmp6check5);


            is5=sc.nextBoolean();
            boolean tmp5run=sc.nextBoolean();
            boolean tmp5check6=sc.nextBoolean();
            break5timeLeft=sc.nextInt();
            break5 = new Break5(tmp5run , tmp5check6);
            sc.close();



        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }


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
        label.setText("score:"+score+"        ball:" + life);
        PaintPresent(g);
        if(blocks.size()==0)Victory();

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
             if ((ball.x >(GAME_WIDTH) - (BALL_DIAMETER)) || ball.x < 0) ball.movX *= -1;
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
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;

                         break;
                         case 6:
                             is6 = true;
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;
                             break;
                         case 7:
                             is7 = true;
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;
                             break;
                         case 8:
                             is8 = true;
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;
                         break;

                         case 9:
                             is9 = true;
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;
                             break;
                         case 10:
                             is10 = true;
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;
                             break;
                         case 11:
                             is11 = true;
                             FindDirection(ball, blocks.get(i));
                             blocks.remove(i);
                             i--;
                             break;

                         default:
                     }
                 }
             }
         }
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
                         balls.add(new Ball(237, 555, GamePanel.BALL_DIAMETER, GamePanel.BALL_DIAMETER, 1, -1, 2, "aks/ball.png"));
                     }
                 }
             }
         }
        repaint();
     }
     public void Victory(){
         inGame=false;
         endGame=true;
         jlabel.setText("You Win!");
         jlabel.setVisible(true);
         thread.stop();
     }
     public void EndGame(){
        inGame=false;
        endGame=true;
        jlabel.setText("Game over!");
        jlabel.setVisible(true);
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



}
