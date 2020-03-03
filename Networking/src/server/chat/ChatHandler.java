package server.chat;

import networking.ClientHandler;

public class ChatHandler extends ClientHandler {
	
	private ChatServer server;
	
	public ChatHandler() {
		// TODO Stub di costruttore generato automaticamente
	}

	public ChatHandler(ClientHandler other) {
		super(other);
		this.copy(other);
	}

	@Override
	public void copy(ClientHandler other) {
		ChatHandler handler = (ChatHandler) other;
		this.setServer(handler.getServer());

	}

	@Override
	public void run() {
		Registration registration = new Registration(this);
		registration.start();
		try {
			registration.join();
		} catch (InterruptedException e1) {
			System.out.println("Registration was interrupted: " + e1.getMessage());
		}
		ChatMsgReceiver receiver = new ChatMsgReceiver(this);
		receiver.start();
		try {
			receiver.join();
		} catch (InterruptedException e) {
			System.out.println("Receiver was interrupted: " + e.getMessage());
		}
	}

	public ChatServer getServer() {
		return server;
	}

	public void setServer(ChatServer server) {
		this.server = server;
	}

}
