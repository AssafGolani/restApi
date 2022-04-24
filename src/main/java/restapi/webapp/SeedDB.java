package restapi.webapp;

import archive.Product;
import archive.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SeedDB {
    private static final Logger logger = LoggerFactory.getLogger(SeedDB.class);

    /*
        CommandLineRunner is an interface that indicates the beans are contained within
        a SpringApplication
     */

    @Bean
    CommandLineRunner seedDatabase(ProfileRepository profileRepo, PostRepository postRepo){
        return args -> {
            UserProfile profile1 =  profileRepo.save(new UserProfile("First Profile"));
            UserProfile profile2 =  profileRepo.save(new UserProfile("First Profile"));

            Post post1 = postRepo.save(new Post("Title 1", "This is our first post", profile1));
            Post post2 = postRepo.save(new Post("Title 2", "This is our second post", profile1));
            profile1.setPosts(Arrays.asList(post1, post2));
            profileRepo.save(profile1);

        };
    }
}
