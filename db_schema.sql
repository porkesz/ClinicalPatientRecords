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
  
INSERT INTO cprdb.employe (firstname, lastname, email, password, start_date, job)
VALUES ('Anna', 'Szabó', 'anna@a.hu', 'anna', cast('2015-11-01' as date), 'doctor');

CREATE TABLE `cprdb`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`));
  
CREATE TABLE `cprdb`.`employe_role` (
  `employe_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`employe_id`, `role_id`),
  INDEX `fkRoleId_idx` (`role_id` ASC),
  CONSTRAINT `fkEmployeId`
    FOREIGN KEY (`employe_id`)
    REFERENCES `cprdb`.`employe` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fkRoleId`
    FOREIGN KEY (`role_id`)
    REFERENCES `cprdb`.`role` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);  