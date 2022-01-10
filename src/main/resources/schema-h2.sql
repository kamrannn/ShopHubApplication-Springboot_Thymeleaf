/*SET
MODE MySQL;
SET
IGNORECASE=TRUE;*/

CREATE TABLE IF NOT EXISTS `business` (
    PRIMARY KEY (businessID),
    `businessID`	INTEGER(10)		NOT NULL AUTO_INCREMENT,
    `name`			VARCHAR(15)		NOT NULL,
    `description`	VARCHAR(100)	NOT NULL,
    `email`			VARCHAR(320)	NOT NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `users` (
    PRIMARY KEY (username),
    `username`	VARCHAR(50)		NOT NULL,
    `password`	VARCHAR(60) 	NOT NULL,
    `enabled`	BOOLEAN			NOT NULL,
    `email`		VARCHAR(320)	NOT NULL,
    `role`		ENUM('CUSTOMER','ADMIN','SUPERADMIN') NOT NULL DEFAULT 'CUSTOMER'
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `userSubscriptions` (
    PRIMARY KEY (userSubscriptionsID),
    `userSubscriptionsID`	INTEGER 	NOT NULL AUTO_INCREMENT,
    `username`				VARCHAR(50)	NOT NULL,
    `businessID`			INTEGER 	NOT NULL,
    FOREIGN KEY (username)		REFERENCES users (username),
    FOREIGN KEY (businessID)	REFERENCES business (businessID)
)  ENGINE=INNODB;
ALTER TABLE usersubscriptions
    ADD CONSTRAINT uq_usersubscriptions UNIQUE(username, businessID);

CREATE TABLE IF NOT EXISTS `authorities`(
    `username`	VARCHAR(50) NOT NULL,
    `authority` VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
) ENGINE=INNODB;
CREATE UNIQUE INDEX ix_auth_username ON authorities (username,authority);