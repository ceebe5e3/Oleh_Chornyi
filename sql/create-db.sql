SET SESSION sql_mode='NO_AUTO_VALUE_ON_ZERO';

CREATE DATABASE provider;
USE provider;

CREATE TABLE IF NOT EXISTS roles (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(10) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
    id INTEGER NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(70) NOT NULL,
	date_of_birth DATE NOT NULL,
	address VARCHAR(50) NOT NULL,
	email VARCHAR(60) NOT NULL UNIQUE,
	login VARCHAR(35) NOT NULL UNIQUE,
	password VARCHAR(128) NOT NULL,
	role_id INTEGER DEFAULT NULL,
	FOREIGN KEY (role_id) REFERENCES roles(id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tariffs (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS services (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS service_tariffs (
    id INTEGER NOT NULL AUTO_INCREMENT,
    service_id INTEGER NOT NULL,
    tariff_id INTEGER NOT NULL,
    price DOUBLE NOT NULL,
    description VARCHAR(200) NOT NULL,
    FOREIGN KEY (service_id) REFERENCES services(id),
    FOREIGN KEY (tariff_id) REFERENCES tariffs(id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS contracts (
    id INTEGER NOT NULL AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    service_tariff_id INTEGER NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (service_tariff_id) REFERENCES service_tariffs(id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS account (
    id INTEGER NOT NULL AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    money DOUBLE NOT NULL DEFAULT '0.0',
    is_blocked VARCHAR(12) NOT NULL,
    is_blocked_by_admin VARCHAR(12) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    PRIMARY KEY (id)
);


ALTER TABLE account
    ALTER is_blocked SET DEFAULT 'UNBLOCKED',
    ALTER is_blocked_by_admin SET DEFAULT 'UNBLOCKED';


CREATE TRIGGER payment
    AFTER INSERT ON contracts
    FOR EACH ROW UPDATE account
    SET money=money-(SELECT price from service_tariffs st JOIN contracts c ON c.service_tariff_id=st.id JOIN users u ON u.id=c.user_id
        WHERE c.user_id=NEW.user_id AND c.service_tariff_id=NEW.service_tariff_id)
    WHERE user_id=NEW.user_id;


DELIMITER $$

CREATE TRIGGER blocker
    BEFORE UPDATE ON account
    FOR EACH ROW
BEGIN
    IF NEW.money<0
    THEN SET NEW.is_blocked="BLOCKED";
    ELSEIF NEW.money>=0
    THEN SET NEW.is_blocked="UNBLOCKED";
    END IF;
END;$$

CREATE TRIGGER account_rules
    BEFORE INSERT ON account
    FOR EACH ROW
BEGIN
    IF (SELECT roles.id FROM roles JOIN users ON users.role_id=roles.id WHERE users.id=NEW.user_id)=0
    THEN signal sqlstate '45000' SET MESSAGE_TEXT='ADMIN CANNOT HAVE AN ACCOUNT';
    END IF;
END;$$

DELIMITER ;

SET GLOBAL event_scheduler = ON;

DELIMITER $$

DROP EVENT IF EXISTS monthlyPayment $$

CREATE DEFINER=`root`@`localhost` EVENT `monthlyPayment` ON SCHEDULE EVERY 1 MONTH STARTS '2020-04-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE account SET money=money-(SELECT SUM(service_tariffs.price) FROM contracts JOIN service_tariffs ON service_tariffs.id=service_tariff_id
    WHERE contracts.user_id=account.user_id) WHERE user_id IN (SELECT user_id FROM contracts)$$

DELIMITER ;