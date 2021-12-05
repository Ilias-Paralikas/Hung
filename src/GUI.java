
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application {

    private static final String Graphics_File_Name = "Graphics";
    private static final String CSS_File_Name = Graphics_File_Name + "/style.css";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("App");
        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, 300, 250);

        scene.getStylesheets().add(CSS_File_Name);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
