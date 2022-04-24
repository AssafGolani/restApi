package archive;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("There is no product corresponding to id = " + id);
    }
    /*
    This custom exception is useless without invocation from one or more controllers
    @ExceptionHandler - use the exception in a specific controller
    @ControllerAdvice - use the exception in more than one controller

     */
}
