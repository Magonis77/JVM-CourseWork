package ProjectSystem;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    JFXButton newTeam, newProject, newProjBigBtn, createTeamBigBtn, returnBtn;

    public void displayNewTeamSideBtn() throws Exception {

        Parent root1 = FXMLLoader.load(getClass().getResource("NewTeam.fxml"));
        Stage window = (Stage) newTeam.getScene().getWindow();
        window.setScene(new Scene(root1));
        window.setTitle("Create New Team");

    }

    public void displayNewProjectSideBtn() throws Exception {

        Parent root2 = FXMLLoader.load(getClass().getResource("NewProject.fxml"));
        Stage window = (Stage) newProject.getScene().getWindow();
        window.setScene(new Scene(root2));
        window.setTitle("Create New Project");

    }


    public void displayNewTeamBigBtn() throws Exception {

        Parent root1 = FXMLLoader.load(getClass().getResource("NewTeam.fxml"));
        Stage window = (Stage) createTeamBigBtn.getScene().getWindow();
        window.setScene(new Scene(root1));
        window.setTitle("Create New Team");

    }

    public void displayNewProjectBigBtn() throws Exception {

        Parent root2 = FXMLLoader.load(getClass().getResource("NewProject.fxml"));
        Stage window = (Stage) newProjBigBtn.getScene().getWindow();
        window.setScene(new Scene(root2));
        window.setTitle("Create New Project");

    }

    public void extBtn() {
        System.exit(0);

    }









}
