CREATE TABLE badge (
    id VARCHAR(255) NOT NULL,
    name VARCHAR (225),
    description VARCHAR(255),
    cost int,
    location VARCHAR (300),
    `level` VARCHAR (30),
    purchases int,
    PRIMARY KEY (id)
);


CREATE TABLE user (
    username VARCHAR (255) NOT NULL,
    email VARCHAR (225),
    is_active bit(1),
    kudos_point int,
    name VARCHAR (300),
    PRIMARY KEY (username)
);

CREATE TABLE user_badges (
    badge_id VARCHAR (255) NOT NULL,
    username VARCHAR (255),
    purchased_at date,
    status VARCHAR (30),
    PRIMARY KEY (username, badge_id)
);
