import java.net.Socket;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

class ClientClass{

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 9000;
    public static void main(String[] args)
    {
        try
        {
            // Establish Client connection to Server Address and Port.
            Socket clientconection = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to Address[Port]: " + SERVER_ADDRESS + "[" + SERVER_PORT + "]" );

            // Set up output-stream to Server.
            PrintWriter outputstream = new PrintWriter(clientconection.getOutputStream(), true);

            // Set up system scanner, to parse user input
            Scanner scanner = new Scanner(System.in);

            // Get client username
            System.out.println("Enter user-name below");
            String username = scanner.nextLine();

            // Send user-name to Server
            outputstream.println(username);
            System.out.println("Name sent to Sever!");

            // Close resources
            clientconection.close();
            outputstream.close();
            scanner.close();
        }
        catch(IOException e)
        {
            System.out.println("Server error " + e.getMessage());
        }
    }

}