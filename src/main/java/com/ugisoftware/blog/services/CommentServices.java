package com.ugisoftware.blog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ugisoftware.blog.dto.CommentCreateDTO;
import com.ugisoftware.blog.dto.CommentUpdateDTO;
import com.ugisoftware.blog.entities.Comments;
import com.ugisoftware.blog.entities.Post;
import com.ugisoftware.blog.entities.User;
import com.ugisoftware.blog.repositories.CommentRepository;


@Service
public class CommentServices {
private CommentRepository commentRepository;
private UserServices userServices;
private PostServices postServices;

public CommentServices(CommentRepository commentRepository,UserServices userServices,PostServices postServices) {
	this.commentRepository = commentRepository;
	this.postServices=postServices;
	this.userServices=userServices;
}

public List<Comments> getAllComments(Optional<Long> userId, Optional<Long> postId) {
	 if(userId.isPresent() && postId.isPresent())
	 {
	 return  commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
	 }
	else if (userId.isPresent() )
	{return commentRepository.findByUserId(userId.get());}
	else if( postId.isPresent())
	{return commentRepository.findByPostId(postId.get());}
	
	else {
		return commentRepository.findAll();
	}
}
public Comments getComment(Long commentId) {
	// TODO Auto-generated method stub
	return commentRepository.findById(commentId).orElse(null);
}

public Comments createComment(CommentCreateDTO newComment) {
	// TODO Auto-generated method stub
	User user=userServices.getUser(newComment.getUserId());
	Post post=postServices.getPost(newComment.getPostId());
	if(user==null || post==null)
		return null;
	Comments toSave= new Comments();
	toSave.setId(newComment.getId());
	toSave.setText(newComment.getText());
	toSave.setUser(user);
	toSave.setPost(post);
	return commentRepository.save(toSave);
}

public Comments updateComment(Long commentId, CommentUpdateDTO editComment) {
	Optional<Comments> comments=commentRepository.findById(commentId);
	if(comments.isPresent())
	{
		Comments foundedPost=comments.get();
		foundedPost.setText(editComment.getText());
		
		return commentRepository.save(foundedPost);
	}
	else {
		{return null;}
	}
	
}

public void deleteComment(Long commentId) {
	commentRepository.deleteById(commentId);
	
}
}
