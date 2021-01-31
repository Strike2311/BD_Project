drop procedure if exists DodajDostawce;
drop procedure if exists GetIdKlienta;
drop procedure if exists GetIdPracownika;
drop procedure if exists GetStawkaPracownika;
drop procedure if exists PodgladStrat;
drop procedure if exists PodgladZyskow;
drop procedure if exists RejestracjaGodzin;
drop procedure if exists UsunDostawce_ID;
drop procedure if exists WyszukajPracownika;
drop procedure if exists ZmienStatusZamowieniaZwrot;
drop procedure if exists ZmienStatusZamowienia;
drop trigger if exists CREATE_ETAT_AFTER_CREATING_PRACOWNIK;
drop procedure if exists CREATE_ETAT_AFTER_CREATING_PRACOWNIK_PROCEDURE;

create
    definer = root@localhost procedure DodajDostawce(IN _nazwa varchar(45), IN _lokalizacja varchar(45),
                                                     IN _surowiec varchar(45), IN _cena float, IN _odleglosc int)
BEGIN
    INSERT INTO dostawcy(nazwa_dostawcy, lokalizacja, surowiec, cena, odleglosc)
    VALUES (_nazwa, _lokalizacja, _surowiec, _cena, _odleglosc);
END;


create
    definer = root@localhost procedure GetIdKlienta(IN _imie varchar(45), IN _nazwisko varchar(45),
                                                    IN _email varchar(45))
BEGIN
    SELECT id_klienci FROM klienci WHERE imie = _imie AND nazwisko = _nazwisko AND email = _email ORDER BY id_klienci DESC LIMIT 1;
END;

create
    definer = root@localhost procedure GetIdPracownika(IN _imie varchar(45), IN _nazwisko varchar(45),
                                                       IN _email varchar(45))
BEGIN
    SELECT id_pracownicy FROM pracownicy WHERE imie = _imie AND nazwisko = _nazwisko AND email = _email ORDER BY id_pracownicy DESC LIMIT 1;
END;



create
    definer = root@localhost procedure GetStawkaPracownika(IN _imie varchar(45), IN _nazwisko varchar(45),
                                                           IN _email varchar(45))
BEGIN
    SELECT stawka FROM pracownicy WHERE imie = _imie AND nazwisko = _nazwisko AND email = _email ORDER BY id_pracownicy DESC LIMIT 1;
END;



create
    definer = root@localhost procedure PodgladStrat()
BEGIN
    SELECT SUM(straty) AS 'SumaStrat' FROM budzet WHERE straty IS NOT NULL;
END;


create
    definer = root@localhost procedure PodgladZyskow()
BEGIN
    SELECT SUM(zyski) AS 'SumaZyskow' FROM budzet WHERE zyski IS NOT NULL;
END;


create
    definer = root@localhost procedure RejestracjaGodzin(IN _godzinyNowe varchar(45), IN _id varchar(45))
BEGIN
    UPDATE etat SET liczba_godzin = _godzinyNowe + liczba_godzin WHERE Pracownicy_id_pracownicy = _id;
END;


create
    definer = root@localhost procedure UsunDostawce_ID(IN _id int)
BEGIN
    DELETE FROM dostawcy WHERE (id_dostawcy = _id);
END;


create
    definer = root@localhost procedure WyszukajPracownika(IN _imie varchar(45), IN _nazwisko varchar(45),
                                                          IN _email varchar(45))
BEGIN
    SELECT * FROM pracownicy WHERE imie = _imie AND nazwisko = _nazwisko AND email = _email;
END;


create
    definer = root@localhost procedure ZmienStatusZamowieniaZwrot(IN _status varchar(45), IN _id varchar(45))
BEGIN
    UPDATE zamowienia SET status = _status WHERE idZamowienia = _id;
END;


create
    definer = root@localhost procedure ZmienStatusZamowienia(IN _status varchar(45), IN _id varchar(45), IN _data date)
BEGIN
    UPDATE zamowienia SET status = _status, data_nadania = _data WHERE idZamowienia = _id;
END;


DELIMITER $$
CREATE TRIGGER `CREATE_ETAT_AFTER_CREATING_PRACOWNIK`
    AFTER INSERT ON PRACOWNICY
    FOR EACH ROW
BEGIN
    SET @id_prac = new.id_pracownicy;
    SET @liczba_godzin = 0;
    call CREATE_ETAT_AFTER_CREATING_PRACOWNIK_PROCEDURE(@id_prac, @liczba_godzin);


end;

CREATE PROCEDURE `CREATE_ETAT_AFTER_CREATING_PRACOWNIK_PROCEDURE`(IN idPracownik INT,IN godziny INT)
BEGIN
    INSERT INTO `mydb`.`etat` (`Pracownicy_id_pracownicy`,`liczba_godzin`)VALUES (idPracownik, godziny);
end;
$$

