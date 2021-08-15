package com.db.db_kudos.controller;

import com.db.db_kudos.model.User;
import com.db.db_kudos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.db.db_kudos.controller.UserController.USER_URL;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(USER_URL)
public class UserController extends AbstractBaseController {
	static final String USER_URL = AbstractBaseController.BASE_URL + "/users";

	@Autowired
	UserService userService;

	@GetMapping("/getAll")
	public ResponseEntity getAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@PostMapping("/")
	public ResponseEntity post(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveOrUpdate(user));
	}

	@GetMapping("/{username}")
	public ResponseEntity get(@PathVariable("username") String username) {
		return ResponseEntity.of(userService.findById(username));
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("username") String username) {
		return ResponseEntity.ok(userService.deleteById(username));
	}
}
