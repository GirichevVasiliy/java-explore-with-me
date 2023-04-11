DROP TABLE IF EXISTS hits;
    CREATE TABLE IF NOT EXISTS hits(
                                id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                app VARCHAR(255) NOT NULL,
                                uri VARCHAR(700) NOT NULL,
                                ip VARCHAR(150) NOT NULL,
                                time_stamp TIMESTAMP WITHOUT TIME ZONE

);