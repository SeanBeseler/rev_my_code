package project1.com.objects;

public class messages {
	private String message;
	private String timeSent;
	private int urgent;
	private int from;
	private int read;
	
	
	public messages() {
		super();
		this.message = null;
		this.timeSent = null;
		this.urgent = 0;
		this.from = 0;
		this.read = 0;
	}
	
	public messages(String message, String timeSent, int urgent, int from) {
		super();
		this.message = message;
		this.timeSent = timeSent;
		this.urgent = urgent;
		this.from = from;
		this.read = 0;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTimeSent() {
		return timeSent;
	}
	public void setTimeSent(String timeSent) {
		this.timeSent = timeSent;
	}
	public int getUrgent() {
		return urgent;
	}
	public void setUrgent(int urgent) {
		this.urgent = urgent;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	
}
