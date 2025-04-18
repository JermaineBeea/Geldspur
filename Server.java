import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.io.IOException;

class Server{

    private static final int serverPort = 9000;
    private static final String serverIP = getServerIP();

    public static void main(String[] args)
    {
        try (ServerSocket serversocket = new ServerSocket(serverPort, 50, InetAddress.getByName("0.0.0.0")))
        {
            // Establish Server connection.
            System.out.println("Connected to ServerIp: " + serverIP);

            // Set client1 connections.
            Socket clientSocket1 = serversocket.accept();
            String Ipclient1 = clientSocket1.getInetAddress().getHostAddress();
            System.out.println("Client1 connected from: " + Ipclient1);
            System.out.println("Waiting for client2...");

            // Set client2 connections.
            Socket clientSocket2 = serversocket.accept();
            String Ipclient2 = clientSocket2.getInetAddress().getHostAddress();
            System.out.println("Client2 connected from: " + Ipclient2);

            // Set up Client input-output streams.
            BufferedReader fromClient1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
            BufferedReader fromClient2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));

            PrintWriter toClient1 = new PrintWriter(clientSocket1.getOutputStream(), true);
            PrintWriter toClient2 = new PrintWriter(clientSocket2.getOutputStream(), true);

            // Recieve client names.
            String client1Name = fromClient1.readLine();
            String client2Name = fromClient2.readLine();

            // Send Welcome messages to clients and send other client name.
            toClient1.println("Hello " + client1Name + ", welcome to the Server");
            toClient1.println(client2Name);

            toClient2.println("Hello " + client2Name + ", welcome to the Server");
            toClient2.println(client1Name);

            // Create Thread to send-recieve from client.
            createThread(client1Name, fromClient1, toClient2);
            createThread(client2Name, fromClient2, toClient1);
        }
        catch(IOException e)
        {
            System.out.println("Server Error");
        }
    }

    // Thread to recieve/send messages from/to client.
    private static void createThread(String clientname, BufferedReader fromClient, PrintWriter toClient)
    {
        new Thread(()-> {

            try
            {   String inMessage;
                while((inMessage = fromClient.readLine()) != null)
                {
                    // Print client messages to each other.
                    System.out.println(clientname + ": " + fromClient);

                    // Send message to other client.
                    toClient.println(inMessage);
                }
            }
            catch(IOException e)
            {
                System.out.println(clientname + ", has been disconnected");
            }

        }).start();
    }

    private static String getServerIP()
    {
        try
        {   // Find non-local IP address.
            try (Socket socket = new Socket("8.8.8.8", 53)) 
            {
                return socket.getLocalAddress().getHostAddress();
            }
        }
        catch(IOException e)
        {
            try
            {   // Find Loval Address.
                return InetAddress.getLocalHost().getHostAddress();
            }
            catch(UnknownHostException ex)
            {
                return "Could not find Server Ip";
            }
        }
    }
}