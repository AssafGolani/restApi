package restapi.webapp;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    Post findByTitle(String title);
    List<Post> findByUserProfileId(Long id);


}
