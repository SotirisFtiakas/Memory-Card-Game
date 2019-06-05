import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * The class that creates the table of the game.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
public class Table {

    private int lines;
    private int rows;
    private String printingLine1;
    private String printingLine2;
    private String printingLine3;
    private ArrayList<Character> contents;
    private HashMap<Integer,Card> coordinatesOfCards;

    public Table() {
        //Empty Constructor
    }

    /**
     * The constructor of class 'Table', which takes as arguments the number of lines and rows.
     * Also, it initializes the Strings 'printingLine1', 'printingLine2' & 'printingLine3' (components
     * used to print the table on the screen) and at the same time initializes an ArrayList, which originally
     * will be used to store all the contents of the cards, and a HashMap, which will afterwards save the
     * coordinates and contents of each card(key=coordinates, value=object of class 'Card').
     *
     * @param lines the number of lines in the table
     * @param rows the number of rows in the table
     */
    public Table(int lines,int rows) {
        this.lines = lines;
        this.rows = rows;
        printingLine1 = "";
        printingLine2 = "";
        printingLine3 = "";
        contents = new ArrayList();
        coordinatesOfCards = new HashMap();
        makeTable();
    }


    /**
     * Method that fills the HashMap, creating therefore the table. Depending on the values of the lines and rows,
     * using the ASCII code and an if loop, at first the ArrayList 'contents' is being filled up with 2 or 3 copies of
     * each card. After making use of the method 'shuffle' of the class 'Collections' the ArrayList is being mixed up
     * and using a for-each loop, one by one the contents of the ArrayList create an object type 'Card' and finally
     * they are placed in the HashMap. The keys must be checked not to cross the boundaries and be adjusted to
     * the allowable values.
     */
    private void makeTable() {
        int key=11;
        if(lines==4 && rows==6) {
            for(int i=65;i<77;i++) {
                contents.add((char)i);
                contents.add((char)i);
            }
            Collections.shuffle(contents);
            for(Character ch : contents) {
                if(key%10==7) {
                    key += 4;
                }
                Card aCard = new Card(ch);
                coordinatesOfCards.put(key, aCard);
                key++;
            }
        }
        if(lines==6 && rows==8) {
            for(int i=65;i<89;i++) {
                contents.add((char)i);
                contents.add((char)i);
            }
            Collections.shuffle(contents);
            for(Character ch : contents) {
                if(key%10==9) {
                    key += 2;
                }
                Card aCard = new Card(ch);
                coordinatesOfCards.put(key, aCard);
                key++;
            }
        }
        else if(lines==6 && rows==6) {
            for(int i=65;i<77;i++) {
                contents.add((char)i);
                contents.add((char)i);
                contents.add((char)i);
            }
            Collections.shuffle(contents);
            for(Character ch : contents) {
                if(key%10==7) {
                    key += 4;
                }
                Card aCard = new Card(ch);
                coordinatesOfCards.put(key, aCard);
                key++;
            }
        }
    }

    /**
     * Method to print the table on the screen (command line). The method 'showTable' gets three arguments.
     * If all the arguments equal 0, the method prints the table containing the remaining cards (the method checks
     * which of the cards exist) with a question mark (?) on top. In any other case, the table is printed having
     * replaced the guessed cards with the content of the cards instead of the question mark. Furthermore, the method
     * prints the names of the lines(right of the table) and rows(top of the table) on their axes respectively.
     * Giving values in only the two first parameters, and the last one 0, is for when the game mode needs two cards
     * max in each turn.
     *
     * @param coor1 the coordinate of the first card
     * @param coor2 the coordinate of the second card
     * @param coor3 the coordinate of the third card, if it is needed
     */
    public void showTable(int coor1,int coor2, int coor3) {
        int i,j,q;
        Character symbol = '?';
        for(q=1;q<rows+1;q++) {
            System.out.print("   "+q+"    ");
        }
        System.out.println("");
        for(i=1;i<lines+1;i++) {
            for(j=1;j<rows+1;j++) {
                if(coor1==i*10+j || coor2==i*10+j || coor3==i*10+j){
                    symbol = coordinatesOfCards.get(i*10+j).getContent();
                }
                if(coordinatesOfCards.get(i*10+j).getExist()) {
                    printingLine1 += "|'''''| ";
                    printingLine2 += "|  "+symbol+"  | ";
                    printingLine3 += "|,,,,,| ";
                    symbol = '?';
                }
                else {
                    printingLine1 += "        ";
                    printingLine2 += "        ";
                    printingLine3 += "        ";
                    symbol = '?';
                }
            }
            printingLine2 += i;
            System.out.println(printingLine1+"\n" + printingLine2 +"\n" + printingLine3 + "\n");
            printingLine1 = "";
            printingLine2 = "";
            printingLine3 = "";
        }
    }

    /**
     * Method that returns the variable lines.
     *
     * @return the number of lines in this game mode
     */
    public int getLines() {
        return lines;
    }

    /**
     * Method that returns the variable rows.
     *
     * @return the number of rows in this game mode
     */
    public int getRows() {
        return rows;
    }

    /**
     * Method the collects the card from the table.
     * @param coor the coordination of the card to be collected
     */
    public void cardCollector(int coor) {
        coordinatesOfCards.get(coor).setExist(false);
    }

    /**
     * Method that returns if the card is still on the table or not.
     * @param coor the coordination of the card (on the table)
     * @return true if the card is on the table, otherwise false
     */
    public boolean getValueExist(int coor) {
        return coordinatesOfCards.get(coor).getExist();
    }

    /**
     * Method that returns the content of a object type 'Card'.
     * @param coor the coordination of the card (on the table)
     * @return a capital letter of the latin alphabet
     */
    public int getValueContent(int coor) {
        return coordinatesOfCards.get(coor).getContent();
    }
}

