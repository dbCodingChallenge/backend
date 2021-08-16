package com.db.db_kudos.model;

import com.db.db_kudos.model.dao.Level;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Badge {
	@Id
	private String id;

	private String name;

	private String description;

	private int cost;

	private String location;

	@Enumerated(EnumType.STRING)
	private Level level;

	private long purchases;
}