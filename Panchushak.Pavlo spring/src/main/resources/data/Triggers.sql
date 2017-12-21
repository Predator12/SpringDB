USE db_spring_pashtet;

DELIMITER //
CREATE TRIGGER AfterInsertProdavecPerfume
AFTER INSERT
ON prodavec_perfume FOR EACH ROW
BEGIN
	DECLARE name_prodavec VARCHAR(50);
    DECLARE name_perfume VARCHAR(90);
    SELECT CONCAT(surname, ' ', name) INTO name_prodavec
    FROM prodavec WHERE prodavec.prodavec_id=new.prodavec_id;
    SELECT CONCAT(perfume_name, ' / ', Author) INTO name_perfume
    FROM perfume WHERE perfume.perfume_id=new.perfume_id;
	INSERT INTO logger (prodavec, perfume, action,
								time_stamp, user)
	VALUES(name_prodavec,  name_perfume, 'GOT', NOW(), USER() );
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER AfterDeleteProdavecPerfume
AFTER DELETE
ON prodavec_perfume FOR EACH ROW
BEGIN
	DECLARE name_prodavec VARCHAR(50);
    DECLARE name_perfume VARCHAR(90);
    SELECT CONCAT(surname, ' ', name) INTO name_prodavec
    FROM prodavec WHERE prodavec.prodavec_id=old.prodavec_id;
    SELECT CONCAT(perfume_name, ' / ', author) INTO name_perfume
    FROM perfume WHERE perfume.perfume_id=old.perfume_id;
	INSERT INTO Logger (prodavec, perfume, action,
                      time_stamp, user)
	VALUES(name_prodavec,  name_perfume, 'GAVEBACK', NOW(), USER() );
END //
DELIMITER ;






