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

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	
	private TodoRepository todoRepository;

	// Spring recommend constructor based injection
	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	// list-todo
	@RequestMapping("list-todo")
	public String getTodoList(ModelMap model) {
		
		String name = getLoggedInUsername(model);

		List<Todo> findByUsername = todoRepository.findByusername(name);

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
		todo.setUsername(name);
		todoRepository.save(todo);
		

		return "redirect:list-todo";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {

		todoRepository.deleteById(id);

		return "redirect:list-todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {

		String name = getLoggedInUsername(model);

		Todo todo = todoRepository.findById(id).get();
		
		model.addAttribute("todo", todo);

		return "todo";
	}
	
	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		String name = getLoggedInUsername(model);
		todo.setUsername(name);
		todoRepository.save(todo);

		return "redirect:list-todo";
	}

	private String getLoggedInUsername(ModelMap model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}


}
