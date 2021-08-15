package com.db.db_kudos.repository;

import com.db.db_kudos.model.Status;
import com.db.db_kudos.model.UserBadgeId;
import com.db.db_kudos.model.UserBadges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBadgesRepository extends JpaRepository<UserBadges, UserBadgeId> {

	List<UserBadges> findById_UsernameAndStatus(String username, Status status);

}
