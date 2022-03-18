package unieuroop.controller.serialization;

public enum Pages {
	/**
	 * @param 
	 */
    DASHBOARD("Dashboard.fxml"),
    /**
     * @param
     */
    STOCK("Stock.fxml"),
    /**
     * @param
     */
    SALES("Sales.fxml"),
    /**
     * @param
     */
    DEPARTMENTS("DepartmentsView.fxml"),
    /**
     * @param
     */
    ANALYTICS("AnalyticType1" +System.getProperty("file.separator")+"SpentEarnedChart.fxml");
    private final String separator = System.getProperty("file.separator");

    private final String fileName;
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
