<template>
    <!-- Elemento principal -->
    <main>
        <!-- Componente Navbar -->
        <Navbar />

        <!-- Barra de Pesquisa -->
        <div class="container">
            <div class="row justify-content-center my-4">
                <div class="col-md-6">
                    <!-- Campo de entrada para pesquisa -->
                    <input type="text" class="form-control" v-model="searchQuery" @input="searchTasks" placeholder="Pesquisar por título">
                </div>
            </div>
        </div>

        <!-- Tabela de Tarefas -->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">Ver Tarefas</h1>
                    <!-- Botão para adicionar nova tarefa -->
                    <a href="/add" class="btn btn-primary">Nova Tarefa</a>
                    <!-- Tabela de tarefas -->
                    <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Título</th>
                            <th scope="col">Descrição</th>
                            <th scope="col">Status</th>
                            <th scope="col">Ação</th>
                          </tr>
                        </thead>
                        <tbody>
                            <!-- Loop para exibir as tarefas -->
                          <tr v-for="task in filteredTasks" :key="task.id">
                            <th scope="row">{{task.id}}</th>
                            <td>{{task.title}}</td>
                            <td>{{task.description}}</td>
                            <td>{{task.status}}</td>
                            <!-- Botões para editar e excluir tarefas -->
                            <td>
                              <a class="btn btn-primary" :href="`/edit/${task.id}`">Editar</a>
                              <button class="btn btn-danger mx-2" @click="deleteTask(task.id)">Excluir</button>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                </div>
            </div>
        </div>
        
    </main>
</template>

<script>
import Navbar from '../components/Navbar.vue'

export default {
    // Nome do componente
    name: 'ViewTasks',
    // Componentes utilizados
    components: {
        Navbar
    },
    // Dados do componente
    data() {
        return {
            // Lista de tarefas e consulta de pesquisa
            tasks: [],
            searchQuery: ''
        }
    },

    // Método executado antes da montagem do componente
    beforeMount(){
        // Chama o método para obter as tarefas
        this.getTasks()
    },

    // Propriedade computada para filtrar as tarefas com base na consulta de pesquisa
    computed: {
        filteredTasks() {
            if (!this.searchQuery) {
                return this.tasks; // Retorna todas as tarefas se a consulta estiver vazia
            }
            // Filtra as tarefas com base na consulta de pesquisa
            return this.tasks.filter(task => {
                return task.title.toLowerCase().includes(this.searchQuery.toLowerCase());
            });
        }
    },

    // Métodos do componente
    methods: {
        // Método para obter todas as tarefas
        getTasks(){
            fetch('http://localhost:8080/task')
            .then(res => res.json())
            .then(data => {
                this.tasks = data; // Define a lista de tarefas com os dados recebidos
                console.log(data);
            })
            .catch(error => {
                console.error('Erro ao buscar tarefas:', error);
            });
        },
        // Método para excluir uma tarefa
        deleteTask(id){
            fetch(`http://localhost:8080/task/${id}`, {
                method: 'DELETE'
            })
            .then(data => {
                console.log(data);
                // Recarrega a lista de tarefas após a exclusão
                this.getTasks();
            })
            .catch(error => {
                console.error('Erro ao excluir tarefa:', error);
            });
        },
        // Método para buscar tarefas com base na consulta de pesquisa
        searchTasks() {
            if (!this.searchQuery) {
                // Se a consulta estiver vazia, recarrega todas as tarefas
                this.getTasks();
            } else {
                // Caso contrário, faz a solicitação para buscar tarefas com base na consulta
                fetch(`http://localhost:8080/task/search?query=${this.searchQuery}`)
                .then(res => res.json())
                .then(data => {
                    this.tasks = data; // Define a lista de tarefas com os dados recebidos da pesquisa
                })
                .catch(error => {
                    console.error('Erro ao buscar tarefas:', error);
                });
            }
        }
    }
}
</script>
