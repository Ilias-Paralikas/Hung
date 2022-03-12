package GameFunctions;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    private static final int POSSIBLE_LETTERS = 26;
    private static final int ASCII_OFFSET = 65;
    private static final int[] VICTORY_POINTS = new int[] { 5, 10, 15, 30 };
    private static final int LOSS_POINTS = 15;
    private static final double[] VICTORT_POINTS_PROBABILITY_THRESHOLD = new double[] { 0.6, 0.4, 0.25, 0.0 };
    private static final int INITIAL_CHANCES = 6;

    public char[] hiddenWord;
    public Boolean[] knownPossitions;
    public ArrayList<char[]> possibleWords;
    public double[][] probabilities;
    public int points = 0;
    public int chancesRemaining = INITIAL_CHANCES;
    public int correctAnswers = 0;


    public double getcorrectAnswersPercentage() {
        if (this.correctAnswers == 0  &&  chancesRemaining == INITIAL_CHANCES)
            return 100;
        else
            return this.correctAnswers / (this.correctAnswers + this.chancesRemaining - INITIAL_CHANCES) *100;
    }


    public Game(String ID) {
        Dictionary Dict = new Dictionary(ID);
        Dict.ReadDictionary();

        this.hiddenWord = Dict.words.get((int) Math.round(Math.random() * Dict.words.size())).toCharArray();

        this.possibleWords = new ArrayList<char[]>();
        for (String word : Dict.words) {
            if (word.length() == this.hiddenWord.length) {
                this.possibleWords.add(word.toCharArray());
            }
        }
        this.knownPossitions = new Boolean[this.hiddenWord.length];
        Arrays.fill(this.knownPossitions, Boolean.FALSE);

        this.probabilities = new double[this.hiddenWord.length][POSSIBLE_LETTERS];
        for (double[] row : this.probabilities)
            Arrays.fill(row, 0);

        if (VICTORY_POINTS.length != VICTORT_POINTS_PROBABILITY_THRESHOLD.length) {
            System.out.println("Victory points do no match their rewards");
        }

        this.Calculateprobabilities();
    }

    public boolean ChooseLetter(char choice, int possition) {
        boolean result = (choice == this.hiddenWord[possition]);
        if (result) {
            correctAnswers += 1;
            if (correctAnswers == hiddenWord.length) {
                System.out.println("YOU WON!");
            }
            for (int i = 0; i < VICTORT_POINTS_PROBABILITY_THRESHOLD.length; i++) {
                if (this.probabilities[possition][choice - ASCII_OFFSET] > VICTORT_POINTS_PROBABILITY_THRESHOLD[i]) {
                    this.points += VICTORY_POINTS[i];
                    break;
                }
            }

            this.possibleWords.removeIf(word -> (word[possition] != choice));
            this.knownPossitions[possition] = true;
        } else {
            this.possibleWords.removeIf(word -> (word[possition] == choice));

            if (this.points > LOSS_POINTS) {
                this.points -= LOSS_POINTS;
            } else {
                this.points = 0;
            }
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

        for (int i = 0; i < this.hiddenWord.length; i++) {
            if (!this.knownPossitions[i]) {
                for (char[] word : this.possibleWords) {
                    this.probabilities[i][word[i] - ASCII_OFFSET] += 1.0 / this.possibleWords.size();
                }
            }
        }
    }

}
