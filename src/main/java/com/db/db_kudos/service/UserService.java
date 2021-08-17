package com.db.db_kudos.service;

import com.db.db_kudos.controller.UserController;
import com.db.db_kudos.model.User;
import com.db.db_kudos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements AbstractService<User, String> {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {
		List<User> users = userRepository.findAll();
		return users.stream().filter(user -> user.isActive()).collect(Collectors.toList());
	}

	@Override
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	public User deleteById(String id) {
		try{
			User user = userRepository.findById(id).orElseThrow();
			user.setActive(false);
			userRepository.save(user);
			return user;
		} catch (NoSuchElementException e) {
			return null;
		}
	}
}
