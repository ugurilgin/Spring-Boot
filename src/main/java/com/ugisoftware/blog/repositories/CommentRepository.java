package com.ugisoftware.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.blog.entities.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long> {

}
