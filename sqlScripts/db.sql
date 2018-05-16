DROP DATABASE IF EXISTS `fast_test`;

CREATE DATABASE IF NOT EXISTS `fast_test`;
USE `fast_test`;

DROP TABLE IF EXISTS `fast_test`.`admins`;
CREATE TABLE `fast_test`.`admins` (
  `id`       INT(11)     NOT NULL AUTO_INCREMENT,
  `name`     VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,

  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `fast_test`.`students`;
CREATE TABLE `fast_test`.`students` (
  `id`        INT          NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45)  NOT NULL,
  `lastname`  VARCHAR(45)  NOT NULL,
  `email`     VARCHAR(100) NOT NULL,
  `rating`    DOUBLE       NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `fast_test`.`questions`;
CREATE TABLE `fast_test`.`questions` (
  `id`     INT          NOT NULL AUTO_INCREMENT,
  `title`  VARCHAR(200) NOT NULL,
  `text`   VARCHAR(400) NOT NULL,
  `item_1` VARCHAR(200) NOT NULL,
  `item_2` VARCHAR(200) NOT NULL,
  `item_3` VARCHAR(200) NOT NULL,
  `item_4` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `fast_test`.`answers`;
CREATE TABLE `fast_test`.`answers` (
  `id`     INT        NOT NULL,
  `item_1` TINYINT(1) NOT NULL,
  `item_2` TINYINT(1) NOT NULL,
  `item_3` TINYINT(1) NOT NULL,
  `item_4` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `fast_test`.`completed_tests`;
CREATE TABLE `fast_test`.`completed_tests` (
  `id`         INT        NOT NULL AUTO_INCREMENT,
  `student_id` INT        NOT NULL,
  `time`       DATETIME   NOT NULL,
  `score`      DOUBLE     NOT NULL,
  `q1_id`      INT        NOT NULL,
  `q1_answ1`   TINYINT(1) NOT NULL,
  `q1_answ2`   TINYINT(1) NOT NULL,
  `q1_answ3`   TINYINT(1) NOT NULL,
  `q1_answ4`   TINYINT(1) NOT NULL,
  `q2_id`      INT        NOT NULL,
  `q2_answ1`   TINYINT(1) NOT NULL,
  `q2_answ2`   TINYINT(1) NOT NULL,
  `q2_answ3`   TINYINT(1) NOT NULL,
  `q2_answ4`   TINYINT(1) NOT NULL,
  `q3_id`      INT        NOT NULL,
  `q3_answ1`   TINYINT(1) NOT NULL,
  `q3_answ2`   TINYINT(1) NOT NULL,
  `q3_answ3`   TINYINT(1) NOT NULL,
  `q3_answ4`   TINYINT(1) NOT NULL,
  `q4_id`      INT        NOT NULL,
  `q4_answ1`   TINYINT(1) NOT NULL,
  `q4_answ2`   TINYINT(1) NOT NULL,
  `q4_answ3`   TINYINT(1) NOT NULL,
  `q4_answ4`   TINYINT(1) NOT NULL,
  `q5_id`      INT        NOT NULL,
  `q5_answ1`   TINYINT(1) NOT NULL,
  `q5_answ2`   TINYINT(1) NOT NULL,
  `q5_answ3`   TINYINT(1) NOT NULL,
  `q5_answ4`   TINYINT(1) NOT NULL,
  `q6_id`      INT        NOT NULL,
  `q6_answ1`   TINYINT(1) NOT NULL,
  `q6_answ2`   TINYINT(1) NOT NULL,
  `q6_answ3`   TINYINT(1) NOT NULL,
  `q6_answ4`   TINYINT(1) NOT NULL,
  `q7_id`      INT        NOT NULL,
  `q7_answ1`   TINYINT(1) NOT NULL,
  `q7_answ2`   TINYINT(1) NOT NULL,
  `q7_answ3`   TINYINT(1) NOT NULL,
  `q7_answ4`   TINYINT(1) NOT NULL,
  `q8_id`      INT        NOT NULL,
  `q8_answ1`   TINYINT(1) NOT NULL,
  `q8_answ2`   TINYINT(1) NOT NULL,
  `q8_answ3`   TINYINT(1) NOT NULL,
  `q8_answ4`   TINYINT(1) NOT NULL,
  `q9_id`      INT        NOT NULL,
  `q9_answ1`   TINYINT(1) NOT NULL,
  `q9_answ2`   TINYINT(1) NOT NULL,
  `q9_answ3`   TINYINT(1) NOT NULL,
  `q9_answ4`   TINYINT(1) NOT NULL,
  `q10_id`     INT        NOT NULL,
  `q10_answ1`  TINYINT(1) NOT NULL,
  `q10_answ2`  TINYINT(1) NOT NULL,
  `q10_answ3`  TINYINT(1) NOT NULL,
  `q10_answ4`  TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `fast_test`.`admins` (name, password)
VALUES
  ('root', 'root'),
  ('LevchenkoU', 'securePass'),
  ('SviatoslavK', 'catName'),
  ('KPIadmin', 'Zgurovsky');

INSERT INTO `fast_test`.`students` (firstname, lastname, email, rating)
VALUES
  ('Derivedmed', 'Oleg', 'oleg.derivedmed@g.c', 0),
  ('Dotsiuk', 'Kateryna', 'lamusevenale5@g.c', 0),
  ('Hamalii', 'Liubomir', 'smart.like1998@g.c', 0),
  ('Holota', 'Yaroslav', 'joshimo.kiev@g.c', 0),
  ('Horpynych-Raduzhenko', 'Ivan', 'ivan.horpynych@g.c', 0),
  ('Hudzenko', 'Ivan', 'wizir01@g.c', 0),
  ('Kondratenko', 'Nikolay', 'striker.kms@g.c', 0),
  ('Kondratenko', 'Oleksandr', 'sashko.kondratenko@g.c', 0),
  ('Korzh', 'Ivan', 'ivankorzh21@g.c', 0),
  ('Kovalenko', 'Boris', 'kovalenko.connect@g.c', 0),
  ('Kruhlov', 'Oleksandr', 'awahrhaft@g.c', 0),
  ('Kuriata', 'Andrii', 'mmmacrosss@gmail.com', 0);

# INSERT INTO `fast_test`.`completed_tests` (student_id, time, score,
#                                            q1_id, q1_answ1, q1_answ2, q1_answ3, q1_answ4,
#                                            q2_id, q2_answ1, q2_answ2, q2_answ3, q2_answ4,
#                                            q3_id, q3_answ1, q3_answ2, q3_answ3, q3_answ4,
#                                            q4_id, q4_answ1, q4_answ2, q4_answ3, q4_answ4,
#                                            q5_id, q5_answ1, q5_answ2, q5_answ3, q5_answ4,
#                                            q6_id, q6_answ1, q6_answ2, q6_answ3, q6_answ4,
#                                            q7_id, q7_answ1, q7_answ2, q7_answ3, q7_answ4,
#                                            q8_id, q8_answ1, q8_answ2, q8_answ3, q8_answ4,
#                                            q9_id, q9_answ1, q9_answ2, q9_answ3, q9_answ4,
#                                            q10_id, q10_answ1, q10_answ2, q10_answ3, q10_answ4)
# VALUES ()


