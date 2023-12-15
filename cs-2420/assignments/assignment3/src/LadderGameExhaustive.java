import java.util.ArrayList;

public class LadderGameExhaustive extends LadderGame{
    public LadderGameExhaustive(String dictionaryFile) {
        super(dictionaryFile);
    }

    @Override
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
                    System.out.println("Seeking exhaustive solution from " + start + " -> " + end);
                    System.out.println("  [" + newLadder.getHistory() + "] total enqueues " + totalenqueues);
                    return;
                }

                queue.enqueue(newLadder);
                totalenqueues++;
            }
        }

        // If the loop completes without finding a ladder, report that no ladder exists
        System.out.println("No word ladder found.");
    }
}

