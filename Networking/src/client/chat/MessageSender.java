package client.chat;

import java.io.IOException;

import entity.Message;

public class MessageSender extends Thread {

	private ChatClient client;

	public MessageSender(ChatClient client) {
		this.setClient(client);
	}
	
	@Override
	public void run() {
		this.sendService();
	}
	
	public void sendService() {
		Message msg = new Message();
		msg.source = this.getClient().getId();
		String line = "";
		while(true) {
			try {
				line = this.getClient().getInput().readLine();
				String[] tokens = line.split("#");
				try {
					msg.data = tokens[0];
					msg.destination = tokens[1];
				} catch (Exception e) {
					if(line.equals("quit"))
						return;
					System.out.println("Invalid input: " + e.getMessage());
					continue;
				}
				this.getClient().getOut().writeUTF(msg.toJson());
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
	}
	
	public ChatClient getClient() {
		return client;
	}

	public void setClient(ChatClient client) {
		this.client = client;
	}
}
