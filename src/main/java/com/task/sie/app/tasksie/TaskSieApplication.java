package com.task.sie.app.tasksie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaskSieApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSieApplication.class, args);
	}

}
