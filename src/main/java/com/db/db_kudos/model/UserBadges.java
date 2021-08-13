package com.db.db_kudos.model;

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

	@Enumerated(EnumType.ORDINAL)
	private Status status;

	private Date purchasedAt;
}
