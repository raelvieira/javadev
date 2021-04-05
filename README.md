# Collaborator API

### API para cadastro de colaboradores

## 👨‍💻 Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias:
- [Java](https://www.java.com/pt_BR/download/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Flyway](https://flywaydb.org/)
- [MySQL](https://www.mysql.com/)
- [Docker](https://www.docker.com/products/docker-desktop)
- [Swagger](https://swagger.io/)
- [Junit 5](https://junit.org/junit5/)
- [Lombok](https://projectlombok.org/)

## 💻 Projeto

O projeto trata-se de uma API para atender uma solicitação de um setor da empresa para cadastro de colaboradores e essa API é consumida por diversos sistemas.

Como forma de facilitar os testes e conhecimento da API, foi disponibilizada uma documentação pelo Swagger através da rota **/swagger-ui.html** para acessá-la, basta executar o projeto!
Além disso, também foi disponibilizada uma rota para o monitoramento da API, para acessar utilize a rota **/actuator**

## 🤔 Como executar

Primeiramente, antes do tutorial de execução queria informar o usuário e senha padrão para se ter acesso ao sistema, que será criado assim que executar o projeto. Ele será criado automáticamente com as migrations do banco de dados.

- Email: israel@email.com
- Senha: 123456

### Bom, você pode executar esse projeto de duas formas, a primeira é pela execução padrão que será descrita mais abaixo e a segunda é através do docker.

Se você decidir executar o projeto pelo docker, a única coisa que você vai precisar fazer é: acessar a pasta raiz onde contém o **docker-compose.ym** e executar o seguinte comando:

`docker-compose up -d --build`

a porta que está sendo exposta para a API é a 8080.

Preste atenção! Em alguns ambientes o docker impede que você acesse através da url **localhost** ou **127.0.0.1** (um exemplo disso é algumas versões do windows), então, em alguns casos você vai ter que acessar pelo endereço **192.168.99.100**, que será o substituto do localhost, mas, verifique isso caso você não consiga ter acesso!

### Executando da maneira tradicional

Para realizar a execução do projeto é bem simples. Este projeto possui integração com o banco de dados MySQL, portanto, é importante que você verifique se todas as configurações de usuário e senha para acessar o banco estejam corretas, você pode fazer isso através do arquivo de propriedades que fica no resources do projeto, como mostra a imagem:

![image](https://user-images.githubusercontent.com/45599504/102031949-4f508d80-3d96-11eb-8aff-4c70ca8c4c61.png)

Após isso, não é necessário criar um banco de dados com o nome que está no projeto. Apenas certifique-se de que as configurações de acesso ao banco de dados estão corretas, isso inclui URL para o banco, Porta (se for necessário), Usuário e Senha (não se esqueça de alterar as do Flyway também). Após se certificar, o projeto irá se encarregar de criar o banco e montar seu esquema, pois, como já mencionado, o projeto já conta com uma tecnologia de migração de banco de dados o Flyway.

Se você estiver rodando por uma IDE, também será necessário que você tenha o plugin do lombok instalado, para que as anotações consigam gerar os códigos de construtores, getters e setters.

Após ter feito essa pequena verificação de configuração, você pode dá um start no projeto, para isso, se você estiver em uma IDE, basta executar a classe **JavaPlenoIsraelVieiraApplication** na raiz do projeto! Se você ainda não tiver baixado as dependências, assim que você executar, provavelmente o projeto vai se encarregar de baixar. Pode demorar um pouco para executar, pois, ele deverá baixar as dependências e realizar a migração do banco.

by Israel Vieira 👋 [Entre em contato pelo Linkedin!](https://www.linkedin.com/in/israelvieiraa/)
