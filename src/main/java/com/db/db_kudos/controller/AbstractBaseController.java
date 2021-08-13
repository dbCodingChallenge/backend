package com.db.db_kudos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(AbstractBaseController.BASE_URL)
abstract public class AbstractBaseController {

	public static final String BASE_URL = "/dbKudos";

//	@Autowired
//	AbstractRepository abstractRepository;

	@GetMapping("/")
	public ResponseEntity check() {
		return ResponseEntity.ok("Server Up!!");
	}



}
