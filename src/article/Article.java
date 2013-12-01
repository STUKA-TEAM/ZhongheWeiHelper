package article;

import java.sql.Timestamp;

public class Article {
private int articleId;
private String title;
private Timestamp date;
private String imagePath;
private String content;
private int listClass;
private int articleClassId;
public int getArticleId() {
	return articleId;
}
public void setArticleId(int articleId) {
	this.articleId = articleId;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Timestamp getDate() {
	return date;
}
public void setDate(Timestamp date) {
	this.date = date;
}
public String getImagePath() {
	return imagePath;
}
public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getListClass() {
	return listClass;
}
public void setListClass(int listClass) {
	this.listClass = listClass;
}
public int getArticleClassId() {
	return articleClassId;
}
public void setArticleClassId(int articleClassId) {
	this.articleClassId = articleClassId;
}
}
