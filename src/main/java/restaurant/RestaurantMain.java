package restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class RestaurantMain {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantMain.class, args);
	}

}
