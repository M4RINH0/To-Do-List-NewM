import { createRouter, createWebHistory } from 'vue-router';
import ViewTasks from '../views/ViewTasks.vue';
import AddTask from '../views/AddTask.vue';
import UpdateTask from '../views/UpdateTask.vue';

const router = createRouter({
  // Define o histórico de navegação baseado no histórico da web
  history: createWebHistory(import.meta.env.BASE_URL),
  // Define as rotas do aplicativo
  routes: [
    {
      // Rota para a página inicial
      path: '/',
      name: 'home',
      component: ViewTasks // Componente de visualização de tarefas
    },
    {
      // Rota para adicionar uma nova tarefa
      path: '/add',
      name: 'add',
      component: AddTask // Componente de adicionar tarefa
    },
    {
      // Rota para editar uma tarefa existente
      path: '/edit/:id',
      name: 'edit',
      component: UpdateTask // Componente de atualizar tarefa
    }
  ]
});

export default router;
