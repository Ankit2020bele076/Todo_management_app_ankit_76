package com.todoapp.TodoManagementApp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int count = 0;
	
	static {
		todos.add(new Todo(++count,"Ankit Payal", "Learn AWS", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++count,"Ankit Payal", "Learn Azure", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++count,"Ankit Payal", "Learn Full Stack", LocalDate.now().plusYears(3), false));
	}
	
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo > predicate = todos -> todos.getUsername().equals(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++count, username, description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		Predicate<? super Todo > predicate = todos -> todos.getId() == id;
		todos.removeIf(predicate);
	}
	public Todo findById(int id) {
		for(Todo i : todos) {
			if(i.getId() == id) {
				return i;
			}
		}
		return null;
	}
	public void updateTodo(Todo todo) {
		Todo T = findById(todo.getId());
		T.setId(todo.getId());
		T.setUsername("Ankit Payal");
		T.setDescription(todo.getDescription());
		T.setTargetDate(todo.getTargetDate());
		T.setDone(todo.isDone());
	}
}
