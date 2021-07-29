CREATE TABLE students (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    github_username VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

INSERT INTO students (name, email, github_username, description) VALUES ('Minta Aladár', 'minta.aladar@gmail.com', 'mintaA', 'Csatlakozott ő is');
INSERT INTO students (name, email, github_username, description) VALUES ('Minta Béla', 'minta.bela@gmail.com', 'mintaB', 'Csatlakozott ő is');
INSERT INTO students (name, email, github_username, description) VALUES ('Minta Cecil', 'minta.cecil@gmail.com', 'mintaC', 'Csatlakozott ő is');
