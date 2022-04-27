import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Ball extends Rectangle {
    public Image pic;
    public double movX , movY;
    public double speed;
    public Ball(int x , int y , int w , int h , double movX , double movY , double speed , String s){
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
        try {
            pic = ImageIO.read(new File(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.movX=movX;
        this.movY=movY;
        this.speed=speed;

    }
    public void draw(Graphics g , Component c){
        g.drawImage(pic , x , y , width , height , c);
    }
    public void move(){
        this.x+=(this.movX*this.speed);
        this.y+=(this.movY*this.speed);
    }

}
