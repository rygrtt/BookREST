CREATE DATABASE  IF NOT EXISTS `BookTracker` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `BookTracker`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: BookTracker
-- ------------------------------------------------------
-- Server version	5.6.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Author`
--

DROP TABLE IF EXISTS `Author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Author` (
  `authorID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  PRIMARY KEY (`authorID`),
  UNIQUE KEY `firstName_2` (`firstName`,`lastName`),
  KEY `firstName` (`firstName`),
  KEY `lastName` (`lastName`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Author`
--

LOCK TABLES `Author` WRITE;
/*!40000 ALTER TABLE `Author` DISABLE KEYS */;
INSERT INTO `Author` VALUES (47,'as','as'),(48,'Gabriel','Josipovci'),(44,'Thomas','Pynchon');
/*!40000 ALTER TABLE `Author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Book_has_Author`
--

DROP TABLE IF EXISTS `Book_has_Author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book_has_Author` (
  `bookId` int(11) NOT NULL,
  `authorId` int(11) NOT NULL,
  PRIMARY KEY (`bookId`,`authorId`),
  KEY `fk_Book_has_Author_Author1_idx` (`authorId`),
  KEY `fk_Book_has_Author_Book_idx` (`bookId`),
  CONSTRAINT `fk_Book_has_Author_Author1` FOREIGN KEY (`authorId`) REFERENCES `Author` (`authorID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Book_has_Author_Book` FOREIGN KEY (`bookId`) REFERENCES `Book` (`bookId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book_has_Author`
--

LOCK TABLES `Book_has_Author` WRITE;
/*!40000 ALTER TABLE `Book_has_Author` DISABLE KEYS */;
INSERT INTO `Book_has_Author` VALUES (1,44),(51,47),(52,48);
/*!40000 ALTER TABLE `Book_has_Author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Book_has_Translator`
--

DROP TABLE IF EXISTS `Book_has_Translator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book_has_Translator` (
  `bookId` int(11) NOT NULL,
  `translatorId` int(11) NOT NULL,
  PRIMARY KEY (`bookId`,`translatorId`),
  KEY `fk_Book_has_Translator_Translator1_idx` (`translatorId`),
  KEY `fk_Book_has_Translator_Book1_idx` (`bookId`),
  CONSTRAINT `fk_Book_has_Translator_Book1` FOREIGN KEY (`bookId`) REFERENCES `Book` (`bookId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Book_has_Translator_Translator1` FOREIGN KEY (`translatorId`) REFERENCES `Translator` (`translatorId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book_has_Translator`
--

LOCK TABLES `Book_has_Translator` WRITE;
/*!40000 ALTER TABLE `Book_has_Translator` DISABLE KEYS */;
INSERT INTO `Book_has_Translator` VALUES (51,23);
/*!40000 ALTER TABLE `Book_has_Translator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Note`
--

DROP TABLE IF EXISTS `Note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Note` (
  `noteId` int(11) NOT NULL AUTO_INCREMENT,
  `pageNoteBegins` int(11) DEFAULT NULL,
  `pageNoteEnds` int(11) DEFAULT NULL,
  `text` longtext,
  `bookId` int(11) NOT NULL,
  PRIMARY KEY (`noteId`),
  KEY `fk_Note_Book1_idx` (`bookId`),
  CONSTRAINT `fk_Note_Book1` FOREIGN KEY (`bookId`) REFERENCES `Book` (`bookId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Note`
--

LOCK TABLES `Note` WRITE;
/*!40000 ALTER TABLE `Note` DISABLE KEYS */;
INSERT INTO `Note` VALUES (4,1,0,'A screaming came across the sky.',1),(6,489,490,'Pitching and rolling furiously, the Anubis drives northward. Lightning flickers all around the horizon, and thunder that remind the military men on board of drumfire announcing battles they\'re not sure now if they survived or still dream, still can wake up into and die . . . .',1),(7,3,4,'asdf',51),(8,17,17,'It is the dark, hard, tobacco-starved, headachy, sour-stomach middle of the day, a million bureaucrats are diligently plotting death and some of them even know it, many about now are already into the send or third pint or highball glass, which produces a certain desperate aura around here.',1),(9,144,144,'...there will always be a gap between understanding and lived actuality.',52);
/*!40000 ALTER TABLE `Note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Translator`
--

DROP TABLE IF EXISTS `Translator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Translator` (
  `translatorId` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  PRIMARY KEY (`translatorId`),
  UNIQUE KEY `firstName_2` (`firstName`,`lastName`),
  KEY `firstName` (`firstName`),
  KEY `lastName` (`lastName`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Translator`
--

LOCK TABLES `Translator` WRITE;
/*!40000 ALTER TABLE `Translator` DISABLE KEYS */;
INSERT INTO `Translator` VALUES (23,'asd','asd'),(15,'Lily','Hammel');
/*!40000 ALTER TABLE `Translator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `userPw` varchar(45) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userName_UNIQUE` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'fidel','castro'),(3,'tom','bombadil'),(4,'John','brown');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `yearPublished` smallint(4) NOT NULL,
  `publisher` varchar(45) NOT NULL,
  `edition` varchar(45) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`bookId`,`userId`),
  UNIQUE KEY `User_userID` (`userId`,`title`),
  KEY `fk_Book_User1_idx` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,1,1972,'Penguin','1st','Gravity\'s Rainbow'),(51,3,0,'asd','asd','asd'),(52,4,2006,'Carcanet','1st','The Singer on the Shore');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `citationview`
--

DROP TABLE IF EXISTS `citationview`;
/*!50001 DROP VIEW IF EXISTS `citationview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `citationview` AS SELECT 
 1 AS `title`,
 1 AS `publisher`,
 1 AS `yearPublished`,
 1 AS `edition`,
 1 AS `bookId`,
 1 AS `noteId`,
 1 AS `pageNoteBegins`,
 1 AS `pageNoteEnds`,
 1 AS `lastName`,
 1 AS `firstName`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'BookTracker'
--

--
-- Final view structure for view `citationview`
--

/*!50001 DROP VIEW IF EXISTS `citationview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `citationview` AS select `B`.`title` AS `title`,`B`.`publisher` AS `publisher`,`B`.`yearPublished` AS `yearPublished`,`B`.`edition` AS `edition`,`B`.`bookId` AS `bookId`,`N`.`noteId` AS `noteId`,`N`.`pageNoteBegins` AS `pageNoteBegins`,`N`.`pageNoteEnds` AS `pageNoteEnds`,`A`.`lastName` AS `lastName`,`A`.`firstName` AS `firstName` from (((`book` `B` join `note` `N` on((`B`.`bookId` = `N`.`bookId`))) join `book_has_author` on((`B`.`bookId` = `book_has_author`.`bookId`))) join `author` `A` on((`book_has_author`.`authorId` = `A`.`authorID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-01  8:52:46
