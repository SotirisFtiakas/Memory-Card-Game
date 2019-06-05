/**
 * A class that saves the player's status.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
public class Player {

    private int correctGuesses;
    private int tries;
    private int wins;
    private String name;

    /**
     * The constructor of class 'Player' that initializes the two variables in 0.
     */
    public Player(String name){
        this.name = name;
        correctGuesses = 0;
        tries = 0;
        wins = 0;
    }

    void setWins(int wins){ this.wins = wins; }

    void addWin() { wins++;}

    int getWins() { return wins; }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){ return name; }

    /**
     * Method to increase the variable 'tries' by one.
     */
    void addTries() { tries++; }

    void setTries(int tries) { this.tries = tries; }

    /**
     * Method that returns the number of tries of the player.
     * @return the variable 'tries'
     */
    int getTries() { return tries; }

    /**
     * Method to increase the variable 'correctGuesses' by one.
     */
    void addCorrectGuesses() { correctGuesses++; }

    /**
     * Method that returns the number of correct guesses of the player.
     * @return the variable 'correctGuesses'
     */
    int getCorrectGuesses() { return correctGuesses; }
}