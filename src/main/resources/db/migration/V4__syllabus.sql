CREATE TABLE syllabus (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

ALTER TABLE training_classes
    ADD syllabus_id BIGINT(20),
    ADD CONSTRAINT syllabus_id FOREIGN KEY (syllabus_id) REFERENCES `mentor-tools`.syllabus (id) ON UPDATE CASCADE ON DELETE CASCADE;
