package unieuroop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ViewLogin implements Initializable{
    @FXML 
    private BorderPane mainPane;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("AAA");
        Pane p;
        try {
            p = FXMLLoader.load(getClass().getResource("/pages/MainCharts.fxml"));
            this.mainPane.setCenter(p);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
