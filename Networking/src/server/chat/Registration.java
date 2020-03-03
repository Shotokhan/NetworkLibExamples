package server.chat;

import java.io.IOException;

import entity.Message;
import entity.MessageType;
import networking.ClientHandler;

public class Registration extends ChatHandler {
	
	public Registration() {}

	public Registration(ClientHandler other) {
		super(other);
	}

	public void run() {
		String id = "";
		synchronized(this.getServer()) {
			id = "client"+this.getServer().getSequence();
			this.getServer().getActiveUsers().put(id, this);
			this.getServer().setSequence(this.getServer().getSequence() + 1);
		}
		Message msg = new Message();
		msg.destination = id;
		msg.type = MessageType.REGISTRATION;
		try {
			this.getOutput().writeUTF(msg.toJson());
		} catch (IOException e) {
			System.out.println("Message not sent to the user");
		}
	}
	
}
