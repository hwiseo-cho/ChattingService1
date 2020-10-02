package chat;

public class ChatDTO {

	private int chatId;
	private String fromId;
	private String toId;
	private String chatContent;
	private String chatTime;

	public ChatDTO() {
		super();
	}

	public ChatDTO(int chatId, String fromId, String toId, String chatContent, String chatTime) {
		super();
		this.chatId = chatId;
		this.fromId = fromId;
		this.toId = toId;
		this.chatContent = chatContent;
		this.chatTime = chatTime;
	}

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public String getChatTime() {
		return chatTime;
	}

	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}

	@Override
	public String toString() {
		return "ChatDTO [chatId=" + chatId + ", fromId=" + fromId + ", toId=" + toId + ", chatContent=" + chatContent
				+ ", chatTime=" + chatTime + "]";
	}
}
