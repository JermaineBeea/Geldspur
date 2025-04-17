package Chat;

import java.io.*;
import java.net.*;
// import java.util.*;

@SuppressWarnings("all")
public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 9876;
    
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected to server");
            
            // Thread for reading messages from server
            new Thread(new MessageReader(socket)).start();
            
            // Send messages from console
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            String userInput;
            while ((userInput = consoleReader.readLine()) != null) {
                out.println(userInput);
                
                if (userInput.equals("/quit")) {
                    break;
                }
            }
            
            socket.close();
            
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
    
    private static class MessageReader implements Runnable {
        private Socket socket;
        private BufferedReader in;
        
        public MessageReader(Socket socket) {
            this.socket = socket;
            try {
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                System.out.println("Error getting input stream: " + e.getMessage());
            }
        }
        
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Error reading from server: " + e.getMessage());
            }
        }
    }
}