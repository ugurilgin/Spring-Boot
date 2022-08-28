package com.ugisoftware.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.blog.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

	RefreshToken findByUserId(Long userId);
}
	
