package com.nithi.webapp.todo;

import java.time.LocalDate;

import org.springframework.boot.convert.DataSizeUnit;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {

	public Todo() {

	}

	public Todo(int id, String username, String course, LocalDate date, boolean done) {
		super();
		this.id = id;
		this.username = username;
		this.course = course;
		this.targetDate = date;
		this.done = done;
	}

	@Id // -> primary KEY
	@GeneratedValue
	private int id;

	// @Column(name = "name")
	private String username;

	@Size(min = 10, message = "Enter atleast 10 character")
	private String course;
	private LocalDate targetDate;
	private boolean done;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public LocalDate getDate() {
		return targetDate;
	}

	public void setDate(LocalDate date) {
		this.targetDate = date;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", course=" + course + ", date=" + targetDate + ", done="
				+ done + "]";
	}

}
