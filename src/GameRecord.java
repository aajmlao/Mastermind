import java.util.Objects;

/**
 * Set up the contents in GameRecord
 */
public class GameRecord implements Comparable<GameRecord>{
    int score;
    String playerID;

    /**
     * compare the object
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRecord that = (GameRecord) o;
        return score == that.score && Objects.equals(playerID, that.playerID);
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return "GameRecord{" +
                "score=" + score +
                ", playerID='" + playerID + '\'' +
                '}';
    }

    /**
     * compare score
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(GameRecord o) {
        return Integer.compare(this.score, o.score);
    }
}
