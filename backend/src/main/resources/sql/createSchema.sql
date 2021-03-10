CREATE TABLE IF NOT EXISTS sport
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(256) NOT NULL,
    description VARCHAR(1024)
);

CREATE TYPE IF NOT EXISTS SEX_ENUM AS ENUM ('male', 'female');

CREATE TABLE IF NOT EXISTS horse
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(256) NOT NULL,
    description VARCHAR(1024),
    birthday    DATE NOT NULL,
    sex         SEX_ENUM NOT NULL,
    favoriteSport BIGINT,
    mother      BIGINT,
    father      BIGINT,
    FOREIGN KEY (favoriteSport) REFERENCES sport(id),
    FOREIGN KEY (mother) REFERENCES horse(id),
    FOREIGN KEY (father) REFERENCES horse(id)
);