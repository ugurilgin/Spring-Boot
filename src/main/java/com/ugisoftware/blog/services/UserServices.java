package com.ugisoftware.blog.services;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ugisoftware.blog.entities.User;
import com.ugisoftware.blog.repositories.UserRepository;

@Service
public class UserServices {
private UserRepository userRepository;

public UserServices(UserRepository userRepository) {

	this.userRepository = userRepository;
}

public List<User> getAllUsers() {
	// TODO Auto-generated method stub
	return userRepository.findAll();
}
public User createUser(User newUser)
{
	PasswordEncoder encoder=new BCryptPasswordEncoder();
	String passwd=encoder.encode(newUser.getPassword());
	newUser.setPassword(passwd);
	return userRepository.save(newUser);
	//System.out.println(encoder.matches("123456", passwd));
}

public User getUser(Long userId) {
	// TODO Auto-generated method stub
	return userRepository.findById(userId).orElse(null);
	}

public User updateUser(User updateUser, Long userId) {
	// TODO Auto-generated method stub
	Optional<User> user=userRepository.findById(userId);
	if(user.isPresent())
	{
		User foundedUser=user.get();
		foundedUser.setUserName(updateUser.getUserName());
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		String passwd=encoder.encode(updateUser.getPassword());
		foundedUser.setPassword(passwd);
		return userRepository.save(foundedUser);
	}
	else
	{
		return null;
	}
}

public void deleteUser(Long userId) {
	// TODO Auto-generated method stub
	 userRepository.deleteById(userId);	
}
}
