package SnakeGameCourse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Closeable {
    
    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;
    
    public Server(String ip, int port){
        try{
        this.socket = new Socket(ip, port);
        this.reader = createReader();
        this.writer = createWriter();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    
    public Server(ServerSocket server){
        try{
        this.socket = server.accept();
        this.reader = createReader();
        this.writer = createWriter();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    
    
    public void write (String message){
      try{
        writer.write(message);
        writer.newLine();
        writer.flush();
    }catch (IOException e){
        throw new RuntimeException(e);
    }
  }
    
    
   public String readline (){
      try{
        return reader.readLine();
    } catch (IOException e){
        throw new RuntimeException(e);
    }
  }
   

    private BufferedReader createReader() {
       try{
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }catch (IOException e){
        throw new RuntimeException(e);
    }
  }
    
   private BufferedWriter createWriter() {
       try{
        return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }catch (IOException e){
        throw new RuntimeException(e);
    }
  }

    @Override
    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }

    String readLine() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void writeLine(String response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
