package SnakeGameCourse;
import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.*;
/**
 * Created by infuntis on 15/01/17.
 */
public class MainWindow extends JFrame {

    public MainWindow(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(525,550);
        setLocation(600,300);
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        
        MainWindow mw = new MainWindow();
         
        //Server
        try(ServerSocket server = new ServerSocket(8000);){
       
            System.out.println("Server started!");
        
            while(true)
                try(Server record = new Server(server)){
                    
                    new Thread(() -> {
                        System.out.println("multithreading");
                    }).start();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
                   
               
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
     
    }

