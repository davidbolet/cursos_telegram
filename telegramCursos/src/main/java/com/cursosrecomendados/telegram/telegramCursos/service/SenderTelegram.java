package com.cursosrecomendados.telegram.telegramCursos.service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Service;

import com.cursosrecomendados.telegram.telegramCursos.model.Course;

//token = 1619304273:AAEhHGCSAjhdA6xEw6-EI8nRtYKHd1IJyc4
@Service
public class SenderTelegram {
	private static final String CHAT_ID = "1337682137";
    private static final String TOKEN = "1619304273:AAEhHGCSAjhdA6xEw6-EI8nRtYKHd1IJyc4";
    
    public void sendMessage(Course course) throws IOException, InterruptedException {
    	String message = "Title: "+ course.getTitle() + " \n Description: " + course.getDescription() + "\n Date: " + course.getDate().toString();
    	 HttpClient client = HttpClient.newBuilder()
                 .connectTimeout(Duration.ofSeconds(5))
                 .version(HttpClient.Version.HTTP_2)
                 .build();
  
         UriBuilder builder = UriBuilder
                 .fromUri("https://api.telegram.org")
                 .path("/{token}/sendMessage")
                 .queryParam("chat_id", CHAT_ID)
                 .queryParam("text", message);
  
         HttpRequest request = HttpRequest.newBuilder()
                 .GET()
                 .uri(builder.build("bot" + TOKEN))
                 .timeout(Duration.ofSeconds(5))
                 .build();
  
         HttpResponse<String> response = client
           .send(request, HttpResponse.BodyHandlers.ofString());
         
         System.out.println(response.body());
    }
}
