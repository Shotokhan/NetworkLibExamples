package server.chat;

import entity.Message;
import entity.MessageType;
import networking.ClientHandler;

public class ChatMsgReceiver extends ChatHandler {
	
	public ChatMsgReceiver() {
		// TODO Stub di costruttore generato automaticamente
	}

	public ChatMsgReceiver(ClientHandler other) {
		super(other);
		// TODO Stub di costruttore generato automaticamente
	}

	@Override
	public void run() {
		while(true) {
			try {
				this.setMsg(this.getInput().readUTF());
				Message msg = new Message(this.getMsg());
				if(msg.type.equals(MessageType.TEXT)) {
					Dispatch dispatch = new Dispatch(this);
					dispatch.start();
				} else if(msg.type.equals(MessageType.LOGOUT)) {
					Logout logout = new Logout(this);
					logout.start();
					logout.join();
					return;
				}
			} catch (Exception e) {
				System.out.println("Error in chatMsgReceiver: " + e.getMessage());
				return;
			}
		}
	}

}
