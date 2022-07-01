package app.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Frontend extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Test");
            primaryStage.setMinHeight(450);
            primaryStage.setMinWidth(545);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/sample.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}