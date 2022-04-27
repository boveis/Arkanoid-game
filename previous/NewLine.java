public class NewLine {
    public int newLinetime;
    public NewLine(int newLinetime){
        this.newLinetime = newLinetime;
    }
    public void MakeNewLine(){
        if(newLinetime<4000){
            newLinetime++;
            return;
        }
        if (newLinetime==4000){
            newLinetime=0;
            for (int i=0 ; i<GamePanel.blocks.size() ; i++)
                GamePanel.blocks.get(i).y+=35;

            for (int j=0 ; j<8 ; j++){
                    GamePanel.blocks.add(new Block((30+(j*55)) , (30+(0*35)) , 40 , 20 , 1 , "aks/normal break.jpg" ));

            }
        }
        return;
    }


}
