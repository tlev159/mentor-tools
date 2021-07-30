CREATE TABLE syllabus (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
#   training_class_id BIGINT NOT NULL,
#   CONSTRAINT fk_training_class_id FOREIGN KEY (training_class_id) REFERENCES `mentor-tools`.training_classes (id) ON UPDATE CASCADE ON DELETE CASCADE
);

ALTER TABLE training_classes
    ADD syllabus_id BIGINT(20),
    ADD CONSTRAINT syllabus_id FOREIGN KEY (syllabus_id) REFERENCES `mentor-tools`.syllabus (id) ON UPDATE CASCADE ON DELETE CASCADE;
