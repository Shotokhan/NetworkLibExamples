package server.noob;

import java.io.IOException;

import networking.ClientHandler;

public class Receiver extends ClientHandler {

	public Receiver() {}

	public Receiver(ClientHandler other) {
		super(other);
	}

	public void startInteraction() {
		String line = "";
		while(!line.equals("quit")) {
			try {
				line = this.getInput().readUTF();
				System.out.println(line);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
		System.out.println("Interaction finished");
	}
	
	public void run() {
		this.startInteraction();
	}

	@Override
	public void copy(ClientHandler other) {
		// TODO Stub di metodo generato automaticamente
		
	}

}
