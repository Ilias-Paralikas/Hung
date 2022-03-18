package UserInterface;

import java.lang.reflect.Array;
import GameFunctions.Game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameWindow extends Application {

    private static final String VISUAL_RESCOURCES_FILE_NAME = "VisualRescources";
    private static final String CSS_FILE_NAME = VISUAL_RESCOURCES_FILE_NAME + "/style.css";
    private static final String DRAWINGS_FILE_NAME = VISUAL_RESCOURCES_FILE_NAME + "/hung_drawings";
    private static final int STAGE_WIDTH = 1800;
    private static final int STAGE_HEIGHT = 900;
    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private int imageNumber = 1;
    private char[] hiddenWordVisual;
    private Game currentGame;
    private Stage primaryStage;
    private Label possibleWordsLength = new Label();
    private Label pointsLabel = new Label();
    private Label correctAnswersPercentage = new Label();
    private Label gapsLabel = new Label();
    private Label PositionSelectorLabel = new Label("PositionSelector");
    private Label LetterSelectorLabel = new Label("LetterSelector");
    private Button SelectionButton = new Button("Select");
    private TableView<Probabilities> table = new TableView<Probabilities>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

// //Creating a GridPane container
// GridPane grid = new GridPane();
// grid.setPadding(new Insets(10, 10, 10, 10));
// grid.setVgap(5);
// grid.setHgap(5);
// //Defining the Name text field
// Label choiceLabel = new Label("Chose the ID of the book you want to play with");
// GridPane.setConstraints(choiceLabel, 0, 0);

// final TextField bookId = new TextField();
// bookId.setPromptText("Enter the id of the book you want to play with");
// bookId.setPrefColumnCount(30);
// bookId.getText();
// GridPane.setConstraints(bookId, 0, 1);
// Button choiceBook = new Button("Play");
// choiceBook.setOnAction(e -> playWithBook(bookId.getText()));
// GridPane.setConstraints(choiceBook, 0, 2);

// grid.getChildren().addAll(bookId,choiceLabel,choiceBook);

currentGame = new Game("OL45883W");




        this.primaryStage = stage;
        this.primaryStage.setTitle("MediaLab Hangman");
        this.hiddenWordVisual = new char[2 * currentGame.hiddenWord.length];
        try {
            frame();
        } catch (Exception e) {
            System.out.println(e);
        }
        // Scene scene = new Scene(grid, STAGE_WIDTH, STAGE_HEIGHT);

        // primaryStage.setScene(scene);
        // primaryStage.show();
    }

    // private void playWithBook(String bookId){

    // }


    private void frame() throws Exception {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setVgap(15);
        grid.setHgap(10);
        System.out.println(currentGame.hiddenWord);
        UpdateStatistics();


       // show image 
        ImageView imageView = new ImageView(DRAWINGS_FILE_NAME + "/" + String.valueOf(this.imageNumber) + ".png");
        GridPane.setConstraints(imageView, 0, 3);
        // gaps

        VisualizeHiddenWord();


        UpdateProbabilities();

        ChoiceBox<Integer> PositionSelector = new ChoiceBox<Integer>();
        for (int i = 0; i < currentGame.hiddenWord.length; i++) {
            if (!currentGame.knownPossitions[i])
                PositionSelector.getItems().add(i);
        }

        GridPane.setConstraints(PositionSelectorLabel, 0, 5);
        GridPane.setConstraints(PositionSelector, 0, 6);

        ChoiceBox<Character> LetterSelector = new ChoiceBox<Character>();
        for (char letter : ALPHABET) {
            LetterSelector.getItems().add(letter);
        }

        GridPane.setConstraints(LetterSelectorLabel, 1, 5);

        GridPane.setConstraints(LetterSelector, 1, 6);
        ////////////////////////////////////////////

        SelectionButton.setOnAction(e -> Select(LetterSelector, PositionSelector));

        GridPane.setConstraints(SelectionButton, 0, 7);

        grid.getChildren().addAll(possibleWordsLength, pointsLabel, correctAnswersPercentage, gapsLabel, imageView,
                table, PositionSelector, PositionSelectorLabel, LetterSelector, LetterSelectorLabel, SelectionButton);

        Scene scene = new Scene(grid, STAGE_WIDTH, STAGE_HEIGHT);

        scene.getStylesheets().add(CSS_FILE_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void Select(ChoiceBox<Character> LetterSelector, ChoiceBox<Integer> PositionSelector) {
        Character letter = LetterSelector.getValue();
        Integer position = PositionSelector.getValue();
        if (!currentGame.ChooseLetter(letter, position)) {
            this.imageNumber += 1;
        }
        try {
            frame();
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    private void UpdateStatistics(){
        //possibleWords
        possibleWordsLength.setText("Possible Words: " + String.valueOf(currentGame.possibleWords.size()));
        GridPane.setConstraints(possibleWordsLength, 0, 0);
        // show points
        pointsLabel.setText("Points: " + String.valueOf(currentGame.points));
        GridPane.setConstraints(pointsLabel, 0, 1);
        // show % of correct answrs
        correctAnswersPercentage
                .setText("Correct Answers: " + String.valueOf(currentGame.getcorrectAnswersPercentage()) + "%");
        GridPane.setConstraints(correctAnswersPercentage, 0, 2);
    }

    private void UpdateProbabilities(){
        for (int i = 0; i < ALPHABET.length; i++) {
            TableColumn<Probabilities, Double> column = new TableColumn<Probabilities, Double>(
                    Character.toString(Array.getChar(ALPHABET, i)));
            column.setSortable(false);
            column.setCellValueFactory(
                    new PropertyValueFactory<Probabilities, Double>(Character.toString(Array.getChar(ALPHABET, i))));
            table.getColumns().add(column);
        }

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        for (double[] prob : currentGame.probabilities) {
            table.getItems().add(new Probabilities(prob));
        }

        GridPane.setConstraints(table, 1, 3);
    }
    private void VisualizeHiddenWord(){
        for (int i = 0; i < currentGame.hiddenWord.length; i++) {
            if (currentGame.knownPossitions[i])
                this.hiddenWordVisual[2 * i] = currentGame.hiddenWord[i];
            else
                this.hiddenWordVisual[2 * i] = '_';
            this.hiddenWordVisual[2 * i + 1] = ' ';
        }
        gapsLabel.setText(String.valueOf(this.hiddenWordVisual));
        gapsLabel.setId("Gaps");
        gapsLabel.setMinWidth(95 * currentGame.hiddenWord.length);
        GridPane.setConstraints(gapsLabel, 0, 4);

    }
}
