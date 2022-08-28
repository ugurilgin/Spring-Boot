package com.ugisoftware.blog.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ugisoftware.blog.dto.request.CommentCreateDTO;
import com.ugisoftware.blog.dto.request.CommentUpdateDTO;
import com.ugisoftware.blog.entities.Comments;
import com.ugisoftware.blog.entities.Post;
import com.ugisoftware.blog.entities.User;
import com.ugisoftware.blog.exceptions.ResourceNotFoundExceptions;
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
	 List<Comments> getAllCommented;
	 if(userId.isPresent() && postId.isPresent())
	 {
		 getAllCommented=  commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
	 }
	else if (userId.isPresent() )
	{getAllCommented= commentRepository.findByUserId(userId.get());}
	else if( postId.isPresent())
	{getAllCommented= commentRepository.findByPostId(postId.get());}
	
	else {
		getAllCommented= commentRepository.findAll();
	}
	 return getAllCommented;
}
public Comments getComment(Long commentId) {
	// TODO Auto-generated method stub
	return commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundExceptions("comment not founded"+commentId ));
}

public Comments createComment(CommentCreateDTO newComment) {
	// TODO Auto-generated method stub
	Comments createCommented;
	User user=userServices.getUser(newComment.getUserId());
	Post post=postServices.getPost(newComment.getPostId());
	if(user==null || post==null)
		createCommented= null;
	Comments toSave= new Comments();
	toSave.setId(newComment.getId());
	toSave.setText(newComment.getText());
	toSave.setUser(user);
	toSave.setPost(post);
	toSave.setCreateDate(new Date());
	createCommented= commentRepository.save(toSave);
	return createCommented;
}

public Comments updateComment(Long commentId, CommentUpdateDTO editComment) {
	Optional<Comments> comments=commentRepository.findById(commentId);
	Comments updateCommented;
	if(comments.isPresent())
	{
		Comments foundedPost=comments.get();
		foundedPost.setText(editComment.getText());
		
		updateCommented= commentRepository.save(foundedPost);
	}
	else {
		{updateCommented= null;}
	}
	return updateCommented;
}

public void deleteComment(Long commentId) {
	commentRepository.deleteById(commentId);
	
}
}
