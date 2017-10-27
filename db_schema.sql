CREATE DATABASE IF NOT EXISTS 'cprdb';
USE 'cprdb';

DROP TABLE IF EXISTS 'patient';

CREATE TABLE `cprdb`.`patient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(100) NULL,
  `lastname` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `telephone` VARCHAR(45) NULL,
  `social_security_number` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

