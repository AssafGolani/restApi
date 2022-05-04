package restapi.webapp;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {


    private final UserProfileAssembler userProfileAssembler;
    private final ProfileRepository profileRepository;

    public UserProfileController(UserProfileAssembler userProfileAssembler, ProfileRepository profileRepository) {
        this.userProfileAssembler = userProfileAssembler;
        this.profileRepository = profileRepository;
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<EntityModel<UserProfile>> singleProfile(@PathVariable Long id){
        return profileRepository.findById(id)
                .map(userProfileAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/profiles")
    ResponseEntity<CollectionModel<EntityModel<UserProfile>>> allProfile(){
        return ResponseEntity.ok(userProfileAssembler.toCollectionModel(profileRepository.findAll()));
    }
}
