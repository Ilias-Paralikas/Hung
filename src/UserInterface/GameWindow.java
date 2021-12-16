package UserInterface;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameWindow extends Application {

    private static final String VISUAL_RESCOURCES_FILE_NAME = "VisualRescources";
    private static final String CSS_FILE_NAME = VISUAL_RESCOURCES_FILE_NAME + "/style.css";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("App");

        Label label1 = new Label("ilias");
        label1.setId("info");
        label1.getStylesheets().add(CSS_FILE_NAME);

        StackPane layout = new StackPane();
        layout.getChildren().add(label1);
        Scene scene = new Scene(layout);

        scene.getStylesheets().add(CSS_FILE_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
