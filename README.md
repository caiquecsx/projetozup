# Desafio ZUP

### 🚀 Sobre

Este é um projeto fictício, um microsserviço para registro de Produtos e Pedidos seguindos algumas regras.  

### 📌 Funcionalidades
API de Produtos
* Criação, listagem, edição e remoção
* Validação de atributos

API de Pedidos
* Pedidos está relacionado com produtos.
* Valor do pedido é calculado considerando o valor dos produtos e desconto informado. 

### 🔨 Tecnologias
* [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Spring Validation](https://spring.io/guides/gs/validating-form-input/)
* [Spring Boot Devtools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html)
* [Springfox - Swagger2](https://springfox.github.io/springfox/)
* [PostgresSQL](https://www.postgresql.org/)
* [Logback](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/reference/html/howto-logging.html)
* [Spring Boot Test & JUnit 5](https://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/html/boot-features-testing.html)
* [Opentracing + Jaeger](https://www.jaegertracing.io/)


### 💻 Como usar

Para iniciar a aplicação é necessário realizar alguns passos:

Instalação básica do projeto com docker:

🚨 Importante! A versão utilizado do docker foi 2.5.0.0
```sh
$ git clone https://github.com/caiquecsx/projetozup.git
$ cd projetozup
$ docker-compose up
```
🚨 Importante! 
O projeto utiliza as portas 8080 e 5432, para comunicação da aplicação e postgres respectivamente,
caso ocorra falha na comunicação com a base, talvez a configuração 
do projeto esteja apontando para ``localhost``
para comunicar com o container da base, deve apontar para ``db``.

Para refazer o build caso existam modificações deve ser executado:
```sh
$ docker-compose up --build
```

Para executar apenas a base de dados e trabalhar localmente:
```sh
$ docker-compose up db
```

Iniciar apenas o Jeager: 
```sh
$ docker-compose up jaeger
```
🚨 Importante! 

O Jaeger ainda não está encontrando o serviço, quando está rodando no docker. Apenas quando o projeto é executado localmente.

---
Desenvolvido por [Caique Campos](https://www.linkedin.com/in/caiquecsx/) 🚀