package ChatSimpler;
// SimpleServer.java
import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started on port 5000");
        
        // Accept two clients
        Socket client1 = serverSocket.accept();
        System.out.println("Client 1 connected");
        
        Socket client2 = serverSocket.accept();
        System.out.println("Client 2 connected");
        
        // Create streams for both clients
        BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
        PrintWriter out1 = new PrintWriter(client1.getOutputStream(), true);
        
        BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
        PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true);
        
        // Tell clients they're connected
        out1.println("Connected to server. Start chatting with the other client.");
        out2.println("Connected to server. Start chatting with the other client.");
        
        // Create threads to handle messages
        new Thread(() -> {
            try {
                String message;
                while ((message = in1.readLine()) != null) {
                    System.out.println("Client 1: " + message);
                    out2.println("Other client: " + message);
                }
            } catch (IOException e) {
                System.out.println("Client 1 disconnected");
            }
        }).start();
        
        new Thread(() -> {
            try {
                String message;
                while ((message = in2.readLine()) != null) {
                    System.out.println("Client 2: " + message);
                    out1.println("Other client: " + message);
                }
            } catch (IOException e) {
                System.out.println("Client 2 disconnected");
            }
        }).start();
    }
}

