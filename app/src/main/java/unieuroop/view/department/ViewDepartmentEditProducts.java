package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

public interface ViewDepartmentEditProducts {

    void initialize(URL location, ResourceBundle resources);

    void updateView();

    void listProductsDepartmentHandler();

    void btnAddProductsHandler();

    void btnRemoveProductsHandler();

}
