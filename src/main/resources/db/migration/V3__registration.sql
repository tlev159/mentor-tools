CREATE TABLE `registration` (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  registrations_type VARCHAR(20) NOT NULL COLLATE 'utf8mb4_general_ci',
  studentId BIGINT(20) NOT NULL,
  classId BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `studentId` (`studentId`) USING BTREE,
  INDEX `classId` (`classId`) USING BTREE,
  CONSTRAINT `FK_classId` FOREIGN KEY (`classId`) REFERENCES `mentor-tools`.`training_classes` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT `FK_stundentId` FOREIGN KEY (`studentId`) REFERENCES `mentor-tools`.`students` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);


INSERT INTO registration (registrations_type, studentId, classId) VALUES ('Active', 1, 2);
INSERT INTO registration (registrations_type, studentId, classId) VALUES ('Exit_In_Progress', 2, 2);
INSERT INTO registration (registrations_type, studentId, classId) VALUES ('Exit', 3, 1);