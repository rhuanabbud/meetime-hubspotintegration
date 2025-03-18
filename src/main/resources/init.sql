CREATE TABLE IF NOT EXISTS contactlog (
    id INT AUTO_INCREMENT PRIMARY KEY,
    objectid BIGINT NOT NULL,
    changesource VARCHAR(255),
    eventid BIGINT,
    subscriptionid INT,
    portalid INT,
    appid INT,
    occurredat BIGINT,
    attemptnumber INT
    );

CREATE TABLE IF NOT EXISTS contact (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    datainclusao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );