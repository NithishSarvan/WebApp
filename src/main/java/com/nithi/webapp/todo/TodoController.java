package com.nithi.webapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private TodoService todoService;

	// Spring recommend constructor based injection
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	// list-todo
	@RequestMapping("list-todo")
	public String getTodoList(ModelMap model) {
		
		String name = getLoggedInUsername(model);
		logger.debug(" getTodo lsit by name {} " ,name );

		List<Todo> findByUsername = todoService.findByUsername(name);

		model.put("todos", findByUsername);

		return "listTodos";
	}

	
	// add-todo -> GET
	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showAddTodo(ModelMap model) {

		String name = getLoggedInUsername(model);

		Todo todo = new Todo(0, name, "", LocalDate.now().plusYears(1), false);

		model.put("todo", todo);

		return "todo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		String name = getLoggedInUsername(model);
		todoService.addTodo(name, todo.getCourse(), todo.getDate(), false);

		return "redirect:list-todo";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {

		todoService.deleteById(id);

		return "redirect:list-todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {

		String name = getLoggedInUsername(model);

		Todo todo = todoService.findById(id);
		
		model.addAttribute("todo", todo);

		return "todo";
	}
	
	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		todoService.updateTodo(todo);

		return "redirect:list-todo";
	}

	private String getLoggedInUsername(ModelMap model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}


}
