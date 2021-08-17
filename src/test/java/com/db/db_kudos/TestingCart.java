package com.db.db_kudos;

import com.db.db_kudos.controller.UserController;
import com.db.db_kudos.model.Badge;
import com.db.db_kudos.model.User;
import com.db.db_kudos.model.dao.Level;
import com.db.db_kudos.repository.BadgeRepository;
import com.db.db_kudos.service.BadgeService;
import com.db.db_kudos.service.UserBadgeService;
import com.db.db_kudos.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestingCart {

	@Autowired
	UserService userService;

	@Autowired
	UserController userController;

	@Autowired
	BadgeService badgeService;

	@Autowired
	UserBadgeService userBadgeService;

	Badge badge1;
	Badge badge2;
	User user;

	@BeforeEach
	public void setTestCases() {
		user = new User("piyush", "piyush@db.com", "Piyush", 50, true);
		badge1 = new Badge("b1", "badge_1", "1st Badge", 60, "location", Level.Elite,0);
		badge2 = new Badge("b2", "badge_2", "2st Badge", 30, "location", Level.Premium,0);
	}

	@Test
	public void testCheckoutWrongUsername() {
		assertEquals(false, userBadgeService.checkout("piyush"));
	}

	@Test
	public void testCheckoutEmptyCart() {
		badge1 = badgeService.save(badge1);
		userService.save(user);
		assertFalse(userBadgeService.checkout("piyush"));
	}

	@Test
	public void testCheckoutGreater() {
		badge1 = badgeService.save(badge1);
		userService.save(user);
		userController.addToCart(badge1.getId(), user.getUsername());
		assertFalse(userBadgeService.checkout("piyush"));
	}

	@Test
	public void testAddingToCartWithoutUser() {
		badge1 = badgeService.save(badge1);
		assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false),userController.addToCart(badge1.getId(), user.getUsername()));
	}

	@Test
	public void cartCheckoutSuccessful() {
		badge2 = badgeService.save(badge2);
		userService.save(user);
		userController.addToCart(badge2.getId(), user.getUsername());
		assertTrue(userBadgeService.checkout("piyush"));
		assertEquals(20, userService.findById(user.getUsername()).get().getKudosPoint());
	}

	@Test
	public void  removeBadgeFromCartUnsuccessful() {
		badge2 = badgeService.save(badge2);
		userService.save(user);
		assertFalse(userBadgeService.removeFromCart(badge2.getId(), "piyush"));
	}

	@Test
	public void removeBadgeFromCartSuccessful() {
		badge2 = badgeService.save(badge2);
		userService.save(user);
		userController.addToCart(badge2.getId(), user.getUsername());
		assertTrue(userBadgeService.removeFromCart(badge2.getId(), "piyush"));
	}

	@Test
	public void getCartSize() {
		badge2 = badgeService.save(badge2);
		userService.save(user);
		assertEquals(0, userBadgeService.getCart(user.getUsername()).getNumberOfBadges());
		userController.addToCart(badge2.getId(), user.getUsername());
		assertEquals(1, userBadgeService.getCart(user.getUsername()).getNumberOfBadges());
	}

}
