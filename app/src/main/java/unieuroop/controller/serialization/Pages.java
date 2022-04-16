package unieuroop.controller.serialization;

public enum Pages {
    /**
     * @param 
     */
    DASHBOARD("/pages/DashBoard/DashBoard.fxml"),
    /**
     * @param 
     */
    LOGIN("/pages/Login/Login.fxml"),
    /**
     * @param 
     */
    DEPARTMENTS("/pages/Department/DepartmentsView.fxml"),
    /**
     * @param 
     */
    LABEL_MERGE_DEPARTMENT("/pages/Department/DepartmentMergeLabel.fxml"),
    /**
     * @param 
     */
    ADD_DEPARTMENTS("/pages/Department/DepartmentAddDepartment.fxml"),
    /**
     * @param 
     */
    DEPARTMENTS_LABEL("/pages/Department/DepartmentsLabelForDepartmentsView.fxml"),
    /**
     * @param 
     */
    DELETE_DEPARTMENTS("/pages/Department/DepartmentDeleteDepartment.fxml"),
    /**
     * @param 
     */
    MERGE_DEPARTMENTS("/pages/Department/DepartmentMergeDepartment.fxml"),
    /**
     * @param 
     */
    EDIT_STAFF_DEPARTMENTS("/pages/Department/DepartmentEditStaff.fxml"),
    /**
     * @param 
     */
    LABEL_PRODUCT_DEPARTMENT("/pages/Department/DepartmentLabelProduct.fxml"),
    /**
     * @param 
     */
    EDIT_PRODUCTS_DEPARTMENTS("/pages/Department/DepartmentsEditProductsView.fxml"),
    /**
     * @param
     */
    DEPARTMENTS_EDIT_PRODUCTS("/pages/Department/DepartmentsEditProductsView.fxml"),
    /**
     * @param
     */
    STOCK("/pages/Stock/StockView.fxml"),
    /**
     * @param
     */
    STOCK_SET_SEARCH_FILTER("/pages/Stock/StockSetFilterView.fxml"),
    /**
     * @param
     */
    STOCK_BUY_PRODUCTS("/pages/Stock/StockBuyProductsView.fxml"),
    /**
     * @param
     */
    STOCK_LABEL_FOR_STOCKBUYPRODUCTS("/pages/Stock/StockLabelForStockBuyProductsView.fxml"),
    /**
     * @param
     */
    SALES("/pages/Sale/MainSale.fxml"),
    /**
     * @param
     */
    LABEL_PRODUCT("/pages/Labels/labelProduct.fxml"),
    /**
     * @param 
     */
    SALE_PRODUCTS("/pages/DashBoard/SaleProducts.fxml"),
    /**
     * @param
     */
    CHOSE_CLIENT("/pages/Client/ChoseClient.fxml"),
    /**
     * @param
     */
    CLIENTS("/pages/Client/clientView.fxml"),
    /**
     * @param
     */
    STAFF("/pages/Staff/staffView.fxml"),
    /**
     * @param file directory of balance's chart
     */
    BALANCE("/pages/Balance/SpentEarnedChart.fxml"),
    /**
     * @param
     */
    MAIN_CATEGORIES_SOLD("/pages/CategoriesSold/MainCategoriesSold.fxml"),
    /**
     * @param
     */
    TABLE_CATEGORIES_SOLD("/pages/CategoriesSold/tableCategoriesSold.fxml"),
    /**
     * @param
     */
    MAIN_DATE_SOLD("/pages/DateSold/MainDateChart.fxml");

    private final String fileName;

    Pages(final String fileName) {
        this.fileName = fileName;
    }
    /**
     * 
     * @return the path of the file 
     */
    public String getPath() {
        return this.fileName;
    }
}
