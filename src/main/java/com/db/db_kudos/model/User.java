package com.db.db_kudos.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	private String username;

	private String email;

	private String name;

	private int kudosPoint;

	private boolean isActive;
}
