package com.db.db_kudos.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Badge {
	@Id
	private int id;

	private String name;

	private String description;

	private int cost;

	private String location;

	Level level;

	private long purchases;
}