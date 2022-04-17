package unieuroop.view.stock;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import unieuroop.controller.stock.ControllerStock;
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
    private RadioButton rdbtnIncreasing;
    @FXML
    private RadioButton rdbtnDecreasing;
    @FXML
    private Button btnConfirmFilter;

    private ViewStock viewStock;
    private ControllerStock controllerStock;
    private boolean increasing;

    public ViewStockSetFilters(final ViewStock viewStock, final ControllerStock controller) {
        this.viewStock = viewStock;
        this.controllerStock = controller;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.sliderMaxAmount.setMax((double) this.controllerStock.getMaxAmountproducts());
        this.sliderMinAmount.setMax((double) this.controllerStock.getMaxAmountproducts());
        this.sliderMaxAmount.setValue((double) this.controllerStock.getMaxAmountproducts());
        this.sliderMaxAmountHandler();
        this.sliderMinAmountHandler();
        this.rdbtnIncreasingHandler();
        this.cmboxCategories.getItems().addAll(this.controllerStock.getCategory());
    }

    /**
     * 
     */
    @FXML
    public void btnConfirmFilterHandler() {
        if (this.cmboxCategories.getValue() != null) {
            this.viewStock.loadProductsByList(this.controllerStock.getListProductsFilterBy(
            this.cmboxCategories.getValue(), (int) this.sliderMinAmount.getValue(), (int) this.sliderMaxAmount.getValue(), this.increasing));
            final Stage stage = (Stage) this.btnConfirmFilter.getScene().getWindow();
            stage.close();
        } else {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Impossible Set Product Filters");
            alert.setContentText("Select the Products Category before confirm and filter the all the products");
            alert.showAndWait();
        }
    }

    /**
     * 
     */
    @FXML
    public void rdbtnIncreasingHandler() {
        this.increasing = true;
        this.rdbtnIncreasing.setSelected(this.increasing);
        this.rdbtnDecreasing.setSelected(!this.increasing);
    }

    /**
     * 
     */
    @FXML
    public void rdbtnDecreasingHandler() {
        this.increasing = false;
        this.rdbtnIncreasing.setSelected(this.increasing);
        this.rdbtnDecreasing.setSelected(!this.increasing);
    }

    @FXML
    public void cmboxCategoriesHandler() {

    }

    /**
     * 
     */
    @FXML
    public void sliderMinAmountHandler() {
        if (checkSliderAmount()) {
            this.lblMinAmount.setText("Min Amount: " + (int) this.sliderMinAmount.getValue());
        } else {
            this.sliderMinAmount.setValue(this.sliderMaxAmount.getValue());
            this.lblMinAmount.setText("Min Amount: " + (int) this.sliderMaxAmount.getValue());
        }
    }

    /**
     * 
     */
    @FXML
    public void sliderMaxAmountHandler() {
        if (checkSliderAmount()) {
            this.lblMaxAmount.setText("Max Amount: " + (int) this.sliderMaxAmount.getValue());
        } else {
            this.sliderMinAmount.setValue(this.sliderMaxAmount.getValue());
            this.lblMinAmount.setText("Min Amount: " + (int) this.sliderMaxAmount.getValue());
            this.lblMaxAmount.setText("Max Amount: " + (int) this.sliderMaxAmount.getValue());
        }
    }

    /**
     * 
     * @return
     */
    private boolean checkSliderAmount() {
        if (this.sliderMinAmount.getValue() > this.sliderMaxAmount.getValue()) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Error Select Quantity");
            alert.setContentText("The minimum quantity must be at least equal to or less than the maximum.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

}
