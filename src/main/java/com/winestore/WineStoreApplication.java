package com.winestore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.winestore.controller.WineStoreController;

@SpringBootApplication
public class WineStoreApplication implements CommandLineRunner {

	@Autowired
	private WineStoreController controller;

	public static void main(String[] args) {
		SpringApplication.run(WineStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		controller.consume();
	}
}