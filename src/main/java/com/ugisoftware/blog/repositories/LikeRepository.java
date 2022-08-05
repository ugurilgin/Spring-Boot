package com.ugisoftware.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.blog.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
