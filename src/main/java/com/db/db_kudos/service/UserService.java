package com.db.db_kudos.service;

import com.db.db_kudos.model.User;
import com.db.db_kudos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService implements AbstractService<User, String> {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
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

	@Override
	public boolean deleteById(String id) {
		try{
			userRepository.deleteById(id);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
