package unieuroop.controller.serialization;

public enum Files {
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

    private static final String DATA_DIRECTORY = "/.unieurOOP";
    private final String fileName;
    private final String homeDirectory = System.getProperty("user.home");
    private final String dir = homeDirectory + "/" + DATA_DIRECTORY;

    Files(final String fileName) {
        this.fileName = fileName;
    }
    /**
     * 
     * @return the path of the file 
     */
    public String getPath() {
        return dir + "/" + this.fileName;
    }
    public String getName() {
        return this.fileName;
    }
}
