

import java.util.Scanner;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

class ReadThread1 extends Thread {
	Socket socket;
	String userName;
	


	ReadThread1(Socket socket, String userName) {
		this.userName = userName;
		this.socket = socket;
		this.start();
	}

	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message;
			
			while ((message = br.readLine()) != null) {
						 String[] parts = message.split(": ", 2); // Split the message into username and content
                if (parts.length == 2) {
                    String senderUserName = parts[0];
                    String messageContent = parts[1];
			
				System.out.println(senderUserName + ": " + messageContent);
			}
		}
				
			System.out.println(userName + " has disconnected."); 
	

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}

public class ChatServer {
	 public final static int port = 1300;

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(port);//fixed address
			System.out.println("Waiting for client");
			Socket socket = server.accept();
			System.out.println("Client connected from " + socket.getInetAddress() + ":" + socket.getPort());
			            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your username: ");
            String userName = scanner.nextLine();

			ReadThread1 rt = new ReadThread1(socket, userName);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			PrintStream ps = new PrintStream(socket.getOutputStream());
			String message;
			System.out.println("Please enter message");
			while ((message = br.readLine()) != null) {
				if (message.equals("/q")) {
					break;
				}
				ps.println(userName + ": " + message);
			}
			ps.flush();
			socket.close();
		} catch (IOException e) {
			 System.err.println("Couldn't start server");
		}
	}
}