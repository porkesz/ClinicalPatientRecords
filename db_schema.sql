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
    
CREATE TABLE `cprdb`.`disease` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patient_id` INT NOT NULL,
  `employe_id` INT NOT NULL,
  `department_id` INT NOT NULL,
  `description` VARCHAR(255) NULL,
  `start_date` DATETIME NULL,
  `end_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `patinetFk_idx` (`patient_id` ASC),
  INDEX `employe_id_idx` (`employe_id` ASC),
  INDEX `department_id_idx` (`department_id` ASC),
  CONSTRAINT `patinetFk`
    FOREIGN KEY (`patient_id`)
    REFERENCES `cprdb`.`patient` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `employeFk`
    FOREIGN KEY (`employe_id`)
    REFERENCES `cprdb`.`employe` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `departmentFk`
    FOREIGN KEY (`department_id`)
    REFERENCES `cprdb`.`department` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO cprdb.disease (patient_id, employe_id, department_id, description, start_date, end_date)
VALUES (1, 1, 1, 'disease description', cast('2017-11-01' as date), cast('2017-11-11' as date));

CREATE TABLE `cprdb`.`employe_department` (
  `employe_id` INT NOT NULL,
  `department_id` INT NOT NULL,
  PRIMARY KEY (`employe_id`, `department_id`),
  INDEX `departmentFk_idx` (`department_id` ASC),
  CONSTRAINT `employeFk0`
    FOREIGN KEY (`employe_id`)
    REFERENCES `cprdb`.`employe` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `departmentFk0`
    FOREIGN KEY (`department_id`)
    REFERENCES `cprdb`.`department` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);