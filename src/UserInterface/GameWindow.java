package UserInterface;

import java.lang.reflect.Array;
import java.util.Arrays;

import GameFunctions.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    Game currentGame = new Game("OL45883W");
    Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    public void Select(ChoiceBox<Character> LetterSelector, ChoiceBox<Integer> PositionSelector) {
        Character letter = LetterSelector.getValue();
        Integer position = PositionSelector.getValue();
        if (!currentGame.ChooseLetter(letter, position)) {
            this.imageNumber += 1;
        }

        try {
            frame();
            System.out.println("selection");
            for (char[] word : currentGame.possibleWords) {
                System.out.println(String.valueOf(word));
            }

            System.out.println(Arrays.deepToString(currentGame.probabilities));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        System.out.println(currentGame.hiddenWord);

        frame();
    }

    public void frame() throws Exception {
        // 1
        primaryStage.setTitle("MediaLab Hangman");
        // 2
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setVgap(15);
        grid.setHgap(10);

        // currentGame.ChooseLetter(currentGame.hiddenWord[0],0);

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
        gapsLabel.setMinWidth(95 * currentGame.hiddenWord.length);
        GridPane.setConstraints(gapsLabel, 0, 4);

        ///////////////////////////////////////////////////////////////////////////////////////////
        // 4b

        TableView<Probabilities> table = new TableView<Probabilities>();

        TableColumn<Probabilities, Double> A = new TableColumn<Probabilities, Double>("A");
        A.setSortable(false);
        TableColumn<Probabilities, Double> B = new TableColumn<Probabilities, Double>("B");
        B.setSortable(false);
        TableColumn<Probabilities, Double> C = new TableColumn<Probabilities, Double>("C");
        C.setSortable(false);
        TableColumn<Probabilities, Double> D = new TableColumn<Probabilities, Double>("D");
        D.setSortable(false);
        TableColumn<Probabilities, Double> E = new TableColumn<Probabilities, Double>("E");
        E.setSortable(false);
        TableColumn<Probabilities, Double> F = new TableColumn<Probabilities, Double>("F");
        F.setSortable(false);
        TableColumn<Probabilities, Double> G = new TableColumn<Probabilities, Double>("G");
        G.setSortable(false);
        TableColumn<Probabilities, Double> H = new TableColumn<Probabilities, Double>("H");
        H.setSortable(false);
        TableColumn<Probabilities, Double> I = new TableColumn<Probabilities, Double>("I");
        I.setSortable(false);
        TableColumn<Probabilities, Double> J = new TableColumn<Probabilities, Double>("J");
        J.setSortable(false);
        TableColumn<Probabilities, Double> K = new TableColumn<Probabilities, Double>("K");
        K.setSortable(false);
        TableColumn<Probabilities, Double> L = new TableColumn<Probabilities, Double>("L");
        L.setSortable(false);
        TableColumn<Probabilities, Double> M = new TableColumn<Probabilities, Double>("M");
        M.setSortable(false);
        TableColumn<Probabilities, Double> N = new TableColumn<Probabilities, Double>("N");
        N.setSortable(false);
        TableColumn<Probabilities, Double> O = new TableColumn<Probabilities, Double>("O");
        O.setSortable(false);
        TableColumn<Probabilities, Double> P = new TableColumn<Probabilities, Double>("P");
        P.setSortable(false);
        TableColumn<Probabilities, Double> Q = new TableColumn<Probabilities, Double>("Q");
        Q.setSortable(false);
        TableColumn<Probabilities, Double> R = new TableColumn<Probabilities, Double>("R");
        R.setSortable(false);
        TableColumn<Probabilities, Double> S = new TableColumn<Probabilities, Double>("S");
        S.setSortable(false);
        TableColumn<Probabilities, Double> T = new TableColumn<Probabilities, Double>("T");
        T.setSortable(false);
        TableColumn<Probabilities, Double> U = new TableColumn<Probabilities, Double>("U");
        U.setSortable(false);
        TableColumn<Probabilities, Double> V = new TableColumn<Probabilities, Double>("V");
        V.setSortable(false);
        TableColumn<Probabilities, Double> W = new TableColumn<Probabilities, Double>("W");
        W.setSortable(false);
        TableColumn<Probabilities, Double> X = new TableColumn<Probabilities, Double>("X");
        X.setSortable(false);
        TableColumn<Probabilities, Double> Y = new TableColumn<Probabilities, Double>("Y");
        Y.setSortable(false);
        TableColumn<Probabilities, Double> Z = new TableColumn<Probabilities, Double>("Z");
        Z.setSortable(false);

        table.getColumns().addAll(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        A.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("A"));
        B.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("B"));
        C.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("C"));
        D.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("D"));
        E.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("E"));
        F.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("F"));
        G.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("G"));
        H.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("H"));
        I.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("I"));
        J.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("J"));
        K.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("K"));
        L.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("L"));
        M.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("M"));
        N.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("N"));
        O.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("O"));
        P.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("P"));
        Q.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("Q"));
        R.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("R"));
        S.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("S"));
        T.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("T"));
        U.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("U"));
        V.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("V"));
        W.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("W"));
        X.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("X"));
        Y.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("Y"));
        Z.setCellValueFactory(
                new PropertyValueFactory<Probabilities, Double>("Z"));

        for (double[] prob : currentGame.probabilities) {
            table.getItems().add(new Probabilities(prob));
        }

        GridPane.setConstraints(table, 1, 3);

        ///////////////////////////////////////////////////////////////////////////////////////////

        Label PositionSelectorLabel = new Label("PositionSelector");
        ChoiceBox<Integer> PositionSelector = new ChoiceBox<Integer>();
        for (int i = 0; i < currentGame.hiddenWord.length; i++) {
            if (!currentGame.knownPossitions[i])
                PositionSelector.getItems().add(i);
        }

        GridPane.setConstraints(PositionSelectorLabel, 0, 5);
        GridPane.setConstraints(PositionSelector, 0, 6);

        Label LetterSelectorLabel = new Label("LetterSelector");
        ChoiceBox<Character> LetterSelector = new ChoiceBox<Character>();
        for (char letter : ALPHABET) {
            LetterSelector.getItems().add(letter);
        }

        GridPane.setConstraints(LetterSelectorLabel, 1, 5);

        GridPane.setConstraints(LetterSelector, 1, 6);
        ////////////////////////////////////////////

        Button button = new Button("Click Me");
        button.setOnAction(e -> Select(LetterSelector,PositionSelector));

        GridPane.setConstraints(button, 0, 7);

        grid.getChildren().addAll(possibleWordsLength, pointsLabel, correctAnswersPercentage, gapsLabel, imageView,
                table, PositionSelector, PositionSelectorLabel, LetterSelector, LetterSelectorLabel, button);

        Scene scene = new Scene(grid, STAGE_WIDTH, STAGE_HEIGHT);

        scene.getStylesheets().add(CSS_FILE_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}

//    A    B    C    D    E    F    G    H    I    J     K    L   M    N    O    P    Q    R    S    T    U    V    W    X    Y    Z
// [[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0], 
//  [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0], 
//  [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0], 
//  [0.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0], 
//  [0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0], 
//  [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0], 
//  [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]]