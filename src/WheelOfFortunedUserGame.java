import java.util.Objects;
import java.util.Scanner;

/**
 * subclass of WheelOfFortune that contains playNext(), play(), getGuess(), reset(), setMaxGuess(),
 * equals(Object o), toString(), and main().
 */
public class WheelOfFortunedUserGame extends WheelOfFortune{
    private Scanner scanner = new Scanner(System.in);
    private int maxGuess = 15;
    private String playerId = null;
    private boolean isFirstPlay = true;
    //private int index = 0;

    /**
     * check the playNext logic
     * @return boolean
     */
    @Override
    public boolean playNext() {
        Scanner scanner = new Scanner(System.in);
        boolean contue = false;
        boolean loop = true;
        char chr;
        int size = phraseList.size();

        if(size != 0){
            System.out.println("Do you want to continue?(y/n)");
            while(loop){
                chr = scanner.nextLine().charAt(0);
                if(chr =='y'||chr=='Y'){
                    contue = true;
                    loop = false;
                } else if (chr=='n'||chr=='N') {
                    loop = false;
                } else{
                    System.out.println("Invalid Input. Please Enter y or n: ");
                }
            }
            //index++;

        }
        reset();
        return contue;
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
        if(isFirstPlay){
            System.out.println("Enter your Player ID: ");
            playerId = scanner.next();
            isFirstPlay = false;
        }
        gameRecord.playerID = playerId;
        System.out.println(super.getHiddenPhrases());
        while(!outOfRange){
            char ch = getGuess();
            super.processGuess(ch, maxGuess);
            if(phrase.equals(super.getHiddenPhrases())){
                outOfRange = true;
                isWin = true;
            } else if (super.getNumGuess() == maxGuess) {
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
     * user guess
     * @return char
     */
    @Override
    protected char getGuess() {
        String guessString = null;
        boolean tooLong = true;
        Scanner scanner = new Scanner(System.in);
        while(tooLong){
            System.out.println("Please enter your guess: ");
            guessString = scanner.next();
            if(guessString.length()>1){
                System.out.println("Only one character at a time. Try a again.");
            }// if input length is longer than 1 character.
            else{
                tooLong = false;
            }
        }
        return guessString.charAt(0);
    }

    /**
     * reset some values
     */
    private void reset() {
        setNumGuess(0);
        setMissGuess(0);
        setMaxGuess();
        resetPreviousGuesses();
    }

    /**
     * set Max Guess.
     */
    private void setMaxGuess(){
        this.maxGuess = 15;
    }

    /**
     * compare object
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WheelOfFortunedUserGame that = (WheelOfFortunedUserGame) o;
        return Objects.equals(playerId, that.playerId);
    }

    /**
     * toSring
     * @return String
     */
    @Override
    public String toString() {
        return "WheelOfFortunedUserGame{" +
                "maxGuess=" + maxGuess +
                ", playerId='" + playerId + '\'' +
                '}';
    }

    public static void main(String[] args){
        WheelOfFortunedUserGame wf = new WheelOfFortunedUserGame();
        AllGamesRecord record = wf.playAll();

        System.out.println("This is the average of all played games: "+record.average());
        for (GameRecord gamesRecord: record.highGameList(3)){
            System.out.println(gamesRecord.playerID);
            System.out.println(gamesRecord.score);
        }
    }
}
