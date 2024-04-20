# Projeto CRUD de Lista de Tarefas (Processo Seletivo NewM)
## Tecnologias Utilizadas: Vue, SpringBoot, PostgreSQL.

### Documentação da Instalação e Configuração da Aplicação:

#### Backend (Spring Boot):

##### Pré-requisitos:
- Java JDK instalado na máquina.
- Apache Maven para gerenciamento de dependências.

##### Configuração do Banco de Dados:
- Certifique-se de ter o PostgreSQL instalado e em execução.
- Configure as propriedades do banco de dados no arquivo `application.properties` ou `application.yml`.

Exemplo de configuração para banco de dados local:
- spring.datasource.url=jdbc:postgresql://localhost:5432/task
- spring.datasource.username=postgres
- spring.datasource.password=1472


##### Execução do Backend:
1. Clone o repositório do backend.
2. Navegue até o diretório do projeto Spring Boot.
3. Execute o comando `mvn spring-boot:run` para iniciar o servidor.

#### Frontend (Vue.js):

##### Pré-requisitos:
- Node.js e npm instalados na máquina.

##### Instalação das Dependências:
1. Clone o repositório do frontend.
2. Navegue até o diretório do projeto Vue.js.
3. Execute o comando `npm install` para instalar as dependências do projeto.

##### Execução do Frontend:
- Após a instalação das dependências, execute o comando `npm run serve` para iniciar o servidor de desenvolvimento do Vue.js.

### Documentação dos Endpoints da API REST:

#### Endpoints da API REST:

- **GET /task:**
Retorna todas as tarefas cadastradas.

- **GET /task/{id}:**
Retorna uma tarefa específica com base no ID fornecido.

- **POST /task:**
Cria uma nova tarefa com os dados fornecidos no corpo da requisição.

- **PUT /task/{id}/status:**
Atualiza o status de uma tarefa específica com base no ID fornecido.

- **DELETE /task/{id}:**
Exclui uma tarefa específica com base no ID fornecido.

- **GET /task/search?query={query}:**
Realiza uma busca por títulos de tarefas semelhantes ao termo de pesquisa fornecido.

