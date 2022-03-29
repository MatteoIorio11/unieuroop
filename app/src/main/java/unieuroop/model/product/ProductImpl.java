package unieuroop.model.product;
import java.io.Serializable;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unieuroop.model.supplier.Supplier;

public final class ProductImpl implements Product, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8521815205261847580L;
    private final int productCode;
    private String name;
    private String brand;
    private Double sellingPrice;
    private final Double purchasePrice;
//    private Optional<Integer> discountPercentage;
    private String description;
    private Category category;
    private final Supplier supplier;
    /**
     * Constructor of product with discount.
     * @param productCode
     * @param name
     * @param brand
     * @param sellingPrice
     * @param purchasePrice
     * @param discount
     * @param description
     * @param category
     * @param supplier
     */
    @JsonCreator
    public ProductImpl(
            @JsonProperty("productCode")final int productCode, 
            @JsonProperty("name")final String name, 
            @JsonProperty("brand")final String brand, 
            @JsonProperty("sellingPrice")final Double sellingPrice, 
            @JsonProperty("purchasePrice")final Double purchasePrice, 
            @JsonProperty("description")final String description, 
            @JsonProperty("category")final Category category, 
            @JsonProperty("supplier")final Supplier supplier) {
        this.productCode = productCode;
        this.name = name;
        this.brand = brand;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.category = category;
        this.supplier = supplier;
    }
    @Override
    public int getProductCode() {
        return this.productCode;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public String getBrand() {
        return this.brand;
    }
    @Override
    public Double getSellingPrice() {
        return this.sellingPrice;
    }
    @Override
    public Double getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
    @Override
    public Category getCategory() {
        return this.category;
    }
    @Override
    public Supplier getSupplier() {
        return this.supplier;
    }
    @Override
    public void setName(final String name) {
        this.name = name;
    }
    @Override
    public void setBrand(final String brand) {
        this.brand = brand;
    }
    @Override
    public void setSellingPrice(final Double price) {
        this.sellingPrice = price;
    }
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }
    @Override
    public void setCategory(final Category category) {
        this.category = category;
    }
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + productCode;
        return result;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductImpl other = (ProductImpl) obj;
        return productCode == other.productCode;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
