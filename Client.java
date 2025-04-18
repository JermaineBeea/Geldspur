import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;

class CLient{

    private static final int serverPort = 9000;

    public static void main(String[] args)
    {
        String serverIP = (args.length > 0) ? args[0] : "localhost";

        try(Socket serversocket = new Socket(serverIP, serverPort))
        {
        
        // Set up Server input-output streams.
        BufferedReader fromServer = new BufferedReader (new InputStreamReader(serversocket.getInputStream()));
        PrintWriter toServer = new PrintWriter(serversocket.getOutputStream(), true);

        // Set up client Keyboard input.
        BufferedReader keyInput = new BufferedReader(new InputStreamReader(System.in));

        // Get client Nname and send to Server.
        System.out.println("Enter name below");
        String clientname = keyInput.readLine();
        toServer.println(clientname);

        //Recieve welcome messages and other client name.
        fromServer.readLine();
        String otherclient = fromServer.readLine();

        // Create Thread fpr in-coming messages from Server.

        new Thread(()-> 
        {
            try
            {
                String inMessage;
                while((inMessage = fromServer.readLine()) != null)
                {
                    System.out.println(otherclient + ": " + inMessage);
                }
            }
            catch(IOException e)
            {
                System.out.println("Disconnected from Server");
                System.exit(0);
            }
        }).start();

        // Send out-going messages to Server.
        String outMessage;
        while((outMessage = keyInput.readLine()) != null)
        {
            toServer.println(outMessage);
        }

        }
        catch(IOException e)
        {
            System.out.println("Client Error!");
        }

    }
}