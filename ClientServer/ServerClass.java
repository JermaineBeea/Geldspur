import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class ServerClass{

    private static final int PORT = 9000;

    public static void main(String[] args)
    {
        try
        {
            // Establish Server connection
            ServerSocket serveconnection = new ServerSocket(PORT);
            System.out.println("Connect to Port: " + PORT);
            System.out.println("Waiting for client...");

            // Accept client connection
            Socket clientconnection = serveconnection.accept();
            System.out.println("Client Connected!");

            // Set up input-stream.
            BufferedReader inputstream = new BufferedReader(new InputStreamReader(clientconnection.getInputStream()));

            // Fetch username from client
            String username = inputstream.readLine();
            System.out.println("Username " + username + " retrieved from Client!");

            serveconnection.close();
            clientconnection.close();
            inputstream.close();
        }
        catch(IOException e)
        {
            System.out.println("Server error " + e.getMessage());
        }
    }
}