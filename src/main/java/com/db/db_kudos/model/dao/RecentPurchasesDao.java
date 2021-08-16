package com.db.db_kudos.model.dao;

import com.db.db_kudos.model.Badge;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentPurchasesDao {
	String username;
	Badge badge;
	Date purchasedAt;
}
