package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import unieuroop.model.department.Department;

public interface ViewMergeLabel {

    void initialize(URL location, ResourceBundle resources);

    boolean isSelected();

    Department getDepartment();

}