CREATE TABLE students (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    github_username VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    training_classId BIGINT NOT NULL,
    CONSTRAINT classId FOREIGN KEY (training_classId) REFERENCES training_classes (id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO students (name, email, github_username, description, training_classId) VALUES ('Minta Aladár', 'minta.aladar@gmail.com', 'mintaA', 'Csatlakozott ő is', 1);
INSERT INTO students (name, email, github_username, description, training_classId) VALUES ('Minta Béla', 'minta.bela@gmail.com', 'mintaB', 'Csatlakozott ő is', 2);
INSERT INTO students (name, email, github_username, description, training_classId) VALUES ('Minta Cecil', 'minta.cecil@gmail.com', 'mintaC', 'Csatlakozott ő is', 1);
