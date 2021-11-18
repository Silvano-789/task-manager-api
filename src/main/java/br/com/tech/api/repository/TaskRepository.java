package br.com.tech.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tech.api.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
