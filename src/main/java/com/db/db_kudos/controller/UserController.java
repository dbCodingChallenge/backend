package com.db.db_kudos.controller;

import com.db.db_kudos.model.User;
import com.db.db_kudos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.db.db_kudos.controller.UserController.USER_URL;

@RestController
@RequestMapping(USER_URL)
public class UserController extends AbstractBaseController {
	public static final String USER_URL = AbstractBaseController.BASE_URL + "/users";

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

	@GetMapping("/get/{id}")
	public ResponseEntity get(@PathVariable("id") String id) {
		return ResponseEntity.of(userService.findById(id));
	}


}
