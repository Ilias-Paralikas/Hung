import java.util.ArrayList;
import java.util.Arrays;

import HungHelpers.*;

public class Game {

    private static final int POSSIBLE_LETTERS = 26;
    private static final int ASCII_BIAS = 65;
    char[] HiddenWord;
    int HiddenLen;
    ArrayList<char[]> possibleWords;
    int possibleWordsLength;
    Boolean[] knownPossitions;
    float[][] probabiites;

    static public void main(String[] args) {
        Game game = new Game("OL45883W");
        System.out.println(game.HiddenWord);
        game.CalculateProbabiites();
        System.out.println(Arrays.deepToString(game.possibleWords.toArray()));
        System.out.println(Arrays.toString(game.probabiites[0]));

        System.out.println("\n\n\n\n");
        game.ChooseLetter(game.HiddenWord[0],0);
        System.out.println(Arrays.deepToString(game.possibleWords.toArray()));
         System.out.println(Arrays.deepToString(game.probabiites));

        // game.CalculateProbabiites();
        // System.out.println(Arrays.deepToString(game.possibleWords.toArray()));
        // System.out.println(Arrays.toString(game.probabiites[0]));

    }

    public Game(String ID) {
        Dictionary Dict = new Dictionary(ID);
        Dict.ReadDictionary();

        String temp = Dict.words.get((int) Math.round(Math.random() * Dict.words.size()));
        this.HiddenWord = temp.toCharArray();
        this.HiddenLen = temp.length();

        this.possibleWords = new ArrayList<char[]>();
        for (String word : Dict.words) {
            if (word.length() == this.HiddenLen) {
                this.possibleWords.add(word.toCharArray());
            }
        }
        this.possibleWordsLength = possibleWords.size();

        this.knownPossitions = new Boolean[this.HiddenLen];
        Arrays.fill(this.knownPossitions, Boolean.FALSE);

        this.probabiites = new float[this.HiddenLen][POSSIBLE_LETTERS];
        for (float[] row : this.probabiites)
            Arrays.fill(row, 0);

    }

    private void CalculateProbabiites() {
        for (float[] row : this.probabiites)
            Arrays.fill(row, 0);

        for (int i = 0; i < this.HiddenLen; i++) {
            if (!this.knownPossitions[i]) {
                for (char[] word : this.possibleWords) {
                    this.probabiites[i][word[i] - ASCII_BIAS] += 1.0 / this.possibleWordsLength;
                }
            }
        }
    }

    private boolean ChooseLetter(char choice, int possition) {
        boolean result = (choice == this.HiddenWord[possition]);
        if (result) {
            this.possibleWords.removeIf(word -> (word[possition] != choice));
            this.knownPossitions[possition] = true;
        } else {
            this.possibleWords.removeIf(word -> (word[possition] == choice));
        }
        
        
        this.possibleWordsLength = this.possibleWords.size();
        // xazh lysh
        this.CalculateProbabiites();
        return result;
    }

}
