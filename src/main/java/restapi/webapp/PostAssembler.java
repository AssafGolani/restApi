package restapi.webapp;

import org.springframework.stereotype.Component;

/**
 * This class uses Spring HATEOAS to convert Post objects to EntityModel<Post>
 * It requires only the RESTController that uses it
 */

@Component
public class PostAssembler extends SimpleIdentifiableRepresentationModelAssembler<Post> {
    public PostAssembler() {
        super(PostController.class);
    }

}
