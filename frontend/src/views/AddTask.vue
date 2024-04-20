<template>
  <main>
      <!-- Componente Navbar -->
      <Navbar />

      <!-- Formulário para adicionar nova tarefa -->
      <div class="my-5">
          <div class="mx-auto w-25 " style="max-width:100%;">
            <h2 class="text-center mb-3">Nova Tarefa</h2>
            <!-- Evento submit chama o método addTask -->
            <form @submit.prevent="addTask">

              <!-- Campo de entrada para o título da tarefa -->
              <div class="row">
                <div class="col-md-12 form-group mb-3">
                  <label for="title" class="form-label">Título</label>
                  <input id="title" type="text" name="title" class="form-control" placeholder="Título" required v-model="task.title">
                </div>
              </div>

              <!-- Campo de entrada para a descrição da tarefa -->
              <div class="row">
                  <div class="col-md-12 form-group mb-3">
                    <label for="description" class="form-label">Descrição</label>
                    <input id="description" type="text" name="description" class="form-control" placeholder="Descrição" required v-model="task.description" >
                  </div>
                </div>

              <!-- Seleção do status da tarefa -->
              <label for="status" class="form-label">Status</label>
              <div class="form-check">
                <input class="form-check-input" type="radio" name="status" id="em andamento" value="em andamento" v-model="task.status">
                <label class="form-check-label" for="em andamento">Em Andamento</label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="radio" name="status" id="concluido" value="concluido" v-model="task.status">
                <label class="form-check-label" for="concluido">Concluído</label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="radio" name="status" id="não iniciado" value="não iniciado" v-model="task.status">
                <label class="form-check-label" for="não iniciado">Não Iniciado</label>
              </div>
             
              <!-- Botão de submissão do formulário -->
              <div class="row">
                <div class="col-md-12 form-group">
                  <input class="btn btn-primary w-100" type="submit" value="Submit">
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
  name: 'AddTask',
  components: {
      Navbar
  },

  data() {
      return {
          // Objeto task para armazenar os dados da nova tarefa
          task: {
              title: '',
              description: '',
              status: ''
          }
      }
  },

  methods: {
      // Método para adicionar uma nova tarefa
      addTask(){
          fetch('http://localhost:8080/task', {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json'
              },
              body: JSON.stringify(this.task)
          })
          .then(data => {
              console.log(data)
              // Redireciona para a página inicial após adicionar a tarefa
              this.$router.push("/");
          })
      }
  },
}
</script>
