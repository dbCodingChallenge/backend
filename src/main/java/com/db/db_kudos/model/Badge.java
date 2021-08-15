package com.db.db_kudos.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

	@Enumerated(EnumType.ORDINAL)
	Level level;

	private long purchases;
}