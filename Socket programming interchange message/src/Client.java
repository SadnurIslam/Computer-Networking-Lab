import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server (change IP if running on a different machine)
            String serverIP = "127.0.0.1"; // Replace with actual server IP if needed
            Socket socket = new Socket(serverIP, 12345);
            System.out.println("Connected to the server. Type 'exit' to disconnect.");

            // Create input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Communication loop
            String message;
            while (true) {
                System.out.print("Client: ");
                message = userInput.readLine();
                output.println(message);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                String response = input.readLine();
                System.out.println("Server: " + response);
            }

            // Close resources
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
