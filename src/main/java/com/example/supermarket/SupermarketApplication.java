package com.example.supermarket;

import java.math.BigDecimal;

import com.example.supermarket.model.Product;
import com.example.supermarket.repository.ProductRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SupermarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupermarketApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository) {
		return args -> {
			System.out.println("================== AQUI ==================");

			Product sugarProduct = new Product();
			sugarProduct.setName("Açúcar");
			sugarProduct.setPrice(new BigDecimal("3.99"));
			sugarProduct.setStockQuantity(50);
			productRepository.save(sugarProduct);

			Product beansProduct = new Product("Beans", new BigDecimal("11.79"), 70);
			productRepository.save(beansProduct);

			System.out.println(productRepository.findAll());

			System.out.println("================== AQUI ==================");
		};
	}

}
