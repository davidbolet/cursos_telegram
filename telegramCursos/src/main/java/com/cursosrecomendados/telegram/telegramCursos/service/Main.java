package com.cursosrecomendados.telegram.telegramCursos.service;

import java.io.IOException;
import java.time.LocalDate;

import com.cursosrecomendados.telegram.telegramCursos.model.Course;

public class Main {

	public static void main(String[] args) {
		
		SenderTelegram send = new SenderTelegram();
		Course message = new Course();
		
		message.setTitle("titol");
		message.setDescription("prova per enviar imatge");
		message.setImage("~/pirctures/1.png");
		message.setLink("http://localhost:8080/courses");
		message.setDate(LocalDate.now());
		
		try {
			send.sendMessage(message);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
