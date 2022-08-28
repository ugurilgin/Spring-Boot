package com.ugisoftware.blog.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ugisoftware.blog.dto.request.CommentCreateDTO;
import com.ugisoftware.blog.dto.request.CommentUpdateDTO;
import com.ugisoftware.blog.entities.Comments;
import com.ugisoftware.blog.services.CommentServices;

@RestController
@RequestMapping("/comments")
public class CommentController {
	private CommentServices commentServices;
	public CommentController(CommentServices commentServices)
	{
		this.commentServices=commentServices;
	}
	
	
	@GetMapping
	public List<Comments> getAllComments(@RequestParam(name="userid") Optional<Long> userId,@RequestParam(name="postid") Optional<Long> postId) {
	return commentServices.getAllComments(userId,postId);
}

   @PostMapping
	public Comments createComments(@RequestBody CommentCreateDTO newUser)
	{
	return commentServices.createComment(newUser);
	}

	@GetMapping("/{commentId}")
	public Comments getComment(@PathVariable Long commentId)
	{
	return commentServices.getComment(commentId);	

	}

	@PutMapping("/{commentId}")
	public Comments updateComment(@RequestBody CommentUpdateDTO updateComment,@PathVariable Long commentId)
	{
		return commentServices.updateComment(commentId,updateComment);
	}

	@DeleteMapping("/{commentId}")
	public void deleteComment(@PathVariable Long commentId)
	{
		commentServices.deleteComment(commentId);
	}
}
