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


### Considerações
> Alguns pontos não foram atingidos até o momento
> Tecnologia, versionamento, collection no postman
> base de dados utilizada Postgres
> Docker não foi utlizado, por falta de experiência
> devido ao tempo, o projeto foi desenvolvido
> focando em tecnologias já conhecidas.
> Opentraing + Jaeger também ainda não implantados no projeto.
> Testes unitários ainda não implantados.

Após o download do projeto:
```sh
$ mvn clean install
$ mvn spring-boot:run
```