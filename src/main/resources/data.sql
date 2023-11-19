
INSERT INTO CURSO(nome) VALUES('Introducao a Psicologia 3');
INSERT INTO CURSO(nome) VALUES('Programacao em Python para Iniciantes');
INSERT INTO CURSO(nome) VALUES('Gestão Financeira Pessoal');

INSERT INTO ALUNO(nome, qtd_cursos_disponivel) VALUES('Maria da Silva', 1);
INSERT INTO ALUNO(nome, qtd_cursos_disponivel) VALUES('Pedro Santos', 1);
INSERT INTO ALUNO(nome, qtd_cursos_disponivel) VALUES('Ana Pereira', 1);

INSERT INTO MATRICULA(nota, pode_fazer_sub, status, curso_id, aluno_ra) VALUES(0, false, 'CURSANDO', 1, 1);
INSERT INTO MATRICULA(nota, pode_fazer_sub, status, curso_id, aluno_ra) VALUES(8.0, false, 'FINALIZADO', 2, 2);
INSERT INTO MATRICULA(nota, pode_fazer_sub, status, curso_id, aluno_ra) VALUES(0, false, 'CURSANDO', 3, 2);