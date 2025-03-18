CREATE TABLE IF NOT EXISTS contactlog (
    id INT AUTO_INCREMENT PRIMARY KEY,
    objectid BIGINT NOT NULL,
    propertyname VARCHAR(255) NOT NULL,
    propertyvalue VARCHAR(255),
    changesource VARCHAR(255),
    eventid BIGINT,
    subscriptionid INT,
    portalid INT,
    appid INT,
    occurredat BIGINT,
    eventtype VARCHAR(255),
    attemptnumber INT
    );