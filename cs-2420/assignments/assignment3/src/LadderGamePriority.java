import java.util.ArrayList;

public class LadderGamePriority extends LadderGame {


    public LadderGamePriority(String dictionaryFile) {
        super(dictionaryFile);
    }

    @Override
    public void play(String start, String end) {
        int totalEnqueues = 0;
        AVLTree<WordInfoPriority> queue = new AVLTree<>();
        AVLTree<WordInfo> usedWords = new AVLTree<>();

        int initialEstimatedWork = estimateWork(start, end);
        queue.insert(new WordInfoPriority(start, 0, initialEstimatedWork));
        totalEnqueues++;

        while (!queue.isEmpty()) {
            WordInfoPriority current = queue.deleteMin();

            if (current.getWord().equals(end)) {
                System.out.println("Seeking A* solution from " + start + " -> " + end);
                System.out.println("  [" + current.getHistory() + "] total enqueues " + totalEnqueues);
                return;
            }

            ArrayList<String> oneAwayWords = oneAway(current.getWord(), false);

            for (String word : oneAwayWords) {
                int newMoves = current.getMoves() + 1;
                int estimatedWork = estimateWork(word, end);

                WordInfo info = usedWords.find(new WordInfo(word, 0));
                if (info == null || newMoves < info.length) {
                    if (info != null) {
                        info.length = newMoves;
                    } else {
                        usedWords.insert(new WordInfo(word, newMoves));
                    }

                    WordInfoPriority newNode = new WordInfoPriority(word, newMoves, estimatedWork, current.getHistory() + " " + word);
                    queue.insert(newNode);
                    totalEnqueues++;
                }
            }
        }
        System.out.println("No word ladder found.");
    }

    private int estimateWork(String current, String end) {
        return diff(current, end);
    }


    private static class WordInfo implements Comparable<WordInfo> {
        String word;
        int length;

        WordInfo(String word, int length) {
            this.word = word;
            this.length = length;
        }

        @Override
        public int compareTo(WordInfo other) {
            return this.word.compareTo(other.word);
        }
    }
}