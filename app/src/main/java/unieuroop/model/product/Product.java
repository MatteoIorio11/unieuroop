package unieuroop.model.product;

public interface Product {
    String getName();

    float getSellingPrice();

    float getPurchasePrice();

    String getDescription();

    Category getCategory();

    Supplier getSupplier();
}
