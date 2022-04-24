package archive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeedDB {
    private static final Logger logger = LoggerFactory.getLogger(SeedDB.class);

    /*
        CommandLineRunner is an interface that indicates the beans are contained within
        a SpringApplication
     */

    @Bean
    CommandLineRunner seedDatabase(ProductRepo myProducts){
        return args -> {
            logger.info("logging " +
                    myProducts.save
                    (new Product("AirPods v3 2021", "Headphones", 250.0)));
            logger.info("logging " +
                    myProducts.save
                            (new Product("Iphone 13", "Cellphones", 1000.0)));
            logger.info("logging " +
                    myProducts.save
                            (new Product("MacBook Pro 13 2021", "Laptops", 1500.0)));
        };
    }
}
