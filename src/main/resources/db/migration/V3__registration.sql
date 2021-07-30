CREATE TABLE `registrations` (
  id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  status VARCHAR(20) NOT NULL COLLATE 'utf8mb4_general_ci',
  student_id BIGINT(20) NOT NULL,
  training_class_id BIGINT(20) NOT NULL,
  CONSTRAINT `fk_class_id` FOREIGN KEY (training_class_id) REFERENCES `mentor-tools`.`training_classes` (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT `fk_student_id` FOREIGN KEY (student_id) REFERENCES `mentor-tools`.`students` (id) ON UPDATE CASCADE ON DELETE CASCADE
);


#INSERT INTO registrations (status, student_id, training_class_id) VALUES ('Active', 1, 2);
#INSERT INTO registrations (status, student_id, training_class_id) VALUES ('Exit_In_Progress', 2, 2);
#INSERT INTO registrations (status, student_id, training_class_id) VALUES ('Exit', 3, 1);