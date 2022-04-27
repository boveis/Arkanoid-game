import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Menu  extends JPanel implements ActionListener {
    GamePanel gamePanel;
    public JButton saveGame;
    public JButton startNewGameBT;
    public JButton pause;
    public JButton restart;
    public JButton loadGame;

    public JTextField nameTX;
    public JTextField loadGameTx;


    public static JScrollPane scrollPane;
    public static JTextArea textArea;


    public Menu(GamePanel gamePanel){
        this.gamePanel=gamePanel;
        this.setPreferredSize(new Dimension(480 , 700));
        this.setFocusable(false);
        this.setBackground(Color.white);
        this.setLayout(null);


        startNewGameBT = new JButton("Start new Game");
        startNewGameBT.setBounds(40,50,180 , 50);
        startNewGameBT.setFont(new Font("Ink Free" , Font.BOLD , 20 ));
        startNewGameBT.setFocusable(false);
        startNewGameBT.setBorder(BorderFactory.createEtchedBorder());
        startNewGameBT.addActionListener(this);


        loadGame = new JButton("Load Game");
        loadGame.setBounds(260,50,180 , 50);
        loadGame.setFont(new Font("Ink Free" , Font.BOLD , 20 ));
        loadGame.setFocusable(false);
        loadGame.setBorder(BorderFactory.createEtchedBorder());
        loadGame.addActionListener(this);

        loadGameTx = new JTextField();
        loadGameTx.setBounds(260,150,100 , 50);
        loadGameTx.setFont(new Font("Ink Free" , Font.BOLD , 20 ));
        loadGameTx.setBorder(BorderFactory.createEtchedBorder());
        loadGameTx.setBackground(Color.lightGray);


        pause = new JButton("Pause");
        pause.setBounds(100,150,100 , 50);
        pause.setFont(new Font("Ink Free" , Font.BOLD , 20 ));
        pause.setFocusable(false);
        pause.setBorder(BorderFactory.createEtchedBorder());
        pause.addActionListener(this);
        pause.setEnabled(false);

        restart = new JButton("Restaer");
        restart.setBounds(100,250,100 , 50);
        restart.setFont(new Font("Ink Free" , Font.BOLD , 20 ));
        restart.setFocusable(false);
        restart.setBorder(BorderFactory.createEtchedBorder());
        restart.addActionListener(this);
        restart.setEnabled(false);




        saveGame = new JButton("save Game");
        saveGame.setBounds(250,250,180 , 50);
        saveGame.setFont(new Font("Ink Free" , Font.BOLD , 15 ));
        saveGame.setFocusable(false);
        saveGame.setBorder(BorderFactory.createEtchedBorder());
        saveGame.addActionListener(this);
        saveGame.setEnabled(false);

        nameTX = new JTextField();
        nameTX.setBorder(BorderFactory.createEtchedBorder());
        nameTX.setBounds(250 , 320,180,50);
        nameTX.setBackground(Color.lightGray);
        nameTX.setFont(new Font("Ink Free" , Font.BOLD , 20 ));
        nameTX.setEnabled(false);

        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        textArea = new JTextArea();
        textArea.setFocusable(false);
        textArea.setBounds(40,420,180 , 200);
        textArea.setFont(new Font("MV Boli" , Font.BOLD , 20 ));
        textArea.setBorder(BorderFactory.createLineBorder(Color.gray , 4));
        textArea.setBackground(Color.white);
        //textArea.setEditable(false);


        scrollPane.setBounds(40,420,180,200);
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.getViewport().add(textArea);
        this.add(scrollPane);
        this.add(startNewGameBT);
        this.add(saveGame);
        this.add(nameTX);
        this.add(pause);
        this.add(restart);
        this.add(loadGame);
        this.add(loadGameTx);

        sort();
        textAreaUpdate();

    }

    public void sort(){
        for (int i=0 ; i<GameFrame.score.size() ; i++){
            for(int j=i+1 ; j<GameFrame.score.size() ; j++ ){
                if(GameFrame.score.get(i)<GameFrame.score.get(j)){
                    int tmp = GameFrame.score.get(j);
                    GameFrame.score.set(j , GameFrame.score.get(i));
                    GameFrame.score.set(i , tmp);
                    String stmp = GameFrame.name.get(j);
                    GameFrame.name.set(j , GameFrame.name.get(i));
                    GameFrame.name.set(i , stmp);
                }
            }
        }
    }
    public void ScoreSave(){
        try {
            FileWriter fr = new FileWriter("tex/score.txt");
            fr.write((GameFrame.score.size()+"\n"));
            for(int i=0 ; i<GameFrame.score.size() ; i++){
                fr.write(GameFrame.score.get(i)+"\n");
            }
            fr.close();

        } catch (IOException er) {
            er.printStackTrace();
        }
        try {
            FileWriter fr = new FileWriter("tex/name.txt");
            fr.write((GameFrame.name.size()+"\n"));
            for(int i=0 ; i<GameFrame.name.size() ; i++){
                fr.write(GameFrame.name.get(i)+"\n");
            }
            fr.close();

        } catch (IOException er) {
            er.printStackTrace();
        }

    }
    public void textAreaUpdate(){
        textArea.setText("");
        for (int i=0 ; i<GameFrame.score.size() ;i++){
            textArea.append(GameFrame.name.get(i)+" : "+GameFrame.score.get(i)+"\n");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==saveGame){
            String username=nameTX.getText();
            if (username.equals("")){
                JOptionPane.showMessageDialog(null , "you have to choose name!" , "" , JOptionPane.WARNING_MESSAGE);
            }
            else{
                boolean check=false;
                nameTX.setEnabled(false);
                for(int i=0 ; i<GameFrame.name.size() ; i++){
                    if(GameFrame.name.get(i).equals(username)){
                        check=true;
                        GameFrame.score.set(i , GamePanel.score);
                    }
                }
                if(!check) {
                    GameFrame.name.add(username);
                    GameFrame.score.add(GamePanel.score);
                }
                ScoreSave();
                sort();
                textAreaUpdate();
                username= "saved\\\\" + username  + ".txt";
                gamePanel.SavedDate(username);
            }
        }



        if (e.getSource()==startNewGameBT){
            GameFrame.gamePanel = new GamePanel("tex/StartGame.txt");
            gamePanel.jlabel.setVisible(false);
            gamePanel.thread=new Thread(()->{
                while (gamePanel.inGame){
                    gamePanel.update();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                }

            });
            gamePanel.thread.start();
            gamePanel.inGame=true;
            startNewGameBT.setEnabled(false);
            pause.setEnabled(true);
            restart.setEnabled(false);
            loadGame.setEnabled(false);
            loadGameTx.setEnabled(false);
            saveGame.setEnabled(false);
            nameTX.setEnabled(false);
        }


        if(e.getSource()==pause){
            if(gamePanel.inGame){
                startNewGameBT.setEnabled(false);
                loadGame.setEnabled(false);
                loadGameTx.setEnabled(false);
                nameTX.setEnabled(true);
                saveGame.setEnabled(true);
                pause.setText("Resume");
                gamePanel.inGame=false;
                gamePanel.thread.stop();
                restart.setEnabled(true);
            }
            else{
                if(gamePanel.endGame){
                    restart.setEnabled(true);
                    saveGame.setEnabled(true);
                    nameTX.setEnabled(true);
                    pause.setEnabled(false);
                    pause.setText("pause");
                    startNewGameBT.setEnabled(false);
                    loadGameTx.setEnabled(false);
                    loadGameTx.setEnabled(false);



                }else{
                    startNewGameBT.setEnabled(false);
                    restart.setEnabled(false);
                    nameTX.setEnabled(false);
                    saveGame.setEnabled(false);
                    loadGame.setEnabled(false);
                    loadGameTx.setEnabled(false);

                    pause.setText("Pause");
                    gamePanel.thread=new Thread(()->{
                        while (gamePanel.inGame){
                            gamePanel.update();
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException err) {
                                err.printStackTrace();
                            }
                        }

                    });
                    gamePanel.thread.start();
                    gamePanel.inGame=true;

                }

            }

        }

        if (e.getSource()==restart){

            loadGame.setEnabled(true);
            loadGameTx.setEnabled(true);
            nameTX.setEnabled(false);
            saveGame.setEnabled(false);
            startNewGameBT.setEnabled(true);
            restart.setEnabled(false);
            pause.setEnabled(false);
        }


        if(e.getSource()==loadGame){
            String name = loadGameTx.getText();
            if(name.equals("")){
                JOptionPane.showMessageDialog(null , "you have to choose name of your game!" , "" , JOptionPane.WARNING_MESSAGE);
            }
            else {
                boolean check = false;
                for (int i = 0; i < GameFrame.name.size(); i++) {
                    if (GameFrame.name.get(i).equals(name)) check = true;
                }
                if (!check) {
                    JOptionPane.showMessageDialog(null, "this game doesnt exist!", "", JOptionPane.WARNING_MESSAGE);
                }
                if (check) {

                    name = "saved\\\\" + name + ".txt";

                    GameFrame.gamePanel = new GamePanel(name);
                    if(!GamePanel.endGame) {
                        gamePanel.jlabel.setVisible(false);
                        gamePanel.thread = new Thread(() -> {
                            while (gamePanel.inGame) {
                                gamePanel.update();
                                try {
                                    Thread.sleep(5);
                                } catch (InterruptedException err) {
                                    err.printStackTrace();
                                }
                            }

                        });
                        gamePanel.thread.start();
                        gamePanel.inGame = true;
                        saveGame.setEnabled(false);
                        nameTX.setEnabled(false);
                        startNewGameBT.setEnabled(false);
                        pause.setEnabled(true);
                        restart.setEnabled(false);
                        loadGame.setEnabled(false);
                        loadGameTx.setEnabled(false);
                    }
                    else{
                        gamePanel.jlabel.setVisible(true);
                        gamePanel.update();
                        saveGame.setEnabled(false);
                        nameTX.setEnabled(false);
                        startNewGameBT.setEnabled(false);
                        pause.setEnabled(false);
                        restart.setEnabled(true);
                        loadGame.setEnabled(false);
                        loadGameTx.setEnabled(false);
                    }
                }
            }
        }
    }
}
