package com.db.db_kudos.service;

import com.db.db_kudos.model.*;
import com.db.db_kudos.repository.BadgeRepository;
import com.db.db_kudos.repository.UserBadgesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserBadgeService implements AbstractService<UserBadges, UserBadgeId> {

	@Autowired
	UserBadgesRepository userBadgesRepository;

	@Autowired
	BadgeRepository badgeRepository;

	@Override
	public List<UserBadges> findAll() {
		return userBadgesRepository.findAll();
	}

	@Override
	public Optional<UserBadges> findById(UserBadgeId id) {
		return userBadgesRepository.findById(id);
	}

	@Override
	public UserBadges saveOrUpdate(UserBadges userBadges) {
		return userBadgesRepository.save(userBadges);
	}

	@Override
	public boolean deleteById(UserBadgeId id) {
		try{
			userBadgesRepository.deleteById(id);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public List<UserBadges> getPurchased(String username) {
		return userBadgesRepository.findById_UsernameAndStatus(username, Status.PURCHASED);
	}

	public CartDAO getCart(String username) {
		List<UserBadges> userBadges = userBadgesRepository.findById_UsernameAndStatus(username, Status.IN_CART);
		CartDAO cart = new CartDAO();
		List<Integer> badgesId =
				userBadges.stream()
						.map(userBadge -> userBadge.getId().getBadgeId())
						.collect(Collectors.toList());
		cart.setBadges(badgesId.stream()
						.map(id -> badgeRepository.findById(id).orElseThrow())
						.collect(Collectors.toList()));
		cart.setUsername(username);
		cart.setNumberOfBadges(cart.getBadges().size());
		cart.setAmount(cart.getBadges().stream().mapToInt(badge -> badge.getCost()).sum());
		return cart;
	}

	public List<UserBadges> getLatestPurchases() {
		return userBadgesRepository.findAll(Sort.by(Sort.Direction.DESC, "purchasedAt"));
	}

	public Boolean addToCart(int id, String username) {
		try {
			badgeRepository.findById(id).orElseThrow();
			UserBadges userBadge = new UserBadges(new UserBadgeId(username, id), Status.IN_CART, new Date());
			userBadgesRepository.save(userBadge);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public Boolean removeFromCart(int id, String username) {
		try {
			UserBadges userBadge = userBadgesRepository.findById(new UserBadgeId(username, id)).orElseThrow();
			userBadgesRepository.delete(userBadge);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
