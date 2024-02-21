DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
	ID			UUID			NOT NULL PRIMARY KEY,
	USER_ID		VARCHAR(255)	NOT NULL UNIQUE,
	USERNAME	VARCHAR(255)	NOT NULL,
	PASSWORD	VARCHAR(255)	NOT NULL,
	ROLE		VARCHAR(100)	NOT	NULL
);
