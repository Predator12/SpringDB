
CREATE SCHEMA IF NOT EXISTS  db_spring_pashtet CHARACTER SET utf8 ;
USE db_spring_pashtet ;

CREATE TABLE IF NOT EXISTS Perfume (
  perfume_id BIGINT NOT NULL AUTO_INCREMENT,
  perfume_name VARCHAR(45) NOT NULL,
  author VARCHAR(45) NOT NULL,
  seller VARCHAR(50) NULL,
  year_of_creating INT NULL,
  amount INT NOT NULL,
  PRIMARY KEY (perfume_id)
  ) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS city (
  city_id BIGINT NOT NULL AUTO_INCREMENT,
  city VARCHAR(25) NOT NULL,
  PRIMARY KEY (city_id)
  ) ENGINE = InnoDB
AUTO_INCREMENT = 1 
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS Prodavec(
  prodavec_id BIGINT NOT NULL AUTO_INCREMENT,
  surname VARCHAR(25) NOT NULL,
  name VARCHAR(25) NOT NULL,
  email VARCHAR(45) NULL,
  city_id BIGINT NULL,
  street VARCHAR(30) NULL,
  pefrumename VARCHAR(10) NULL,
  PRIMARY KEY (prodavec_id),
  CONSTRAINT fk_prodavec_city1
    FOREIGN KEY (city_id)
    REFERENCES db_spring_pashtet.city (city_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS Prodavec_perfume (
  prodavec_id BIGINT NOT NULL,
  perfume_id BIGINT NOT NULL,
  PRIMARY KEY (prodavec_id, perfume_id),
  CONSTRAINT prodavecpermufe_ibfk_1
    FOREIGN KEY (prodavec_id)
    REFERENCES db_spring_pashtet.Prodavec (prodavec_id),
  CONSTRAINT prodavecperfume_ibfk_2
    FOREIGN KEY (perfume_id)
    REFERENCES db_spring_pashtet.Perfume (perfume_id)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS logger (
  logger_id BIGINT NOT NULL AUTO_INCREMENT,
  prodavec VARCHAR(50) NOT NULL,
  perfume VARCHAR(90) NOT NULL,
  action VARCHAR(10) NOT NULL,
  time_stamp DATETIME NOT NULL,
  user VARCHAR(50) NULL,
  PRIMARY KEY (logger_id)
) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;









