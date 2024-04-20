package com.newm.backend.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.newm.backend.task.TaskRequestDTO;
import com.newm.backend.task.Task;
import com.newm.backend.task.TaskRepository;
import com.newm.backend.task.TaskResponseDTO;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    // Método para salvar uma nova tarefa
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveTask(@RequestBody TaskRequestDTO data){
        Task taskData = new Task(data);
        repository.save(taskData);
    }

    // Método para obter todas as tarefas
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<TaskResponseDTO> getAll(){
        return repository.findAll().stream().map(TaskResponseDTO::new).toList();
    }

    // Método para obter uma tarefa pelo seu ID
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada com o ID: " + id));
        return ResponseEntity.ok(new TaskResponseDTO(task));
    }

    // Método para atualizar o status de uma tarefa pelo seu ID
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long id, @RequestBody String status) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada com o ID: " + id));

        // Atualizar o status da tarefa
        task.setStatus(status);
        repository.save(task);

        return ResponseEntity.ok().build();
    }

    // Método para excluir uma tarefa pelo seu ID
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada com o ID: " + id);
        }
    }

    // Método para buscar tarefas com base em uma consulta de título semelhante
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/search")
    public ResponseEntity<List<TaskResponseDTO>> searchTasks(@RequestParam(required = false) String query) {
        if (query == null || query.isEmpty()) {
            // Se a consulta estiver vazia, retorne todas as tarefas
            List<TaskResponseDTO> allTasks = repository.findAll().stream().map(TaskResponseDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(allTasks);
        } else {
            // Caso contrário, execute a pesquisa
            List<Task> foundTasks = repository.findByTitleSimilarTo(query);
            if (foundTasks.isEmpty()) {
                // Se nenhuma tarefa for encontrada, retorne um 404
                return ResponseEntity.notFound().build();
            } else {
                // Se as tarefas forem encontradas, retorne-as
                List<TaskResponseDTO> foundTaskDTOs = foundTasks.stream().map(TaskResponseDTO::new).collect(Collectors.toList());
                return ResponseEntity.ok(foundTaskDTOs);
            }
        }
    }
}
