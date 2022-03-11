package unieuroop.controller.serialization;

public enum Files {
    /**
     * @param fileName Products.json
     */
    PRODUCTS("Products.json"),
    /**
     * @param fileName Products.json
     */
    STAFFS("Staffs.json"),
    /**
     * @param fileName Products.json
     */
    SALES("Sales.json"),
    /**
     * @param fileName Products.json
     */
    STOCK("Stock.json"),
    /**
     * @param fileName Products.json
     */
    SUPPLIERS("Suppliers.json"),
    /**
     * @param fileName Products.json
     */
    SHOP("Shop.json"),
    /**
     * @param fileName Products.json
     */
    DEPARTMENTS("Departments.json");

    private final String fileName;
    private static final String SEPARATOR = System.getProperty("file.separator");
    Files(final String fileName) {
        this.fileName = fileName;
    }

    public String getPath(){
        return Files.SEPARATOR + this.fileName;
    }
}
