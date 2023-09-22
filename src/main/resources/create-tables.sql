CREATE DATABASE IF NOT EXISTS `devops_db`;
use `devops_db`;

DROP TABLE IF EXISTS `matricula`;
DROP TABLE IF EXISTS `curso`;
DROP TABLE IF EXISTS `aluno`;

CREATE TABLE `curso` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

INSERT INTO `curso` VALUES
	(1, "Botanica");
    
CREATE TABLE `aluno` (
    ra INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    qtd_cursos_disponivel INT
);

INSERT INTO `aluno` VALUES
	(1, "William", 1);

CREATE TABLE `matricula` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nota DECIMAL(5, 2),
    pode_fazer_sub BOOLEAN,
    status VARCHAR(20),
    Curso_id INT,
    Aluno_ra INT,
    FOREIGN KEY (Curso_id) REFERENCES Curso(id),
    FOREIGN KEY (Aluno_ra) REFERENCES Aluno(ra)
);

-- INSERT INTO `matricula` VALUES
-- 	(1, 7.0, false, "CURSANDO", 1, 1);