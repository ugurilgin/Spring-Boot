package com.ugisoftware.blog.dto;

public class LikeCreateDTO {
private Long id;
private Long postId;
private Long userId;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getPostId() {
	return postId;
}
public void setPostId(Long postId) {
	this.postId = postId;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}

}
