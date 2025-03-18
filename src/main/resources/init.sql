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