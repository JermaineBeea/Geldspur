import java.io.*;
import java.net.*;

class Server {
    // Default port
    private static final int PORT = 5000;
    
    public static void main(String[] args) {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);
            System.out.println("Waiting for client...");
            
            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");
            
            // Set up input stream to receive data from client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            // Read the name sent by client
            String username = in.readLine();
            
            // Print welcome messages
            System.out.println("Hello " + username + "!");
            System.out.println("Welcome to the server!");
            System.out.println("You are now connected to the server.");
            
            // Close resources
            in.close();
            clientSocket.close();
            serverSocket.close();
            
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}