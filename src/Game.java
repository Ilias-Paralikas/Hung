import java.util.ArrayList;
import java.util.Arrays;

import HungHelpers.*;

public class Game {

    private static final int POSSIBLE_LETTERS = 26;
    private static final int ASCII_BIAS = 65;
    private static final int[] victoryPoints = new int[] { 5, 10, 15, 30 };
    private static final int lossPoints = 15;
    private static final double[] victoryPointsProbabilityThreshold = new double[] { 0.6, 0.4, 0.25, 0.0 };
    private char[] HiddenWord;
    private ArrayList<char[]> possibleWords;
    private int possibleWordsLength;
    private Boolean[] knownPossitions;
    private double[][] probabilities;
    private int points = 0;
    private int chancesRemaining = 6;

    static public void main(String[] args) {
        Game game = new Game("OL45883W");
        System.out.println(game.HiddenWord);
        game.Calculateprobabilities();
        System.out.println(Arrays.deepToString(game.possibleWords.toArray()));
        System.out.println(Arrays.toString(game.probabilities[0]));
        System.out.println(game.points);
        System.out.println("\n\n\n\n");
        game.ChooseLetter((char) (game.HiddenWord[0] + 1), 0);
        System.out.println(Arrays.deepToString(game.possibleWords.toArray()));
        System.out.println(Arrays.deepToString(game.probabilities));
        System.out.println(game.points);

        // game.Calculateprobabilities();
        // System.out.println(Arrays.deepToString(game.possibleWords.toArray()));
        // System.out.println(Arrays.toString(game.probabilities[0]));

    }

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
        this.possibleWordsLength = possibleWords.size();

        this.knownPossitions = new Boolean[this.HiddenWord.length];
        Arrays.fill(this.knownPossitions, Boolean.FALSE);

        this.probabilities = new double[this.HiddenWord.length][POSSIBLE_LETTERS];
        for (double[] row : this.probabilities)
            Arrays.fill(row, 0);

        if (victoryPoints.length != victoryPointsProbabilityThreshold.length) {
            System.out.println("Victory points do no match their rewards");
        }

    }

    private void Calculateprobabilities() {
        for (double[] row : this.probabilities)
            Arrays.fill(row, 0);

        for (int i = 0; i < this.HiddenWord.length; i++) {
            if (!this.knownPossitions[i]) {
                for (char[] word : this.possibleWords) {
                    this.probabilities[i][word[i] - ASCII_BIAS] += 1.0 / this.possibleWordsLength;
                }
            }
        }
    }

    private boolean ChooseLetter(char choice, int possition) {
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

        this.possibleWordsLength = this.possibleWords.size();
        // xazh lysh
        this.Calculateprobabilities();
        return result;
    }

}
