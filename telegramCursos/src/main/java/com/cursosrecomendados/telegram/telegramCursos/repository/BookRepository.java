package com.cursosrecomendados.telegram.telegramCursos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cursosrecomendados.telegram.telegramCursos.model.Book;


@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
	List<Book> findByTitle(String title);
}
