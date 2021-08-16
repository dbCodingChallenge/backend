package com.db.db_kudos.service;

import com.db.db_kudos.model.*;
import com.db.db_kudos.model.dao.CartDAO;
import com.db.db_kudos.model.dao.RecentPurchasesDao;
import com.db.db_kudos.model.dao.Status;
import com.db.db_kudos.repository.BadgeRepository;
import com.db.db_kudos.repository.UserBadgesRepository;
import com.db.db_kudos.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserBadgeService implements AbstractService<UserBadges, UserBadgeId> {

	@Autowired
	UserBadgesRepository userBadgesRepository;

	@Autowired
	BadgeRepository badgeRepository;

	@Autowired
	UserRepository userRepository;

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

	public CartDAO getPurchased(String username) {
		List<UserBadges> userBadges = userBadgesRepository.findById_UsernameAndStatus(username, Status.PURCHASED);
		CartDAO cart = buildUserBadges(userBadges, username);
		return cart;
	}

	public CartDAO getCart(String username) {
		List<UserBadges> userBadges = userBadgesRepository.findById_UsernameAndStatus(username, Status.IN_CART);
		CartDAO cart = buildUserBadges(userBadges, username);
		cart.setAmount(cart.getBadges().stream().mapToInt(Badge::getCost).sum());
		return cart;
	}

	private CartDAO buildUserBadges(List<UserBadges> userBadges, String username) {
		CartDAO cart = new CartDAO();
		cart.setBadges(getListCartBadges(userBadges));
		cart.setUsername(username);
		cart.setNumberOfBadges(cart.getBadges().size());
		return cart;
	}
	private List<Badge> getListCartBadges(List<UserBadges> userBadges) {
		List<String> badgesId =
				userBadges.stream()
						.map(userBadge -> userBadge.getId().getBadgeId())
						.collect(Collectors.toList());
		return badgesId.stream()
				.map(id -> badgeRepository.findById(id).orElseThrow())
				.collect(Collectors.toList());
	}

	public List<RecentPurchasesDao> getRecentPurchases() {
		List<UserBadges> userBadges =
				userBadgesRepository
						.findByStatus(Status.PURCHASED, Sort.by(Sort.Direction.DESC, "purchasedAt"));
		return userBadges.stream()
				.map(userBadge ->
						new RecentPurchasesDao(
								userBadge.getId().getUsername(),
								badgeRepository.findById(userBadge.getId().getBadgeId()).orElseThrow(),
								userBadge.getPurchasedAt()))
				.collect(Collectors.toList());
	}

	public Boolean addToCart(String id, String username) {
		try {
			badgeRepository.findById(id).orElseThrow();
			UserBadges userBadge = new UserBadges(new UserBadgeId(username, id), Status.IN_CART, new Date());
			userBadgesRepository.save(userBadge);
			log.info("Item: " + id + " added to cart for user: " + username);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public Boolean removeFromCart(String id, String username) {
		try {
			UserBadges userBadge = userBadgesRepository.findById(new UserBadgeId(username, id)).orElseThrow();
			userBadgesRepository.delete(userBadge);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public Boolean checkout(String username) {
		try {
			User user = userRepository.findById(username).orElseThrow();
			List<UserBadges> userBadges = userBadgesRepository.findById_UsernameAndStatus(username, Status.IN_CART);
			List<Badge> badges = getListCartBadges(userBadges);
			int total_cost = badges.stream().mapToInt(Badge::getCost).sum();
			if (total_cost > user.getKudosPoint()) {
				return false;
			}
			user.setKudosPoint(user.getKudosPoint() - total_cost);
			userBadges.stream().forEach(userBadge -> {
				userBadge.setPurchasedAt(new Date());
				userBadge.setStatus(Status.PURCHASED);
				userBadgesRepository.save(userBadge);
			});
			badges.stream().forEach(badge -> {
				badge.setPurchases(badge.getPurchases() + 1);
				badgeRepository.save(badge);
			});
			log.info("Purchasing successful for user: " + username);
			return true;
		} catch (NoSuchElementException e) {
			log.info("Username "+ username + " not exists");
			return false;
		}
	}
}
