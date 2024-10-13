
![Logo](https://i.ibb.co/28CdGFY/BANNER-TAQUI.png)
---
# Projeto TAQUI

## Vídeo pedido na entrega

https://www.youtube.com/playlist?list=PLnsC4Y30EcL7TPk9xY3RDiu0ZEcvT0F7Z

[![Assista ao Video](https://i.ibb.co/DVRpqxq/taqui-imagem-tela-video-2.png)](https://www.youtube.com/playlist?list=PLnsC4Y30EcL7TPk9xY3RDiu0ZEcvT0F7Z)

---
# Documentação TAQUI

Primeiro deve ter instalado na sua maquina java 17 e o gradle(tutorial de como instalar o gradle: https://www.youtube.com/watch?v=R5MwoMsnh2E )

1 - Adicionamos no arquivo build.gradle o plugin do azure:
```
plugins {
    id "com.microsoft.azure.azurewebapp" version "1.1.0"
}     
```
vai ficar assim:

```
plugins {
	id 'java'
	id 'org.springframework.boot' version '3.3.3'
	id 'io.spring.dependency-management' version '1.1.6'
	id "com.microsoft.azure.azurewebapp" version "1.10.0"


}
```
2 - Adicionamos  no arquivo build.gradle a configuração do  plugin do azure:
```
azurewebapp {
	subscription = '77473478-7b1d-4345-95f9-5d7f456d8c84'
	resourceGroup = 'rgsprint3'
	appName = 'sprint3java'
	pricingTier = 'F1'
	region = 'brazilsouth'
	runtime {
		os = 'Windows'
		webContainer = 'Java SE'
		javaVersion = 'Java 17'
	}
}

```

3 - No cmd navegue até o projeto e rode esse comando:
```
gradle azureWebAppDeploy
```
fonte: https://devblogs.microsoft.com/java/gradle-deploy-java-web-apps-to-azure-in-one-step/

---------------------------------------

SQL:

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

  - Home : https://sprint3java.azurewebsites.net/template
  - Lista de Usuarios : https://sprint3java.azurewebsites.net/listaUsuariosTemplate
  - Lista de Produtos: https://sprint3java.azurewebsites.net/listaProdutosTemplate
  - Cadastrar Usuarios: https://sprint3java.azurewebsites.net/cadastroUsuario
  - Cadastrar Produtos: https://sprint3java.azurewebsites.net/cadastroProduto

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

:small_blue_diamond:Renato Romeu
RM 551325
<a href="https://www.linkedin.com/in/renato-russano-706423a3/">Linkedin</a>
<a href="https://github.com/RenatoRussano">Github</a>
