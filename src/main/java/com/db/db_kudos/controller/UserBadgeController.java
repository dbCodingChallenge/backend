package com.db.db_kudos.controller;

import com.db.db_kudos.service.UserBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(UserBadgeController.USER_BADGE_URL)
public class UserBadgeController {
	static final String USER_BADGE_URL = BadgeController.BADGE_URL + "/checkout";

	@Autowired
	UserBadgeService userBadgeService;

	@GetMapping("/{username}")
	public ResponseEntity<Boolean> cartCheckout(@PathVariable("username") String username) {
		return ResponseEntity.ok(userBadgeService.checkout(username));
	}
}
