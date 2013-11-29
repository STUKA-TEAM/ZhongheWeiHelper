package weather;

import java.util.ArrayList;


public class WeatherInfoContainer {
private int error;
private String status;
private String date;
private ArrayList<Result> results;
public int getError() {
	return error;
}
public void setError(int error) {
	this.error = error;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public ArrayList<Result> getResults() {
	return results;
}
public void setResults(ArrayList<Result> results) {
	this.results = results;
}


}
