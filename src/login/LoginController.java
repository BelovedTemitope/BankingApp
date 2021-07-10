package login;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginController {

    @FXML
    private Pane main_area;

    public void closeApp(MouseEvent mouseEvent){
        Platform.exit();
        System.exit(0);
    }

    public void createAccount(MouseEvent mouseEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/createaccount/CreateAccount.fxml"));
        main_area.getChildren().removeAll();
        main_area.getChildren().addAll(fxml);
    }
}
