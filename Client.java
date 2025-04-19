import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Client{

    private static final int serverPort = 3000;

    public static void main(String[] args)
    {
        String serverIP = (args.length > 0) ? args[0] : "localhost";

        try(Socket serversocket = new Socket(serverIP, serverPort))
        {
            // Establish input-output stream from-to Server.
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(serversocket.getInputStream()));
            PrintWriter toServer = new PrintWriter(serversocket.getOutputStream(), true);

            // Set up input from user Console(Keyboard) input.
            BufferedReader keyInput = new BufferedReader(new InputStreamReader(System.in));

            // Recieve message from Server that connection has been established.
            System.out.println(fromServer.readLine());

            // Send client name to Server.
            System.out.println("Enter Name below");
            String clientname = keyInput.readLine();
            toServer.println(clientname);

            // Recieve other client name.
            String otherclient = fromServer.readLine();
            System.out.println("You are connected to " + otherclient);
            System.out.println("You can Begin to send messages to " + otherclient);

            // Establish Thread to Recieve message from Server
            new Thread(()-> 
            {
                try
                {
                    String serverMessage;
                    while((serverMessage = fromServer.readLine()) != null)
                    {   
                        // Recieve message from other client.
                        System.out.println(otherclient + ": " + serverMessage);
                    }
                }
                catch(IOException e)
                {
                    System.out.println("You have been disconnected from Server");
                    toServer.println(clientname + " has been disconnected from Server");
                    e.printStackTrace();
                    System.exit(0);
                }
            }).start();

            // Send client message to Server.
            String clientMessage;
            try
            {
                while((clientMessage = keyInput.readLine()) != null)
                {
                    toServer.println(clientMessage);
                }
            }
            catch(IOException e)
            {
                System.out.println("You have been disconnected from Server");
                toServer.println(clientname + " has been disconnected from Server");
                e.printStackTrace();
                System.exit(0);
            }

        }
        catch(IOException e)
        {
            System.out.println("Client error " + e.getMessage());
            e.printStackTrace();
        }
    }
}