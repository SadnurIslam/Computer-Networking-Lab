import java.io.*;
import java.net.*;

public class Server1 {
    public static void main(String[] args) {
        try {
            // Create a server socket listening on port 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is waiting for a client...");

            // Accept client connection
            Socket socket = serverSocket.accept();
            System.out.println("Connected to " + socket.getInetAddress());

            // Create input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Communication loop
            String message;
            while (true) {
                message = input.readLine();
                if (message == null || message.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Client: " + message);

                // Send response
                System.out.print("Server: ");
                BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
                String reply = serverInput.readLine();
                output.println(reply);
            }

            // Close resources
            input.close();
            output.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
