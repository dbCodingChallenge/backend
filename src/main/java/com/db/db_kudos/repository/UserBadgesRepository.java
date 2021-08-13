package com.db.db_kudos.repository;

import com.db.db_kudos.model.UserBadgeId;
import com.db.db_kudos.model.UserBadges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBadgesRepository extends JpaRepository<UserBadges, UserBadgeId> {
}
