# Trabalho de Programação Orientada a Objetos e Banco de Dados

Este trabalho foi realizado em conjunto pelas disciplinas de "Programação Orientada a Objetos" e "Banco de Dados", durante o 5º período do curso de Engenharia de Computação no IFSULDEMINAS, campus Poços de Caldas.

## Sistema de Gerenciamento de Biblioteca com Herança e Polimorfismo em Java com MySQL

### Descrição do Projeto

Desenvolva um sistema de gerenciamento de biblioteca utilizando Java, implementando conceitos de herança e polimorfismo para criar uma estrutura de classes eficiente. O sistema deve representar usuários, funcionários, livros e outros itens da biblioteca e deve interagir com um banco de dados MySQL para armazenar e gerenciar dados. A interface de interação com o sistema será via linha de comando.

### Estrutura de Classes

- **Pessoa (Classe Base)**
  - **Atributos:** CPF, Nome
  - Usuários e Funcionários herdarão desta classe.

- **Usuário (Deriva de Pessoa)**
  - **Atributos:** Endereço, Telefone

- **Funcionário (Deriva de Pessoa)**
  - **Atributos:** Função, Salário

- **Item de Biblioteca (Classe Base Abstrata)**
  - **Atributos:** Código, Título, Status (disponível, emprestado)

- **Livro (Deriva de Item de Biblioteca e Implementa Emprestavel)**
  - **Atributos:** Edição, Gênero, Ano de Publicação, CódigoEditora

- **Editora**
  - **Atributos:** Código, Nome, Contato

- **Autores**
  - **Atributos:** Código, Nome, Nacionalidade

- **Emprestavel (Interface)**
  - **Métodos:** emprestar(), devolver()

### Operações de Gerenciamento

- **Cadastro e Atualização:**
  - Adicionar, editar e remover informações de usuários, funcionários, livros, editoras e autores.

- **Gestão de Empréstimos:**
  - Permitir o empréstimo e a devolução de livros, utilizando os métodos definidos na interface Emprestavel.

- **Consultas:**
  - Buscar informações sobre itens da biblioteca, empréstimos, usuários e funcionários.

### Requisitos Técnicos

- Implementar herança nas classes para aproveitar atributos e métodos comuns.
- Utilizar polimorfismo para tratar diferentes tipos de itens emprestáveis de maneira uniforme.
- Conectar com um banco de dados MySQL para persistência de dados.
- Desenvolver uma interface de linha de comando para interação com o sistema.
- A estrutura das classes pode ser ajustada conforme necessário para atender às especificidades do projeto.

### Instruções Adicionais

- O arquivo `biblioteca.sql` deve ser importado para o MySQL Workbench para estabelecer a conexão com o banco de dados.
- Caso o usuário e a senha do banco de dados sejam diferentes, é necessário alterar as configurações na classe `ConexaoMysql.java`.
