package com.paul1204.inventoryservice;

import com.paul1204.inventoryservice.model.Inventory;
import com.paul1204.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory i = new Inventory();
			i.setSkuCode("iphone_13");
			i.setQuantity(100);

			Inventory i1 = new Inventory();
			i1.setSkuCode("iphone_12");
			i1.setQuantity(0);

			Inventory i2 = new Inventory();
			i2.setSkuCode("iphone_11");
			i2.setQuantity(100000);

			Inventory i3 = new Inventory();
			i3.setSkuCode("iphone_x");
			i3.setQuantity(0);
			inventoryRepository.save(i);
			inventoryRepository.save(i1);
			inventoryRepository.save(i2);
			inventoryRepository.save(i3);

			//stopped at 2hr. Placing Order doesnt fully cooperate
		};
	}
}
