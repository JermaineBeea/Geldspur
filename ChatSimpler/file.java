import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

class Server
{

	private static final int serverPort = 1100;
	private static final String serverIp = getServerIp();
	
	public static void main(String[] args) throws IOException
	{
		// Establish Server connection.
		
		ServerSocket serversocket = new ServerSocket(serverPort, 50, InetAddress.getByName("0,0,0,0"));
		System.out.println("Connection established to Port " + serverPort);
		System.out.println("Connected to Server Ip " + serverIp);
		System.out.println("Waiting for client connections...");
		
		
		// Set up client connections to Server.
		
		Socket client1 = serversocket.accept();
		String client1IP = client1.getInetAddress().getHostAddress();
		System.out.println("Client1 connected from: " + client1IP);
		System.out.println("Waiting for Client2...");
		
		Socket client2 = serversocket.accept();
		String client2IP = client2.getInetAddress().getHostAddress();
		System.out.println("Client2 connected from: " + client2IP);
		
		// Set up input and output streams for clients.
		
		BufferedReader inClient1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
		BufferedReader inClient2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
		
		PrintWriter outClient1 = new PrintWriter(client1.getOutputStream(), true);
		PrintWriter outClient2 = new PrintWriter(client2.getOutputStream(), true);
		
		// Inform clients of connection.
		
		outClient1.println("Connection established, send messages to client2");
		outClient2.println("Connection established, send messages to client1");
		
		// Create Threads for reiciving and sending messages.
		
		createThread("Client1", inClient1, outClient2);
		createThread("Client2", inClient2, outClient1);
		
	}
	
	private static String getServerIp()
	{
		try
		{
			Socket socket = new Socket("8,8,8,8", 53);
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
				return "Unable to find Host Address!";
			}
		}
	}
	
	private static void createThread(String clientname, BufferedReader inClient, PrintWriter outClient)
	{
		new Thread(()-> 
		{
			try
			{
				String message;
				
				while((message = inClient.readLine()) != null)
				{	
					// Server log of message.
					System.out.println(clientname + " sent: " + message);
					
					// Send message to other client.
					outClient.println("Message from " + clientname + ": " + message);
					
				}
			}
			catch(IOException e)
			{
				System.out.println("Connection lost from " + clientname);
			}
		
		}).start();
	}
}