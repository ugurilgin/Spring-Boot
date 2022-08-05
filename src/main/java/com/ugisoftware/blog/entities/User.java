package com.ugisoftware.blog.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="Users")
public class User {
	
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
@SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq")
@Column(name = "id",unique=true, nullable = false)
private Long id;
private String userName;
private String password;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
}
