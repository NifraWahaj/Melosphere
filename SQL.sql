create database SDAProjectP1;
use SDAProjectP1;

drop table Listener;
create table Listener(
Lname varchar(50) NOT NULL UNIQUE PRIMARY KEY,
LPassword varchar(50) NOT NULL,
LEmail varchar(255) NOT NULL
);
select * from listener;
select * from Artist;
drop table Artist;
create table Artist(
AName varchar(50) NOT NULL UNIQUE PRIMARY KEY,
APassword varchar(50) NOT NULL,
AEmail varchar(255) NOT NULL,
ABio mediumtext
);

drop table Song;
create table Song(
SongID int NOT NULL auto_increment primary key,
SName varchar(255) NOT NULL,
AName varchar(255) NOT NULL,
SongFile mediumblob,
Genre varchar(100),
ReleaseDate date,
streams int,
foreign key (AName) references Artist(AName)
ON DELETE CASCADE
ON UPDATE CASCADE
);

drop table FollowingArtist;
CREATE TABLE FollowingArtist (
    LName VARCHAR(50) NOT NULL,
    AName VARCHAR(50) NOT NULL,
    PRIMARY KEY (LName, AName),
    FOREIGN KEY (LName) REFERENCES Listener (LName)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (AName) REFERENCES Artist (AName)
	ON DELETE CASCADE
    ON UPDATE CASCADE
);

drop table FavSongs;
CREATE TABLE FavSongs
(
SongID int,
LName varchar(50),
CONSTRAINT PK_FavSongs PRIMARY KEY (SongID,LName),
foreign key (SongID) references Song(SongID)
ON DELETE CASCADE
ON UPDATE CASCADE,

foreign key (Lname) references Listener(Lname)
ON DELETE CASCADE
ON UPDATE CASCADE
);

drop table LPlaylist;
CREATE TABLE LPlaylist (
    PlaylistID int NOT NULL auto_increment primary key,
    PName varchar(255) NOT NULL,
    PDescription varchar(255),
    LName varchar(50),
    INDEX (LName),
    UNIQUE (PName, LName),  -- Added a unique constraint to ensure playlist names are unique per listener
    FOREIGN KEY (LName) REFERENCES Listener(LName)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

drop table LPSongs;
CREATE TABLE LPSongs (
    PName varchar(255) NOT NULL,
    LName varchar(50) NOT NULL,
    SongID int NOT NULL,
    PRIMARY KEY (PName, LName, SongID),
    FOREIGN KEY (PName) REFERENCES LPlaylist (PName)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (LName) REFERENCES LPlaylist (LName)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (SongID) REFERENCES Song (SongID)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
    INDEX (PName),
    INDEX (LName),
    INDEX (SongID)
);


drop table APlaylist;
CREATE TABLE APlaylist (
    PlaylistID int NOT NULL auto_increment primary key,
    PName varchar(255) NOT NULL,
    PDescription varchar(255),
    AName varchar(50),
    INDEX (AName),
    UNIQUE (PName, AName),  -- Added a unique constraint to ensure playlist names are unique per listener
    FOREIGN KEY (AName) REFERENCES Artist(AName)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

drop table APSongs;
CREATE TABLE APSongs (
    PName varchar(255) NOT NULL,
    AName varchar(50) NOT NULL,
    SongID int NOT NULL,
    PRIMARY KEY (PName, AName, SongID),
    FOREIGN KEY (PName) REFERENCES APlaylist (PName)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (AName) REFERENCES APlaylist (AName)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (SongID) REFERENCES Song (SongID)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
    INDEX (PName),
    INDEX (AName),
    INDEX (SongID)
);














use SDAProjectP1;
delete from FavSongs where LName = 'lolo';
select * FROM Listener;
SELECT * FROM Song;
select * from FavSongs;
SELECT * FROM Artist;
select * from LPlaylist;

SHOW GRANTS FOR 'root'@'localhost';
SHOW PROCEDURE STATUS WHERE Db = 'sdaprojectp1';

drop procedure loginListener;
DELIMITER $$
CREATE PROCEDURE loginListener(IN LNameIn varchar(50), IN LPasswordIn varchar(50), IN LEmailIn varchar(255), OUT status BIT(1))
BEGIN
    DECLARE listenerCount INT;
    SET listenerCount = (SELECT COUNT(*) FROM Listener WHERE Lname = LNameIn AND LPassword = LPasswordIn AND LEmail = LEmailIn);

    IF listenerCount = 0 THEN
        SET status = false;
    ELSE
        SET status = true;
    END IF;
END$$
DELIMITER ;

drop procedure signupListener;
DELIMITER $$
CREATE PROCEDURE signupListener(
    IN LNameIn varchar(50), 
    IN LPasswordIn varchar(50), 
    IN LEmailIn varchar(255), 
    OUT status BIT(1)
)
BEGIN 
    DECLARE listenerCount, emailCount INT;
    SELECT COUNT(*) INTO listenerCount FROM Listener WHERE Lname = LNameIn;
    SELECT COUNT(*) INTO emailCount FROM Listener WHERE LEmail = LEmailIn;
    IF listenerCount > 0 OR emailCount > 0 THEN
        SET status = false;
    ELSE
        INSERT INTO Listener(Lname, LPassword, LEmail) VALUES (LNameIn, LPasswordIn, LEmailIn);
        SET status = true;
    END IF;
END$$
DELIMITER ;

drop procedure loginArtist;
DELIMITER $$
CREATE PROCEDURE loginArtist(IN ANameIn varchar(50), IN APasswordIn varchar(50), IN AEmailIn varchar(255), OUT status BIT(1))
BEGIN
    DECLARE ArtistCount INT;
    SET ArtistCount = (SELECT COUNT(*) FROM Artist WHERE AName = ANameIn AND APassword = APasswordIn AND AEmail = AEmailIn);

    IF ArtistCount = 0 THEN
        SET status = false;
    ELSE
        SET status = true;
    END IF;
END$$
DELIMITER ;


drop procedure signupArtist;
DELIMITER $$
CREATE PROCEDURE signupArtist(
    IN ANameIn varchar(50), 
    IN APasswordIn varchar(50), 
    IN AEmailIn varchar(255), 
    IN ABioIn mediumtext,
    OUT status BIT(1)
)
BEGIN 
    DECLARE artistCount, emailCount INT;
    SELECT COUNT(*) INTO artistCount FROM Artist WHERE AName = ANameIn;
    SELECT COUNT(*) INTO emailCount FROM Artist WHERE AEmail = AEmailIn;
    IF artistCount > 0 OR emailCount > 0 THEN
        SET status = false;
    ELSE
        INSERT INTO Artist(AName, APassword, AEmail, ABio) VALUES (ANameIn, APasswordIn, AEmailIn, ABioIn);
        SET status = true;
    END IF;
END$$
DELIMITER ;

drop procedure add_favorite_song;
DELIMITER $$
CREATE PROCEDURE add_favorite_song (
    IN song_name VARCHAR(255),
    IN listener_name VARCHAR(50),
    IN artist_name VARCHAR(255),
    OUT success BOOLEAN
)
BEGIN
    DECLARE song_id INT;
    SELECT SongID INTO song_id FROM Song WHERE SName = song_name AND AName = artist_name;
    IF song_id IS NULL THEN
        SET success = FALSE;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Song not found';
    ELSE
        INSERT INTO FavSongs (SongID, LName) VALUES (song_id, listener_name);
        SET success = TRUE;
    END IF;
END$$
DELIMITER ;

drop procedure check_duplicate_fav_song;
DELIMITER $$
CREATE TRIGGER check_duplicate_fav_song
BEFORE INSERT ON FavSongs
FOR EACH ROW
BEGIN
    DECLARE duplicate_found BOOLEAN DEFAULT FALSE;
    IF EXISTS(SELECT * FROM FavSongs WHERE SongID = NEW.SongID AND LName = NEW.LName) THEN
        SET duplicate_found = TRUE;
    END IF;
END$$
DELIMITER ;

drop procedure getRandomSongs;
DELIMITER $$
CREATE PROCEDURE getRandomSongs()
BEGIN
    SELECT SName, Genre, AName, ReleaseDate, streams 
    FROM Song 
    ORDER BY RAND() 
    LIMIT 5;
END$$
DELIMITER ;

drop procedure getRandomArtists;
DELIMITER $$
CREATE PROCEDURE getRandomArtists()
BEGIN
    SELECT AName, ABio 
    FROM Artist 
    ORDER BY RAND() 
    LIMIT 4;
END$$
DELIMITER ;

drop procedure getFavSongs;
DELIMITER $$
CREATE PROCEDURE getFavSongs (
    IN listener_name VARCHAR(50)
)
BEGIN
    SELECT S.SName, S.AName, S.Genre, S.ReleaseDate, S.streams 
    FROM Song S 
    INNER JOIN FavSongs F ON S.SongID = F.SongID
    WHERE F.LName = listener_name;
END$$
DELIMITER ;

drop procedure getListenerProfile;
DELIMITER $$
CREATE PROCEDURE getListenerProfile (
    IN listener_name VARCHAR(50)
)
BEGIN
    SELECT * FROM Listener WHERE LName = listener_name;
END$$
DELIMITER ;

drop procedure getArtistProfile;
DELIMITER $$
CREATE PROCEDURE getArtistProfile (
    IN artist_name VARCHAR(50)
)
BEGIN
    SELECT * FROM Artist WHERE AName = artist_name;
END$$
DELIMITER ;

drop procedure getTopSongs;
DELIMITER $$
CREATE PROCEDURE getTopSongs(IN artistName VARCHAR(50))
BEGIN
  SELECT SName, Genre, ReleaseDate, streams 
  FROM Song 
  WHERE AName = artistName 
  ORDER BY streams DESC LIMIT 5;
END $$
DELIMITER ;

drop procedure getArtistSongs;
DELIMITER $$
CREATE PROCEDURE getArtistSongs(IN artistName VARCHAR(50))
BEGIN
  SELECT SName, Genre, ReleaseDate, streams 
  FROM Song 
  WHERE AName = artistName 
  ORDER BY streams DESC;
END$$
DELIMITER ;

drop procedure getArtistPlaylist;
DELIMITER $$
CREATE PROCEDURE getArtistPlaylist(IN name VARCHAR(50))
BEGIN
SELECT PName, PDescription FROM LPlaylist WHERE LName = name;
END $$
DELIMITER ;

drop procedure ListenerCreatePlaylist;
DELIMITER $$
CREATE PROCEDURE ListenerCreatePlaylist(IN LisName VARCHAR(50), IN P_Name VARCHAR(255), IN P_Des VARCHAR(255), OUT Result Int)
BEGIN 
DECLARE Lis_Count int;
SELECT Count(*) INTO Lis_Count FROM Listener WHERE LName = LisName;
IF Lis_Count = 0 THEN SET Result = 0;
END IF;
IF Length(P_Name) > 255 THEN SET Result = 0;
END IF;
IF Length(P_Des) > 255 THEN SET Result = 0;
END IF;
INSERT INTO LPlaylist(PName,PDescription,LName) VALUES (P_Name,P_Des,LisName);
SET RESULT = 1;
END$$
DELIMITER ;

drop procedure InsertFollowArtist;
DELIMITER $$
CREATE PROCEDURE InsertFollowArtist(IN L_Name VARCHAR(50), IN A_Name VARCHAR(50), OUT result INT)
BEGIN
    DECLARE exit HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SET result = 0;
    END;
    
    START TRANSACTION;
    SET result = 1;
    SELECT COUNT(*) INTO @count FROM FollowingArtist WHERE LName = L_Name AND AName = A_Name;
    IF @count > 0 THEN
        SET result = 0;
    ELSE
        INSERT INTO FollowingArtist (LName, AName) VALUES (L_Name, A_Name);
    END IF;
    
    COMMIT;
END$$
DELIMITER ;


drop PROCEDURE UnFollowArtist;
DELIMITER $$
CREATE PROCEDURE UnFollowArtist(IN L_Name VARCHAR(50), IN A_Name VARCHAR(50), OUT result INT)
BEGIN
DECLARE exit HANDLER FOR SQLEXCEPTION
BEGIN
ROLLBACK;
SET result = 0;
END;
START TRANSACTION;
SET result = 1;
SELECT COUNT(*) INTO @count FROM FollowingArtist WHERE LName = L_Name AND AName = A_Name;

IF @count = 0 THEN
    SET result = 0;
ELSE
    DELETE FROM FollowingArtist WHERE LName = L_Name AND AName = A_Name;
END IF;
COMMIT;
END$$
DELIMITER ;


drop procedure check_following_artist;
DELIMITER $$
CREATE PROCEDURE check_following_artist(IN listener_name VARCHAR(50), IN artist_name VARCHAR(50), OUT result INT)
BEGIN
    SELECT COUNT(*) INTO result FROM FollowingArtist WHERE LName = listener_name AND AName = artist_name;
END$$
DELIMITER ;

drop procedure GetFollowedArtists;
DELIMITER $$
CREATE PROCEDURE GetFollowedArtists(IN listener_name VARCHAR(50))
BEGIN
    SELECT Artist.AName, Artist.ABio FROM FollowingArtist
    JOIN Artist ON FollowingArtist.AName = Artist.AName
    WHERE FollowingArtist.LName = listener_name;
END$$
DELIMITER ;

drop procedure getPlaylistSongs;
DELIMITER $$
CREATE PROCEDURE getPlaylistSongs(IN L_Name VARCHAR(50), IN P_Name VARCHAR(255))
BEGIN
    SELECT s.SName, a.AName, s.Genre, s.ReleaseDate, s.streams
    FROM LPlaylist p
    JOIN LPSongs ps ON p.PName = ps.PName AND p.LName = ps.LName
    JOIN Song s ON ps.SongID = s.SongID
    JOIN Artist a ON s.AName = a.AName
    WHERE p.LName = L_Name AND p.PName = P_Name;
END $$
DELIMITER ; 

drop procedure insertSongIntoPlaylist;
DELIMITER $$
CREATE PROCEDURE insertSongIntoPlaylist(IN L_Name VARCHAR(50), IN P_Name VARCHAR(255), IN S_Name VARCHAR(255), IN A_Name VARCHAR(50), OUT success INT)
BEGIN
    DECLARE song_id INT;
    SELECT SongID INTO song_id FROM Song WHERE SName = S_Name AND AName = A_Name;
    
    IF song_id IS NULL THEN
        SET success = 0;
    ELSE
        INSERT INTO LPSongs(PName, LName, SongID) VALUES (P_Name, L_Name, song_id);
        SET success = 1;
    END IF;
END $$
DELIMITER ;


drop procedure uploadSong;
DELIMITER $$
CREATE PROCEDURE uploadSong(
    IN SongName VARCHAR(255),
    IN ArtistName VARCHAR(255),
    IN SongFile MEDIUMBLOB,
    IN Genre VARCHAR(100),
    IN ReleaseDate DATE,
    OUT Result INT
)
BEGIN
    DECLARE songCount INT;
    SELECT COUNT(*) INTO songCount
    FROM Song
    WHERE SName = SongName AND AName = ArtistName;
    
    IF songCount > 0 THEN
        SET Result = -1; -- song name already exists for that artist
    ELSEIF SongName = '' OR ArtistName = '' OR SongFile IS NULL THEN
        SET Result = -2; -- song name, artist name, or song file is empty
    ELSE
        INSERT INTO Song (SName, AName, SongFile, Genre, ReleaseDate)
        VALUES (SongName, ArtistName, SongFile, Genre, ReleaseDate);
        
        SET Result = 1;
    END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE deleteListener(IN listener_name VARCHAR(50), OUT result INT)
BEGIN
    DECLARE exit handler for sqlexception
    BEGIN
        ROLLBACK;
        SET result = -1;
    END;
    START TRANSACTION;
    DELETE FROM LPSongs WHERE LName = listener_name;
    DELETE FROM FavSongs WHERE LName = listener_name;
    DELETE FROM FollowingArtist WHERE LName = listener_name;
    DELETE FROM LPlaylist WHERE LName = listener_name;
    DELETE FROM Listener WHERE LName = listener_name;
    SET result = 1;
    COMMIT;
END$$
DELIMITER ;

drop procedure deleteArtist;
DELIMITER $$
CREATE PROCEDURE deleteArtist(
  IN artistName VARCHAR(50),
  OUT numDeleted INT
)
BEGIN
  DECLARE EXIT HANDLER FOR SQLEXCEPTION
  BEGIN
    SELECT -1 INTO numDeleted;
  END;

  START TRANSACTION;
  DELETE FROM FavSongs WHERE SongID IN (
    SELECT SongID FROM Song WHERE AName = artistName
  );
  DELETE FROM LPSongs WHERE SongID IN (
    SELECT SongID FROM Song WHERE AName = artistName
  );
  DELETE FROM Song WHERE AName = artistName;
  DELETE FROM FollowingArtist WHERE AName = artistName;
  DELETE FROM Artist WHERE AName = artistName;
  SELECT ROW_COUNT() INTO numDeleted;
  SELECT 1 INTO numDeleted;
  COMMIT;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE delete_playlist(IN in_listener_name varchar(50), IN in_playlist_name varchar(255), OUT out_deleted boolean)
BEGIN
    DECLARE playlist_id INT;
    SET out_deleted = FALSE;
    SELECT PlaylistID INTO playlist_id FROM LPlaylist WHERE PName = in_playlist_name AND LName = in_listener_name;
    IF playlist_id IS NULL THEN
        SET out_deleted = FALSE;
    ELSE
        DELETE FROM LPSongs WHERE PName = in_playlist_name AND LName = in_listener_name;
        DELETE FROM LPlaylist WHERE PName = in_playlist_name AND LName = in_listener_name;
        SET out_deleted = TRUE;
    END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE remove_song_from_favorites(IN p_listener_name VARCHAR(50), IN p_artist_name VARCHAR(50), IN p_song_name VARCHAR(255), OUT p_success BOOLEAN)
BEGIN
    DECLARE v_song_id INT;
    DECLARE v_listener_count INT;
    SELECT SongID INTO v_song_id FROM Song WHERE AName = p_artist_name AND SName = p_song_name;
    SELECT COUNT(*) INTO v_listener_count FROM FavSongs WHERE LName = p_listener_name AND SongID = v_song_id;
    IF v_listener_count = 0 THEN
        SET p_success = FALSE;
    ELSE
        DELETE FROM FavSongs WHERE LName = p_listener_name AND SongID = v_song_id;
        SET p_success = TRUE;
    END IF;
END $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE remove_song_from_playlist(
    IN p_listener_name VARCHAR(50),
    IN p_artist_name VARCHAR(50),
    IN p_song_name VARCHAR(255),
    IN p_playlist_name VARCHAR(255),
    OUT p_success BOOLEAN
)
BEGIN
    DECLARE v_song_id INT;
    DECLARE v_playlist_id INT;
    SELECT SongID INTO v_song_id FROM Song WHERE AName = p_artist_name AND SName = p_song_name;
    SELECT PlaylistID INTO v_playlist_id FROM LPlaylist WHERE LName = p_listener_name AND PName = p_playlist_name;
    IF v_song_id IS NULL OR v_playlist_id IS NULL THEN
        SET p_success = FALSE;
    ELSE
        DELETE FROM LPSongs WHERE LName = p_listener_name AND PName = p_playlist_name AND SongID = v_song_id;
        SET p_success = TRUE;
    END IF;
END $$
DELIMITER ;

DROP TRIGGER IF EXISTS delete_song_cascade;
DELIMITER $$
CREATE TRIGGER delete_song_cascade
BEFORE DELETE ON Song
FOR EACH ROW
BEGIN
    DELETE FROM FavSongs WHERE SongID = OLD.SongID;
    DELETE FROM LPSongs WHERE SongID = OLD.SongID;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER deleteSong
BEFORE DELETE ON Song
FOR EACH ROW
BEGIN
    DELETE FROM FavSongs WHERE SongID = OLD.SongID;
    DELETE FROM LPSongs WHERE SongID = OLD.SongID;
END$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE callDeleteSong(IN artist_name varchar(255), IN song_name varchar(255), OUT success boolean)
BEGIN
    DECLARE song_id INT;
    SELECT SongID INTO song_id FROM Song WHERE AName = artist_name AND SName = song_name;
    IF song_id IS NOT NULL THEN
        DELETE FROM Song WHERE SongID = song_id;
        SET success = TRUE;
    ELSE
        SET success = FALSE;
    END IF;
END$$
DELIMITER 







select * from song;
select * from listener;
select * from artist;