package unieuroop.view.stock;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import unieuroop.controller.stock.ControllerStockImpl;
import unieuroop.model.product.Category;

public class ViewStockSetFilters implements Initializable {

    @FXML
    private Label lblMinAmount;
    @FXML
    private Slider sliderMinAmount;
    @FXML
    private Label lblMaxAmount;
    @FXML
    private Slider sliderMaxAmount;
    @FXML
    private ComboBox<Category> cmboxCategories;
    @FXML
    private Button btnConfirmFilter;

    private ViewStock viewStock;
    private ControllerStockImpl controllerStock;

    public ViewStockSetFilters(final ViewStock viewStock, final ControllerStockImpl controller) {
        this.viewStock = viewStock;
        this.controllerStock = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }

    @FXML
    public void btnConfirmFilterHandler() {
        
    }

    @FXML
    public void cmboxCategoriesHandler() {
        
    }

}
