package archive;

public enum ProductCategory {
    PROD1(ProductCategory.PROD1_BEAN),
    PROD2(ProductCategory.PROD2_BEAN);

    private static String PROD1_BEAN = "PROD_1_HANDLER";
    private static String PROD2_BEAN = "PROD_2_HANDLER";

    private String bean;

    ProductCategory(String beanName){
        this.bean = beanName;
    }

    public String getBean(){
        return bean;
    }
}
