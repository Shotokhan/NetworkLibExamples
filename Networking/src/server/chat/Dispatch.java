package server.chat;

import java.io.DataOutputStream;
import java.io.IOException;

import entity.Message;
import networking.ClientHandler;

public class Dispatch extends ChatHandler {
	
	public Dispatch() {
	}

	public Dispatch(ClientHandler other) {
		super(other);
	}

	@Override
	public void run() {
		Message msg = new Message(this.getMsg());
		DataOutputStream destination = null;
		boolean goodDestination = true;
		synchronized(this) {
			if(!this.getServer().getActiveUsers().containsKey(msg.destination)) {
				goodDestination = false;
			} else {
				destination = this.getServer().getActiveUsers().get(msg.destination).getOutput();
			}
		}
		if(!goodDestination) {
			Message errorMsg = new Message();
			errorMsg.destination = msg.source;
			errorMsg.data = "Error: destination specified not found";
			errorMsg.source = "server";
			try {
				this.getOutput().writeUTF(errorMsg.toJson());
			} catch (IOException e) {
				System.out.println("Problems in sending error msg to client: " + e.getMessage());
			}
		} else {
			try {
				destination.writeUTF(this.getMsg());
			} catch (IOException e) {
				System.out.println("Problem in routing msg to destination: " + e.getMessage());
			}
		}
	}

}
