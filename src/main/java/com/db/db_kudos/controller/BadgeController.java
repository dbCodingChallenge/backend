package com.db.db_kudos.controller;

import com.db.db_kudos.model.Badge;
import com.db.db_kudos.model.dao.RecentPurchasesDao;
import com.db.db_kudos.service.BadgeService;
import com.db.db_kudos.service.UserBadgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping(BadgeController.BADGE_URL)
public class BadgeController {
	static final String BADGE_URL = BaseController.BASE_URL + "/badge";

	@Autowired
	BadgeService badgeService;

	@Autowired
	UserBadgeService userBadgeService;

	@GetMapping("/")
	public ResponseEntity<List<Badge>> getAll() {
		return ResponseEntity.ok(badgeService.findAll());
	}

	@PostMapping("/")
	public ResponseEntity<Badge> post(@RequestBody Badge badge) {
		try {
			badgeService.findById(badge.getId()).orElseThrow();
			log.info("Id already exists !!");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.CREATED).body(badgeService.save(badge));
		}
	}

	@PutMapping("/")
	public ResponseEntity<Badge> put(@RequestBody Badge badge) {
		try {
			badgeService.findById(badge.getId()).orElseThrow();
			return ResponseEntity.status(HttpStatus.CREATED).body(badgeService.update(badge));
		} catch (NoSuchElementException e) {
			log.info("Username not exists !!");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Badge> get(@PathVariable("id") String id) {
		return ResponseEntity.of(badgeService.findById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Badge> deleteBadge(@PathVariable("id") String id) {
		Badge badge = badgeService.deleteById(id);
		if(badge != null) {
			return ResponseEntity.ok(badge);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badge);
		}
	}

	@GetMapping("/recent")
	public ResponseEntity<List<RecentPurchasesDao>> getRecentPurchases() {
		return ResponseEntity.ok(userBadgeService.getRecentPurchases());
	}
}
