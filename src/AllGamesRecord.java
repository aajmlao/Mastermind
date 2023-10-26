import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * AllGamesRecord class that contains methods, such as add(), average(),average(String playerID)
 * highGameList(int n),highGameList(String playerID,int n), toString(), and equals(Object o).
 */
public class AllGamesRecord {
    protected ArrayList<GameRecord> allGameRecord = new ArrayList<>();

    /**
     * add gameRecord to the ArrayList of AllGameRecord.
     * @param gameRecord
     */
    public void add(GameRecord gameRecord){
        allGameRecord.add(gameRecord);
    }

    public float average(){
        float sum = 0;
        for (GameRecord gameRecord : allGameRecord) {
            sum += gameRecord.score;
        }
        return sum/allGameRecord.size();
    }

    /**
     * Calculate the average of all players
     * @param playerID
     * @return average of specific player
     */
    public float average(String playerID){
        float sum = 0;
        int i = 0;
        for (GameRecord gameRecord: allGameRecord){
            if(gameRecord.playerID.equals(playerID)){
                sum += gameRecord.score;
                i++;
            }
        }
        return sum/i;
    }

    /**
     * sorted highGameList and get the nth high records
     * @param n
     * @return highGameRecord
     */
    public ArrayList<GameRecord> highGameList(int n){
        Collections.sort(allGameRecord);
        ArrayList<GameRecord> highGameRecord = new ArrayList<>();
        for(int i = allGameRecord.size()-1; (allGameRecord.size() - n) <= i; i--){
            highGameRecord.add(allGameRecord.get(i));
        }
        return highGameRecord;
    }

    /**
     * sorted highGameList and get the nth high records for specific player
     * @param playerID
     * @param n
     * @return
     */
    public ArrayList<GameRecord> highGameList(String playerID,int n){
        Collections.sort(allGameRecord);
        ArrayList<GameRecord> playerHighGameRecord = new ArrayList<>();
        ArrayList<GameRecord> newPlayerHighGameRecord = new ArrayList<>();
        for (int i = 0; i < allGameRecord.size();i++){

            if (this.allGameRecord.get(i).playerID.equals(playerID)){
                playerHighGameRecord.add(allGameRecord.get(i));
            }
        }

        for (int i = playerHighGameRecord.size()-1; playerHighGameRecord.size()-n <= i ;i--){
            newPlayerHighGameRecord.add(playerHighGameRecord.get(i));
        }
        return newPlayerHighGameRecord;
    }

    /**
     * toString allGameRecord.
     * @return
     */
    @Override
    public String toString() {
        return "AllGamesRecord{" +
                "allGameRecord=" + allGameRecord +
                '}';
    }

    /**
     * compare allGameRecord
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllGamesRecord record = (AllGamesRecord) o;
        return Objects.equals(allGameRecord, record.allGameRecord);
    }


}
