import java.util.Objects;
import java.util.Scanner;

/**
 * subclass of GuessingGame that contains methods, such as playNext(), play(), toString(),
 * equals(Object o), and main()
 */
public class Mastermind extends GuessingGame {
    private Scanner scanner = new Scanner(System.in);
    private boolean isWin;
    private int index = 0;
    private boolean isFirstTime = true;
    private String playerID;

    /**
     * check the play next logic
     * @return boolean
     */
    @Override
    public boolean playNext() {
        Scanner scanner = new Scanner(System.in);
        boolean contue = false;
        boolean loop = true;
        char chr;
        int size = phraseList.size();

        if(size > index){
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
            index++;
        }
        return contue;
    }

    /**
     * play the game
     * @return GameRecord
     */
    @Override
    public GameRecord play() {
        GameRecord gameRecord = new GameRecord();
        String randomPhrase = super.randomPhrase();
        super.generateHiddenPhrase(randomPhrase);

        if(isFirstTime){
            System.out.print("Enter Player ID: ");
             playerID = scanner.nextLine();
            isFirstTime = false;
        }
        gameRecord.playerID = playerID;
        int maxGuess = 6;
        int numGuess = 0;
        boolean isNotFinish = true;

        while (isNotFinish && numGuess < maxGuess){
            System.out.println("Here is your phrase: "+getHiddenPhrases());
            System.out.println("Red, Green, Blue, Yellow, Orange, and Purple (R,G,B,Y,O,P)");
            System.out.print("Input Your Answer: ");
            isWin = process(scanner.nextLine());
            if(isWin){
                gameRecord.score++;
                isNotFinish = false;
            }
            numGuess++;
            System.out.println("Chance left: "+ (maxGuess-numGuess));
        }
        if(isWin){
            System.out.println("You Win.");
            System.out.println(gameRecord.playerID+ " got "+gameRecord.score);
        } else {
            System.out.println("You lose");
        }
        return gameRecord;
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return "Mastermind{" +
                "playerID='" + playerID + '\'' +
                '}';
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
        Mastermind that = (Mastermind) o;
        return Objects.equals(playerID, that.playerID);
    }

    public static void main(String[] args) {
        Mastermind mastermind = new Mastermind();
        AllGamesRecord record = mastermind.playAll();
        System.out.println("This is the average of all played games: "+record.average());
        for (GameRecord gamesRecord: record.highGameList(2)){
            System.out.println(gamesRecord.playerID);
            System.out.println(gamesRecord.score);
        }
    }
}