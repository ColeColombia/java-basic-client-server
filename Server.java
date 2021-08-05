//server creation practice
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;


public class Server{

//server socket
private ServerSocket listener;

//client Socket
private Socket client;

//create instance of the server
public Server(){
  try{
    listener = new ServerSocket(12345, 10);
  }
  catch(IOException ioe){
    System.out.println("IO Exception: " + ioe.getMessage());
  }
}

public void listen(){
  try{
    System.out.println("Server is listening");
    client = listener.accept();
    System.out.println("Now about to process the client");
    processClient();
  }
  catch(IOException ioe){
    System.out.println("IO Exception " + ioe.getMessage());
  }
}

public void processClient(){
  //communicate with the CLIENT
  //initiate channels
  try{
    ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
    out.flush();
    ObjectInputStream input = new ObjectInputStream(client.getInputStream());

    //Communicate
    String msg = (String)input.readObject();
    System.out.println("Message from client " + msg);
    out.writeObject("Hellow " + msg);
    out.flush();

    //close connection
    out.close();
    input.close();
    client.close();
  }
  catch(IOException ioe){
    System.out.println("IO Exception: " + ioe.getMessage());
  }
  catch (ClassNotFoundException cnfe)
  {
      System.out.println("Class not found: " + cnfe.getMessage());
  }
}

public static void main(String[] args){
  //start SERVER
  Server server = new Server();
  server.listen();
}

}
