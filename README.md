# Culture Events API

Este projeto é uma API para gerenciamento de eventos culturais, construída com Spring Boot 3 e Java 17. A API fornece funcionalidades para a criação, leitura, atualização e exclusão de eventos culturais.

## Tecnologias Utilizadas

- **Java**: 17
- **Spring Boot**: 3.4.0
- **Banco de Dados**: PostgreSQL
- **Swagger**: Para documentação da API
- **Docker**: Para containerização da aplicação

## Pré-requisitos

- Java 17
- Docker e Docker Compose
- Gradle

## Configuração da API

A configuração da API está disponível na classe `SwaggerConfig`, onde você pode encontrar:

- **Título**: Culture Events API
- **Descrição**: API para gerenciamento de eventos culturais
- **Versão**: 1.0.0
- **Contato**: Equipe de Suporte - [anderson.luiz.sales@gmail.com](mailto:anderson.luiz.sales@gmail.com)
- **Licença**: Apache 2.0

A API está disponível em [http://localhost:9080/api](http://localhost:9080/api).

## Como Executar o Projeto

### 1. Compilar a Aplicação

Primeiro, você precisa compilar a aplicação para gerar o arquivo JAR:

```bash
./gradlew build
```

### 2. Executar com Docker
Após a compilação, você pode usar o Docker Compose para construir e executar os containers:

```bash
sudo docker-compose up --build
```
### 3. Acessar a API
A API estará disponível em http://localhost:9080/api. Você pode acessar a documentação da API gerada pelo Swagger em:

### Swagger
http://localhost:9080/api/swagger-ui/index.html#/
