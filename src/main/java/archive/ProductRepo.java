package archive;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This class is a Data Access Layer class (DAL)
 * Basic CRUD functionality is implemented according to the specific database
 */
public interface ProductRepo extends JpaRepository<Product, Long> {
    /*
    implemented automatically by JpaRepository
    IoC - Inversion of Control - declare what you want to do,
    the framework will be the one to implement it
     */
    List<Product> getProductsByProductName(String name);
}
