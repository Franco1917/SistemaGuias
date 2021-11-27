package com.core.inscriptionAplication.service;

import com.core.inscriptionAplication.entity.Teacher;
import com.core.inscriptionAplication.entity.User;

import javax.validation.Valid;

public interface UserService {


	
	public Iterable<User>getAllUsers();

	public User createSubject1(@Valid User user);
}
