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

import com.ugisoftware.blog.dto.request.PostCreateDTO;
import com.ugisoftware.blog.dto.request.PostUpdateDTO;
import com.ugisoftware.blog.dto.response.PostResponseDTO;
import com.ugisoftware.blog.entities.Post;
import com.ugisoftware.blog.services.PostServices;

@RequestMapping("/posts")
@RestController
public class PostController {
	private PostServices postServices;

public PostController(PostServices postServices)
{
	this.postServices=postServices;
}

@GetMapping
public List<PostResponseDTO> getAllPosts(@RequestParam(name = "userid") Optional<Long> userId) {
	return postServices.getAllPosts(userId);
}

@PostMapping
public Post  createPost(@RequestBody PostCreateDTO newPost) {
	return postServices.createPost(newPost);
}

@GetMapping("/{postId}")
public Post getPost(@PathVariable Long postId) {
	return postServices.getPost(postId);
}

@PutMapping("/{postId}")
public Post updatePost(@PathVariable Long postId,@RequestBody PostUpdateDTO editPost)
{
	return postServices.updatePost(postId,editPost);
} 

@DeleteMapping("/{postId}")
public void deletePost(@PathVariable Long postId)
{
	postServices.deletePost(postId);
}

}
