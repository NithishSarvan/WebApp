package com.nithi.webapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();

	private static int totalCount = 0;

	static {

		todos.add(new Todo(++totalCount, "Nithish", "Spring BOot", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++totalCount, "Nithish", "Core Java", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++totalCount, "Nithish", "SQL", LocalDate.now().plusYears(3), false));
	}

	public List<Todo> findByUsername(String userName) {
		System.out.println("### todo service user name = "+ userName  );
		
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(userName);
		return todos.stream().filter(predicate).toList();
	}

	public void addTodo(String name, String course, LocalDate targetDate, boolean isDone) {

		Todo todo = new Todo(++totalCount, name, course, targetDate, isDone);

		todos.add(todo);

	}

	public void deleteById(int id) {

		Predicate<? super Todo> predicate = todo -> todo.getId() == id;

		todos.removeIf(predicate);

	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;

		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo( Todo todo) {
		
		deleteById(todo.getId());
		todos.add(todo);

	}
}
