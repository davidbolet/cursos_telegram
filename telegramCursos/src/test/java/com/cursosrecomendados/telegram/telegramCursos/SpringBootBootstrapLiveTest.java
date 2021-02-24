package com.cursosrecomendados.telegram.telegramCursos;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cursosrecomendados.telegram.telegramCursos.model.Book;

import io.restassured.RestAssured;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ResponseOptions;
import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;

import static org.junit.Assert.assertEquals;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true",classes= Application.class)
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
	 */
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

	private String createBookAsUri(Book book) {
		Response response = (Response) RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(book)
				.post(API_ROOT);
		return API_ROOT + "/" + ((ResponseBodyExtractionOptions) response).jsonPath().get("id");
	}

	@Test
	public void whenGetAllBooks_thenOK() {
		Response response = (Response) RestAssured.get(API_ROOT);

		assertEquals(HttpStatus.OK.value(),
				((ResponseOptions<io.restassured.response.Response>) response).getStatusCode());
	}
	
	@Test
	public void whenCreateNewBook_thenCreated() {
	    Book book = createRandomBook();
	    Response response = (Response) RestAssured.given()
	      .contentType(MediaType.APPLICATION_JSON_VALUE)
	      .body(book)
	      .post(API_ROOT);
	    
	    assertEquals(HttpStatus.CREATED.value(), ((ResponseOptions<io.restassured.response.Response>) response).getStatusCode());
	}
	
	@Test
	public void whenInvalidBook_thenError() {
	    Book book = createRandomBook();
	    book.setAuthor(null);
	    Response response = (Response) RestAssured.given()
	      .contentType(MediaType.APPLICATION_JSON_VALUE)
	      .body(book)
	      .post(API_ROOT);
	    
	    assertEquals(HttpStatus.BAD_REQUEST.value(), ((ResponseOptions<io.restassured.response.Response>) response).getStatusCode());
	}
	
	@Test
	public void whenUpdateCreatedBook_thenUpdated() {
	    Book book = createRandomBook();
	    String location = createBookAsUri(book);
	    book.setId(Long.parseLong(location.split("api/books/")[1]));
	    book.setAuthor("newAuthor");
	    Response response = (Response) RestAssured.given()
	      .contentType(MediaType.APPLICATION_JSON_VALUE)
	      .body(book)
	      .put(location);
	    
	    assertEquals(HttpStatus.OK.value(), ((ResponseOptions<io.restassured.response.Response>) response).getStatusCode());

	    response = (Response) RestAssured.get(location);
	    
	    assertEquals(HttpStatus.OK.value(), ((ResponseOptions<io.restassured.response.Response>) response).getStatusCode());
	    assertEquals("newAuthor", ((ResponseBodyExtractionOptions) response).jsonPath()
	      .get("author"));
	}
	
	@Test
	public void whenDeleteCreatedBook_thenOk() {
	    Book book = createRandomBook();
	    String location = createBookAsUri(book);
	    Response response = (Response) RestAssured.delete(location);
	    
	    assertEquals(HttpStatus.OK.value(), ((ResponseOptions<io.restassured.response.Response>) response).getStatusCode());

	    response = (Response) RestAssured.get(location);
	    assertEquals(HttpStatus.NOT_FOUND.value(), ((ResponseOptions<io.restassured.response.Response>) response).getStatusCode());
	}
}
