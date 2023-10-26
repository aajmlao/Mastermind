import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * subclass of WheelOfFortune and implements WheelOfFortunePlayer that contains WheelOfFortuneAIGame(),
 * WheelOfFortuneAIGame(String player), WheelOfFortuneAIGame(List<String> players),
 * playNext(), play(), nextGuess(), playerId(), reset(), setMaxGuess(), getGuess() ,
 * equals(Object o), toString(), and main()
 */
public class WheelOfFortuneAIGame extends WheelOfFortune implements WheelOfFortunePlayer{
    private int maxGuess = 15;
    private int index = 0;
    private int indexOfPlayer = 0;
    private List<String> players = new ArrayList<>();
    private int indexOfPhrase = 0;
    /**
     * three constructors
     */
    public WheelOfFortuneAIGame(){
        players.add("AA8687");
    }
    public WheelOfFortuneAIGame(String player){
        players.add(player);
    }
    public WheelOfFortuneAIGame(List<String> players){
       this.players = players;
    }

    /**
     * check the playNext logic
     * @return boolean
     */
    @Override
    public boolean playNext() {
        int sizeOfPhrase = getPhraseList().size(); // keep subtracting.
        int sizeOfPlayers = players.size()-1; // there were a player processed already.

        boolean isNotFinish = true;
        if (sizeOfPhrase == indexOfPhrase){
            if(sizeOfPlayers == indexOfPlayer){
                isNotFinish = false;
            }else{
                super.readPhrases("phrases.txt");
                indexOfPlayer++;

            }
        }
        reset();
        return isNotFinish;
    }

    /**
     * play the game
     * @return GameRecord
     */
    @Override
    public GameRecord play() {
        GameRecord gameRecord = new GameRecord();
        boolean outOfRange = false;
        boolean isWin = false;
        String phrase = super.randomPhrase();
        super.generateHiddenPhrase(phrase);
        gameRecord.playerID = playerId();
        System.out.println(super.getHiddenPhrases());

        while(!outOfRange){
            char ch = getGuess();
            super.processGuess(ch, maxGuess);
            if(phrase.equals(super.getHiddenPhrases())){
                outOfRange = true;
                isWin = true;
            } else if (super.getNumGuess() == maxGuess){
                break;
            }
        }
        if (isWin){
            gameRecord.score++;
        }
        System.out.println(gameRecord.playerID +" got "+gameRecord.score+ " point.");
        return gameRecord;
    }

    /**
     * logic of game play
     * @return char
     */
    @Override
    public char nextGuess() {
        String botGuess;
        int phraseLength = getPhraseList().size();
        if (phraseLength <= 5){
            botGuess = "aniesorltudpmhcbkgywfvjzxq";
        }else if (phraseLength <= 12) {
            botGuess = "eisntarolcpfumvdghybzkwxqj";
        } else {
            botGuess = "ioetrsanclphumydgbzvfkxjqw";
        }
        return botGuess.charAt(index);
    }

    /**
     * get player ID
     * @return String
     */
    @Override
    public String playerId(){
        return players.get(indexOfPlayer);
    }

    /**
     * reset some values
     */
    @Override
    public void reset() {
        setNumGuess(0);
        setMissGuess(0);
        setMaxGuess();
        resetPreviousGuesses();
        index=0;
    }

    /**
     * set maxGuess to 15
     */
    private void setMaxGuess(){
        this.maxGuess = 15;
    }

    /**
     * get Guess
     * @return char
     */
    @Override
    protected char getGuess() {
        char nextGuess = nextGuess();
        index++;
        return nextGuess;
    }

    /**
     * compare object
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WheelOfFortuneAIGame that = (WheelOfFortuneAIGame) o;
        return maxGuess == that.maxGuess && Objects.equals(players, that.players);
    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        return "WheelOfFortuneAIGame{" +
                "maxGuess=" + maxGuess +
                ", index=" + index +
                ", players=" + players +
                '}';
    }

    public static void main(String[] args){
        List<String> playerList = new ArrayList<>();
        try {
            playerList = Files.readAllLines(Paths.get("players.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        WheelOfFortuneAIGame wf = new WheelOfFortuneAIGame(playerList);
        AllGamesRecord record = wf.playAll();

        System.out.println("\nThis is the average for all nine games: "+ record.average());
        System.out.println("This is the average: "+record.average("KK8687") +" for KK8687");
        System.out.println("This is the average: "+record.average("JJ8687")+ " for JJ8687");
        System.out.println("This is the average: "+record.average("AA8687")+" for AA8687");
        for (GameRecord gamesRecord: record.highGameList(9)){
            System.out.println(gamesRecord.playerID);
            System.out.println(gamesRecord.score);
        }

    }
}
