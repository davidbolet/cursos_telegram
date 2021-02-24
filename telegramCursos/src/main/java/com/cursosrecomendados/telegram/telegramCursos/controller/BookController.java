package com.cursosrecomendados.telegram.telegramCursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cursosrecomendados.telegram.telegramCursos.exception.BookIdMismatchException;
import com.cursosrecomendados.telegram.telegramCursos.exception.BookNotFoundException;
import com.cursosrecomendados.telegram.telegramCursos.model.Book;
import com.cursosrecomendados.telegram.telegramCursos.repository.BookRepository;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @ResponseBody
    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }
    
    @ResponseBody
    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id)
          .orElseThrow(BookNotFoundException::new);
    }

    @ResponseBody
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }
    
    @ResponseBody
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
          .orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }
    
    @ResponseBody
    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
          throw new BookIdMismatchException();
        }
        bookRepository.findById(id)
          .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }
}
