package com.db.db_kudos.controller;

import com.db.db_kudos.model.Badge;
import com.db.db_kudos.model.CartDAO;
import com.db.db_kudos.model.UserBadges;
import com.db.db_kudos.service.BadgeService;
import com.db.db_kudos.service.UserBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(BadgeController.BADGE_URL)
public class BadgeController extends AbstractBaseController {
	public static final String BADGE_URL = AbstractBaseController.BASE_URL + "/badge";

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
		return ResponseEntity.status(HttpStatus.CREATED).body(badgeService.saveOrUpdate(badge));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Badge> get(@PathVariable("id") int id) {
		return ResponseEntity.of(badgeService.findById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBadge(@PathVariable("id") int id) {
		return ResponseEntity.ok(badgeService.deleteById(id));
	}

	@GetMapping("/{username}/getPurchased")
	public ResponseEntity<List<UserBadges>> getPurchasedBadges(@PathVariable("username") String username) {
		return ResponseEntity.ok(userBadgeService.getPurchased(username));
	}

	@GetMapping("/{username}/getCart")
	public ResponseEntity<CartDAO> getCartBadges(@PathVariable("username") String username) {
		return ResponseEntity.ok(userBadgeService.getCart(username));
	}

	@GetMapping("/latest")
	public ResponseEntity<List<UserBadges>> getLatestPurchases() {
		return ResponseEntity.ok(userBadgeService.getLatestPurchases());
	}

	@GetMapping("{id}/toCart/{username}")
	public ResponseEntity<Boolean> addToCart(@PathVariable("id") int id, @PathVariable("username") String username) {
		return ResponseEntity.ok(userBadgeService.addToCart(id, username));
	}

	@GetMapping("{id}/remove/{username}")
	public ResponseEntity<Boolean> removeFromCart(@PathVariable("id") int id, @PathVariable("username") String username) {
		return ResponseEntity.ok(userBadgeService.removeFromCart(id, username));
	}

}
