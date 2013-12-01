package message;

import java.sql.Timestamp;

public class Message {
private int messageId;
private String content;
private String userName;
private String contact;
private Timestamp Date;
public int getMessageId() {
	return messageId;
}
public void setMessageId(int messageId) {
	this.messageId = messageId;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public Timestamp getDate() {
	return Date;
}
public void setDate(Timestamp date) {
	Date = date;
}

}
