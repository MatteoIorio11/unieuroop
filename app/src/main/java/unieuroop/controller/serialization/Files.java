package unieuroop.controller.serialization;

public enum Files {
    /**
     * @param fileName Products.json
     */
    PRODUCTS("Products.json"),
    /**
     * @param fileName Staffs.json
     */
    STAFFS("Staffs.json"),
    /**
     * @param fileName Sales.json
     */
    SALES("Sales.json"),
    /**
     * @param fileName Stock.json
     */
    STOCK("Stock.json"),
    /**
     * @param fileName Suppliers.json
     */
    SUPPLIERS("Suppliers.json"),
    /**
     * @param fileName Shop.json
     */
    SHOP("Shop.json"),
    /**
     * @param fileName Departments.json
     */
    DEPARTMENTS("Departments.json");

    private final String fileName;
    private static final String SEPARATOR = System.getProperty("file.separator");

    Files(final String fileName) {
        this.fileName = fileName;
    }
    /**
     * 
     * @return the path of the file 
     */
    public String getPath() {
        return Files.SEPARATOR + this.fileName;
    }
}
