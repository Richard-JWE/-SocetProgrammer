 Java NetworkSocketProgrammerChatServer ReadMe

The outcome of this Java application is to communicate between nodes, a chat server, and a chat client.

Chat Server functionality
Connection Handling: Listens for incoming connections from clients.
Client Registration: Accepts clients and prompts for usernames.
Message Broadcasting: Relays messages received from one client to all other connected clients.
Disconnection Handling: Detects when a client disconnects and notifies other clients.

Chat Client functionality
Connection Establishment: Connects to the chat server to participate in the chat.
Username Assignment: Allows users to choose their usernames upon connection.
Message Sending/Receiving: Enables users to send messages to the server and receive messages from other connected users.



Exiting the Chat: Allows users to quit the chat. Type "/q" to quit the chat.

Compile the Code: Compile both ChatServer.java and ChatClient.java using a Java compiler.
 Run the Server: Start the chat server by running the ChatServer class with the command: javac ChatServer.java




Run the Client: Launch the chat client by executing the ChatClient class with the command: javac ChatClient.java

Server Address and Port: The client connects to the server using the specified server address and port address.
Username Entry: Upon connecting, the client will be asked for a username to be known as in the application.
Sending Messages: Type messages in the client's command line interface to send them to the server. Type "/q" to exit the chat.

