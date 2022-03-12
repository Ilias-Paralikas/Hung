package UserInterface;

import java.util.ArrayList;
import java.util.Arrays;

import GameFunctions.Game;
import javafx.application.Application;
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
    //private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
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

        TableView<Probabilities>  table = new TableView<Probabilities>();

        TableColumn<Probabilities,Double> A = new TableColumn<Probabilities,Double>("A");
        TableColumn<Probabilities,Double> B = new TableColumn<Probabilities,Double>("B");
        TableColumn<Probabilities,Double> C = new TableColumn<Probabilities,Double>("C");
        TableColumn<Probabilities,Double> D = new TableColumn<Probabilities,Double>("D");
        TableColumn<Probabilities,Double> E = new TableColumn<Probabilities,Double>("E");
        TableColumn<Probabilities,Double> F = new TableColumn<Probabilities,Double>("F");
        TableColumn<Probabilities,Double> G = new TableColumn<Probabilities,Double>("G");
        TableColumn<Probabilities,Double> H = new TableColumn<Probabilities,Double>("H");
        TableColumn<Probabilities,Double> I = new TableColumn<Probabilities,Double>("I");
        TableColumn<Probabilities,Double> J = new TableColumn<Probabilities,Double>("J");
        TableColumn<Probabilities,Double> K = new TableColumn<Probabilities,Double>("K");
        TableColumn<Probabilities,Double> L = new TableColumn<Probabilities,Double>("L");
        TableColumn<Probabilities,Double> M = new TableColumn<Probabilities,Double>("M");
        TableColumn<Probabilities,Double> N = new TableColumn<Probabilities,Double>("N");
        TableColumn<Probabilities,Double> O = new TableColumn<Probabilities,Double>("O");
        TableColumn<Probabilities,Double> P = new TableColumn<Probabilities,Double>("P");
        TableColumn<Probabilities,Double> Q = new TableColumn<Probabilities,Double>("Q");
        TableColumn<Probabilities,Double> R = new TableColumn<Probabilities,Double>("R");
        TableColumn<Probabilities,Double> S = new TableColumn<Probabilities,Double>("S");
        TableColumn<Probabilities,Double> T = new TableColumn<Probabilities,Double>("T");
        TableColumn<Probabilities,Double> U = new TableColumn<Probabilities,Double>("U");
        TableColumn<Probabilities,Double> V = new TableColumn<Probabilities,Double>("V");
        TableColumn<Probabilities,Double> W = new TableColumn<Probabilities,Double>("W");
        TableColumn<Probabilities,Double> X = new TableColumn<Probabilities,Double>("X");
        TableColumn<Probabilities,Double> Y = new TableColumn<Probabilities,Double>("Y");
        TableColumn<Probabilities,Double> Z = new TableColumn<Probabilities,Double>("Z");
        
        table.getColumns().addAll(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y);
        table.setPrefSize( 1000, 300 );

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        A.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("A")
        );
        B.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("B")
        );
        C.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("C")
        );
        D.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("D")
        );
        E.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("E")
        );
        F.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("F")
        );
        G.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("G")
        );
        H.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("H")
        );
        I.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("I")
        );
        J.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("J")
        );
        K.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("K")
        );
        L.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("L")
        );
        M.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("M")
        );
        N.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("N")
        );
        O.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("O")
        );        
        P.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("P")
        );        
        Q.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("Q")
        );
        R.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("R")
        );
        S.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("S")
        );
        T.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("T")
        );
        U.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("U")
        );
        V.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("V")
        );
        W.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("W")
        );
        X.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("X")
        );
        Y.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("Y")
        );
        Z.setCellValueFactory(
            new PropertyValueFactory<Probabilities,Double>("Z")
        );

        ArrayList<Double> test = new ArrayList<Double> (Arrays.asList(0.3,0.2,0.1,0.3,0.2,0.1,0.3,0.2,0.1,0.3,0.2,0.1,0.3,0.2,0.1,0.3,0.2,0.1,0.3,0.2,0.1,0.3,0.2,0.1,0.1,0.1)); 

        table.getItems().add(new Probabilities(test   ));

        GridPane.setConstraints(table, 1, 3);


       // System.out.println(my.probs.size());





        ///////////////////////////////////////////////////////////////////////////////////////////

        grid.getChildren().addAll(possibleWordsLength, pointsLabel, correctAnswersPercentage, gapsLabel, imageView,table);

        Scene scene = new Scene(grid, STAGE_WIDTH, STAGE_HEIGHT);

        scene.getStylesheets().add(CSS_FILE_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
