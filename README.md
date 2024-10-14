
![Logo](https://i.ibb.co/28CdGFY/BANNER-TAQUI.png)
---
# Projeto TAQUI

## Vídeo pedido na entrega

https://www.youtube.com/playlist?list=PLnsC4Y30EcL5EpXuIRGfx6y7TnEQxdoOL

[![Assista ao Video](https://i.ibb.co/DVRpqxq/taqui-imagem-tela-video-2.png)](https://www.youtube.com/playlist?list=PLnsC4Y30EcL5EpXuIRGfx6y7TnEQxdoOL)

---
# Documentação TAQUI


---------------------------------------
Dockerfile:
```
# Usar a imagem base do JDK 17
FROM openjdk:17-jdk-slim AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar os arquivos de construção do Gradle
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src/
COPY gradlew ./
COPY gradlew.bat ./

# Dar permissão de execução ao script gradlew
RUN chmod +x gradlew

#tive que adicionar essa duas linhas pois o meu pc não estava reconhecendo gradle
RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix gradlew

# Executar o comando de construção do Gradle
RUN ./gradlew build --no-daemon

# Usar uma imagem mais leve para a aplicação final
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado da fase de construção
COPY --from=build /app/build/libs/*.jar app.jar

# Expor a porta 8080
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
```
Comandos no cmd:
```
//Buildar a imagem
docker build -t imagem .

//Testar a imagem local
docker run -d -p 8080:8080 imagem

//Criar grupo de recurso
az group create --name rg-cp5 --location eastus
 
//Criar ACR
az acr create --resource-group rg-cp5 --name acrcp5 --sku Basic
 
//Logar no ACR
az acr login --name acrcp5

//Colocar tag pra subir no ACR
docker tag imagem acrcp5.azurecr.io/rm552342-cp5:v1

//Subir no ACR
docker push acrcp5.azurecr.io/rm552342-cp5:v1
 
//Testar puxando imagem do ACR local
//docker run -d -p 8080:8080 acrcp5.azurecr.io/rm552342-cp5:v1

//Entrar no recurso criado, configurações chaves de acesso e habilitar a caixinha de admin

//Logar novamente usando a senha gerada pelo azure
az acr login --name acrcp5 --username acrcp5 --password colocar-a-senha-aqui


az container create `
--resource-group rg-cp5 `
--name acicp5rm552342 `
--image acrcp5.azurecr.io/rm552342-cp5:v1 `
--cpu 1 `
--memory 1 `
--registry-login-server acrcp5.azurecr.io `
--registry-username acrcp5 `
--registry-password colocar-a-senha-aqui `
--ip-address Public `
--dns-name-label acicp5rm552342 `
--ports 3000 80 8080


```
SQL mysql( foi usado esse no video):

```
create table taqui_produto (
        pk_id_produto bigint not null,
        ds_descricao VARCHAR(100) not null,
        nm_exibicao VARCHAR(50) not null,
        nr_preco float(23) not null,
        pk_id_usuario bigint not null,
        primary key (pk_id_produto)
    ) engine=InnoDB
    create table taqui_usuario (
        pk_id_usuario bigint not null,
        ds_email VARCHAR(100) not null,
        nm_exibicao VARCHAR(50) not null,
        ds_senha VARCHAR(15) not null,
        primary key (pk_id_usuario)
    ) engine=InnoDB

    alter table taqui_usuario 
       drop index UK_EMAIL

    alter table taqui_usuario 
       add constraint UK_EMAIL unique (ds_email)

    alter table taqui_produto 
       add constraint FKicugv4fe13c00njhjgqgp9wv6 
       foreign key (pk_id_usuario) 
       references taqui_usuario (pk_id_usuario) 
       on delete cascade
```
SQL oracle:

```
    create table taqui_produto (
        pk_id_produto NUMBER(10) not null,
        ds_descricao VARCHAR(100) not null,
        nm_exibicao VARCHAR(50) not null,
        nr_preco float(24) not null,
        pk_id_usuario NUMBER(10) not null,
        primary key (pk_id_produto)
    )

    create table taqui_usuario (
        pk_id_usuario NUMBER(10) not null,
        ds_email VARCHAR(100) not null,
        nm_exibicao VARCHAR(50) not null,
        ds_senha VARCHAR(15) not null,
        primary key (pk_id_usuario)
    )

    alter table taqui_usuario 
       drop constraint UK_EMAIL

    alter table taqui_usuario 
       add constraint UK_EMAIL unique (ds_email)

    create sequence sq_tb_produto start with 1 increment by 1

    create sequence sq_tb_usuario start with 1 increment by 1

    alter table taqui_produto 
       add constraint FKicugv4fe13c00njhjgqgp9wv6 
       foreign key (pk_id_usuario) 
       references taqui_usuario 
       on delete cascade
```
---------------------------------------
 ## Instruções de testes:


 No sistema temos essas url:

  - Home : http://acicp5rm552342.eastus.azurecontainer.io:8080/template
  - Lista de Usuarios : http://acicp5rm552342.eastus.azurecontainer.io:8080/listaUsuariosTemplate
  - Lista de Produtos: http://acicp5rm552342.eastus.azurecontainer.io:8080/listaProdutosTemplate
  - Cadastrar Usuarios: http://acicp5rm552342.eastus.azurecontainer.io:8080/cadastroUsuario
  - Cadastrar Produtos: http://acicp5rm552342.eastus.azurecontainer.io:8080/cadastroProduto

 No sistema podemos deletar, cadastrar, editar e ver a lista de produtos e Usuarios

Campos obrigatórios: *

Cadastro e Edição de usúario:

Nome* - O campo não pode estar vazio e deve ter no máximo 50 carácteres.

Senha*- Não pode estar vazio, deve ter no minimo 12 e maximo de 15 caracteres e conter pelo menos um caractere especial e uma letra maiúscula.

Email* - O campo não pode estar vazio, deve ter no máximo 50 carácteres e deve ser um email com @.

<a href="https://ibb.co/zHctKNL"><img src="https://i.ibb.co/2Y2pX5L/cadastro-usuario.png" alt="cadastro-usuario"/></a>

Cadastro e Edição de produto: 

Deve ter um usúario cadastro para poder cadastrar um produto.

Nome* - O campo não pode estar vazio e deve ter no máximo 50 carácteres.

Descrição* - O campo não pode estar vazio e deve ter no máximo 50 carácteres.

Preço* - O campo não pode estar nulo.

Id do úsuario* - Deve ser o mesmo id que já tem cadastrado na lista de usuários

<img src="https://i.ibb.co/JHHtyDT/cadastro-produto.jpg" alt="cadastro-produto" border="0" />

Ao deletar um usuário, os produtos vinculados a ele são excluídos automaticamente.

Ao editar um usuário não deve deixar nenhum campo vazio.

Lista de Produto:

<img src="https://i.ibb.co/jgY7Gyq/lista-produto.png" alt="lista-produto" border="0" />
-------------------------------------------------------------------------------------

Lista de Usuários:
<img src="https://i.ibb.co/XWkgmZy/lista-usuario.png" alt="lista-usuario" border="0" />
------------------------------------------------------------------------
Aquitetura de JAVA:

<img src="https://i.ibb.co/D9kPjTQ/arquitetura-do-projeto.png" alt="arquitetura-do-projeto" border="0" />

---------------------------------------

Rodando o projeto na própria maquina

 No sistema temos essas url:

  - Home : http://localhost:8080/template
  - Lista de Usuarios : http://localhost:8080/listaUsuariosTemplate
  - Lista de Produtos: http://localhost:8080/listaProdutosTemplate
  - Cadastrar Usuarios: http://localhost:8080/cadastroUsuario
  - Cadastrar Produtos: http://localhost:8080/cadastroProduto

---------------------------------------------------

## Integrantes
:small_blue_diamond:Gabriel Sampaio
RM 552342		
<a href="https://www.linkedin.com/in/gabrielsampaiogianini/">Linkedin</a>
<a href="https://github.com/gabrielsampaiog">Github</a>

:small_blue_diamond:Gabriel Neves
RM 552244
<a href="https://www.linkedin.com/in/-gabriel-neves/">Linkedin</a>
<a href="https://github.com/Neveszera">Github</a>

:small_blue_diamond:Livia Freitas
RM 99892
<a href="https://www.linkedin.com/in/l%C3%ADvia-freitas-ferreira/">Linkedin</a>
<a href="https://github.com/freitaslivia">Github</a>

:small_blue_diamond:Rafael Mendonça
RM 552422
<a href="https://www.linkedin.com/in/rafael-henrique-de-mendon%C3%A7a-51263326b/">Linkedin</a>
<a href="https://github.com/rhmendonca">Github</a>


:small_blue_diamond:Renato Romeu
RM 551325
<a href="https://www.linkedin.com/in/renato-russano-706423a3/">Linkedin</a>
<a href="https://github.com/RenatoRussano">Github</a>
