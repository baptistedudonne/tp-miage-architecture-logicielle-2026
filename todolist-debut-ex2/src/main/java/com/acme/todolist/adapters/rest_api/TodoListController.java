package com.acme.todolist.adapters.rest_api;

import com.acme.todolist.application.port.in.AddTodoItem;
import com.acme.todolist.application.port.in.GetTodoItems;
import com.acme.todolist.domain.TodoItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Le controlleur Spring MVC qui expose les endpoints REST
 * 
 * @author bflorat
 *
 */
@RestController
public class TodoListController {
	

	private final GetTodoItems getTodoItems;
	private final AddTodoItem addTodoItems;
	// A compléter
	
	
	@Inject
	//A compléter
	public TodoListController(GetTodoItems getTodoItems,
							  AddTodoItem addTodoItem) {
		this.getTodoItems = getTodoItems;
		this.addTodoItems = addTodoItem;
	}
	
	@GetMapping("/todos")
	public List<TodoItem> getAllTodoItems() {
		return this.getTodoItems.getAllTodoItems();
	}
	
	
	// Endpoint de type POST vers "/todos"
	@PostMapping("/todos")
	@ResponseStatus(HttpStatus.CREATED)
	public void addTodoItem(@RequestBody TodoItem item) {
		this.addTodoItems.addTodoItem(item);
	}
	
	
}
