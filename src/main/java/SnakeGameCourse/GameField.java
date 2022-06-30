package SnakeGameCourse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GameField extends JPanel implements ActionListener{
    private final int SIZE = 480;
    private final int DOT_SIZE = 24;
    private final int ALL_DOTS = 480;
    private Image dot;
    private Image apple;
    private Image tree1;
    private Image tree2;
    private Image tree3;
    private Image tree4;
    private Image castle;
    private Image castle1;
    private int speed = 150;
    private int forR;
    private int Record;
    private int appleX;
    private int appleY;
    private int tree1X;
    private int tree1Y;
    private int tree2X;
    private int tree2Y;
    private int tree3X;
    private int tree3Y;
    private int tree4X;
    private int tree4Y;
    private int castle1X;
    private int castle1Y;
    private int castleX;
    private int castleY;
    private int apple11;
    private int tree11;
    private int tree22;
    private int tree33;
    private int tree44;
    private int castle11;
    private int castle12;
    private int score;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    public static Audio khit;
    public static Audio hit;
    public static Audio ost;
    
    
    public GameField(){
        setBackground(Color.black);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }

    public void initGame(){
        khit = new Audio("res/khit.wav", 0.75);
        hit = new Audio("res/hit.wav", 0.75);
        ost = new Audio("res/ost.wav", 0.7);
        dots = 1;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i*DOT_SIZE;
            y[i] = 48;
        }
        
        if(inGame){
        ost.play();
        ost.setVolume();
        }
        
        timer = new Timer(speed,this);
        timer.start();
        createApple();
        createTree1();
        createTree2();
        createTree3();
        createTree4();
        createCastle();
        createCastle1();
        
    }
    

    public void createApple(){
        
        if (apple11 != (tree11 | tree22 | tree33 | tree44 | castle11 | castle12)){
            appleX = new Random().nextInt(20)*DOT_SIZE;
            appleY = new Random().nextInt(20)*DOT_SIZE;
            apple11 = appleX + appleY;
            }
     }
    
    public void createTree1(){
        tree1X = new Random().nextInt(20)*DOT_SIZE;
        tree1Y = new Random().nextInt(20)*DOT_SIZE;
        tree11 = tree1X + tree1Y;
    }
    public void createTree2(){
        tree2X = new Random().nextInt(20)*DOT_SIZE;
        tree2Y = new Random().nextInt(20)*DOT_SIZE;
        tree22 = tree2X + tree2Y;
    }
    public void createTree3(){
        tree3X = new Random().nextInt(20)*DOT_SIZE;
        tree3Y = new Random().nextInt(20)*DOT_SIZE;
        tree33 = tree3X + tree3Y;
    }
    public void createTree4(){
        tree4X = new Random().nextInt(20)*DOT_SIZE;
        tree4Y = new Random().nextInt(20)*DOT_SIZE;
        tree44 = tree4X + tree4Y;
    }
    public void createCastle(){
        castleX = new Random().nextInt(20)*DOT_SIZE;
        castleY = new Random().nextInt(20)*DOT_SIZE;
        castle11 = castleX + castleY; 
    }
     public void createCastle1(){
        castle1X = new Random().nextInt(20)*DOT_SIZE;
        castle1Y = new Random().nextInt(20)*DOT_SIZE;
        castle12 = castle1X + castle1Y; 
    }
    
    
    public void loadImages(){
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
        ImageIcon iif = new ImageIcon("tree1.png");
        tree1 = iif.getImage();
        ImageIcon iig = new ImageIcon("tree2.png");
        tree2 = iig.getImage();
        ImageIcon iih = new ImageIcon("tree3.png");
        tree3 = iih.getImage();
        ImageIcon iij = new ImageIcon("tree4.png");
        tree4 = iij.getImage();
        ImageIcon iic = new ImageIcon("castle.png");
        castle = iic.getImage();
        ImageIcon iiu = new ImageIcon("castle1.png");
        castle1 = iiu.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            String str1 = "SCORE: " + score;
            g.setColor(Color.white);
            g.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
            g.drawString(str1,1,25);
            g.drawImage(apple,appleX,appleY,this);
            g.drawImage(tree1,tree1X,tree1Y,this);
            g.drawImage(tree2,tree2X,tree2Y,this);
            g.drawImage(tree3,tree3X,tree3Y,this);
            g.drawImage(tree4,tree4X,tree4Y,this);
            g.drawImage(castle,castleX,castleY,this);
            g.drawImage(castle1,castle1X,castle1Y,this);
            for (int i = 0; i < dots; i++) {
            g.drawImage(dot,x[i],y[i],this);
            }
        } 
        if(inGame == false){
            
           /*//Client
         try (Server record = new Server("127.0.0.1", 8000)){
             System.out.println("thread");
              
             if (forR < score){
                forR = score;
            }
             try {
                FileInputStream fis = new FileInputStream("Record.txt");
                Scanner sc = new Scanner(fis);
                while(sc.hasNext()) {
                    Record = sc.nextInt();
                } 
            }catch (FileNotFoundException ex) {
                Logger.getLogger(GameField.class.getName()).log(Level.SEVERE, null, ex);
                }
             if (Record < forR){
                try (FileWriter fileWriter = new FileWriter("Record.txt")) {
                     fileWriter.write(String.valueOf(forR));
                     fileWriter.flush();
    
            }catch (IOException ex) {
             Logger.getLogger(GameField.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } catch (IOException e){
            e.printStackTrace();
          }*/
         
             if (forR < score){
                forR = score;
            }
           
            try {
                FileInputStream fis = new FileInputStream("Record.txt");
                Scanner sc = new Scanner(fis);
                while(sc.hasNext()) {
                    Record = sc.nextInt();
                } 
            }catch (FileNotFoundException ex) {
                Logger.getLogger(GameField.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            if (Record < forR){
                try (FileWriter fileWriter = new FileWriter("Record.txt")) {
                     fileWriter.write(String.valueOf(forR));
                     fileWriter.flush();
    
            }catch (IOException ex) {
             Logger.getLogger(GameField.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            String str = Stream.of("GAME"," ","OVER").collect(Collectors.joining());
            g.setColor(Color.red);
            g.setFont(new Font("Palatino Linotype", Font.BOLD, 24));
            g.drawString(str,180,SIZE/2);
            String str1 = "SCORE: " + score;
            g.setColor(Color.white);
            g.setFont(new Font("Palatino Linotype", Font.BOLD, 24));
            g.drawString(str1,190,450);
            String str2 = "RECORD: " + Record;
            g.setColor(Color.white);
            g.setFont(new Font("Palatino Linotype", Font.BOLD, 24));
            g.drawString(str2,190,490);
        }
      }
  
        
    public void move(){
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(left){
            x[0] -= DOT_SIZE;
        }
        if(right){
            x[0] += DOT_SIZE;
        } if(up){
            y[0] -= DOT_SIZE;
        } if(down){
            y[0] += DOT_SIZE;
        }
    }

    public void checkApple(){
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            score += 10;
            khit.sound();
            khit.setVolume();
            createApple();
        }
    }

    public void checkCollisions(){
        for (int i = dots; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                hit.sound();
                hit.setVolume();
                ost.stop();
                inGame = false;  
            }
        }

        if(x[0]>SIZE){
            hit.sound();
            hit.setVolume();
            ost.stop();
            inGame = false;
        }
        if(x[0]<0){
            hit.sound();
            hit.setVolume();
            ost.stop();
            inGame = false;
        }
        if(y[0]>SIZE){
            hit.sound();
            hit.setVolume();
            ost.stop();
            inGame = false;
        }
        if(y[0]<0){
            hit.sound();
            hit.setVolume();
            ost.stop();
            inGame = false;
        }
        if(x[0] == tree1X && y[0] == tree1Y){
            hit.sound();
            hit.setVolume();
            ost.stop();
            inGame = false;
        }
        if(x[0] == tree4X && y[0] == tree4Y){
            hit.sound();
            hit.setVolume();
            ost.stop();
            inGame = false;
        }
        if(x[0] == tree2X && y[0] == tree2Y){
            hit.sound();
            hit.setVolume();
            ost.stop();
            inGame = false;
        }
        if(x[0] == tree3X && y[0] == tree3Y){
            hit.sound();
            hit.setVolume();
            ost.stop();
            inGame = false;
        }
        if(x[0] == castleX && y[0] == castleY){
            hit.sound();
            hit.setVolume();
            ost.stop();
            inGame = false;
        }
        if(x[0] == castle1X && y[0] == castle1Y){
            hit.sound();
            hit.setVolume();
            ost.stop();
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checkCollisions();
            move();

        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
                right = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
                left = false;
            }

            if(key == KeyEvent.VK_UP && !down){
                right = false;
                up = true;
                left = false;
                down = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                right = false;
                down = true;
                left = false;
                up = false;
            }
        }
    }
}
    
    
    
 

    
    
            
        
    

