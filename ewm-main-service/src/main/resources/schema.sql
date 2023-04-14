DROP TABLE IF EXISTS users;
    CREATE TABLE IF NOT EXISTS users(
                                id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                app VARCHAR(255) NOT NULL,
                                uri VARCHAR(700) NOT NULL,
                                ip VARCHAR(32) NOT NULL,
                                time_stamp TIMESTAMP WITHOUT TIME ZONE
);