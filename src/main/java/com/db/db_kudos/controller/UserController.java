package com.db.db_kudos.controller;

import com.db.db_kudos.model.User;
import com.db.db_kudos.model.dao.CartDAO;
import com.db.db_kudos.model.dao.ShoppingListDao;
import com.db.db_kudos.service.UserBadgeService;
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
public class UserController {
	static final String USER_URL = BaseController.BASE_URL + "/users";

	@Autowired
	UserService userService;

	@Autowired
	UserBadgeService userBadgeService;

	@GetMapping("/")
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@PostMapping("/")
	public ResponseEntity<User> post(@RequestBody User user) {
		if (userService.findById(user.getUsername()).isPresent()) {
			log.info("Username already exists !!");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
		}
	}

	@PutMapping("/")
	public ResponseEntity<User> put(@RequestBody User user) {
		try {
			if(userService.findById(user.getUsername()).isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			return ResponseEntity.ok(userService.save(user));
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}


	@GetMapping("/{username}")
	public ResponseEntity<User> get(@PathVariable("username") String username) {
		return ResponseEntity.of(userService.findById(username));
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<User> deleteUser(@PathVariable("username") String username) {
		User user = userService.deleteById(username);
		if(user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
		}
	}

	@PostMapping("{username}/addToCart/badge/{id}")
	public ResponseEntity<Boolean> addToCart(@PathVariable("id") String id, @PathVariable("username") String username) {
		boolean response = userBadgeService.addToCart(id, username);
		if(response) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@PostMapping("{username}/removeFromCart/badge/{id}")
	public ResponseEntity<Boolean> removeFromCart(@PathVariable("id") String id, @PathVariable("username") String username) {
		boolean response = userBadgeService.removeFromCart(id, username);
		if(response) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@GetMapping("/{username}/getPurchased")
	public ResponseEntity<CartDAO> getPurchasedBadges(@PathVariable("username") String username) {
		if (userService.findById(username).isEmpty()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.ok(userBadgeService.getPurchased(username));
	}

	@GetMapping("/{username}/getCart")
	public ResponseEntity<CartDAO> getCartBadges(@PathVariable("username") String username) {
		if (userService.findById(username).isEmpty()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.ok(userBadgeService.getCart(username));
	}

	// Response same as before
	@GetMapping("/{username}/shoppingList/")
	public ResponseEntity<List<ShoppingListDao>> getBatchListByUser(@PathVariable("username") String username) {
		if (userService.findById(username).isEmpty()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.ok(userBadgeService.getBadgeListByUser(username));
	}
	//To-Do
	@GetMapping("/{username}/badgesOnShop")
	public ResponseEntity<List<ShoppingListDao>> getBadgeListByUser(@PathVariable("username") String username) {
		return ResponseEntity.ok(userBadgeService.getBadgeOnShopByUser(username));
	}

	@GetMapping("/{username}/checkout")
	public ResponseEntity<Boolean> cartCheckout(@PathVariable("username") String username) {
		Boolean response = userBadgeService.checkout(username);
		if(response) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	public interface Constants {
		String PAYLOAD = "payload";
	}
}
