public class WordInfoPriority implements Comparable<WordInfoPriority> {
    private String word;
    private int moves;
    private int priority;
    private String history;

    public WordInfoPriority(String word, int moves, int estimatedWork) {
        this.word = word;
        this.moves = moves;
        this.history = word;
        this.priority = moves + estimatedWork;
    }

    public WordInfoPriority(String word, int moves, int estimatedWork, String history) {
        this.word = word;
        this.moves = moves;
        this.history = history;
        this.priority = moves + estimatedWork;
    }

    public String getWord() {
        return this.word;
    }

    public int getMoves() {
        return this.moves;
    }

    public String getHistory() {
        return this.history;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public String toString() {
        return String.format("Word: %s, Moves: %d, Priority: %d, History: [%s]",
                word, moves, priority, history);
    }

    @Override
    public int compareTo(WordInfoPriority other) {
        return Integer.compare(this.priority, other.priority);
    }
}