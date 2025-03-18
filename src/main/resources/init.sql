CREATE TABLE IF NOT EXISTS contact_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    object_id BIGINT NOT NULL,
    property_name VARCHAR(255) NOT NULL,
    property_value VARCHAR(255),
    change_source VARCHAR(255),
    event_id BIGINT,
    subscription_id INT,
    portal_id INT,
    app_id INT,
    occurred_at BIGINT,
    event_type VARCHAR(255),
    attempt_number INT
    );