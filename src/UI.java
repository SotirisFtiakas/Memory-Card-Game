import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The purpose of this class is to allow interaction between the user and the game.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
public class UI {

    private String typeOfGame;
    private Table aTable;
    private int x, y, coor1, coor2, coor3;
    private Scanner scanner;
    private int game;

    /**
     * The constructor of class 'UI'. First, the constructor shows the starting messages of the game and scans the type
     * of the game. At the same time, it makes sure that the user-input is correct. If it is incorrect, a message appears
     * telling the user that his input was incorrect, thus he needs to give the game-type anew. Once the game-type has been
     * declared, the rows and the columns of the table are being initialized accordingly.
     */
    public UI(){
        scanner = new Scanner(System.in);
        System.out.println("|            MEMORY CARD GAME             |\n-------------------------------------------\n|   Basic    |    Double    |    Triple   |");
        System.out.println("-------------------------------------------\n|            Choose game type:            |");
        System.out.print("                   ");
        typeOfGame = (scanner.nextLine()).toLowerCase();
        typeOfGame = typeOfGame.replaceAll("\\s","");
        while(!(typeOfGame.equals("basic") || typeOfGame.equals("double") || typeOfGame.equals("triple"))) {
            System.out.println("Wrong game type. Choose again: ");
            System.out.print("                   ");
            typeOfGame = (scanner.nextLine()).toLowerCase();
            typeOfGame = typeOfGame.replaceAll("\\s","");
        }
        if(typeOfGame.equals("basic")){
            aTable = new Table(4,6);
            game = 12;

        }
        else if(typeOfGame.equals("double")) {
            aTable = new Table(6,8);
            game = 24;
        }
        else{
            aTable = new Table(6,6);
            game = 12;
        }
        aTable.showTable(0,0,0);
    }

    /**
     * Method that returns the variable game.
     *
     * @return the number of same cards in this game mode
     */
    public int getGame() {
        return game;
    }

    /**
     * Method that returns the coordinates of a card in one single integer. It takes as an argument the order-number of
     * the coordinate that is given each time, and scans the x and y parameters. It then makes sure that the parameters
     * are well defined, and if not, it asks the user to type the parameters again. The main error-recognition patterns
     * are: Out of bounds, Empty Slot, Wrong Input. When the parameter inputs are correct, the method returns the final
     * integer-coordinate which consists of both the x and y parameters.
     *
     * @param order is the order-number of the coordinates given (first, second or third card's)
     * @return an int
     */
    public int findCoordinate( int order ){
        String again = "";
        boolean valid = false, bounds, empty, nowrong;
        String coorOrder;
        if (order == 1)
            coorOrder = "first";
        else if (order == 2)
            coorOrder = "second";
        else
            coorOrder = "third";

        while (!valid){
            try {
                do {
                    System.out.println("Give " + coorOrder + " card's coordinates " + again + "(2 space-seperated values):");
                    Scanner tempScan = new Scanner(System.in);
                    bounds = true;
                    nowrong = true;
                    x = tempScan.nextInt();
                    y = tempScan.nextInt();
                    String clearBuffer = tempScan.nextLine();
                    clearBuffer = clearBuffer.replaceAll("\\s","");
                    if(!clearBuffer.equals("")){
                        System.out.println("WRONG");
                        nowrong = false;
                    }
                    empty = true;
                    if(nowrong) {
                        if ((x >= 1 && x <= aTable.getLines()) && (y >= 1 && y <= aTable.getRows())) {
                            if (!aTable.getValueExist(x * 10 + y)) {
                                System.out.println("Empty Slot");
                                empty = false;
                                again = "again ";
                            }
                        }

                        if (x < 1 || x > aTable.getLines()) {
                            System.out.println("First coordinate is out of bounds");
                            bounds = false;
                            again = "again ";
                        }
                        if (y < 1 || y > aTable.getRows()) {
                            System.out.println("Second coordinate is out of bounds");
                            bounds = false;
                            again = "again ";
                        }
                    }
                } while(!bounds || !empty || !nowrong);
                valid = true;
            }

            catch (InputMismatchException e) {
                System.out.println("Wrong Input!");
                again = "again ";
            }
        }
        return x*10+y;
    }

    /**
     * Method that implements each turn of the game. It takes as an argument an object Player, that is the player of each turn.
     * Then, after increasing the player's tries, it uses the method "findCoordinate" twice or three times based on the game-type 
     * showing the new table each time a new card is being revealed making sure at the same time that the player hasn't already 
     * given the same card-coordinates a second time per turn. When all the coordinates have been given, it checks if all the
     * cards chosen are the same. If they are, the cards are removed from the table. If not, the different cards opened are
     * getting hidden again. If there are no more cards on the table, the game finishes with a message "game finished", plus a
     * message with the total number of the player's tries.
     */
    public void nextTurn(Player player) throws InterruptedException {

        player.addTries();
        if (typeOfGame.equals("triple")){
            coor1 = findCoordinate(1);
            aTable.showTable(coor1,0,0);
            coor2 = findCoordinate(2);
            while(coor2 == coor1){
                System.out.println("Already Given");
                coor2 = findCoordinate(2);
            }
            aTable.showTable(coor1,coor2,0);
            coor3 = findCoordinate(3);
            while(coor3 == coor1 || coor3 == coor2){
                System.out.println("Already Given");
                coor3 = findCoordinate(3);
            }
            aTable.showTable(coor1,coor2,coor3);
            if(aTable.getValueContent(coor1) == aTable.getValueContent(coor2) && aTable.getValueContent(coor2) == aTable.getValueContent(coor3)){
                aTable.cardCollector(coor1);
                aTable.cardCollector(coor2);
                aTable.cardCollector(coor3);
                game--;
                player.addCorrectGuesses();
            }
        }
        else{
            coor1 = findCoordinate(1);
            aTable.showTable(coor1,0,0);
            coor2 = findCoordinate(2);
            coor3 = 0;
            while(coor2 == coor1){
                System.out.println("Already Given");
                coor2 = findCoordinate(2);
            }
            aTable.showTable(coor1,coor2,coor3);
            if(aTable.getValueContent(coor1) == aTable.getValueContent(coor2)){
                aTable.cardCollector(coor1);
                aTable.cardCollector(coor2);
                game--;
                player.addCorrectGuesses();
            }
        }
        Thread.sleep(3*1000);
        if(game == 0) {
            System.out.println("You needed " + player.getTries() +" tries.");
            System.out.println(".................GAME FINISHED.................");
        }
        else
            aTable.showTable(0,0,0);
    }
}
