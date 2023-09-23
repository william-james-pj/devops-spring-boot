CREATE DATABASE IF NOT EXISTS `devops_db` CHARACTER SET utf8 COLLATE utf8_general_ci;
use `devops_db`;

DROP TABLE IF EXISTS `matricula`;
DROP TABLE IF EXISTS `curso`;
DROP TABLE IF EXISTS `aluno`;

CREATE TABLE IF NOT EXISTS `curso` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

INSERT INTO `curso` VALUES
	(1, "Introdução à Psicologia 3");
INSERT INTO `curso` VALUES
	(2, "Programação em Python para Iniciantes");
INSERT INTO `curso` VALUES
	(3, "Gestão Financeira Pessoal");
    
CREATE TABLE IF NOT EXISTS `aluno` (
    ra INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    qtd_cursos_disponivel INT
);

INSERT INTO `aluno` VALUES
	(1, "Maria da Silva", 1);
INSERT INTO `aluno` VALUES
	(2, "Pedro Santos", 1);
INSERT INTO `aluno` VALUES
	(3, "Ana Pereira", 1);

CREATE TABLE IF NOT EXISTS `matricula` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nota DECIMAL(5, 2),
    pode_fazer_sub BOOLEAN,
    status VARCHAR(20),
    Curso_id INT,
    Aluno_ra INT,
    FOREIGN KEY (Curso_id) REFERENCES Curso(id),
    FOREIGN KEY (Aluno_ra) REFERENCES Aluno(ra)
);

INSERT INTO `matricula` VALUES
	(1, 0, false, "CURSANDO", 1, 1);
INSERT INTO `matricula` VALUES
	(2, 8.0, false, "FINALIZADO", 2, 2);
INSERT INTO `matricula` VALUES
	(3, 0, false, "CURSANDO", 3, 2);
INSERT INTO `matricula` VALUES
	(4, 0, false, "CURSANDO", 3, 3);