public class WinkBreak {
    public int winkTime;
    public WinkBreak(int winkTime){
        this.winkTime=winkTime;
    }
    public void MakeWink(){
        if (winkTime<200)winkTime++;
        else{
            winkTime=0;
            for (int i=0 ; i<GamePanel.blocks.size() ; i++){
                if (GamePanel.blocks.get(i).type==3){
                    if (GamePanel.blocks.get(i).active)GamePanel.blocks.get(i).active=false;
                    else GamePanel.blocks.get(i).active=true;
                }
            }
        }
    }
}
