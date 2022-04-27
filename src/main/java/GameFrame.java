import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameFrame extends JFrame {
    public static GamePanel gamePanel;
    public Menu menu;
    public static ArrayList<String> name;
    public static ArrayList<Integer> score;
    public GameFrame(){

        name = new ArrayList<String>();
        score= new ArrayList<Integer>();


        try {
            File fl = new File("tex/name.txt");

            Scanner scanner = new Scanner(fl);
            int n  = scanner.nextInt();
            while (n>0){
                n--;
                String s=scanner.next();
                name.add(s);
            }
            scanner.close();
        } catch (FileNotFoundException er) {
            er.printStackTrace();
        }

        try {
            File fl = new File("tex/score.txt");
            Scanner scanner = new Scanner(fl);
            int n = scanner.nextInt();
            while (n>0){
                n--;
                int tmpsccore =scanner.nextInt();
                score.add(tmpsccore);
            }
            scanner.close();
        } catch (FileNotFoundException er) {
            er.printStackTrace();
        }


        gamePanel = new GamePanel("tex/StartGame.txt");
        menu = new Menu(gamePanel);
        this.setTitle("Arkanoid");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        this.setVisible(true);
       // this.setLayout(new BorderLayout());
        this.setLayout(null);
        this.setSize(1000,700);

        gamePanel.setBounds(0 , 0 , 500,700);


        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(20 , 700));
        panel.setFocusable(false);
        panel.setBackground(Color.darkGray);
        panel.setBounds(500 , 0,20,700);

        menu.setBounds(520,0,480,700);


        this.add(gamePanel );
        this.add(menu);
        this.add(panel );





    }




}