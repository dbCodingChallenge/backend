package com.db.db_kudos.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDAO {

	private String username;

	private List<Badge> badges;

	private double amount = 0;

	private int numberOfBadges = 0;
}
