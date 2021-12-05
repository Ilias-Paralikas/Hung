package HungHelpers;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    private static final int POSSIBLE_LETTERS = 26;
    private static final int ASCII_BIAS = 65;
    private static final int[] victoryPoints = new int[] { 5, 10, 15, 30 };
    private static final int lossPoints = 15;
    private static final double[] victoryPointsProbabilityThreshold = new double[] { 0.6, 0.4, 0.25, 0.0 };
    private char[] HiddenWord;
    private Boolean[] knownPossitions;
    private ArrayList<char[]> possibleWords;
    private double[][] probabilities;
    private int points = 0;
    private int chancesRemaining = 6;

    class Gamestate {
        public char[] Gamestate_HiddenWord;
        public Boolean[] Gamestate_knownPossitions;
        public ArrayList<char[]> Gamestate_PossibleWords;
        public double[][] Gamestate_Probabilities;
        public int Gamestate_Points;
        public int Gamestate_ChancesRemaining;
        public boolean Gamestate_result;

        public Gamestate(boolean result) {
            this.Gamestate_HiddenWord = Game.this.HiddenWord;
            this.Gamestate_knownPossitions = Game.this.knownPossitions;
            this.Gamestate_PossibleWords = Game.this.possibleWords;
            this.Gamestate_Probabilities = Game.this.probabilities;
            this.Gamestate_Points = Game.this.points;
            this.Gamestate_ChancesRemaining = Game.this.chancesRemaining;
            this.Gamestate_result = result;

        }
    }

    // public static void main(String[] args) {
    // Game game = new Game("OL45883W");
    // System.out.println(game.HiddenWord);
    // System.out.println(Arrays.deepToString(game.probabilities));
    // Gamestate gamestate = game.ChooseLetter('A', 1);
    // System.out.println(Arrays.deepToString(gamestate.Gamestate_Probabilities));

    // System.out.println(gamestate.Gamestate_HiddenWord);

    // }

    public Game(String ID) {
        Dictionary Dict = new Dictionary(ID);
        Dict.ReadDictionary();

        this.HiddenWord = Dict.words.get((int) Math.round(Math.random() * Dict.words.size())).toCharArray();

        this.possibleWords = new ArrayList<char[]>();
        for (String word : Dict.words) {
            if (word.length() == this.HiddenWord.length) {
                this.possibleWords.add(word.toCharArray());
            }
        }
        this.knownPossitions = new Boolean[this.HiddenWord.length];
        Arrays.fill(this.knownPossitions, Boolean.FALSE);

        this.probabilities = new double[this.HiddenWord.length][POSSIBLE_LETTERS];
        for (double[] row : this.probabilities)
            Arrays.fill(row, 0);

        if (victoryPoints.length != victoryPointsProbabilityThreshold.length) {
            System.out.println("Victory points do no match their rewards");
        }

        this.Calculateprobabilities();
    }

    public boolean ChooseLetter(char choice, int possition) {
        boolean result = (choice == this.HiddenWord[possition]);
        if (result) {
            for (int i = 0; i < victoryPointsProbabilityThreshold.length; i++) {
                if (this.probabilities[possition][choice - ASCII_BIAS] > victoryPointsProbabilityThreshold[i]) {
                    this.points += victoryPoints[i];
                    break;
                }
            }

            this.possibleWords.removeIf(word -> (word[possition] != choice));
            this.knownPossitions[possition] = true;
        } else {
            this.possibleWords.removeIf(word -> (word[possition] == choice));
            this.points -= lossPoints;
            this.chancesRemaining -= 1;
            if (this.chancesRemaining == 0) {
                System.out.println("You lost!");
            }
        }

        // xazh lysh
        this.Calculateprobabilities();
        return result;
    }

    private void Calculateprobabilities() {
        for (double[] row : this.probabilities)
            Arrays.fill(row, 0);

        for (int i = 0; i < this.HiddenWord.length; i++) {
            if (!this.knownPossitions[i]) {
                for (char[] word : this.possibleWords) {
                    this.probabilities[i][word[i] - ASCII_BIAS] += 1.0 / this.possibleWords.size();
                }
            }
        }
    }

}
