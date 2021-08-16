package com.db.db_kudos.controller;

import com.db.db_kudos.model.User;
import com.db.db_kudos.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static com.db.db_kudos.controller.UserController.USER_URL;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping(USER_URL)
public class UserController extends AbstractBaseController {
	static final String USER_URL = AbstractBaseController.BASE_URL + "/users";

	@Autowired
	UserService userService;

	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@PostMapping("/")
	public ResponseEntity<User> post(@RequestBody User user) {
		try {
			userService.findById(user.getUsername()).orElseThrow();
			return ResponseEntity.ok(null);
		} catch (NoSuchElementException e) {
			log.info("Username already exists !!");
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
		}
	}

	@PutMapping("/")
	public ResponseEntity<User> put(@RequestBody User user) {
		try {
			userService.findById(user.getUsername()).orElseThrow();
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.update(user));
		} catch (NoSuchElementException e) {
			log.info("Username not exists !!");
			return ResponseEntity.ok(null);
		}
	}


	@GetMapping("/{username}")
	public ResponseEntity<User> get(@PathVariable("username") String username) {
		return ResponseEntity.of(userService.findById(username));
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("username") String username) {
		return ResponseEntity.ok(userService.deleteById(username));
	}
}
