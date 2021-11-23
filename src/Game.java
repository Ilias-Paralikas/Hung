import HungHelpers.*;

public class Game {
    public static void main(String[] args) {
        Dictionary CurrentDict = new Dictionary("OL45883W");
        CurrentDict.ReadDictionary();

        String word = CurrentDict.words.get((int)Math.round(Math.random() * CurrentDict.words.size()));

        System.out.println(word);

    }
}
