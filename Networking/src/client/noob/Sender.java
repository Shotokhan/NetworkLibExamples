package client.noob;

import java.io.IOException;

import networking.ClientManager;

public class Sender extends ClientManager {

	public Sender(String address, int port) {
		super(address, port);
	}

	public void startInteraction() {
		String line = "";
		while(!line.equals("quit")) {
			try {
				line = this.getInput().readLine();
				this.getOut().writeUTF(line);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO: check for good input
		Sender sender = new Sender(args[0], Integer.parseInt(args[1]));
		// Sender sender = new Sender("127.0.0.1", 5000);
		sender.startInteraction();
	}

}
