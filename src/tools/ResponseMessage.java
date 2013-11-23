package tools;

public class ResponseMessage {
private boolean status;
private String message;
private String dataString;
public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getDataString() {
	return dataString;
}
public void setDataString(String dataString) {
	this.dataString = dataString;
}

}
