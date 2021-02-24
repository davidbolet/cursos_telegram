package com.cursosrecomendados.telegram.telegramCursos.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cursosrecomendados.telegram.telegramCursos.model.Course;
import com.cursosrecomendados.telegram.telegramCursos.repository.CourseRepository;
import com.cursosrecomendados.telegram.telegramCursos.service.SenderTelegram;

@RestController("/courses")
public class CourseController {
	
	@Autowired
	private CourseRepository courserepository;
	
	@Autowired
	private SenderTelegram sender;
	
	@ResponseBody
    @GetMapping("/title/")
    public Iterable findAll() {
        return courserepository.findAll();
    }
    
    @ResponseBody
    @GetMapping("/title/{courseTitle}")
    public List findByTitle(@PathVariable String courseTitle) {
        return courserepository.findByTitle(courseTitle);
    }

	
	@ResponseBody
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	public Course create(Course course) {
		return courserepository.save(course);
	}
	
	@ResponseBody
    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
	public void send(Course course) {
		try {
			sender.sendMessage(course);
			this.create(course);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
