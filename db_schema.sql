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

CREATE TABLE `cprdb`.`department` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `department_name` VARCHAR(100) NULL,
  `capacity` INT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `cprdb`.`employe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(100) NULL,
  `lastname` VARCHAR(100) NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `start_date` DATETIME NULL,
  `job` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));
