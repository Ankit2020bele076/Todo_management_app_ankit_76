package com.todoapp.TodoManagementApp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@RequestMapping("/list-todos")
	public String listAllTodos(ModelMap model) {
		String username = (String)model.get("name");
		List<Todo> listtodos = todoRepository.findByUsername(username);
		model.put("todo",listtodos);
		return "listTodos";
	}
	
	@RequestMapping(value = "/add-todo", method=RequestMethod.GET)
	public String addNewTodo(ModelMap model) {
		String username = (String)model.get("name");
		Todo todo = new Todo(1,username,"",LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "add-todo";
	}
	
	@RequestMapping(value = "/add-todo", method=RequestMethod.POST)
	public String showNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()) {
			return "add-todo";
		}
		
		String username = (String)(model.get("name"));
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todos"; //this will redirect the user back to list-todos url
	}
	
	@RequestMapping("/delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updatetodo(@RequestParam int id, ModelMap model) {
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute("todo", todo);
		return "add-todo";
	}
	
	@RequestMapping(value="/update-todo", method = RequestMethod.POST)
	public String ShowUpdatedTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "add-todo";
		}
		String username = (String)model.get("name");
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
}
