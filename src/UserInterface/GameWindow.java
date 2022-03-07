package UserInterface;

import java.util.Arrays;

import GameFunctions.Game;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameWindow extends Application {

    private static final String VISUAL_RESCOURCES_FILE_NAME = "VisualRescources";
    private static final String CSS_FILE_NAME = VISUAL_RESCOURCES_FILE_NAME + "/style.css";
    private static final String DRAWINGS_FILE_NAME = VISUAL_RESCOURCES_FILE_NAME + "/hung_drawings";
    private static final int STAGE_WIDTH = 1800;
    private static final int STAGE_HEIGHT = 900;
    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    int imageNumber = 1;
    char[] hiddenWordVisual;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1
        primaryStage.setTitle("MediaLab Hangman");
        // 2
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setVgap(15);
        grid.setHgap(10);

        Game currentGame = new Game("OL45883W");
        ////////////////////////////////// 3 TOP PART//////////////////////////////////
        // show avaliable words
        Label possibleWordsLength = new Label(
                "Possible Words: " + String.valueOf(currentGame.possibleWords.size()));
        GridPane.setConstraints(possibleWordsLength, 0, 0);
        // show points
        Label pointsLabel = new Label("Points: " + String.valueOf(currentGame.points));
        GridPane.setConstraints(pointsLabel, 0, 1);
        // show % of correct answrs
        Label correctAnswersPercentage = new Label(
                "Correct Answers: " + String.valueOf(currentGame.getcorrectAnswersPercentage()) + "%");
        GridPane.setConstraints(correctAnswersPercentage, 0, 2);

        ////////////////////////////////////////// MID LEFT PART
        ////////////////////////////////////////// ////////////////////////////////////////////////
        // image
        ImageView imageView = new ImageView(DRAWINGS_FILE_NAME + "/" + String.valueOf(this.imageNumber) + ".png");
        GridPane.setConstraints(imageView, 0, 3);
        // gaps

        this.hiddenWordVisual = new char[2 * currentGame.hiddenWord.length];


        for (int i = 0; i < currentGame.hiddenWord.length; i++) {
            if (currentGame.knownPossitions[i])
                this.hiddenWordVisual[2 * i] = currentGame.hiddenWord[i];
            else
                this.hiddenWordVisual[2 * i] = '_';
            this.hiddenWordVisual[2 * i + 1] = ' ';
        }

        Label gapsLabel = new Label(String.valueOf(this.hiddenWordVisual));
        gapsLabel.setId("Gaps");
        GridPane.setConstraints(gapsLabel, 0, 4);

        ///////////////////////////////////////////////////////////////////////////////////////////
        //4b
        TableView<Double> table = new TableView<Double>();
        TableColumn A = new TableColumn("A");
        TableColumn B = new TableColumn("B");
        TableColumn C = new TableColumn("C");

        final ObservableList<Probabilities> data = FXCollections.observableArrayList(
        new Probabilities(0.3,0.2,1.3),
        new Probabilities(0.3,0.2,1.3),
        new Probabilities(0.3,0.2,1.4)
        );

        A.setCellValueFactory(
    new PropertyValueFactory<Probabilities,Double>("A")
);
B.setCellValueFactory(
    new PropertyValueFactory<Probabilities,Double>("B")
);
C.setCellValueFactory(
    new PropertyValueFactory<Probabilities,Double>("C")
);

table.setItems(data);

        table.getColumns().addAll(A, B, C);

        System.out.println(Arrays.deepToString(currentGame.probabilities));


        GridPane.setConstraints(table, 1, 3);




        ///////////////////////////////////////////////////////////////////////////////////////////

        grid.getChildren().addAll(possibleWordsLength, pointsLabel, correctAnswersPercentage, gapsLabel, imageView,table);

        Scene scene = new Scene(grid, STAGE_WIDTH, STAGE_HEIGHT);

        scene.getStylesheets().add(CSS_FILE_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
