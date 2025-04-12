import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {
    public static void main(String[] args) {
        try {
            // Connect to server
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server!");
            
            // Set up scanner for user input
            Scanner scanner = new Scanner(System.in);
            
            // Set up output stream to send data to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            // Ask for name
            System.out.print("Enter name: ");
            String userInput = scanner.nextLine();
            
            // Send name to server
            out.println(userInput);
            System.out.println("Name sent to server!");
            
            // Close resources
            scanner.close();
            out.close();
            socket.close();
            
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}