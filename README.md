# Desafio zup

Funcionalidades desenvolvidas:

API de Produtos e Pedidos
  - Criação, listagem, edição e remoção
  - validação dos atributos enviados na requisição
  - Pedidos se relacionam a produtos.
  - Valor dos produtos considera o valor de todos os envolvidos no pedido e o valor total é calculado baseado no desconto informado.

### Tecnologias
* [Java 11]
* [Spring Data JPA]
* [Spring Validation]
* [Spring Boot Devtools]
* [Springfox - Swagger2]
* [PostgresSQL]
* [Logback]
* [Spring Boot Test & JUnit 5]
* [Opentracing + Jaeger]

Após o download do projeto:

Dependências e build: 
```sh
$ mvn package --DskipTests
```

Iniciar projeto:
```sh
$ docker-compose up
```

Iniciar Jeager
```sh
$ docker run -p 9090:16686 —-name jaeger -d jaegertracing/all-in-one:1.17
```