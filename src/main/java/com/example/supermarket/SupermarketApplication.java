package com.example.supermarket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.supermarket.model.Product;
import com.example.supermarket.model.Sale;
import com.example.supermarket.repository.ProductRepository;
import com.example.supermarket.repository.SaleRepository;

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
	CommandLineRunner commandLineRunner(ProductRepository productRepository, SaleRepository saleRepository) {
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

			Sale sale = new Sale();
			sale.setProduct(sugarProduct);
			sale.setQuantity(10);
			sale.setSaleDateTime(LocalDateTime.now());
			saleRepository.save(sale);

			System.out.println(saleRepository.findAll());

			System.out.println("================== AQUI ==================");
		};
	}

}
