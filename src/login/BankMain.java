package login;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BankMain extends Application {

    public static Stage stage = null;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        primaryStage.setTitle("Satch Holistic Banking");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        this.stage = primaryStage;
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/design/design.css").toExternalForm());
        primaryStage.setScene(scene);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getSceneX()-xOffset);
                stage.setY(event.getSceneY()-yOffset);
            }
        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
