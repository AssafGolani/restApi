package restapi.webapp;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    private PostRepository postRepository;
    private PostAssembler postAssembler;

    public PostController(PostRepository postRepository, PostAssembler postAssembler){
        this.postRepository = postRepository;
        this.postAssembler = postAssembler;
    }

    @GetMapping("/profiles/{id}/posts")
    public ResponseEntity<CollectionModel<EntityModel<Post>>> profilePosts(@PathVariable Long id){
        return ResponseEntity.ok(postAssembler.toCollectionModel(postRepository.findByUserProfileId(id)));
    }
}
