package com.newm.backend.task;

// Classe de transferência de dados (DTO) para respostas de busca de tarefas
public record TaskResponseDTO(Long id, String title, String description, String status) {

    // Construtor que aceita um objeto Task e extrai os atributos para criar um DTO de resposta
    public TaskResponseDTO (Task task){
        this(task.getId(), task.getTitle(), task.getDescription(), task.getStatus());
    }
}
