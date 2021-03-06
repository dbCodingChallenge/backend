package com.db.db_kudos.model;

import com.db.db_kudos.model.dao.Status;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@Entity
public class UserBadges {

	@EmbeddedId
	private UserBadgeId id;

	@Enumerated(EnumType.STRING)
	private Status status;

	private Date purchasedAt;
}
