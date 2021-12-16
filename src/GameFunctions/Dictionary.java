package GameFunctions;

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

import Exceptions.*;

public class Dictionary {

    private static final File medialab = new File("src/medialab");
    private static final int SHORT_WORD_THRESHOLD = 6;
    private static final int LONG_WORD_THRESHOLD = 9;
    private static final int LONG_WORDS_PERCENTAGE = 20;
    String ID;
    String filename;
    String url;
    public ArrayList<String> words = new ArrayList<String>();

    public Dictionary(String providedID) {
        medialab.mkdir();
        ID = providedID;
        url = "https://openlibrary.org/works/" + ID + ".json";
        filename = "src/medialab/hangman_DICTIONARY - " + ID + ".txt";

        try {
            medialab.mkdir();
            if (new File(filename).createNewFile()) {
                CreateDictionary();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ReadDictionary() {
        try {
            if (new File(filename).createNewFile()) {
                CreateDictionary();
                return;
            }
            words.clear();
            Scanner reader = new Scanner(new FileReader(filename));
            while (reader.hasNextLine()) {
                String word = reader.nextLine();
                words.add(word);
            }
            reader.close();

            ValidateDictionary();
        } catch (IDNotMatch e) {
            System.out.println("got ID not match");
            System.out.println(e);
            (new File(filename)).delete();
        } catch (Exception e) {
            words.clear();
            (new File(filename)).delete();
            System.out.println(e);
            System.out.println(
                    "Dictionaries are generated automatically from the openLibrary API and tested before stored. \nPlease dont meddle with them manually. \nThe file will be deleted so you can rerun the program.");
        }
    }

    private void CreateDictionary() throws Exception {
        try {
            String description = GetDescription();
            String[] parsed_description = description.split("\\s+");
            CorretDictionary(parsed_description);
            StoreWords();
        } catch (Exception e) {
            System.out.println("Error message" + e);
            throw new IDNotMatch("Possible problems, wrong ID, API down, no internet connection");
        }

    }

    private void ValidateDictionary() throws Exception {
        int long_counter = 0, short_counter = 0;
        for (String word : words) {
            int len = word.length();
            if (len < SHORT_WORD_THRESHOLD) {
                throw new InvalidRangeException("Found word with less than " + SHORT_WORD_THRESHOLD + " letters");
            } else {
                if (len >= LONG_WORD_THRESHOLD) {
                    long_counter++;
                } else {
                    short_counter++;
                }
            }
        }
        if (long_counter + short_counter < 20) {
            throw new UndersizeException("Less than 20 words in Dict");
        }

        if (short_counter > (100 / LONG_WORDS_PERCENTAGE - 1) * long_counter) {
            throw new UnbalancedException("Less than 20% of the words consist of nine or more letters");
        }

        HashSet<String> unique = new HashSet<String>(words);
        if (words.size() != unique.size()) {
            throw new InvalidCountExeception("Word duplicate found");
        }
    }

    private String GetBook() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        String book = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
                .thenApply((String responseBody) -> responseBody).join();

        return book;
    }

    private String GetDescription() throws Exception {

        String book = GetBook();
        JSONObject jsonBook = new JSONObject(book);
        return jsonBook.getString("description");

    }

    private void CorretDictionary(String[] parsed_description) throws UndersizeException {
        HashSet<String> short_words = new HashSet<String>();
        HashSet<String> long_words = new HashSet<String>();

        for (String punctuated_word : parsed_description) {
            String word = punctuated_word.replaceAll("\\p{Punct}", "").toUpperCase();
            if (word.length() >= SHORT_WORD_THRESHOLD) {
                if (word.length() >= LONG_WORD_THRESHOLD) {
                    long_words.add(word);
                } else {
                    short_words.add(word);
                }
            }
        }

        for (String word : long_words) {
            words.add(word);
        }

        int balance_counter = 0;
        int short_size = (100 / LONG_WORDS_PERCENTAGE - 1) * words.size();

        for (String short_word : short_words) {
            if (balance_counter >= short_size) {
                break;
            }
            words.add(short_word);
            balance_counter++;
        }

        if (words.size() < 20) {
            words.clear();
            throw new UndersizeException(
                    "The book you have chosen does not meet the criteria to be turned into a Disctionary, please select another one");
        }
    }

    private void StoreWords() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (String word : words) {
            writer.write(word);
            writer.write("\n");
        }
        writer.close();

    }

}
