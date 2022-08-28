package com.ugisoftware.blog.dto.response;

import com.ugisoftware.blog.entities.Like;

public class LikeResponseDTO {
private Long id;
private Long userId;
private Long postId;
public LikeResponseDTO(Like entity) {
	this.id=entity.getId();
	this.userId = entity.getUser().getId();
	this.postId = entity.getPost().getId();
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public Long getPostId() {
	return postId;
}
public void setPostId(Long postId) {
	this.postId = postId;
}


}
