package client.chat;

import java.io.IOException;

import networking.ClientManager;
import entity.Message;
import entity.MessageType;

public class ChatClient extends ClientManager {

	private String id = null;
	private volatile boolean receiving = true;
	
	public ChatClient(String address, int port) {
		super(address, port);
	}
	
	public boolean register() {
		Message registration = new Message();
		registration.type = MessageType.REGISTRATION;
		try {
			this.getOut().writeUTF(registration.toJson());
			String line = this.getRemoteInput().readUTF();
			registration = new Message(line);
			if(!registration.type.equals(MessageType.REGISTRATION) || registration.destination.equals("")) {
				System.out.println("Registration failed: received a bad message from server");
				return false;
			}
			this.setId(registration.destination);
		} catch (IOException e) {
			System.out.println("Registration failed: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	public void logout() {
		Message logout = new Message();
		logout.source = this.getId();
		logout.type = MessageType.LOGOUT;
		try {
			this.getOut().writeUTF(logout.toJson());
		} catch (IOException e) {
			// do nothing
		}
	}
	
	public static void main(String[] args) {
		// TODO: check for good input
		ChatClient client = new ChatClient(args[0], Integer.parseInt(args[1]));
		if(!client.register()) {
			return;
		}
		System.out.println("Registration OK: your id is: " + client.getId());
		MessageSender sender = new MessageSender(client);
		sender.start();
		MessageReceiver receiver = new MessageReceiver(client);
		receiver.start();
		try {
			sender.join();
		} catch (InterruptedException e) {
			System.out.println("Sender interrupted: " + e.getMessage());
		}
		client.setReceiving(false);
		client.logout();
		try {
			receiver.join();
		} catch (InterruptedException e) {
			System.out.println("Receiver did non terminate properly: " + e.getMessage());
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isReceiving() {
		return receiving;
	}

	public void setReceiving(boolean receiving) {
		this.receiving = receiving;
	}

}
