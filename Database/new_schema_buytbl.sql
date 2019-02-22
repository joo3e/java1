-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: new_schema
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `buytbl`
--

DROP TABLE IF EXISTS `buytbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buytbl` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `userID` char(15) NOT NULL,
  `prodName` char(15) NOT NULL,
  `groupName` char(15) DEFAULT NULL,
  `price` char(15) NOT NULL,
  `amount` char(15) NOT NULL,
  PRIMARY KEY (`num`),
  KEY `userID` (`userID`),
  CONSTRAINT `buytbl_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `usertbl` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buytbl`
--

LOCK TABLES `buytbl` WRITE;
/*!40000 ALTER TABLE `buytbl` DISABLE KEYS */;
INSERT INTO `buytbl` VALUES (1,'KBS','운동화',NULL,'30','2'),(2,'KBS','노트북','전자','1000','1'),(3,'JYP','모니터','전자','200','1'),(4,'BBK','모니터','전자','200','5'),(5,'KBS','청바지','의류','50','3'),(6,'BBK','메모리','전자','80','10'),(7,'SSK','책','서적','15','5'),(8,'EJW','책','서적','15','2'),(9,'EJW','청바지','의류','50','1'),(10,'BBK','운동화',NULL,'30','2'),(11,'EJW','책','서적','15','1'),(12,'BBK','운동화',NULL,'30','2');
/*!40000 ALTER TABLE `buytbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-22 15:28:22
