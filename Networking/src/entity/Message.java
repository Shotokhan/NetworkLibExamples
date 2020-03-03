package entity;

import com.google.gson.Gson;

public class Message {
	public String data = "";
	public String source = "";
	public String destination = "";
	public MessageType type = MessageType.TEXT;
	
	public Message() {}
	
	public Message(String json) {
		this.fromJson(json);
	}
	
	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}
	
	public void fromJson(String json) {
		Gson gson = new Gson();
		Message msg = gson.fromJson(json, Message.class);
		this.data = msg.data;
		this.source = msg.source;
		this.destination = msg.destination;
		this.type = msg.type;
	}
	
	public String toString() {
		String output = "[" + this.source + "] : " + this.data;
		return output;
	}
}
