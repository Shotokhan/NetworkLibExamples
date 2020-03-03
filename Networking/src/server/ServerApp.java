package server;

import networking.*;
import server.chat.ChatHandler;
import server.chat.ChatServer;
import server.noob.Receiver;

public class ServerApp {
	
	public static void main(String args[]) {
		int basePort = Integer.parseInt(args[0]);
		ServerLauncher launcher = new ServerLauncher();
		launcher.getLaunchList().add(new ConcreteListener(basePort, new Receiver()));
		ChatHandler chatHandler = new ChatHandler();
		ChatServer chatServer = new ChatServer(basePort + 1, chatHandler);
		chatHandler.setServer(chatServer);
		launcher.getLaunchList().add(chatServer);
		launcher.runListeners();
	}
}
