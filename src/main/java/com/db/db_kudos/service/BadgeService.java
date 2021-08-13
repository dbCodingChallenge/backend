package com.db.db_kudos.service;

import com.db.db_kudos.model.Badge;
import com.db.db_kudos.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BadgeService implements AbstarctService<Badge, Integer>{

	@Autowired
	BadgeRepository badgeRepository;

	@Override
	public List<Badge> findAll() {
		return badgeRepository.findAll();
	}

	@Override
	public Optional<Badge> findById(Integer id) {
		return badgeRepository.findById(id);
	}

	@Override
	public Badge saveOrUpdate(Badge badge) {
		return badgeRepository.save(badge);
	}

	@Override
	public boolean deleteById(Integer id) {
		try{
			badgeRepository.deleteById(id);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}