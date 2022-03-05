package unieuroop.model.product;

public final class ProductImpl implements Product {
    private final int productCode;
    private String name;
    private float sellingPrice;
    private final float purchasePrice;
    private String description;
    private Category category;
    private final Supplier supplier;
    /**
     * Constructor of Products.
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
        this.productCode = productCode;
        this.name = name;
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
    public float getSellingPrice() {
        return this.sellingPrice;
    }

    @Override
    public float getPurchasePrice() {
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

}
