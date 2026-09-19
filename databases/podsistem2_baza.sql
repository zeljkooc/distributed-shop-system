CREATE DATABASE  IF NOT EXISTS `podsistem2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `podsistem2`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: podsistem2
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `artikal`
--

DROP TABLE IF EXISTS `artikal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artikal` (
  `idArtikla` int NOT NULL AUTO_INCREMENT,
  `nazivArtikla` varchar(45) NOT NULL,
  `opisArtikla` varchar(45) NOT NULL,
  `cenaArtikla` int NOT NULL,
  `popust` int NOT NULL,
  `idKategorije` int NOT NULL,
  `idKorisnika` int NOT NULL,
  PRIMARY KEY (`idArtikla`),
  KEY `idKategorije_idx` (`idKategorije`),
  CONSTRAINT `idKategorije` FOREIGN KEY (`idKategorije`) REFERENCES `kategorija` (`idKategorije`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artikal`
--

LOCK TABLES `artikal` WRITE;
/*!40000 ALTER TABLE `artikal` DISABLE KEYS */;
INSERT INTO `artikal` VALUES (1,'Laptop','ASUS Laptop',65000,10,2,1),(2,'Racunar','Intel i9, 32GB, RTX 4060',200000,20,1,1);
/*!40000 ALTER TABLE `artikal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artikli_u_korpi`
--

DROP TABLE IF EXISTS `artikli_u_korpi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artikli_u_korpi` (
  `idStavke` int NOT NULL AUTO_INCREMENT,
  `kolicina` int NOT NULL,
  `idKorpeFK` int NOT NULL,
  `idArtiklaFK` int NOT NULL,
  PRIMARY KEY (`idStavke`),
  KEY `idArtiklaFK_idx` (`idArtiklaFK`),
  KEY `idKorpeFK_idx` (`idKorpeFK`),
  CONSTRAINT `idArtiklaFK` FOREIGN KEY (`idArtiklaFK`) REFERENCES `artikal` (`idArtikla`),
  CONSTRAINT `idKorpeFK` FOREIGN KEY (`idKorpeFK`) REFERENCES `korpa` (`idKorpe`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artikli_u_korpi`
--

LOCK TABLES `artikli_u_korpi` WRITE;
/*!40000 ALTER TABLE `artikli_u_korpi` DISABLE KEYS */;
/*!40000 ALTER TABLE `artikli_u_korpi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artikli_u_listi_zelja`
--

DROP TABLE IF EXISTS `artikli_u_listi_zelja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artikli_u_listi_zelja` (
  `idArtikli_u_listi_zelja` int NOT NULL AUTO_INCREMENT,
  `idListeZelja` int NOT NULL,
  `idArtikla` int NOT NULL,
  `vremeDodavanja` varchar(45) NOT NULL,
  PRIMARY KEY (`idArtikli_u_listi_zelja`),
  KEY `idListeZelja_idx` (`idListeZelja`),
  KEY `idArtikla_idx` (`idArtikla`),
  CONSTRAINT `idArtikla` FOREIGN KEY (`idArtikla`) REFERENCES `artikal` (`idArtikla`) ON UPDATE CASCADE,
  CONSTRAINT `idListeZelja` FOREIGN KEY (`idListeZelja`) REFERENCES `lista_zelja` (`idListaZelja`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artikli_u_listi_zelja`
--

LOCK TABLES `artikli_u_listi_zelja` WRITE;
/*!40000 ALTER TABLE `artikli_u_listi_zelja` DISABLE KEYS */;
/*!40000 ALTER TABLE `artikli_u_listi_zelja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategorija`
--

DROP TABLE IF EXISTS `kategorija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kategorija` (
  `idKategorije` int NOT NULL AUTO_INCREMENT,
  `nazivKategorije` varchar(45) NOT NULL,
  `idNadkategorije` int DEFAULT NULL,
  PRIMARY KEY (`idKategorije`),
  KEY `idNadkategorije_idx` (`idNadkategorije`),
  CONSTRAINT `idNadkategorije` FOREIGN KEY (`idNadkategorije`) REFERENCES `kategorija` (`idKategorije`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategorija`
--

LOCK TABLES `kategorija` WRITE;
/*!40000 ALTER TABLE `kategorija` DISABLE KEYS */;
INSERT INTO `kategorija` VALUES (1,'Bela tehnika',NULL),(2,'Elektronika',NULL);
/*!40000 ALTER TABLE `kategorija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korpa`
--

DROP TABLE IF EXISTS `korpa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korpa` (
  `idKorpe` int NOT NULL AUTO_INCREMENT,
  `ukupnaCena` int NOT NULL,
  `idKorisnika` int NOT NULL,
  PRIMARY KEY (`idKorpe`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korpa`
--

LOCK TABLES `korpa` WRITE;
/*!40000 ALTER TABLE `korpa` DISABLE KEYS */;
INSERT INTO `korpa` VALUES (1,0,1),(2,0,2),(3,0,3);
/*!40000 ALTER TABLE `korpa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lista_zelja`
--

DROP TABLE IF EXISTS `lista_zelja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lista_zelja` (
  `idListaZelja` int NOT NULL AUTO_INCREMENT,
  `datumKreiranja` date NOT NULL,
  `idKorisnika` int NOT NULL,
  PRIMARY KEY (`idListaZelja`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lista_zelja`
--

LOCK TABLES `lista_zelja` WRITE;
/*!40000 ALTER TABLE `lista_zelja` DISABLE KEYS */;
INSERT INTO `lista_zelja` VALUES (1,'2026-03-12',1),(2,'2026-03-10',2),(3,'2026-03-05',3);
/*!40000 ALTER TABLE `lista_zelja` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-14  5:32:19
