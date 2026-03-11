package com.acme.todolist.application.service;

import com.acme.todolist.application.port.in.AddTodoItem;
import com.acme.todolist.application.port.out.UpdateTodoItem;
import com.acme.todolist.domain.TodoItem;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class AddTodoItemService implements AddTodoItem {

	private UpdateTodoItem updateTodoItem;

	@Inject
	public AddTodoItemService(UpdateTodoItem updateTodoItem) {
		this.updateTodoItem = updateTodoItem;
	}

	@Override
	public void addTodoItem(TodoItem item) {
		this.updateTodoItem.storeNewTodoItem(item);
	}

}
