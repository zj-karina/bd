CREATE TABLE user
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(40)  NOT NULL,
    lastname  VARCHAR(40)  NOT NULL,
    login     VARCHAR(40)  NOT NULL,
    password  VARCHAR(255) NOT NULL,
    role      VARCHAR(10)  NOT NULL,
    blocked   bit(1),
    enabled   bit(1)
);


-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET
@OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET
@OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Table `mydb`.`baugetary_basis_of_training`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `budgetary_basis_of_training`(
    `state_budget_reciept_number` INT NOT NULL,
    `availability_of_original_certificate` VARCHAR(3) NOT NULL,
    PRIMARY KEY (`state_budget_reciept_number`)
);


-- -----------------------------------------------------
-- Table `mydb`.`paid_basis_of_training`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paid_basis_of_training`(
    `payment_receipt_number` INT NOT NULL,
    `availability_of_original_certificate` VARCHAR(3) NOT NULL,
    PRIMARY KEY (`payment_receipt_number`)
);

-- -----------------------------------------------------
-- Table `mydb`.`target_learning_framework`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `target_learning_framework`(
    `number_of_contract_with_company` INT NOT NULL,
    `receipt_of_payment` VARCHAR(3) NOT NULL,
    `availability_of_original_certificate` VARCHAR(3) NOT NULL,
    PRIMARY KEY(`number_of_contract_with_company`)
);

-- -----------------------------------------------------
-- Table `university`.`admission_officer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admission_officer`
(
    `id_admission_officer` INT NOT NULL AUTO_INCREMENT,
    `busyness` VARCHAR(50) NULL DEFAULT NULL,
    `signature_admission_officer` INT NOT NULL,
    `filling_out_application_id` INT NOT NULL,
    PRIMARY KEY(`id_admission_officer`),
    CONSTRAINT `admission_officer_ibfk_1` FOREIGN KEY(`filling_out_application_id`)
        REFERENCES `filling_out_application`(`application_number`)
);

-- -----------------------------------------------------
-- Table `university`.`package_of_documents`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `package_of_documents`
(
    `id_enrollee` INT NOT NULL,
    `way_of_filling` VARCHAR(10) NOT NULL,
    `photo` VARCHAR(3) NOT NULL,
    `medical_certificate` VARCHAR(3) NOT NULL,
    `IA_documents` VARCHAR(3) NOT NULL,
    `filling_out_application_id` INT NOT NULL,
    PRIMARY KEY(`id_enrollee`),
    CONSTRAINT `package_of_documents_ibfk_2` FOREIGN KEY(`filling_out_application_id`)
        REFERENCES `filling_out_application`(`application_number`)
);


-- -----------------------------------------------------
-- Table `university`.`filling_out_an_application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `filling_out_application`
(
    `application_number` INT NOT NULL AUTO_INCREMENT,
    `text_of_application` VARCHAR(255) NOT NULL,
    `signature_enrollee` INT NOT NULL,
    `number_of_contest` INT NOT NULL,
    PRIMARY KEY(`application_number`),
    CONSTRAINT `filling_out_application_ibfk_1` FOREIGN KEY(`number_of_contest`)
        REFERENCES `contest`(`number_of_contest`)
);

-- -----------------------------------------------------
-- Table `university`.`enrollee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enrollee`(
    `id_enrollee` INT NOT NULL AUTO_INCREMENT,
    `full_name` VARCHAR (50) NOT NULL,
    `sum_exam_points` INT NOT NULL,
    `sum_IA_points` INT NOT NULL,
    `passport_numbers` INT NULL DEFAULT NULL,
    `certificate_numbers` INT NULL DEFAULT NULL,
    `direction` INT NOT NULL,
    `filling_out_application_id` INT NOT NULL,
    PRIMARY KEY(`id_enrollee`),
    CONSTRAINT `enrollee_ibfk_1` FOREIGN KEY(`filling_out_application_id`)
        REFERENCES `filling_out_application`(`application_number`)
);

-- -----------------------------------------------------
-- Table `university`.`consultant_teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `consultant_teacher`
(
    `id_teacher` INT NOT NULL AUTO_INCREMENT,
    `full_name` VARCHAR(30) NOT NULL,
    `department` VARCHAR(20) NOT NULL,
    `institute` VARCHAR(5) NOT NULL,
    `id_enrollee` INT NOT NULL,
    PRIMARY KEY(`id_teacher`),
    CONSTRAINT `consultant_teacher_ibfk_1` FOREIGN KEY (`id_enrollee` )
        REFERENCES `enrollee` (`id_enrollee`)
);

-- -----------------------------------------------------
-- Table `university`.`contest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `contest`(
    `number_of_contest` INT NOT NULL AUTO_INCREMENT,
    `passing_score` INT NOT NULL,
    `year_of_contest` INT NOT NULL,
    `budgetary_basis_of_training_id` INT NOT NULL,
    `paid_basis_of_training_id` INT NOT NULL,
    `target_learning_framework_id` INT NOT NULL,
    PRIMARY KEY(`number_of_contest`),
    CONSTRAINT `contest_ibfk_1` FOREIGN KEY(`budgetary_basis_of_training_id`)
        REFERENCES `budgetary_basis_of_training`(`state_budget_reciept_number`),
    CONSTRAINT `contest_ibfk_2` FOREIGN KEY(`paid_basis_of_training_id`)
        REFERENCES `paid_basis_of_training`(`payment_receipt_number`),
    CONSTRAINT `contest_ibfk_3` FOREIGN KEY(`target_learning_framework_id`)
        REFERENCES `target_learning_framework`(`number_of_contract_with_company`)
);

INSERT INTO budgetary_basis_of_training VALUES
('1234', 'Yes'), ('4321', 'Yes'), ('2345', 'No'), ('5432', 'Yes');

INSERT INTO paid_basis_of_training VALUES
('1234', 'Yes'), ('4321', 'Yes'), ('2345', 'No'), ('5432', 'Yes');

INSERT INTO target_learning_framework VALUES
('1234', 'Yes', 'No'), ('4321', 'Yes', 'No'), ('2345', 'No', 'No'), ('5432', 'Yes', 'Yes');

INSERT INTO contest VALUES
(null, 260, 2021, '1234', '1234', '1234'),
(null, 245, 2021, '1234', '1234', '1234'),
(null, 210, 2021, '1234', '1234', '1234'),
(null, 201, 2021, '1234', '1234', '1234'),
(null, 199, 2021, '4321','4321', '4321'),
(null, 200, 2021, '4321', '4321', '4321'),
(null, 270, 2021, '4321', '4321', '4321'),
(null, 270, 2021, '2345', '2345', '2345'),
(null, 270, 2021, '2345', '2345', '2345');

INSERT INTO filling_out_application VALUES
(null, 'enroll', '12234', 1),
(null, 'transfer', '13223', 2),
(null, 'accept', '43223', 3),
(null, 'return documents', '13366', 4),
(null, 'enroll', '33333', 5),
(null, 'transfer', '44444', 6),
(null, 'accept', '55555', 7),
(null, 'return documents', '55666', 8),
(null, 'return documents', '99999', 9);

INSERT INTO package_of_documents VALUES
(1, 'offline', 'Yes', 'Yes', 'Yes', 9),
(2, 'offline', 'Yes', 'Yes', 'Yes', 8),
(3, 'offline', 'Yes', 'No', 'Yes', 7),
(4, 'online', 'Yes', 'Yes', 'No', 6),
(5, 'offline', 'No', 'No', 'Yes', 5),
(6, 'online', 'Yes', 'Yes', 'No', 4),
(7, 'offline', 'No', 'Yes', 'Yes', 3),
(8, 'online', 'Yes', 'No', 'No', 2),
(9, 'offline', 'Yes', 'Yes', 'Yes', 1);

INSERT INTO admission_officer VALUES
(null, 'accept documents', 11, 1),
(null, 'accept documents', 22, 2),
(null, 'assistant', 33, 3),
(null, 'accept documents', 44, 4),
(null, 'assistant', 55, 5),
(null, 'accept documents', 66, 6),
(null, 'validation', 77, 7),
(null, 'validation', 88, 8),
(null, 'assistant', 99, 9);


INSERT INTO enrollee VALUES
(null, 'Дорофеев Д.Д.', 250, 10, 444555, 12345, 30301, 1),
(null, 'Комар К.К.', 240, 2, 111222, 23456, 30302, 2),
(null, 'Иванов И.И.', 230, 5, 222333, 34567, 30303, 3),
(null, 'Петров П.П.', 241, 0, 333444, 45678, 30304, 4),
(null, 'Сидоров С.С.', 207, 0, 555666, 56789, 30305, 5),
(null, 'Константинов К.К.', 178, 10, 777777, 67890, 30306, 6),
(null, 'Исаев И.И.', 207, 0, 777888, 78901, 30307, 7),
(null, 'Мясников М.М.', 189, 10, 888999, 89012, 30302, 8),
(null, 'Сергеев С.С.', 233, 8, 999999, 90123, 30304, 9);

INSERT INTO consultant_teacher VALUES
(null, 'Карпов К.К.', 'VT', 'IT', 1),
(null, 'Смирнов С.С.', 'VM-2', 'IT', 2),
(null, 'Бондарь Б.Б.', 'PM', 'IT', 3),
(null, 'Макаров М.М.', 'VT', 'IT', 4),
(null, 'Семенов С.С', 'PPI', 'IT', 5),
(null, 'Иванова И.И.', 'VM-2', 'IT', 6);

SET
SQL_MODE=@OLD_SQL_MODE;
SET
FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET
UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
