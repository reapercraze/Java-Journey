import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class LadderGame {
    protected ArrayList<ArrayList<String>> wordTable = new ArrayList<>();
    protected ArrayList<ArrayList<String>> tableCopy;

    public LadderGame(String dictionaryFile) {
        readDictionary(dictionaryFile);
        // Create copy of wordTable at the begining
        tableCopy = new ArrayList<>(wordTable.size());
        for (ArrayList<String> wordList : wordTable) {
            ArrayList<String> wordListCopy = new ArrayList<>(wordList);
            tableCopy.add(wordListCopy);
        }
    }

    public abstract void play(String start, String end);

    public ArrayList<String> oneAway(String word, boolean withRemoval) {
        ArrayList<String> oneAwayWords = new ArrayList<>();
        int wordLength = word.length();

        // Find the ArrayList containing words of the same length as the input word in dictionaryCopy
        ArrayList<String> wordsOfSameLengthCopy = new ArrayList<>(tableCopy.get(wordLength));

        for (String candidate : wordsOfSameLengthCopy) {
            // Check if candidate and word have exactly one differing character
            if (diff(candidate, word) == 1) {
                oneAwayWords.add(candidate);

                // Remove the candidate word from dictionaryCopy if withRemoval is true
                if (withRemoval) {
                    tableCopy.get(wordLength).remove(candidate);
                }
            }
        }

        return oneAwayWords;
    }

    // To check if two words are one letter away from each other
    protected int diff(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return -1;
        }

        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }

    public void listWords(int length, int howMany) {
        // length must be the length of the table or smaller
        if (wordTable.size() < length) {
            System.out.println("No words with that length");
            return;
        }
        // Print the list of howMany words
        ArrayList<String> list = wordTable.get(length);
        for (int i = 0; i < howMany; i++) {
                System.out.println(list.get(i));
        }

    }

    /*
        Reads a list of words from a file, putting all words of the same length into the same array.
     */
    private void readDictionary(String dictionaryFile) {
        File file = new File(dictionaryFile);
        ArrayList<String> allWords = new ArrayList<>();


        // Track the longest word, because that tells us how big to make the array.
        int longestWord = 0;
        try (Scanner input = new Scanner(file)) {
            // Start by reading all the words into memory.
            while (input.hasNextLine()) {
                String word = input.nextLine().toLowerCase();
                allWords.add(word);
                longestWord = Math.max(longestWord, word.length());
            }

            for (int i = 0; i < longestWord; i++) {
                ArrayList<String> row = new ArrayList<>();
                for (String word: allWords) {
                    if (i == word.length()) {
                        row.add(word);
                    }
                }
                wordTable.add(row);
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary: " + ex);
        }
    }
}