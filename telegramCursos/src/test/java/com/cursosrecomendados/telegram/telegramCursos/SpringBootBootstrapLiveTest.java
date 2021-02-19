package com.cursosrecomendados.telegram.telegramCursos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootBootstrapLiveTest {

	private static final String API_ROOT = "http://localhost:8081/api/books";

	private Book createRandomBook() {
		Book book = new Book();
		book.setTitle(randomAlphabetic(10));
		book.setAuthor(randomAlphabetic(15));
		return book;
	}
	/*
	 * He creat el metode random peque no me'l detectava 
	 * */
	private String randomAlphabetic(int name) {
		char n;
		Random rnd = new Random();
		String cadena = "";
		for (int i = 0; i < name; i++) {
			n = (char) (rnd.nextDouble() * 26.0 + 65.0);
			cadena += n;
		}
		return cadena;
	}

	/*
	 * Em dona error en el response i el mediaType no me'l importa be
	  	private String createBookAsUri(Book book) {
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(book).post(API_ROOT);
		return API_ROOT + "/" + response.jsonPath().get("id");
	}
	
	@Test
	public void whenGetAllBooks_thenOK() {
	    Response response = RestAssured.get(API_ROOT);
	 
	    assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void whenGetBooksByTitle_thenOK() {
	    Book book = createRandomBook();
	    createBookAsUri(book);
	    Response response = RestAssured.get(
	      API_ROOT + "/title/" + book.getTitle());
	    
	    assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	    assertTrue(response.as(List.class)
	      .size() > 0);
	}
	@Test
	public void whenGetCreatedBookById_thenOK() {
	    Book book = createRandomBook();
	    String location = createBookAsUri(book);
	    Response response = RestAssured.get(location);
	    
	    assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	    assertEquals(book.getTitle(), response.jsonPath()
	      .get("title"));
	}

	@Test
	public void whenGetNotExistBookById_thenNotFound() {
	    Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));
	    
	    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}
	*/
}
