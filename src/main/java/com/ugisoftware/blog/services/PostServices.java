package com.ugisoftware.blog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ugisoftware.blog.dto.LikeResponseDTO;
import com.ugisoftware.blog.dto.PostCreateDTO;
import com.ugisoftware.blog.dto.PostResponseDTO;
import com.ugisoftware.blog.dto.PostUpdateDTO;
import com.ugisoftware.blog.entities.Post;
import com.ugisoftware.blog.entities.User;
import com.ugisoftware.blog.repositories.PostRepository;

@Service
public class PostServices {
private PostRepository postRepository;
private LikeServices likeServices;
private UserServices userServices;
public PostServices(PostRepository postRepository, UserServices userServices) {
	this.postRepository = postRepository;
	this.userServices=userServices;
	
}
public void setLikeService(LikeServices likeServices)
{
	this.likeServices=likeServices;
}
public List<PostResponseDTO> getAllPosts(Optional<Long> userId) {
	// TODO Auto-generated method stub
	List<Post> list;
	if(userId.isPresent())
		{list= postRepository.findByUserId(userId.get());}
		else {
			list= postRepository.findAll();
		}
	return list.stream().map(p ->  {
	List <LikeResponseDTO> likes=likeServices.getAllLikes(Optional.of(null),Optional.of(p.getId()) );
	return new  PostResponseDTO(p,likes);}).collect(Collectors.toList()) ;
}

public Post getPost(Long postId) {
	// TODO Auto-generated method stub
	return postRepository.findById(postId).orElse(null);
}

public Post createPost(PostCreateDTO newPost) {
	// TODO Auto-generated method stub
	User user=userServices.getUser(newPost.getUserId());
	if(user==null)
		return null;
	Post toSave= new Post(); 
	toSave.setId(newPost.getId());
	toSave.setText(newPost.getText());
	toSave.setTitle(newPost.getTitle());
	toSave.setUser(user);
	return postRepository.save(toSave);
}

public Post updatePost(Long postId, PostUpdateDTO editPost) {
	Optional<Post> post=postRepository.findById(postId);
	if(post.isPresent())
	{
		Post foundedPost=post.get();
		foundedPost.setText(editPost.getText());
		foundedPost.setTitle(editPost.getTitle());
		return postRepository.save(foundedPost);
	}
	else {
		{return null;}
	}
	
}

public void deletePost(Long postId) {
	postRepository.deleteById(postId);
	
}


}
