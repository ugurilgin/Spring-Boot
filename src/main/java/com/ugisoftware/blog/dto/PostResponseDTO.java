package com.ugisoftware.blog.dto;

import java.util.List;

import com.ugisoftware.blog.entities.Post;

public class PostResponseDTO {
private Long id;
private Long userid;
private String userName;
private String title;
private String text;
private List<LikeResponseDTO> postLikes;
public PostResponseDTO(Post entity, List<LikeResponseDTO> likes) {
	this.id = entity.getId();
	this.userid = entity.getUser().getId();
	this.userName = entity.getUser().getUserName();
	this.title = getTitle();
	this.text = getText();
	this.postLikes=likes;
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
	return userName;
}
public void setUserName(String userNameString) {
	this.userName = userNameString;
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
public List<LikeResponseDTO> getPostLikes() {
	return postLikes;
}
public void setPostLikes(List<LikeResponseDTO> postLikes) {
	this.postLikes = postLikes;
}


}
