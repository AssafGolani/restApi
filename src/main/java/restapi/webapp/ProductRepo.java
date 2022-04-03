package restapi.webapp;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This class is a Data Access Layer class (DAL)
 * Basic CRUD functionality is implemented according to the specific database
 */
public interface ProductRepo extends JpaRepository<Product, Long> {

}
