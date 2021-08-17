package com.db.db_kudos;

import com.db.db_kudos.controller.BadgeController;
import com.db.db_kudos.model.Badge;
import com.db.db_kudos.model.dao.Level;
import com.db.db_kudos.service.BadgeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BadgeTest {

	@Autowired
	BadgeService badgeService;

	@Autowired
	BadgeController badgeController;

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void badgeCreation() {
		Badge badge = new Badge("1", "1", "11", 1, "location", Level.Classic,0);
		Badge badge1 = badgeService.save(badge);
		assertEquals(1, badgeService.findAll().size());
	}

	@Test
	public void badgeCreationDuplicate() {
		Badge badge = new Badge("1", "1", "11", 1, "location", Level.Classic,0);
		Badge badge1 = badgeService.save(badge);
		System.out.println(badge1);
		assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null), badgeController.post(badge1));
	}
}
