package com.db.db_kudos.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UserBadgeId implements Serializable {
	private String username;
	private int badgeId;
}
