# Projeto Spring Boot com MySQL

Este é um projeto Spring Boot que utiliza o MySQL como banco de dados. Siga as instruções abaixo para configurar e rodar o projeto.

## Pré-requisitos

Certifique-se de que você tenha as seguintes ferramentas instaladas em sua máquina:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html): Instale o Java JDK 8 ou posterior.
- [Maven](https://maven.apache.org/): Instale o Maven para gerenciar as dependências do projeto.
- [MySQL](https://www.mysql.com/): Instale o MySQL Server para criar o banco de dados.

## Configuração do Banco de Dados

1. Crie um banco de dados no MySQL. Você pode usar o MySQL Workbench ou o cliente MySQL de sua escolha.

2. Abra o arquivo `create-tables.sql` que está na raiz do projeto no seu cliente MySQL.

3. Após a execução bem-sucedida do arquivo SQL, as tabelas e outros objetos definidos no arquivo serão criados no banco de dados MySQL especificado.

4. Abra o arquivo src/main/resources/application.properties e configure as propriedades do banco de dados de acordo com suas credenciais:

   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_seu_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

   Substitua nome_do_seu_banco, seu_usuario e sua_senha pelos valores corretos.

## Executando o Projeto

1. Abra um terminal na raiz do projeto.

2. Compile o projeto usando o Maven:

   ```shell
   mvn clean install
   ```

3. Inicie a aplicação Spring Boot:

   ```shell
   mvn spring-boot:run
   ```

   A aplicação será iniciada na porta padrão 8080.

4. Abra um navegador web e acesse http://localhost:8080 para verificar se a aplicação está em execução. Você deve ver uma página inicial ou uma mensagem de boas-vindas.
