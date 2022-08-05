package com.ugisoftware.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.blog.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
