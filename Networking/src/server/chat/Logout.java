package server.chat;

import java.io.DataOutputStream;
import java.io.IOException;

import entity.Message;
import entity.MessageType;
import networking.ClientHandler;

public class Logout extends ChatHandler {
	
	public Logout() {
	}

	public Logout(ClientHandler other) {
		super(other);
	}

	@Override
	public void run() {
		Message msg = new Message(this.getMsg());
		String id = msg.source;
		DataOutputStream out = null;
		synchronized(this) {
			out = this.getServer().getActiveUsers().get(id).getOutput();
			this.getServer().getActiveUsers().remove(id);
		}
		msg = new Message();
		msg.destination = id;
		msg.type = MessageType.LOGOUT;
		msg.source = "server";
		msg.data = "logout";
		try {
			out.writeUTF(msg.toJson());
		} catch (IOException e) {
			System.out.println("Message not sent to the user");
		}
	}

}
