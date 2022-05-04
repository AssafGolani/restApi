package restapi.webapp;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.StreamSupport;

@RestController
public class PostController {
    private PostRepository postRepository;
    private PostAssembler postAssembler;
    private PostDTOAssembler dtoAssembler;

    public PostController(PostRepository postRepository, PostAssembler postAssembler, PostDTOAssembler dtoAssembler){
        this.postRepository = postRepository;
        this.postAssembler = postAssembler;
        this.dtoAssembler = dtoAssembler;
    }

    @GetMapping("/profiles/{id}/posts")
    public ResponseEntity<CollectionModel<EntityModel<Post>>> profilePosts(@PathVariable Long id){
        return ResponseEntity.ok(postAssembler.toCollectionModel(postRepository.findByUserProfileId(id)));
    }

    @GetMapping("/posts")
    public ResponseEntity<CollectionModel<EntityModel<Post>>> allPosts(){
        return ResponseEntity.ok(postAssembler.toCollectionModel(postRepository.findAll()));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<EntityModel<Post>> singlePost(@PathVariable Long id){
        return postRepository.findById(id)
                .map(postAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/posts/{id}/info")
    public ResponseEntity<EntityModel<PostDTO>> postInfo(@PathVariable Long id){
        return postRepository.findById(id)
                .map(PostDTO::new)
                .map(dtoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/posts/info")
    public ResponseEntity<CollectionModel<EntityModel<PostDTO>>> allPostsInfo(){
//        dtoAssembler.toCollectionModel(StreamSupport.class)
    }

}
