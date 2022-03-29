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
    DEPARTMENTS("Departments.json"),
    /**
     * @param fileName Clients.json
     */
    CLIENTS("Clients.json"),
    /**
     * @param fileName Bills.json
     */
    BILLS("Bills.json"),
    /**
     * @param fileName ShopName.json
     */
    SHOPNAME("ShopName.json");

    private final String fileName;
    private final String separator = System.getProperty("file.separator");
    private final String dir = "src" + this.separator + "main" + this.separator + "resources" + this.separator + "files";

    Files(final String fileName) {
        this.fileName = fileName;
    }

    public String getSeparator() {
        return this.separator;
    }
    /**
     * 
     * @return the path of the file 
     */
    public String getPath() {
        return dir + this.separator + this.fileName;
    }
}
