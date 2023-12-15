import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class WritePoetry {
    public String writePoem(String file, String startWord, int length, boolean printHashtable) {
        Random rnd = new Random();

        HashTable table = makeHashTable(file);
        StringBuilder sb = new StringBuilder();

        sb.append(startWord + " ");
        WordFreqInfo wordInfo = (WordFreqInfo) table.find(startWord);
        // Get the follow word based on a random num and the follow count
        for (int i = 0; i < length; i++) {
            int count = rnd.nextInt(wordInfo.getOccurCount());
            String followWord = wordInfo.getFollowWord(count);

            // Deal with punctuation
            if (!Character.isLetter(followWord.charAt(followWord.length() - 1))) {
                sb.deleteCharAt(sb.length() - 1);
                sb.append(followWord + "\n");
            } else {
                sb.append(followWord + " ");
            }
            wordInfo = (WordFreqInfo) table.find(followWord);
        }

        char lastChar = sb.charAt(sb.length() - 1);

        if (Character.isLetter(lastChar)) {
            // If the last character is a letter, append a period and a new line.
            sb.append(".\n");
        } else if (Character.isWhitespace(lastChar)) {
            // If the last character is a space, remove it and append a period and a new line.
            sb.deleteCharAt(sb.length() - 1);
            sb.append(".\n");
        }

        // Print HashTable if true
        if (printHashtable) {
            System.out.println(table.toString(table.size()));
        }
        return sb.toString();
    }

    private HashTable<String, WordFreqInfo> makeHashTable(String filename) {
        File file = new File("src/" + filename);
        HashTable<String, WordFreqInfo> table = new HashTable<>();

        try (Scanner input = new Scanner(file)) {
            // Start by reading all the words into memory.
            String prevWord = "";
            while (input.hasNextLine()) {
                // Read line by line
                String line = input.nextLine();
                // Get list of words
                String[] words = line.split("[ ]");

                // For each word check to see if in hash table to add or update follow count
                for (String word : words) {
                    if (prevWord.equals("")) {
                        prevWord = word;
                        continue;
                    }
                    word = word.toLowerCase();
                    if (word.length() > 0) {

                        // Check for punctuation
                        char lastChar = word.charAt(word.length() - 1);
                        if (Character.isLetter(lastChar)) {
                            if (table.contains(prevWord)) {
                                WordFreqInfo wordFreq = table.find(prevWord);
                                wordFreq.updateFollows(word);
                            } else {
                                WordFreqInfo wordFreq = new WordFreqInfo(prevWord, 0);
                                wordFreq.updateFollows(word);
                                table.insert(prevWord, wordFreq);
                            }
                            prevWord = word;
                        } else {
                            word = word.substring(0, word.length()-1);
                            if (table.contains(prevWord)) {
                                WordFreqInfo wordFreq = table.find(prevWord);
                                wordFreq.updateFollows(word);
                            } else {
                                WordFreqInfo wordFreq = new WordFreqInfo(prevWord, 0);
                                wordFreq.updateFollows(word);
                                table.insert(prevWord, wordFreq);
                            }
                            prevWord = word;
                            String punct = Character.toString(lastChar);
                            if (table.contains(prevWord)) {
                                WordFreqInfo wordFreq = table.find(prevWord);
                                wordFreq.updateFollows(punct);
                            } else {
                                WordFreqInfo wordFreq = new WordFreqInfo(prevWord, 0);
                                wordFreq.updateFollows(punct);
                                table.insert(prevWord, wordFreq);
                            }
                            prevWord = punct;
                        }
                    }
                }


            }
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary: " + ex);
        }

    return table;
    }
}
