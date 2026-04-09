# Sistema de Gerenciamento de Produtos
Este projeto foi desenvolvido como prática inicial em Java e banco de dados.
Objetivo: entender operações CRUD e integração com SQL.

Um sistema CRUD de gerenciamento de produtos desenvolvido em Java, com menu interativo no terminal. Foi meu primeiro projeto conectando Java a um banco de dados real — e aprendi bastante no processo.

---

## O que ele faz

- Cadastrar produtos com nome, descrição, preço e quantidade
- Listar todos os produtos salvos no banco
- Buscar um produto pelo ID
- Atualizar as informações de um produto
- Deletar um produto (com confirmação antes de apagar)

Tudo salvo de verdade no MySQL — feche o programa, abra de novo, os dados continuam lá.

---

## Tecnologias

- **Java 25**
- **MySQL** via XAMPP
- **JDBC** para comunicação com o banco
- **Padrão DAO** para separar a lógica de acesso a dados

---

## Estrutura do projeto

```
src/
├── model/         → classe Produto (encapsulamento com getters/setters)
├── dao/           → interface ProdutoDAO + implementação com JDBC
├── db/            → ConnectionFactory (gerencia a conexão com o banco)
└── app/           → Main.java (menu interativo no terminal)
```

---

## Como rodar

### Pré-requisitos
- JDK 17 ou superior
- XAMPP com MySQL rodando
- MySQL Connector/J adicionado ao classpath

### Banco de dados

No phpMyAdmin (`http://localhost/phpmyadmin`), execute:

```sql
CREATE DATABASE IF NOT EXISTS sistema_gerenciamento
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE sistema_gerenciamento;

CREATE TABLE IF NOT EXISTS produtos (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(100)   NOT NULL,
    descricao   VARCHAR(255),
    preco       DECIMAL(10, 2) NOT NULL,
    quantidade  INT            NOT NULL DEFAULT 0,
    criado_em   TIMESTAMP      DEFAULT CURRENT_TIMESTAMP
);
```

### Conexão

Em `src/db/ConnectionFactory.java`, ajuste as credenciais:

```java
private static final String URL     = "jdbc:mysql://localhost:3306/sistema_gerenciamento";
private static final String USUARIO = "root";
private static final String SENHA   = ""; // padrão XAMPP
```

### Executando

Abra o `Main.java` no VS Code e clique em **Run**. O menu vai aparecer no terminal.

---

## O que aprendi

Esse projeto foi meu primeiro contato com persistência de dados em Java. Aprendi na prática como funciona o padrão DAO, como usar `PreparedStatement` para evitar SQL injection, e como organizar um projeto orientado a objetos de forma que cada classe tenha uma responsabilidade clara.

---

## Autor

Kauã Moreira — [github.com/kaua-moreira](https://github.com/kaua-moreira)
