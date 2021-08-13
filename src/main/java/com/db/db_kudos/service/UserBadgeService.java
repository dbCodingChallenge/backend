package com.db.db_kudos.service;

import com.db.db_kudos.model.UserBadgeId;
import com.db.db_kudos.model.UserBadges;
import com.db.db_kudos.repository.UserBadgesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserBadgeService implements AbstarctService<UserBadges, UserBadgeId> {

	@Autowired
	UserBadgesRepository userBadgesRepository;

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
}
