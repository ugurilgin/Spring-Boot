package com.ugisoftware.blog.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ugisoftware.blog.dto.LikeCreateDTO;
import com.ugisoftware.blog.dto.LikeResponseDTO;
import com.ugisoftware.blog.entities.Like;
import com.ugisoftware.blog.services.LikeServices;

@RestController
@RequestMapping("/likes")
public class LikeController {
private LikeServices likeServices;
public LikeController(LikeServices likeServices)
{
	this.likeServices=likeServices;
}

@GetMapping
public List<LikeResponseDTO> getAllLikes(@RequestParam(name="userid") Optional<Long> userId,@RequestParam(name="postid") Optional<Long> postId) {
return likeServices.getAllLikes(userId,postId);
}

@PostMapping
public Like createLike(@RequestBody LikeCreateDTO newLike)
{
return likeServices.createLike(newLike);
}

@GetMapping("/{likeId}")
public Like getLike(@PathVariable Long likeId)
{
return likeServices.getLike(likeId);	

}

@DeleteMapping("/{likeId}")
public void deleteComment(@PathVariable Long likeId)
{
	likeServices.deleteLike(likeId);
}



}
