package com.acme.todolist.adapters.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.acme.todolist.adapters.persistence.entity.TodoItemJpaEntity;
import org.springframework.data.repository.CrudRepository;;

/**
 * DAO d'accès aux TodoItem
 * @author bflorat
 *
 */
public interface TodoItemRepository extends CrudRepository<TodoItemJpaEntity,String> {
			
	List<TodoItemJpaEntity> findAll(); 
	
	Optional<TodoItemJpaEntity> findById(String id);
	
	
}
