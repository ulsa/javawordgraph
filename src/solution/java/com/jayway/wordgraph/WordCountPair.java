package com.jayway.wordgraph;

public class WordCountPair {
    // @BEGIN_VERSION SORT_COUNTED_WORDS
    private final String word;

    private final int count;

    public WordCountPair(String word, int count) {
        super();
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + count;
        result = prime * result + ((word == null) ? 0 : word.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WordCountPair other = (WordCountPair) obj;
        if (count != other.count)
            return false;
        if (word == null) {
            if (other.word != null)
                return false;
        }
        else if (!word.equals(other.word))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[" + word + " " + count + "]";
    }
    // @END_VERSION SORT_COUNTED_WORDS
}
