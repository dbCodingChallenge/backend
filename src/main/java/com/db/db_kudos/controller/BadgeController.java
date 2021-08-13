package com.db.db_kudos.controller;

import com.db.db_kudos.model.Badge;
import com.db.db_kudos.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BadgeController.BADGE_URL)
public class BadgeController extends AbstractBaseController {
	public static final String BADGE_URL = AbstractBaseController.BASE_URL + "/badge";

	@Autowired
	BadgeService badgeService;

	@GetMapping("/getAll")
	public ResponseEntity getAll() {
		return ResponseEntity.ok(badgeService.findAll());
	}

	@PostMapping("/")
	public ResponseEntity post(@RequestBody Badge badge) {
		return ResponseEntity.status(HttpStatus.CREATED).body(badgeService.saveOrUpdate(badge));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity get(@PathVariable("id") int id) {
		return ResponseEntity.of(badgeService.findById(id));
	}
}
