package unieuroop.model.product;

public interface Product {
    /**
     * 
     * @return : the unique code of the product
     */
    int getProductCode();
    /**
     * 
     * @return : the name of the product
     */
    String getName();
    /**
     * 
     * @return : the brand of the product
     */
    String getBrand();
    /**
     * 
     * @return : the price the shop decided as selling price
     */
    Double getSellingPrice();
    /**
     * 
     * @return : the price payed by the shop to get this product
     */
    Double getPurchasePrice();

    /**
     * 
     * @return : a general description of the product
     */
    String getDescription();
    /**
     * 
     * @return : the category of the product
     */
    Category getCategory();

    /**
     * change the name of the product.
     * @param name
     */
    void setName(String name);
    /**
     * change the brand of the product.
     * @param brand
     */
    void setBrand(String brand);
    /**
     * change the selling price of the product.
     * @param price
     */
    void setSellingPrice(Double price);

    /**
     * change the description of the product.
     * @param description
     */
    void setDescription(String description);
    /**
     * change the category of the product.
     * @param category
     */
    void setCategory(Category category);
}
