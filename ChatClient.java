

import java.net.Socket;
import java.util.Scanner;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.IOException;


class ReadThread extends Thread {
	Socket socket;
	Scanner scanner;
	String userName;
	int port = 1300;

	ReadThread(Socket socket, String userName) {
		this.userName = userName;
		this.socket = socket;
		this.start();
	}

	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String message;
			

			while ((message = br.readLine()) != null) {
				 String[] parts = message.split(": ", 2); // Split the message into username and message content
                if (parts.length == 2) {
                    String senderUserName = parts[0]; //assigns the sender user name
                    String messageContent = parts[1]; //assigns message content
			
				System.out.println(senderUserName + ": " + messageContent);// prints user name + the message the user entered
			}
		}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}


public class ChatClient {
	public static void main(String[] args) {
		try {
			InetAddress inet = InetAddress.getLocalHost();
			Socket socket = new Socket(inet, 1300);//connected to the fixed address, might change and see available ports
			System.out.println("Connected...");
			     Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your username: ");
            String userName = scanner.nextLine();
			ReadThread rt = new ReadThread(socket, userName);
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
			socket.close();
		}

		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}