package com.db.db_kudos.service;

import com.db.db_kudos.model.Badge;
import com.db.db_kudos.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BadgeService implements AbstractService<Badge, String> {

	@Autowired
	BadgeRepository badgeRepository;

	@Override
	public List<Badge> findAll() {
		return badgeRepository.findAll(Sort.by(Sort.Direction.DESC, "cost"));
	}

	@Override
	public Optional<Badge> findById(String id) {
		return badgeRepository.findById(id);
	}

	@Override
	public Badge save(Badge badge) {
		UUID uuid = UUID.randomUUID();
		badge.setId(uuid.toString());
		return badgeRepository.save(badge);
	}

	@Override
	public Badge update(Badge badge) {
		return badgeRepository.save(badge);
	}

	public Badge deleteById(String id) {
		try{
			Badge badge = badgeRepository.findById(id).orElseThrow();
			badgeRepository.deleteById(id);
			return badge;
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
