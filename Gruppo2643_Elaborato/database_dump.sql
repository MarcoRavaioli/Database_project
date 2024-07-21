-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: localhost    Database: SportCenterManager
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Abbonamenti`
--

DROP TABLE IF EXISTS `Abbonamenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Abbonamenti` (
  `IDabbonamento` int NOT NULL AUTO_INCREMENT,
  `IDmembro` int NOT NULL,
  `DataFine` date NOT NULL,
  `IDcategoria` int NOT NULL,
  PRIMARY KEY (`IDabbonamento`),
  UNIQUE KEY `IDmembro` (`IDmembro`,`IDcategoria`),
  KEY `IDcategoria` (`IDcategoria`),
  CONSTRAINT `abbonamenti_ibfk_1` FOREIGN KEY (`IDmembro`) REFERENCES `Membri` (`IDmembro`) ON DELETE CASCADE,
  CONSTRAINT `abbonamenti_ibfk_2` FOREIGN KEY (`IDcategoria`) REFERENCES `Categorie` (`IDcategoria`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Abbonamenti`
--

LOCK TABLES `Abbonamenti` WRITE;
/*!40000 ALTER TABLE `Abbonamenti` DISABLE KEYS */;
INSERT INTO `Abbonamenti` VALUES (4,1,'2024-09-29',2),(5,8,'2025-08-01',7),(6,3,'2023-06-15',1),(7,4,'2024-07-24',6),(8,1,'2024-08-16',6);
/*!40000 ALTER TABLE `Abbonamenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Accessi`
--

DROP TABLE IF EXISTS `Accessi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Accessi` (
  `IDcategoria` int NOT NULL,
  `IDstruttura` int NOT NULL,
  PRIMARY KEY (`IDcategoria`,`IDstruttura`),
  KEY `IDstruttura` (`IDstruttura`),
  CONSTRAINT `accessi_ibfk_1` FOREIGN KEY (`IDcategoria`) REFERENCES `Categorie` (`IDcategoria`) ON DELETE CASCADE,
  CONSTRAINT `accessi_ibfk_2` FOREIGN KEY (`IDstruttura`) REFERENCES `Strutture` (`IDstruttura`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Accessi`
--

LOCK TABLES `Accessi` WRITE;
/*!40000 ALTER TABLE `Accessi` DISABLE KEYS */;
INSERT INTO `Accessi` VALUES (1,1),(2,1),(5,1),(2,2),(5,2),(6,2),(1,4),(2,4),(5,4),(2,5),(5,5),(2,6),(4,6),(7,6);
/*!40000 ALTER TABLE `Accessi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Attrezzi`
--

DROP TABLE IF EXISTS `Attrezzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Attrezzi` (
  `IDattrezzo` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(64) NOT NULL,
  `Descrizione` text,
  `Quantita` int NOT NULL,
  `IDcorso` int NOT NULL,
  PRIMARY KEY (`IDattrezzo`),
  KEY `IDcorso` (`IDcorso`),
  CONSTRAINT `attrezzi_ibfk_1` FOREIGN KEY (`IDcorso`) REFERENCES `Corsi` (`IDcorso`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Attrezzi`
--

LOCK TABLES `Attrezzi` WRITE;
/*!40000 ALTER TABLE `Attrezzi` DISABLE KEYS */;
INSERT INTO `Attrezzi` VALUES (1,'palla_medica','cidcoaihflakn',1,1),(2,'arco','arco di precisione 6kg',3,9);
/*!40000 ALTER TABLE `Attrezzi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Categorie`
--

DROP TABLE IF EXISTS `Categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Categorie` (
  `IDcategoria` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(64) NOT NULL,
  `Descrizione` text NOT NULL,
  `Prezzo` decimal(10,2) NOT NULL,
  PRIMARY KEY (`IDcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categorie`
--

LOCK TABLES `Categorie` WRITE;
/*!40000 ALTER TABLE `Categorie` DISABLE KEYS */;
INSERT INTO `Categorie` VALUES (1,'del Passero','permette di usufruire delle strutture in via del Passero',300.00),(2,'Consiglieri','Permette di entrare in tutte le strutture',0.00),(4,'per_istruttori_Ravenna','per accedere alle strutture di Ravenna',100.00),(5,'per_istruttori_cervia','permette di accedere a tutte le strutture di Cervia',150.00),(6,'Piscina','Permette l\'accesso alla piscina',150.00),(7,'Poligono','permette di accedere al poligono di tiro',250.00);
/*!40000 ALTER TABLE `Categorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Conduzioni`
--

DROP TABLE IF EXISTS `Conduzioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Conduzioni` (
  `IDcorso` int NOT NULL,
  `IDmembro` int NOT NULL,
  `IsCoordinatore` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IDcorso`,`IDmembro`),
  KEY `IDmembro` (`IDmembro`),
  CONSTRAINT `conduzioni_ibfk_1` FOREIGN KEY (`IDcorso`) REFERENCES `Corsi` (`IDcorso`) ON DELETE CASCADE,
  CONSTRAINT `conduzioni_ibfk_2` FOREIGN KEY (`IDmembro`) REFERENCES `Membri` (`IDmembro`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Conduzioni`
--

LOCK TABLES `Conduzioni` WRITE;
/*!40000 ALTER TABLE `Conduzioni` DISABLE KEYS */;
INSERT INTO `Conduzioni` VALUES (6,13,0),(9,11,1);
/*!40000 ALTER TABLE `Conduzioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Corsi`
--

DROP TABLE IF EXISTS `Corsi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Corsi` (
  `IDcorso` int NOT NULL AUTO_INCREMENT,
  `Titolo` varchar(64) NOT NULL,
  `Descrizione` text NOT NULL,
  `FasciaOraria` varchar(16) NOT NULL,
  `IDstruttura` int NOT NULL,
  PRIMARY KEY (`IDcorso`),
  KEY `IDstruttura` (`IDstruttura`),
  CONSTRAINT `corsi_ibfk_1` FOREIGN KEY (`IDstruttura`) REFERENCES `Strutture` (`IDstruttura`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Corsi`
--

LOCK TABLES `Corsi` WRITE;
/*!40000 ALTER TABLE `Corsi` DISABLE KEYS */;
INSERT INTO `Corsi` VALUES (1,'Calcetto','calcetto a 7, un istruttore vi seguirà per tutto il corso','15-19',1),(2,'Nuoto sincronizzato','insegnamo nuoto sincronizzato a squadre','19-22',2),(6,'arm_wrestling','corso divertente che insegna una disciplina sempre più popolare','16-19',4),(7,'Dodgeball','dodgeball con regole ufficiali','09-11',5),(8,'palla_fantasma','ci trovi nel campo da dogeball','19-21',5),(9,'tiro_arco','corso di tiro con l\'arco per principanti','21-23',6);
/*!40000 ALTER TABLE `Corsi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Indirizzi`
--

DROP TABLE IF EXISTS `Indirizzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Indirizzi` (
  `IDindirizzo` int NOT NULL AUTO_INCREMENT,
  `CAP` varchar(10) NOT NULL,
  `Via` varchar(64) NOT NULL,
  `NumeroCivico` varchar(10) NOT NULL,
  `Citta` varchar(64) NOT NULL,
  `Stato` varchar(64) NOT NULL,
  PRIMARY KEY (`IDindirizzo`),
  UNIQUE KEY `CAP` (`CAP`,`Via`,`NumeroCivico`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Indirizzi`
--

LOCK TABLES `Indirizzi` WRITE;
/*!40000 ALTER TABLE `Indirizzi` DISABLE KEYS */;
INSERT INTO `Indirizzi` VALUES (1,'48015','dei Mille','23','Cervia','Italia'),(6,'48015','salara','1125','Cervia','Italia'),(7,'48007','dismano','77','Ravenna','Italia'),(8,'48015','del passero','9','Cervia','Italia');
/*!40000 ALTER TABLE `Indirizzi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Iscrizioni`
--

DROP TABLE IF EXISTS `Iscrizioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Iscrizioni` (
  `IDiscrizione` int NOT NULL AUTO_INCREMENT,
  `IDmembro` int NOT NULL,
  `IDlezione` int NOT NULL,
  `Data` date NOT NULL,
  PRIMARY KEY (`IDiscrizione`),
  UNIQUE KEY `IDmembro` (`IDmembro`,`IDlezione`),
  KEY `IDlezione` (`IDlezione`),
  CONSTRAINT `iscrizioni_ibfk_1` FOREIGN KEY (`IDmembro`) REFERENCES `Membri` (`IDmembro`) ON DELETE CASCADE,
  CONSTRAINT `iscrizioni_ibfk_2` FOREIGN KEY (`IDlezione`) REFERENCES `Lezioni` (`IDlezione`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Iscrizioni`
--

LOCK TABLES `Iscrizioni` WRITE;
/*!40000 ALTER TABLE `Iscrizioni` DISABLE KEYS */;
INSERT INTO `Iscrizioni` VALUES (6,1,9,'2024-07-21'),(7,1,11,'2024-07-21'),(8,1,13,'2024-07-21'),(9,1,5,'2024-07-21');
/*!40000 ALTER TABLE `Iscrizioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Lezioni`
--

DROP TABLE IF EXISTS `Lezioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Lezioni` (
  `IDlezione` int NOT NULL AUTO_INCREMENT,
  `IDcorso` int NOT NULL,
  `Data` date NOT NULL,
  `Orario` time NOT NULL,
  `Descrizione` text,
  `MaxPersone` int NOT NULL,
  PRIMARY KEY (`IDlezione`),
  UNIQUE KEY `IDcorso` (`IDcorso`,`Data`),
  CONSTRAINT `lezioni_ibfk_1` FOREIGN KEY (`IDcorso`) REFERENCES `Corsi` (`IDcorso`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lezioni`
--

LOCK TABLES `Lezioni` WRITE;
/*!40000 ALTER TABLE `Lezioni` DISABLE KEYS */;
INSERT INTO `Lezioni` VALUES (4,1,'2024-08-01','19:30:00','preparazione atletica',30),(5,1,'2024-08-03','19:30:00','preparazione atletica 2',30),(6,1,'2024-08-05','19:30:00','preparazione atletica 3',30),(7,2,'2024-07-30','11:30:00','per imparare a nuotare',15),(8,2,'2024-08-01','11:30:00','impariamo lo stile libero',15),(9,2,'2024-08-04','12:00:00','impariamo il dorso',15),(10,7,'2024-09-01','21:00:00','partitella di conoscenza',8),(11,7,'2024-09-10','21:00:00','iniziamo con le basi',8),(12,9,'2024-08-10','15:00:00','impariamo a tenere in mano l\'arco',20),(13,9,'2024-08-20','15:00:00','chi non fa centro non torna più',20),(14,1,'2024-06-13','22:00:00','riunione iniziale',40);
/*!40000 ALTER TABLE `Lezioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Membri`
--

DROP TABLE IF EXISTS `Membri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Membri` (
  `IDmembro` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(64) NOT NULL,
  `Cognome` varchar(64) NOT NULL,
  `DataNascita` date NOT NULL,
  `Email` varchar(64) NOT NULL,
  `Password` varchar(64) NOT NULL,
  `Documento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDmembro`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Membri`
--

LOCK TABLES `Membri` WRITE;
/*!40000 ALTER TABLE `Membri` DISABLE KEYS */;
INSERT INTO `Membri` VALUES (1,'marco','ravaioli','2001-08-29','marcoravaioli@email.com','marcoravaioli',''),(2,'ma','fe','1968-01-08','mafe@email.com','mafe',NULL),(3,'ch','fe','2001-11-07','email@email.com','',NULL),(4,'en','ra','1968-09-01','enra@email.com','enra',NULL),(5,'massimo','fes','1956-08-17','massimofes@email.com','massimofes','documenti/istruttore_sci_massimofes'),(8,'rafa','nadal','1990-03-13','rafanadal@email.com','rafanadal','documenti/istruttore_tennis_rafanadal'),(11,'robin','hood','1005-11-14','robinhood@email.com','robinhoodpassword','documenti/istruttore_arco_robinhood.pdf'),(12,'cr','bor','2001-10-31','crbor@email.com','crbor',NULL),(13,'davide','bravaccio','2001-08-17','davidebravaccio@email.com','davidebravaccio','documenti/istruttore_armWrestling'),(14,'','','2000-01-01','','',NULL);
/*!40000 ALTER TABLE `Membri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Strutture`
--

DROP TABLE IF EXISTS `Strutture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Strutture` (
  `IDstruttura` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(64) NOT NULL,
  `Descrizione` text NOT NULL,
  `IDindirizzo` int NOT NULL,
  PRIMARY KEY (`IDstruttura`),
  KEY `IDindirizzo` (`IDindirizzo`),
  CONSTRAINT `strutture_ibfk_1` FOREIGN KEY (`IDindirizzo`) REFERENCES `Indirizzi` (`IDindirizzo`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Strutture`
--

LOCK TABLES `Strutture` WRITE;
/*!40000 ALTER TABLE `Strutture` DISABLE KEYS */;
INSERT INTO `Strutture` VALUES (1,'Campo_da_calcio','campo da calcio ad 11 che talvolta più essere rigato per 7 o 9',8),(2,'Piscina_esterna','Piscina con misure olimpiche al chiuso con spogliatoi e docce',1),(4,'Tavoli_armwrestling','diversi tavoli con misure ufficiali da arm_wrestrling',8),(5,'campo_dodgeball','campo ristrutturato da dodgeball con regole americane',1),(6,'poligono','poligono di tiro per arco, prossimamente si espanderà anche per armi a fuoco',7);
/*!40000 ALTER TABLE `Strutture` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-21 11:31:37
