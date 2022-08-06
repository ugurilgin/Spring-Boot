package com.ugisoftware.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.blog.entities.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long> {

	

	List<Comments> findByUserId(Long long1);

	List<Comments> findByPostId(Long long1);

	List<Comments> findByUserIdAndPostId(Long long1, Long long2);

}
