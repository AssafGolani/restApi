//package restapi.webapp;
//
//import org.springframework.hateoas.CollectionModel;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import javax.validation.Valid;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * URI vs. URL
// * URI - an identifier of a resource: file, geolocation, etc....
// * For example: /main.htm
// * URL - an identifier with access information: domain name with protocol
// * for example: http://www.wikipedia.org/main.htm
// */
//@RestController
//public class ProductController {
//    // each method in the controller will be injected to the response's body and not a view template
//    private final ProductRepo productRepo;
//
//    ProductController(ProductRepo productRepo) {
//        this.productRepo = productRepo;
//    }
//
//    /*
//     retrieve all products
//     relied on Jackson component for deserialization (converts Java objects to JSON)
//     1. explicitly returned an object instead of its representation
//     2. NO links to move between representations in our system (RPC)
//    */
////    @GetMapping("/products")
////    List<Product> getAllProducts() {
////        return productRepo.findAll();
////    }
//
//    @GetMapping("/products")
//    CollectionModel<EntityModel<Product>> getAllProduct(){
//        List<Product> productList = productRepo.findAll();
//        /*
//                move to java 8 stream to perform some actions and transformations
//        */
//        List<EntityModel<Product>> entityList = productList.stream().map(product->EntityModel.of(product,
//                        linkTo(methodOn(ProductController.class).getSingleProduct(product.getId())).withSelfRel(),
//                        linkTo(methodOn(ProductController.class).getAllProduct()).withRel("back to all products")))
//                .collect(Collectors.toList());
//        return CollectionModel.of(entityList,linkTo(methodOn(ProductController.class)
//                .getAllProduct()).withSelfRel());
//    }
//
//
//
//
//
//    @PostMapping("/products")
//    Product createProduct(@RequestBody Product newProduct) { // Relied on Jackson component for serialization
//        return productRepo.save(newProduct); // Relied on Jackson component for deserialization
//    }
//
//
////    @GetMapping("/products/{id}")
////    Product getSingleProduct(@PathVariable Long id) {
////        return productRepo.findById(id)
////                .orElseThrow(() -> new ProductNotFoundException(id));
////    }
//
//    /**
//     * This method extracts id as path variable and replaces the old product if exits
//     *
//     * @param id
//     * @param newProduct
//     * @return updated product or the newly created product
//     */
//    @PutMapping("/products/{id}")
//    Product updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {
//        return productRepo.findById(id)
//                //convert row in table to Java object
//                .map(productToUpdate -> {
//                    productToUpdate.setProductName(newProduct.getProductName());
//                    productToUpdate.setPrice(newProduct.getPrice());
//                    productToUpdate.setCategory(newProduct.getCategory());
//                    // save Java object to row in an SQL table
//                    return productRepo.save(productToUpdate);
//                    //if product doesn't exist either throw an exception or create a new product
//                }).orElseGet(() -> {
//                    return productRepo.save(newProduct);
//                });
//    }
//
//
//    @DeleteMapping("/products/{id}")
//    void deleteProduct(@PathVariable Long id) {
//        productRepo.deleteById(id);
//    }
//
//
//    /**
//     * Entity model is a Spring container for objects: instead of returning a concrete object and rely on Jackson to
//     * transform it to a simple JSON, manually wrap the concrete object and add special links to it that the user of
//     * the API can use and query.
//     * @param id
//     * @return
//     */
//    @GetMapping("/products/{id}")
//    EntityModel<Product> getSingleProduct(@PathVariable Long id){
//        Product product = productRepo.findById(id).orElseThrow(()->
//                new ProductNotFoundException(id));
//        return EntityModel.of(product,
//                // HAL - add attributes for object's links
//                linkTo(methodOn(ProductController.class).getSingleProduct(id)).withSelfRel(),
//                linkTo(methodOn(ProductController.class).getAllProduct()).withRel("back to all products"));
//    }
//
//}
