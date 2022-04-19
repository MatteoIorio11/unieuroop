package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import unieuroop.model.department.Department;

public final class ViewMergeLabelImpl implements Initializable, ViewMergeLabel {

    @FXML
    private Label lblDepartment;
    @FXML
    private CheckBox chkboxDepartmentSelected;

    private final Department department;

    public ViewMergeLabelImpl(final Department department) {
        this.department = department;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.lblDepartment.setText(this.department.toString());
    }

    @Override
    public boolean isSelected() {
        return this.chkboxDepartmentSelected.isSelected();
    }

    @Override
    public Department getDepartment() {
        return this.department;
    }

}
