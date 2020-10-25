package ProjectSystem;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewProjectController {

    @FXML
    JFXButton returnBtn;


    public void setReturnBtn() throws Exception {

        Parent root1 = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage window = (Stage) returnBtn.getScene().getWindow();
        window.setScene(new Scene(root1));

    }

}
