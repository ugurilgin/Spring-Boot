package com.ugisoftware.blog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;


import com.ugisoftware.blog.dto.LikeCreateDTO;
import com.ugisoftware.blog.dto.LikeResponseDTO;
import com.ugisoftware.blog.entities.Like;
import com.ugisoftware.blog.entities.Post;
import com.ugisoftware.blog.entities.User;
import com.ugisoftware.blog.repositories.LikeRepository;

@Service
public class LikeServices {
	private LikeRepository likeRepository;
	private UserServices userServices;
	private PostServices postServices;

	public LikeServices(LikeRepository likeRepository,UserServices userServices,PostServices postServices) {
		this.likeRepository = likeRepository;
		this.postServices=postServices;
		this.userServices=userServices;
	}

	public List<LikeResponseDTO> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
		List<Like> list;
		 if(userId.isPresent() && postId.isPresent())
		 {
		 list=  likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
		 }
		else if (userId.isPresent() )
		{list= likeRepository.findByUserId(userId.get());}
		else if( postId.isPresent())
		{list= likeRepository.findByPostId(postId.get());}
		
		else {
			list= likeRepository.findAll();
		}
		 return list.stream().map(p -> new LikeResponseDTO(p)).collect(Collectors.toList());
	}
	public Like getLike(Long likeId) {
		// TODO Auto-generated method stub
		return likeRepository.findById(likeId).orElse(null);
	}

	public Like createLike(LikeCreateDTO newLike) {
		// TODO Auto-generated method stub
		User user=userServices.getUser(newLike.getUserId());
		Post post=postServices.getPost(newLike.getPostId());
		if(user==null || post==null)
			return null;
		Like toSave= new Like();
		toSave.setId(newLike.getId());
		toSave.setUser(user);
		toSave.setPost(post);
		return likeRepository.save(toSave);
	}

	

	public void deleteLike(Long likeId) {
		likeRepository.deleteById(likeId);
		
	}
	}