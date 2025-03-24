CREATE DATABASE `clientes`;
USE `clientes`;

CREATE TABLE `clientes` (
  `client_id` bigint NOT NULL,
  `age` bigint DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `identification` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` enum('TRUE','FALSE') DEFAULT NULL,
  `gender` enum('MASCULINO','FEMENINO') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `clientes`
  ADD PRIMARY KEY (`client_id`);

ALTER TABLE `clientes`
  MODIFY `client_id` bigint NOT NULL AUTO_INCREMENT;


CREATE DATABASE `cuenta_movimientos`;
USE `cuenta_movimientos`;

CREATE TABLE `clientes` (
  `client_id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB;

CREATE TABLE `cuentas` (
  `balance` decimal(38,2) DEFAULT NULL,
  `client_id` bigint NOT NULL,
  `account_id` bigint NOT NULL,
  `account_number` varchar(10) NOT NULL,
  `status` enum('True','False') DEFAULT NULL,
  `account_type` enum('CORRIENTE','AHORRO') NOT NULL
) ENGINE=InnoDB;

CREATE TABLE `movimientos` (
  `transaction_id` bigint NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  `initial_balance` decimal(38,2) DEFAULT NULL,
  `value` decimal(38,2) DEFAULT NULL,
  `balance` decimal(38,2) DEFAULT NULL
) ENGINE=InnoDB;


--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`client_id`);

ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`account_id`),
  ADD UNIQUE KEY `UK_ACCOUNT_NUMBER` (`account_number`),
  ADD KEY `FK_ACC_CLIENT_ID` (`client_id`);

ALTER TABLE `movimientos`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `FK_TRX_ACCOUNT_ID` (`account_id`);

ALTER TABLE `cuentas`
  MODIFY `account_id` bigint NOT NULL AUTO_INCREMENT;

ALTER TABLE `movimientos`
  MODIFY `transaction_id` bigint NOT NULL AUTO_INCREMENT;

ALTER TABLE `cuentas`
  ADD CONSTRAINT `FK_ACC_CLIENT_ID` FOREIGN KEY (`client_id`) REFERENCES `clientes` (`client_id`);

ALTER TABLE `movimientos`
  ADD CONSTRAINT `FK_TRX_ACCOUNT_ID` FOREIGN KEY (`account_id`) REFERENCES `cuentas` (`account_id`);