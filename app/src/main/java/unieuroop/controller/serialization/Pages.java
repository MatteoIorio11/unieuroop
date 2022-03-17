package unieuroop.controller.serialization;

public enum Pages {
    DASHBOARD("Dashboard.fxml"),
    STOCK("Stock.fxml"),
    SALES("Sales.fxml"),
    DEPARTMENTS("DepartmentsView.fxml"),
    ANALYTICS("testChart.fxml");
    
    private final String fileName;
    private final String separator = System.getProperty("file.separator");
    private final String dir = this.separator + "pages";

    Pages(final String fileName) {
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
