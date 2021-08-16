CREATE TABLE badge (
    id int NOT NULL,
    name VARCHAR (30),
    description VARCHAR(200),
    cost int,
    location VARCHAR (300),
    `level` VARCHAR (30),
    purchases int
    PRIMARY KEY (id)
);


CREATE TABLE user (
    username VARCHAR (255) NOT NULL,
    email VARCHAR (30),
    is_active bit(1),
    kudos_point int,
    name VARCHAR (300),
    PRIMARY KEY (username)
);

CREATE TABLE user_badges (
    badge_id VARCHAR (255) NOT NULL,
    username VARCHAR (30),
    purchased_at date,
    status VARCHAR (30),
    PRIMARY KEY (username, badge_id)
);
