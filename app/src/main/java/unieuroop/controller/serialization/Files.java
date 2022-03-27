package unieuroop.controller.serialization;

public enum Files {
    /**
     * @param fileName Products.xml
     */
    PRODUCTS("Products.xml"),
    /**
     * @param fileName Staffs.xml
     */
    STAFFS("Staffs.xml"),
    /**
     * @param fileName Sales.xml
     */
    SALES("Sales.xml"),
    /**
     * @param fileName Stock.xml
     */
    STOCK("Stock.xml"),
    /**
     * @param fileName Suppliers.xml
     */
    SUPPLIERS("Suppliers.xml"),
    /**
     * @param fileName Shop.xml
     */
    SHOP("Shop.xml"),
    /**
     * @param fileName Departments.xml
     */
    DEPARTMENTS("Departments.xml"),
    /**
     * @param fileName Clients.xml
     */
    CLIENTS("Clients.xml"),
    /**
     * @param fileName Bills.xml
     */
    BILLS("Bills.xml"),
    /**
     * @param fileName ShopName.xml
     */
    SHOPNAME("ShopName.xml");

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
