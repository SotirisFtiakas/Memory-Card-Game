/**
 * The class whose objects are the cards of the game.
 * Each object of this class has a content(a capital letter of the english alphabet)
 * and the variable 'exist' that informs us whether this card is still on the table during the game.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
public class Card {
    private Character content;
    private boolean exist;

    public Card() {
        //Empty Constructor
    }

    /**
     * The constructor of the class 'Card' defines the card's content and
     * makes the variable 'exist' true as the card exists when it is created.
     *
     * @param content a character of a capital latin letter
     */
    public Card(Character content) {
        this.content = content;
        exist = true;
    }

    /**
     * Method that changes the variable 'exist' of an object type 'Card'.
     *
     * @param exist a boolean type variable to indicates if the card is still on the table
     */
    public void setExist(boolean exist) {
        this.exist = exist;
    }

    /**
     * Method that returns if the object type 'Card' exists or not.
     *
     * @return a boolean, the variable 'exist'
     */
    public boolean getExist() {
        return exist;
    }

    /**
     * Method that returns the content of an object type 'Card', returning the variable 'content'.
     *
     * @return a capital letter of the latin alphabet
     */
    public Character getContent() {
        return content;
    }
}

