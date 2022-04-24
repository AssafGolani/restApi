package restapi.webapp;

import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<UserProfile, Long> {
}
