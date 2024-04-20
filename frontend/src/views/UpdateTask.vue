<template>
  <main>
    <!-- Componente Navbar -->
    <Navbar />

    <!-- Formulário para atualizar a tarefa -->
    <div class="my-5">
      <div class="mx-auto w-50" style="max-width: 100%;">
        <h2 class="text-center mb-3">Atualizar Status da Tarefa</h2>
        <form @submit.prevent="updateTask">

          <!-- Título da tarefa (não editável) -->
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label class="form-label">Título</label>
              <input type="text" class="form-control" :value="task.title" readonly>
            </div>
          </div>

          <!-- Descrição da tarefa (não editável) -->
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label class="form-label">Descrição</label>
              <textarea class="form-control" rows="3" readonly>{{ task.description }}</textarea>
            </div>
          </div>

          <!-- Seleção do status da tarefa -->
          <label for="status" class="form-label">Status</label>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="status" id="em andamento" value="em andamento"
              v-model="task.status">
            <label class="form-check-label" for="em andamento">Em Andamento</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="status" id="concluido" value="concluido"
              v-model="task.status">
            <label class="form-check-label" for="concluido">Concluído</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="status" id="não iniciado" value="não iniciado"
              v-model="task.status">
            <label class="form-check-label" for="não iniciado">Não Iniciado</label>
          </div>

          <!-- Botão para atualizar a tarefa -->
          <div class="row">
            <div class="col-md-12 form-group">
              <button class="btn btn-primary w-100" @click="updateTask">Atualizar</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>

<script>
import Navbar from '../components/Navbar.vue';

export default {
  name: 'UpdateTask',
  components: {
    Navbar
  },

  data() {
    return {
      // Objeto task para armazenar os detalhes da tarefa
      task: {
        id: '',
        title: '',
        description: '',
        status: ''
      }
    }
  },

  // Método que é executado antes da montagem do componente
  beforeMount() {
    // Chama o método para obter os detalhes da tarefa
    this.getTask();
  },

  methods: {
    // Método para obter os detalhes da tarefa
    getTask() {
      fetch(`http://localhost:8080/task/${this.$route.params.id}`)
        .then(res => res.json())
        .then(data => {
          this.task = data; // Define os detalhes da tarefa no objeto task
          console.log(this.task);
        })
        .catch(error => {
          console.error('Erro ao buscar os detalhes da tarefa:', error);
        });
    },

    // Método para atualizar a tarefa
    updateTask() {
      const status = this.task.status; // Extrai o status da tarefa
      // Envia uma requisição PUT para atualizar o status da tarefa
      fetch(`http://localhost:8080/task/${this.$route.params.id}/status`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: status // Envia apenas o status como uma string
      })
        .then(response => {
          if (response.ok) {
            console.log('Status da tarefa atualizado com sucesso');
            this.$router.push('/'); // Redireciona para a página inicial após a atualização
          } else {
            console.error('Falha ao atualizar o status da tarefa:', response.statusText);
          }
        })
        .catch(error => {
          console.error('Erro ao atualizar o status da tarefa:', error);
        });
    }
  }
}
</script>
