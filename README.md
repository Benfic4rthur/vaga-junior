# Cadastro e Consulta de Abastecimentos

Solução do desafio técnico para cadastro e consulta de abastecimentos em posto de combustível. A implementação foi feita como API REST, uma das opções previstas no enunciado original.

## Tecnologias

- Java 17
- Spring Boot 4.1.1
- Maven
- Spring Web
- Spring Data JPA
- H2 Database

## Como executar

É necessário ter Java 17 e Maven instalados.

```bash
mvn spring-boot:run
```

A aplicação inicia em:

```
http://localhost:8080
```

O banco H2 é armazenado em `./data/posto`, então os dados permanecem salvos após reiniciar a aplicação. A pasta `data` não é versionada.

## Endpoints

Os três recursos possuem operações de criação, consulta, alteração e exclusão.

| Recurso | Endpoint |
| --- | --- |
| Tipos de combustível | `/tipos-combustivel` |
| Bombas | `/bombas` |
| Abastecimentos | `/abastecimentos` |

Operações disponíveis:

- `GET /recurso`
- `GET /recurso/{id}`
- `POST /recurso`
- `PUT /recurso/{id}`
- `DELETE /recurso/{id}`

### Tipo de combustível

```json
{
  "nome": "Gasolina Comum",
  "precoLitro": 6.19
}
```

### Bomba

A bomba deve apontar para um tipo de combustível já cadastrado.

```json
{
  "nome": "Bomba 1",
  "tipoCombustivel": {
    "id": 1
  }
}
```

### Abastecimento

O abastecimento deve apontar para uma bomba já cadastrada.

```json
{
  "bomba": {
    "id": 1
  },
  "data": "2026-09-26",
  "valorTotal": 100.00,
  "litragem": 15.898
}
```

## Estrutura

O projeto está separado em quatro pacotes principais:

- `model`, entidades persistidas pelo JPA
- `repository`, acesso aos dados com Spring Data
- `service`, regras de cadastro e relacionamentos
- `controller`, endpoints HTTP da API

Os relacionamentos implementados são:

- uma bomba pertence a um tipo de combustível
- um abastecimento pertence a uma bomba

## Testes

Os testes automatizados usam H2 em memória, sem alterar o banco utilizado ao executar a aplicação normalmente.

Para executar:

```bash
mvn test
```

## Uso do código

© 2026 Arthur Benfica Graff. Este projeto foi desenvolvido exclusivamente para fins de avaliação técnica. Não é autorizada a utilização, reprodução ou exploração comercial deste código sem autorização expressa do autor.
