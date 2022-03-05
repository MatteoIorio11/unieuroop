package unieuroop.model.product;


import java.util.Optional;

import unieuroop.model.supplier.Supplier;

public final class ProductImpl implements Product {
    private final int productCode;
    private String name;
    private float sellingPrice;
    private final float purchasePrice;
    private Optional<Integer> discountPercentage;
    private String description;
    private Category category;
    private final Supplier supplier;
    /**
     * Constructor of product with discount.
     * @param productCode
     * @param name
     * @param sellingPrice
     * @param purchasePrice
     * @param discount
     * @param description
     * @param category
     * @param supplier
     */
    public ProductImpl(final int productCode, final String name, final float sellingPrice, 
            final float purchasePrice, final Optional<Integer> discount, final String description, final Category category, 
            final Supplier supplier) {
        this.productCode = productCode;
        this.name = name;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.discountPercentage = discount;
        this.description = description;
        this.category = category;
        this.supplier = supplier;
    }
    /**
     * Constructor of products without discount.
     * @param productCode
     * @param name
     * @param sellingPrice
     * @param purchasePrice
     * @param description
     * @param category
     * @param supplier
     */
    public ProductImpl(final int productCode, final String name, final float sellingPrice, 
            final float purchasePrice, final String description, final Category category, 
            final Supplier supplier) {
        this(productCode, name, sellingPrice, purchasePrice, Optional.empty(), description, category, supplier);
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
    public float getSellingPrice() {
        return this.sellingPrice;
    }
    @Override
    public float getPurchasePrice() {
        return purchasePrice;
    }
    @Override
    public Optional<Integer> getDiscountPercentage() {
            return this.discountPercentage;
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
    public float getDiscountedSallingPrice() {
        return this.sellingPrice - this.sellingPrice * this.discountPercentage.orElse(0) / 100;
    }
    @Override
    public void setName(final String name) {
        this.name = name;
    }
    @Override
    public void setSellingPrice(final float price) {
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
    public void setDiscountPercentage(final int discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException();
        } else {
            this.discountPercentage = Optional.of(discount);
        }
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
}
