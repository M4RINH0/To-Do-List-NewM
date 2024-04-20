package com.newm.backend.task;

// Classe de transferência de dados (DTO) para solicitações de criação/atualização de tarefas
public record TaskRequestDTO(String title, String description, String status) {
}
