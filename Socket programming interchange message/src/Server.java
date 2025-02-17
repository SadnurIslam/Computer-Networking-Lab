import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

    public static void main(String[] args) throws Exception{

        ServerSocket ss= new ServerSocket(7777);
        Socket s1=ss.accept();

        BufferedReader input = new BufferedReader(new InputStreamReader(s1.getInputStream()));
        PrintWriter output = new PrintWriter(s1.getOutputStream(), true);

        System.out.println("Connected to " + s1.getInetAddress());

        String message;
        while(true){
            message = input.readLine();
            System.out.println("Client: " + message);
            String reply = "";
            if(message.equalsIgnoreCase("quit")){
                break;
            }
            else if(message.equalsIgnoreCase("hi")){
                reply="hello";
                output.println(reply);
            }
            else if(message.equalsIgnoreCase("ok")){
                reply ="ok";
                for(int i=1;i<=9;i++){
                    output.println(reply);
                }
            }
            else if(message.equalsIgnoreCase("date")){
                Date date=new Date();
                reply=date.toString();
                output.println(reply);
            }
            else{
                reply="unknown command";
                output.println(reply);
            }
            System.out.println("Server: "+reply);

        }
        ss.close();
        s1.close();

    }


}