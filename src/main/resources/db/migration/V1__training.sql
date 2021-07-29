CREATE TABLE training_class (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  start_date DATE,
  end_date DATE
);

INSERT INTO training_class (name, start_date, end_date) VALUES ('Struktúraváltó', '2021-01-01', '2021-02-02');
INSERT INTO training_class (name, start_date, end_date) VALUES ('Haladó', '2021-04-01', '2021-07-02');