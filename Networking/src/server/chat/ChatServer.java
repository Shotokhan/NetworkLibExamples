package server.chat;

import java.util.HashMap;

import networking.*;

public class ChatServer extends ConcreteListener {

	private volatile HashMap<String, ClientHandler> activeUsers = new HashMap<String, ClientHandler>();
	private volatile int sequence = 0;
	
	public ChatServer() {
		// TODO Stub di costruttore generato automaticamente
	}

	public ChatServer(int port, ClientHandler clientHandler) {
		super(port, clientHandler);
		// TODO Stub di costruttore generato automaticamente
	}

	public HashMap<String, ClientHandler> getActiveUsers() {
		return activeUsers;
	}

	public void setActiveUsers(HashMap<String, ClientHandler> activeUsers) {
		this.activeUsers = activeUsers;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}
