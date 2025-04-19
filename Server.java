import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server{

    private static final String serverIP = getServerIp();
    private static final int serverPort = 3000;

    public static void main(String[] args)
    {
        try(ServerSocket serversocket = new ServerSocket(serverPort, 50, InetAddress.getByName("0.0.0.0")))
        {
            // Establish connection to Server.
            System.out.println("Connected to Server. Port[" + serverPort + "]| IP[" + serverIP + "]");
            System.out.println("Waiting for clients...");

            // Connect clients and establish input-output streams.
            Socket client1Socket = serversocket.accept();
            String IPclient1 = client1Socket.getInetAddress().getHostAddress();
            BufferedReader fromClient1 = new BufferedReader(new InputStreamReader(client1Socket.getInputStream()));
            PrintWriter toClient1 = new PrintWriter(client1Socket.getOutputStream(), true);

            Socket client2Socket = serversocket.accept();
            String IPclient2 = client2Socket.getInetAddress().getHostAddress();
            BufferedReader fromClient2 = new BufferedReader(new InputStreamReader(client2Socket.getInputStream()));
            PrintWriter toClient2 = new PrintWriter(client2Socket.getOutputStream(), true);
            
            // Log connection and send message to client
            System.out.println("Client 1 connected from: " + IPclient1);
            toClient1.println("Connection established to Server "+ serverIP);

            // Log connection and send message to client
            System.out.println("Client 2 connected from: " + IPclient2);
            toClient2.println("Connection established to Server"+ serverIP);

            // Recieve name from clients.
            String client1name = fromClient1.readLine();
            String client2name = fromClient2.readLine();

            // Send names to other clients;
            toClient1.println(client2name);
            toClient2.println(client1name);

            // Establish Thread to send/reciev messages form clients.
            clientThread(client1name, fromClient1, toClient2);
            clientThread(client2name, fromClient2, toClient1);
        }
        catch(IOException e)
        {
            System.out.println("Server error " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getServerIp()
    {
        try(Socket socket = new Socket("8.8.8.8", 53))
        {   
            // Get non-local address.
            return socket.getLocalAddress().getHostAddress();
        }
        catch(IOException e)
        {
            try
            {
                return InetAddress.getLocalHost().getHostAddress();
            }
            catch(UnknownHostException ex)
            {
                ex.printStackTrace();
                return "Cannot find serverIP";
            }
        }
    }

    private static void clientThread(String clientname, BufferedReader fromclient, PrintWriter toClient)
        {
            new Thread(()-> 
            {
                try
                {
                    String clientMessage;
                    while((clientMessage = fromclient.readLine()) != null)
                    {
                        // Print messages from client.
                        System.out.println(clientname + ": " + clientMessage);

                        // Send message to other client
                        toClient.println(clientMessage);
                    }
                }
                catch(IOException e)
                {
                    System.out.println("Server thread error" + e.getMessage());
                    e.printStackTrace();
                }
            }).start();
        }

}