package com.app.tests.Pojos;

public class Posts {
	private String title;
	private int id;
	private String body;
	private int userID;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "Posts [title=" + title + ", id=" + id + ", body=" + body + ", userID=" + userID + "]";
	}

}
