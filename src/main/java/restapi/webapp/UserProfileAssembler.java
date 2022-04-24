package restapi.webapp;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserProfileAssembler extends SimpleIdentifiableRepresentationModelAssembler<UserProfile> {
        public UserProfileAssembler(){
            super(UserProfileController.class);
        }

    @Override
    public void addLinks(EntityModel<UserProfile> resource) {
        super.addLinks(resource);
        /*
        Optional.ofNullable - this method is used to get an instance with the specified type.
        for example: Optional.ofNullable(20)
         */
        Optional<Long> tempId = Optional.ofNullable(resource.getContent().getId());
        tempId.ifPresent(id->{
            resource.add(linkTo(methodOn(PostController.class).profilePosts(id)).withRel(""));
        });
    }
}


