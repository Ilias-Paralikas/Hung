package HungHelpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import org.json.JSONObject;
import Exceptions.UndersizeException;

public class Dictionary {


    public static void main(String[] args) {
        String ID = "OL45883W";

        try {
            CreateDictionary(ID);
            ArrayList<String> words = ReadDictionary(ID);
            System.out.println(words);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static ArrayList<String> ReadDictionary(String ID) {
        try {
            ArrayList<String> words = new ArrayList<String>();

            try {
                CreateDictionary(ID);
            } catch (Exception e) {
                System.out.println(e);
            }

            Scanner reader = new Scanner(new FileReader("src/medialab/hangman_DICTIONARY - " + ID + ".txt"));
            while (reader.hasNextLine()) {
                String word = reader.nextLine();
                words.add(word);
            }
            reader.close();
            return words;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void CreateDictionary(String ID) throws Exception {
        String description = GetDescription(ID);
        String[] parsed_description = description.split("\\s+");
        HashSet<String> words = CorretDictionary(parsed_description);
        FileManager(ID);
        StoreHashSet(words, ID);

    }

    private static String GetBook(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        String book = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
                .thenApply((String responseBody) -> responseBody).join();

        return book;
    }

    private static String GetDescription(String ID) throws Exception {

        String url = "https://openlibrary.org/works/" + ID + ".json";
        String book = GetBook(url);
        try {
            JSONObject jsonBook = new JSONObject(book);
            return jsonBook.getString("description");

        } catch (Exception e) {
            throw new Exception("The ID code you provided does not match a book");
        }
    }

    private static HashSet<String> CorretDictionary(String[] parsed_description) throws UndersizeException {
        HashSet<String> short_words = new HashSet<String>();
        HashSet<String> long_words = new HashSet<String>();

        for (String punctuated_word : parsed_description) {
            String word = punctuated_word.replaceAll("\\p{Punct}", "").toUpperCase();
            if (word.length() >= 6) {
                if (word.length() >= 9) {
                    long_words.add(word);
                } else {
                    short_words.add(word);
                }
            }
        }

        Integer balance_counter = 0;
        Integer short_size = 4 * long_words.size();

        for (String short_word : short_words) {
            if (balance_counter >= short_size) {
                break;
            }
            long_words.add(short_word);
            balance_counter++;
        }

        if (long_words.size() < 20) {
            throw new UndersizeException("small words count");
        }

        return long_words;
    }

    private static void StoreHashSet(HashSet<String> words, String ID) throws IOException {
        String filename = FileManager(ID);
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (String word : words) {
            writer.write(word);
            writer.write("\n");
        }
        writer.close();

    }

    private static String FileManager(String ID) throws IOException {

        File medialab = new File("src/medialab");
        String filename = "src/medialab/hangman_DICTIONARY - " + ID + ".txt";
        File file = new File(filename);

        try {
            medialab.mkdir();
            file.createNewFile();
        } catch (Exception e) {
            System.out.println(e);
        }

        return filename;
    }
}
