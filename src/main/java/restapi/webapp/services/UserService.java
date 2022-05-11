package restapi.webapp.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import restapi.webapp.pojo.GitHubUser;

import java.util.concurrent.CompletableFuture;

@Service
/**
 * We use @Service to do 2 things:
 * 1. theoretical decoupling: between our Views (DAO/DTOS/HTML docs returned to the responses body), Controllers that
 * serve users at different URLs with specific parameters
 * and the business logic of our application (Model)
 * Model is usually represents BL operations and DB access
 * 2. In practical sense: this annotation makes Spring scan this component and use it as a candidate for auto-wiring
 *
 * This class will use a remote REST endpoint to get data from Github's api
 * We will use a type called RestTemplate to go to a specific URL and create a GitHubUser object from the RestTemplate.
 * This is done using a RestTemplateBuilder
 */
public class UserService {
    private RestTemplate template;

    public UserService(RestTemplateBuilder restTemplateBuilder) {
        this.template = restTemplateBuilder.build();
    }

    /**
     * This method should run asynchronously in order to get
     * information about a specific user - using @Async annotation and CompletableFuture
     * Our program needs to send and HTTP GET request to a remote REST endpoint
     * we ought to get a JSON representing the user.
     * @param userName
     * @return
     */
    @Async
    public CompletableFuture<GitHubUser> userDetails(String userName){
        String urlTemplate = String.format("https://api.github.com/users/%s", userName);
        GitHubUser aUser = this.template.getForObject(urlTemplate, GitHubUser.class);
        // return a completableFuture<GitHUbUser> when the computation is done
        return CompletableFuture.completedFuture(aUser);
    }

}
