package com.db.db_kudos.model.dao;

import com.db.db_kudos.model.Badge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShoppingListDao {
	private Badge badge;
	Status status;
}
