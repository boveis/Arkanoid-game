import org.graalvm.compiler.nodes.AbstractLocalNode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Block extends Rectangle {
    public Image pic;
    public int paddleMov;
    public boolean active;
    public boolean destroyed;
    public int type;
    public Block(int x , int y , int w , int h , int type, String s  ){
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
        this.type=type;
        destroyed=false;
        active=true;
        try {
            pic = ImageIO.read(new File(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics g , Component c){
        if (!destroyed){
            if (type==3 && !active)return;
            g.drawImage(pic , x , y , width , height , c);
        }
    }
    public void PaddleMove(){
        this.x+=paddleMov;
        if (this.x>((GamePanel.GAME_WIDTH)-this.width))this.x=(int)((GamePanel.GAME_WIDTH)-this.width);
        if (this.x<0)this.x=0;
    }

}
