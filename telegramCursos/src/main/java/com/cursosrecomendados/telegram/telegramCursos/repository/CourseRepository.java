package com.cursosrecomendados.telegram.telegramCursos.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cursosrecomendados.telegram.telegramCursos.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long>{
	List <Course> findByTitle(String title);
}
