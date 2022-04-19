package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import unieuroop.model.department.Department;

public interface ViewMergeLabel {

    /**
     * 
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * 
     * @return To Do.
     */
    boolean isSelected();

    /**
     * 
     * @return TpDO.
     */
    Department getDepartment();

}
