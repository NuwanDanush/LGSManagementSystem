package lgsapp.controlers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashbordControll implements Initializable {
    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnAddSec;

    @FXML
    private Button btn_help;


    //my bad - the freaking mouse event
  /*  @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnDashboard) {
            loadStage("/lgsapp/views/home.fxml");
        } else if (mouseEvent.getSource() == btnAddSec) {
          //  loadStage("/home/fxml/Students.fxml");
        } else if (mouseEvent.getSource() == btn_help) {
           // loadStage("/home/fxml/Timetable.fxml");
        }
    }*/

    @FXML
    void btnHome(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lgsapp/views/home.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setFullScreen (true);



    }

}