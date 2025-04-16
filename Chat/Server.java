package Chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 9876;
    private static HashMap<String, PrintWriter> clientWriters = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Chat Server starting on port " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                
                // Create a new thread to handle this client
                Thread handler = new Thread(new ClientHandler(clientSocket));
                handler.start();
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                // Set up input and output streams
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                // Get client name
                out.println("Enter your name:");
                clientName = in.readLine();
                System.out.println(clientName + " has joined");
                
                // Add this client to the map
                synchronized (clientWriters) {
                    clientWriters.put(clientName, out);
                }
                
                // Broadcast to all clients that a new user has joined
                broadcast(clientName + " has joined the chat");
                
                // Process messages from this client
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equals("/quit")) {
                        break;
                    }
                    broadcast(clientName + ": " + message);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                // Client is leaving
                if (clientName != null) {
                    System.out.println(clientName + " is leaving");
                    synchronized (clientWriters) {
                        clientWriters.remove(clientName);
                    }
                    broadcast(clientName + " has left the chat");
                }
                
                try {
                    socket.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
        }
        
        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters.values()) {
                    writer.println(message);
                }
            }
        }
    }
}

