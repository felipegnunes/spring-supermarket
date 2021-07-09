package com.example.supermarket;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.supermarket.model.Product;
import com.example.supermarket.model.Sale;
import com.example.supermarket.model.UserRole;
import com.example.supermarket.repository.UserRepository;
import com.example.supermarket.service.AuthService;
import com.example.supermarket.service.ProductService;
import com.example.supermarket.service.SaleService;

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
	CommandLineRunner commandLineRunner(ProductService productService, SaleService saleService, AuthService authService,
			UserRepository userRepository) {
		return args -> {
			System.out.println("================== CommandLineRunner ==================");

			Set<UserRole> authorities = new HashSet<>();
			authorities.add(new UserRole(UserRole.ADMIN));
			authService.signup("mainadmin", "admin@supermarket.com", "adminpsw", authorities);
			System.out.println(userRepository.findAll());

			Product sugarProduct = new Product();
			sugarProduct.setName("Açúcar");
			sugarProduct.setPrice(new BigDecimal("3.99"));
			sugarProduct.setStockQuantity(50);
			productService.save(sugarProduct);

			Product beansProduct = new Product("Beans", new BigDecimal("11.79"), 70);
			productService.save(beansProduct);

			System.out.println(productService.findAll());

			Sale sugarSale = new Sale(sugarProduct, Integer.valueOf(10), LocalDateTime.now());
			saleService.save(sugarSale);

			Sale beansSale = new Sale(beansProduct, Integer.valueOf(2), LocalDateTime.now());
			saleService.save(beansSale);

			System.out.println(saleService.findAll());
			System.out.println(productService.findAll());

			System.out.println("================== CommandLineRunner ==================");
		};
	}

}
