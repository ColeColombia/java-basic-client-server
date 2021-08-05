
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Client{
  private Socket server;

  public Client(){

    try{
      server = new Socket("127.0.0.1", 12345);
    }
    catch(IOException ioe)
    {
      System.out.println("IOException " + ioe.getMessage());
    }
  }

  public void communicate(){
    try{
      ObjectOutputStream output = new ObjectOutputStream(server.getOutputStream());
      output.flush();
      ObjectInputStream input = new ObjectInputStream(server.getInputStream());

      //Communicate
      output.writeObject("Cole");
      output.flush();
      String response = (String)input.readObject();
      System.out.println("From server>> " + response);

      //close connection
      output.close();
      input.close();
      server.close();
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
    Client client = new Client();
    client.communicate();
  }

}
