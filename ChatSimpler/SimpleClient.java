
import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        // Get server IP from command line or use localhost as default
        String serverIP = "localhost";
        if (args.length > 0) {
            serverIP = args[0];
        }
        
        int port = 5000;
        
        System.out.println("Attempting to connect to " + serverIP + " on port " + port);
        try (Socket socket = new Socket(serverIP, port)) {
            System.out.println("Connected to server: " + socket.getInetAddress().getHostAddress());
            
            // Create reader for server messages
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Create writer to send messages
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            // Create reader for keyboard input
            BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));

            // Get client name from user
            System.out.print("Enter your name: ");
            String clientName = keyboardIn.readLine();
            out.println(clientName);
            System.out.println("Welcome " + clientName + "! You are now connected to the server.");
            
            // Thread to read messages from server
            new Thread(() -> {
                try {
                    String message;
                    while ((message = serverIn.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server");
                    System.exit(0);
                }
            }).start();
            
            // Main thread reads from keyboard and sends to server
            String userInput;
            while ((userInput = keyboardIn.readLine()) != null) {
                out.println(userInput);
            }
        }
    }
}