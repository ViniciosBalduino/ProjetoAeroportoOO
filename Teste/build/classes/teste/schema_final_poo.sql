CREATE DATABASE  IF NOT EXISTS `poo_aeroporto` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `poo_aeroporto`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: poo_aeroporto
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `nascimento` date NOT NULL,
  `documento` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataModificacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,'Vinicios Balduino','1996-04-26','999888777666','VBalduino','Admin','2025-11-30 16:40:43','2025-11-30 16:40:43'),(2,'Vitor Ferreira','2000-01-01','123456789','VFerreira','Admin','2025-11-30 16:42:04','2025-11-30 16:42:04'),(3,'ADMIN','2000-01-01','111222333444','Admin','Admin','2025-11-30 16:42:42','2025-11-30 16:42:42');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aeroporto`
--

DROP TABLE IF EXISTS `aeroporto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aeroporto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `abreviacao` varchar(45) NOT NULL,
  `cidade` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataModificacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aeroporto`
--

LOCK TABLES `aeroporto` WRITE;
/*!40000 ALTER TABLE `aeroporto` DISABLE KEYS */;
INSERT INTO `aeroporto` VALUES (1,'Estadual Doutor Leite Lopes','RAO','Ribeirão Preto','2025-11-30 17:34:30','2025-11-30 17:34:30'),(2,'Mario de Almeida Franco','UBA','Uberaba','2025-11-30 17:34:30','2025-11-30 17:34:30'),(3,'Tenete coronel Aviador César Bombonato','UDI','Uberlândia','2025-11-30 17:34:30','2025-11-30 17:34:30'),(4,'AEROPORTO POO','POO','IFTM UPT','2025-12-03 01:13:18','2025-12-03 01:13:18');
/*!40000 ALTER TABLE `aeroporto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boardingpass`
--

DROP TABLE IF EXISTS `boardingpass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boardingpass` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idTicket` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `idAssento` varchar(45) NOT NULL,
  `numeroAssento` varchar(45) NOT NULL,
  `idVoo` varchar(45) NOT NULL,
  `nomePassageiro` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idTicketBoardingPass_idx` (`idTicket`),
  CONSTRAINT `idTicketBoardingPass` FOREIGN KEY (`idTicket`) REFERENCES `ticket` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boardingpass`
--

LOCK TABLES `boardingpass` WRITE;
/*!40000 ALTER TABLE `boardingpass` DISABLE KEYS */;
/*!40000 ALTER TABLE `boardingpass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkin`
--

DROP TABLE IF EXISTS `checkin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idTicket` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `documento` varchar(45) NOT NULL,
  `estado` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idTicket_idx` (`idTicket`),
  CONSTRAINT `idTicket` FOREIGN KEY (`idTicket`) REFERENCES `ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkin`
--

LOCK TABLES `checkin` WRITE;
/*!40000 ALTER TABLE `checkin` DISABLE KEYS */;
/*!40000 ALTER TABLE `checkin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companhiaaerea`
--

DROP TABLE IF EXISTS `companhiaaerea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companhiaaerea` (
  `id` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `abreviacao` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataModificacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companhiaaerea`
--

LOCK TABLES `companhiaaerea` WRITE;
/*!40000 ALTER TABLE `companhiaaerea` DISABLE KEYS */;
INSERT INTO `companhiaaerea` VALUES ('1VASP','Viação Aérea São Paulo','VASP','2025-11-30 16:55:51','2025-11-30 16:55:51'),('2Varig','Viação aerea Rio Grandense','Varig','2025-11-30 16:55:51','2025-11-30 16:55:51'),('3Web','WebJet','Web','2025-11-30 16:55:51','2025-11-30 16:55:51');
/*!40000 ALTER TABLE `companhiaaerea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `despachobagagem`
--

DROP TABLE IF EXISTS `despachobagagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `despachobagagem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idTicket` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idTicketDespacho_idx` (`idTicket`),
  CONSTRAINT `idTicketDespacho` FOREIGN KEY (`idTicket`) REFERENCES `ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `despachobagagem`
--

LOCK TABLES `despachobagagem` WRITE;
/*!40000 ALTER TABLE `despachobagagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `despachobagagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `nascimento` date NOT NULL,
  `documento` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataModificacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,'Ana Clara','1990-10-15','111222233333','ana','ana123','2025-11-30 17:29:03','2025-11-30 17:29:03'),(2,'Bruno','1985-07-30','1223334444','bruno','bruno123','2025-11-30 17:29:03','2025-11-30 17:29:03'),(3,'Func','2000-01-01','999999999','func','func','2025-11-30 17:29:03','2025-11-30 17:29:03');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passageiro`
--

DROP TABLE IF EXISTS `passageiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passageiro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `nascimento` date NOT NULL,
  `documento` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataModificacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passageiro`
--

LOCK TABLES `passageiro` WRITE;
/*!40000 ALTER TABLE `passageiro` DISABLE KEYS */;
INSERT INTO `passageiro` VALUES (1,'Marineide','1996-04-26','123456789','MarineideGatinha','Marineide123','2025-11-30 16:46:02','2025-11-30 16:46:02'),(2,'Flavio','1996-04-25','321654987','123','123','2025-11-30 16:46:02','2025-11-30 16:46:02'),(3,'Felicia','1996-04-24','987654321','Felicia','PinkCerebro','2025-11-30 16:46:02','2025-11-30 16:46:02'),(4,'joao','2025-12-02','123321123','joao123','123joao','2025-12-02 04:05:05','2025-12-02 04:05:05');
/*!40000 ALTER TABLE `passageiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` varchar(45) NOT NULL,
  `valor` double NOT NULL,
  `idVooAssento` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `nomePassageiro` varchar(45) NOT NULL,
  `idVoo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idVooAssento_idx` (`idVooAssento`),
  CONSTRAINT `idVooAssento` FOREIGN KEY (`idVooAssento`) REFERENCES `vooassentos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vooassentos`
--

DROP TABLE IF EXISTS `vooassentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vooassentos` (
  `id` varchar(45) NOT NULL,
  `idVoo` varchar(45) NOT NULL,
  `idPassageiro` int NOT NULL,
  `numeroAssento` int NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  PRIMARY KEY (`id`,`idVoo`,`numeroAssento`),
  KEY `idPassageiro_idx` (`idPassageiro`),
  KEY `idVoo_idx` (`idVoo`),
  CONSTRAINT `idPassageiro` FOREIGN KEY (`idPassageiro`) REFERENCES `passageiro` (`id`),
  CONSTRAINT `idVoo` FOREIGN KEY (`idVoo`) REFERENCES `voos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vooassentos`
--

LOCK TABLES `vooassentos` WRITE;
/*!40000 ALTER TABLE `vooassentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `vooassentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voos`
--

DROP TABLE IF EXISTS `voos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voos` (
  `id` varchar(45) NOT NULL,
  `companhia` varchar(45) NOT NULL,
  `origem` varchar(45) NOT NULL,
  `destino` varchar(45) NOT NULL,
  `data` date NOT NULL,
  `horario` time NOT NULL,
  `duracao` time NOT NULL,
  `capacidade` int NOT NULL,
  `estado` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataModificacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idCompanhia_idx` (`companhia`),
  KEY `idCompanhiaAerea_idx` (`companhia`),
  KEY `COmpanhiaAereaVoo_idx` (`companhia`),
  CONSTRAINT `COmpanhiaAereaVoo` FOREIGN KEY (`companhia`) REFERENCES `companhiaaerea` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voos`
--

LOCK TABLES `voos` WRITE;
/*!40000 ALTER TABLE `voos` DISABLE KEYS */;
INSERT INTO `voos` VALUES ('RAO-UBA-Web','3Web','Ribeirão Preto','Uberaba','2025-12-05','20:00:00','01:20:00',60,'agendado','2025-11-30 17:24:04','2025-11-30 17:24:04'),('UBA-UDI-Varig','2Varig','Uberaba','Uberlandia','2025-12-04','17:00:00','00:40:00',60,'agendado','2025-11-30 17:24:04','2025-11-30 17:24:04'),('UBA-UDI-VASP','1VASP','Uberaba','Uberlandia','2025-12-20','20:00:00','00:40:00',60,'agendado','2025-11-30 17:24:04','2025-11-30 17:24:04');
/*!40000 ALTER TABLE `voos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-03  3:37:14
