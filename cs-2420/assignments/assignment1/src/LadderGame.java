import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class LadderGame {
    private ArrayList<ArrayList<String>> wordTable = new ArrayList<>();
    private ArrayList<ArrayList<String>> tableCopy;

    public LadderGame(String dictionaryFile) {
        readDictionary(dictionaryFile);
        // Create copy of wordTable at the begining
        tableCopy = new ArrayList<>(wordTable.size());
        for (ArrayList<String> wordList : wordTable) {
            ArrayList<String> wordListCopy = new ArrayList<>(wordList);
            tableCopy.add(wordListCopy);
        }
    }

    public void play(String start, String end) {
        int totalenqueues = 0;
        // Check if the start and end words are of the same length
        if (start.length() != end.length()) {
            System.out.println("Start and end words must have the same length.");
            return;
        }

        // Check if the start and end words are in the dictionary
        int wordLength = start.length();
        ArrayList<String> wordsLength = wordTable.get(wordLength);
        if (!wordsLength.contains(start) || !wordsLength.contains(end)) {
            System.out.println("Start or end word is not in the dictionary.");
            return;
        }

        // Create a copy of the dictionary
        tableCopy = new ArrayList<>(wordTable.size());
        for (ArrayList<String> wordList : wordTable) {
            ArrayList<String> wordListCopy = new ArrayList<>(wordList);
            tableCopy.add(wordListCopy);
        }

        // Initialize a custom Queue to perform breadth-first search
        Queue<WordInfo> queue = new Queue<>();
        queue.enqueue(new WordInfo(start, 0, start));
        totalenqueues++;

        while (!queue.isEmpty()) {
            WordInfo currentLadder = queue.dequeue();

            // Find words that are one away from the last word in the current ladder
            ArrayList<String> oneAwayWords = oneAway(currentLadder.getWord(), true);

            // Extend the ladder and enqueue it for further exploration
            for (String word : oneAwayWords) {
                WordInfo newLadder = new WordInfo(word, currentLadder.getMoves() + 1, currentLadder.getHistory() + " " + word);

                // If the current ladder's last word is equal to the end word, a ladder is found
                if (word.equals(end)) {
                    System.out.print(start + " -> " + end + " : " + newLadder.getMoves() + " Moves ");
                    System.out.println("[" + newLadder.getHistory() + "] total enqueues " + totalenqueues);
                    return;
                }

                queue.enqueue(newLadder);
                totalenqueues++;
            }
        }

        // If the loop completes without finding a ladder, report that no ladder exists
        System.out.println("No word ladder found.");
    }

    public ArrayList<String> oneAway(String word, boolean withRemoval) {
        ArrayList<String> oneAwayWords = new ArrayList<>();
        int wordLength = word.length();

        // Find the ArrayList containing words of the same length as the input word in dictionaryCopy
        ArrayList<String> wordsOfSameLengthCopy = new ArrayList<>(tableCopy.get(wordLength));

        for (String candidate : wordsOfSameLengthCopy) {
            // Check if candidate and word have exactly one differing character
            if (diff(candidate, word)) {
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
    private boolean diff(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
                if (diff > 1) {
                    // More than one differing character
                    return false;
                }
            }
        }
        // Exactly one differing character
        return diff == 1;
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