package client.chat;

import java.io.IOException;

import entity.Message;

public class MessageReceiver extends Thread {

	private ChatClient client;

	@Override
	public void run() {
		this.receiveService();
	}
	
	public void receiveService() {
		while(client.isReceiving()) {
			try {
				String line = this.getClient().getRemoteInput().readUTF();
				Message msg = new Message(line);
				System.out.println(msg);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
	}
	
	public MessageReceiver(ChatClient client) {
		this.client = client;
	}
	
	public ChatClient getClient() {
		return client;
	}

	public void setClient(ChatClient client) {
		this.client = client;
	}
}
