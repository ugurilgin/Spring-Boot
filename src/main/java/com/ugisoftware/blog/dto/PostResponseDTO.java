package com.ugisoftware.blog.dto;

import com.ugisoftware.blog.entities.Post;

public class PostResponseDTO {
private Long id;
private Long userid;
private String userNameString;
private String title;
private String text;

public PostResponseDTO(Post entity) {
	this.id = entity.getId();
	this.userid = entity.getUser().getId();
	this.userNameString = entity.getUser().getUserName();
	this.title = getTitle();
	this.text = getText();
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getUserid() {
	return userid;
}
public void setUserid(Long userid) {
	this.userid = userid;
}
public String getUserName() {
	return userNameString;
}
public void setUserName(String userNameString) {
	this.userNameString = userNameString;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}

}
