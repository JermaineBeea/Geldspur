
import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        // Define port
        int port = 5000;
        
        // Create server socket that accepts connections on all network interfaces
        ServerSocket serverSocket = new ServerSocket(port, 50, InetAddress.getByName("0.0.0.0"));
        System.out.println("Server started on port " + port);
        System.out.println("Server IP address: " + getServerIP());
        
        // Accept two clients
        Socket client1 = serverSocket.accept();
        System.out.println("Client 1 connected from: " + client1.getInetAddress().getHostAddress());
        
        Socket client2 = serverSocket.accept();
        System.out.println("Client 2 connected from: " + client2.getInetAddress().getHostAddress());
        
        // Create streams for both clients
        BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
        PrintWriter out1 = new PrintWriter(client1.getOutputStream(), true);
        
        BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
        PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true);

        String client1Name = in1.readLine();
        String client2Name = in2.readLine();
        System.out.println("Client 1 name: " + client1Name);
        System.out.println("Client 2 name: " + client2Name);
        // Send welcome messages to both clients
        // out1.println("Welcome " + client1Name + "! You are now connected to " + client2Name);
        // out2.println("Welcome " + client2Name + "! You are now connected to " + client1Name);
        
        // Tell clients they're connected
        out1.println("Connected to server. Start chatting with the other client.");
        out2.println("Connected to server. Start chatting with the other client.");
        
        // Create threads to handle messages
        new Thread(() -> 
        {
            try 
            {
                String message;
                while ((message = in1.readLine()) != null) 
                {
                    System.out.println("Client 1: " + message);
                    out2.println("Other client: " + message);
                }
            } 
            catch (IOException e) 
            {
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
    
    // Method to get server's IP address to display for connection info
    private static String getServerIP() {
        try {
            // This tries to find the non-localhost IP address
            try (Socket socket = new Socket("8.8.8.8", 53)) 
            {
                return socket.getLocalAddress().getHostAddress();
            }
        } catch (IOException e) 
        {
            // Fallback method if the above doesn't work
            try 
            {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException ex) 
            {
                return "Could not determine IP address. Use 'ipconfig' or 'ifconfig' to find it.";
            }
        }
    }
}

