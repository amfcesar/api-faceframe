# Api-faceframe 

Api-faceframe é um microserviço de para redes social, essa api tem como objetivo forncer o gerencimento de fotos e post de um usuario. 

---

<p align="center">
  <a href="#architecture">Arquitetura</a>&nbsp;&nbsp;
  <a href="#setup">Configurações</a>&nbsp;&nbsp;
  <a href="#api-documentation">API Documentação</a>&nbsp;&nbsp;
  <a href="#logs">Logs</a>&nbsp;&nbsp;
</p>

---

## Arquitetura

A arquitetura é bem simples. Todas as requisições, passam por um prox reverso `nginx` 
e em seguinda são distribuida entre os dois containers da api, que por sua vez acessam
o banco de dados. 

## Configuração

Clone o repositório na sua pasta de trabalho:

```ssh
git clone git@github.com:amfcesar/api-faceframe.git
```

É altamente recomendável usar o [Docker](https://www.docker.com/) para configurar o ambiente do projeto, além do [Docker Compose](https://docs.docker.com/compose/) para orquestrar.


Adicione ao seu `docker-compose.yml` a especificação do contêiner abaixo:

```yml
  faceframe1:
    build:
      dockerfile: Dockerfile
      context: .
    container_name: faceframe1
    ports:
      - "8080"
    networks:
      - production-network
```

O contêiner api-faceframe depende dos seguintes contêiners `postgres` e `ngnix`. Verifique se você já os possui configurados e em execução. Você pode seguir as etapas principais do projeto  [configurações](www.google.com) para ajudá-lo.


## API Documentation

We document our API with [Swagger](https://swagger.io/). Check it out here:

* [Local](http://localhost:8080/swagger-ui.html)

## Logs

Todas as funções que acessam o banco de dados são logadas. Para facilitar o monitoramento da aplição, ela esta muito bem logada.

