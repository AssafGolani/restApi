package restapi.webapp;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
// @RestController specifies that methods will inject return values to the body's response instead of templates
// that the user needs to render
public class ProductDAOController {
    private final ProductRepo productRepo;
    private final ProductEntityAssembler productAssembler;

    public ProductDAOController(ProductRepo productRepo, ProductEntityAssembler productAssembler) {
        this.productRepo = productRepo;
        this.productAssembler = productAssembler;
    }

    @GetMapping("/products/{id}")
    EntityModel<Product> getSingleProduct(@PathVariable Long id) {
        Product product = productRepo.findById(id).orElseThrow(() ->
                new ProductNotFoundException(id));
        return productAssembler.toModel(product);
    }


    @GetMapping("/products")
    CollectionModel<EntityModel<Product>> getAllProduct() {
        List<EntityModel<Product>> products = productRepo.findAll()
                .stream()
                .map(productAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductDAOController.class)
                .getAllProduct()).withSelfRel());
    }


    @PostMapping("/products")
    ResponseEntity<EntityModel<Product>> createProduct(@RequestBody Product newProduct) {
        Product savedProduct = productRepo.save(newProduct);
        // status code:201
        return ResponseEntity.created(linkTo(methodOn(ProductDAOController.class)
                        // implicitly gets Self HREF of the new product
                        .getSingleProduct(savedProduct.getId()))
                        .toUri())
                .body(productAssembler.toModel(savedProduct));
    }

    @PostMapping("/newproduct")
    ResponseEntity<?> newProduct(@RequestBody Product newProduct) {
        try {
            // status code:201
            Product savedProduct = productRepo.save(newProduct);
            EntityModel<Product> entityProduct = productAssembler.toModel(savedProduct);

            return ResponseEntity.created(new URI(entityProduct.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                    .body(entityProduct);
        } catch (URISyntaxException e) {
            // status code:400
            return ResponseEntity.badRequest().body("Cannot create the product corresponding to " + newProduct);
        }
    }

    /**
     * a request parameter is something that we pass after the URL using: "?parameterName=parameterValue"
     * if multiple request params are declared, seperate them using &
     * ?parameterName1=parameterValue1&?parameterName2=parameterValue2
     * @param price is a query string
     * @return
     */
    @GetMapping("/products/sale")
    CollectionModel<EntityModel<Product>> getProductOnSale(@RequestParam Double price){
        List<EntityModel<Product>> products = productRepo.findAll()
                .stream().filter(product -> product.getPrice() < price)
                .sorted().map(productAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductDAOController.class)
                .getAllProduct()).withSelfRel());
    }

    @GetMapping("/products/advancedsale")
    CollectionModel<EntityModel<Product>> getProductOnSaleAdvanced(@RequestParam (defaultValue = "1500") Double price){
        List<EntityModel<Product>> products = productRepo.findAll()
                .stream().filter(product -> product.getPrice() < price)
                .sorted().map(productAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductDAOController.class)
                .getAllProduct()).withSelfRel());
    }

    @GetMapping("/products/optionals")
    @ResponseBody
    String getOptionalMessage(@RequestParam Optional<String> someParam){
        return "someParameter value = " + someParam.orElseGet(()->"you haven't entered an argument");
    }
}
