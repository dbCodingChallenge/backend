package com.db.db_kudos.controller;

import com.db.db_kudos.model.Badge;
import com.db.db_kudos.model.dao.CartDAO;
import com.db.db_kudos.model.dao.RecentPurchasesDao;
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
	static final String BADGE_URL = AbstractBaseController.BASE_URL + "/badge";

	@Autowired
	BadgeService badgeService;

	@Autowired
	UserBadgeService userBadgeService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Badge>> getAll() {
		return ResponseEntity.ok(badgeService.findAll());
	}

	@PostMapping("/")
	public ResponseEntity<Badge> post(@RequestBody Badge badge) {
		return ResponseEntity.status(HttpStatus.CREATED).body(badgeService.saveOrUpdate(badge));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Badge> get(@PathVariable("id") String id) {
		return ResponseEntity.of(badgeService.findById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBadge(@PathVariable("id") String id) {
		return ResponseEntity.ok(badgeService.deleteById(id));
	}

	@GetMapping("/{username}/getPurchased")
	public ResponseEntity<CartDAO> getPurchasedBadges(@PathVariable("username") String username) {
		return ResponseEntity.ok(userBadgeService.getPurchased(username));
	}

	@GetMapping("/{username}/getCart")
	public ResponseEntity<CartDAO> getCartBadges(@PathVariable("username") String username) {
		return ResponseEntity.ok(userBadgeService.getCart(username));
	}

	@GetMapping("/recent")
	public ResponseEntity<List<RecentPurchasesDao>> getRecentPurchases() {
		return ResponseEntity.ok(userBadgeService.getRecentPurchases());
	}

	@GetMapping("{id}/toCart/{username}")
	public ResponseEntity<Boolean> addToCart(@PathVariable("id") String id, @PathVariable("username") String username) {
		return ResponseEntity.ok(userBadgeService.addToCart(id, username));
	}

	@GetMapping("{id}/remove/{username}")
	public ResponseEntity<Boolean> removeFromCart(@PathVariable("id") String id, @PathVariable("username") String username) {
		return ResponseEntity.ok(userBadgeService.removeFromCart(id, username));
	}

}
