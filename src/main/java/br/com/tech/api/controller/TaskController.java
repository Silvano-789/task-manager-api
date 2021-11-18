package br.com.tech.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.tech.api.model.Task;
import br.com.tech.api.repository.TaskRepository;

@RestController
@RequestMapping("/task")
@CrossOrigin("http://localhost:4200/")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping
	public Task save(@RequestBody Task task) {
		return taskRepository.save(task);
	}
	
	@RequestMapping()
	public List<Task> getAll(){
		return taskRepository.findAll();
	}
	
	/*url/tech-api/task/id*/
	@RequestMapping("{id}")
	public Task getById(@PathVariable Long id) {
		return taskRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PatchMapping("{id}/done")
	public Task markAsDone(@PathVariable Long id) {
		return taskRepository.findById(id).map(task -> {
			task.setDone(true);
			task.setDoneDate(LocalDateTime.now());
			taskRepository.save(task);
			return task;
		}).orElse(null);
	}

	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		taskRepository.deleteById(id);
	}	
	
}
