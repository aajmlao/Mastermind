import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * subclass of Game that contains methods, such as readPhrases(), randomPhrase(),generateHiddenPhrase(String phrase),
 * getHiddenPhrases(),processGuess(String guess), equals(Object o), toString(), and main()
 */
public abstract class GuessingGame extends Game{
    protected String phrase;
    protected final StringBuilder hiddenPhrase = new StringBuilder();
    protected String file = "phrase.txt";
    protected List<String> phraseList  = readPhrases(file);

    /**
     * read Phrases from file
     * @return phraseList
     */
    protected List<String> readPhrases(String file){
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get(file));
        } catch (IOException e) {
            System.out.println(e);
        }
        return phraseList;
    }

    /**
     * randomly get phrase
     * @return phrase
     */
    protected String randomPhrase(){
        Random rand = new Random();
        int r = rand.nextInt(phraseList.size());
        this.phrase = phraseList.get(r);
        phraseList.remove(r);
        return phrase;
    }

    /**
     * generate Hidden Phrase
     * @param phrase
     */
    protected void generateHiddenPhrase(String phrase){
        hiddenPhrase.setLength(0);//reset StringBuilder
        //convert each char of a string to *
        for(int i = 0; i < phrase.length(); i++){
            if(Character.isLetter(phrase.charAt(i))){ //check if all the characters in the object are letter.
                this.hiddenPhrase.append('*');// replace to * in the same location.
            }else{
                this.hiddenPhrase.append(phrase.charAt(i));
            }
        }
    }

    /**
     * get Hidden Phrases
     * @return String
     */
    protected String getHiddenPhrases(){
        return hiddenPhrase.toString();
    }

    /**
     * process the game
     * @param guess
     * @return boolean
     */

    protected boolean process(String guess){
        boolean isWin = false;
        boolean isContinue = true;
        int index = 0;
        int correctNum = 0;
        int partialNum = 0;
        int wrongNum = 0;
        StringBuilder guessToUpper = new StringBuilder();

        for (int i = 0; i < guess.length(); i++){
            guessToUpper.append(Character.toUpperCase(guess.charAt(i)));
        }
        if (phrase.contentEquals(guessToUpper)){
            isWin = true;
            correctNum = 4;
        }else{
            while(isContinue){
                if (index == phrase.length()){
                    isContinue = false;
                } else if (phrase.indexOf(guessToUpper.charAt(index)) >= 0){
                    if (phrase.charAt(index) == guessToUpper.charAt(index)) {
                        correctNum++;
                    }else{
                        partialNum++;
                    }
                } else if (phrase.indexOf(guessToUpper.charAt(index)) < 0) {
                    wrongNum++;
                }
                index++;
            }
        }
        System.out.println(correctNum+" exact, "+ partialNum+" partial, "+ wrongNum+ " Wrong.");
        return isWin;
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
        GuessingGame that = (GuessingGame) o;
        return Objects.equals(phrase, that.phrase) && Objects.equals(hiddenPhrase, that.hiddenPhrase) && Objects.equals(phraseList, that.phraseList);
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return "GuessingGame{" +
                "phrase='" + phrase + '\'' +
                ", hiddenPhrase=" + hiddenPhrase +
                ", phraseList=" + phraseList +
                '}';
    }

    /**
     * get the phraseList
     * @return List<String>
     */
    protected List<String> getPhraseList(){
        return phraseList;
    }
}
