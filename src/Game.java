import java.util.Objects;

/**
 * abstract class Game
 */
public abstract class Game {
    AllGamesRecord allGamesRecord = new AllGamesRecord();
    /**
     * play all games
     * @return allGamesRecord
     */
    public AllGamesRecord playAll(){

        boolean isContinue = true;
        while (isContinue) {
            allGamesRecord.add(play());
            isContinue = playNext();
        }
        return allGamesRecord;
    }

    /**
     * requirement of playNext
     * @return a boolean
     */
    public abstract boolean playNext();

    /**
     * requirement of play
     * @return GameRecord
     */
    public abstract GameRecord play();

    /**
     * Compare object
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(allGamesRecord, game.allGamesRecord);
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return "Game{" +
                "allGamesRecord=" + allGamesRecord +
                '}';
    }
}

