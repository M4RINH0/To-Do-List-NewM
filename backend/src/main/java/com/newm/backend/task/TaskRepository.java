package com.newm.backend.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Método para encontrar tarefas por status
    List<Task> findByStatus(String status);

    // Consulta personalizada para encontrar tarefas com títulos semelhantes
    @Query("SELECT t FROM tasks t WHERE LOWER(t.title) LIKE LOWER(concat('%', :query, '%'))")
    List<Task> findByTitleSimilarTo(@Param("query") String query);
}
