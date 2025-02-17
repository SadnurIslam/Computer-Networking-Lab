import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception{

        Socket s2= new Socket("localhost", 7777);

        BufferedReader input = new BufferedReader(new InputStreamReader(s2.getInputStream()));
        PrintWriter output = new PrintWriter(s2.getOutputStream(), true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Connected to the server. Type 'quit' to disconnect.");

        String message="";
        while(true){
            System.out.print("Client: ");
            message = userInput.readLine();
            output.println(message);
            output.flush();
            if(message.equals("quit")){
                break;
            }
            else if(message.equals("ok")){
                for(int i=1;i<=9;i++){
                    String response = input.readLine();
                    System.out.println("Server: " + response);
                }
            }
            else{
                String response = input.readLine();
                System.out.println("Server: " + response);
            }

        }
        s2.close();
    }
}