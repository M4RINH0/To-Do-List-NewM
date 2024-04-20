package com.newm.backend.task;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tasks")
@Entity(name = "tasks")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    //Declaração dos tipos e variaveis da Classe
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Setter
    private String status;

    // Construtor que aceita um objeto TaskRequestDTO para inicializar os campos
    public Task(TaskRequestDTO data) {
        this.title = data.title();
        this.description = data.description();
        this.status = data.status();
    }

}
