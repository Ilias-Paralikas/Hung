import HungHelpers.*;


public class Game {
    public static void main(String[] args) {
        Dictionary CurrentDict =  new Dictionary("OL45883W");
        try {
            CurrentDict.ReadDictionary();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(CurrentDict.words);

    }
}
