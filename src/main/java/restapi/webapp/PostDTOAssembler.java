package restapi.webapp;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

@Component
public class PostDTOAssembler implements SimpleRepresentationModelAssembler<PostDTO> {

    @Override
    public void addLinks(EntityModel<PostDTO> resource) {
        resource.add(linkTo(methodOn(PostController.class)
                .postInfo(resource.getContent().getPost().getId()))
                .withSelfRel());
        // TODO: add link to original data


    }

    @Override
    public void addLinks(CollectionModel<EntityModel<PostDTO>> resources) {

    }
}
