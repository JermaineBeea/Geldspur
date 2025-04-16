package ChatSimpler;


// SimpleClient.java
import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Connected to server");
        
        // Create reader for server messages
        BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        // Create writer to send messages
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        // Create reader for keyboard input
        BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
        
        // Thread to read messages from server
        new Thread(() -> {
            try {
                String message;
                while ((message = serverIn.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Disconnected from server");
            }
        }).start();
        
        // Main thread reads from keyboard and sends to server
        String userInput;
        while ((userInput = keyboardIn.readLine()) != null) {
            out.println(userInput);
        }
    }
}