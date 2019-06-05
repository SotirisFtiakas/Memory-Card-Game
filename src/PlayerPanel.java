import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * The class where every type of game is created and played.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */

public class PlayerPanel extends JPanel {


    private int duelkeno = 4;
    private int rows;
    private int cols;
    private int numberOfPlayers;
    private int numberOfBotPlayers;
    private String gameMode;
    private int counter, timesClicked = 0;
    private int zeygariaKartwn;
    private int[] CoordsClicked = {0, 0, 0, 0};
    private ArrayList<Player> PAIKTES;
    private int PlayerNumber;
    private infoPanel infoPanel;
    private HashMap<Integer, Character> MemoryHash, MemoryHash1;
    private int tempKey;
    private int allagh_gyrou=0;
    private boolean epiloghPaikth = false;
    private boolean gameFinished = false;


    public boolean getGameFinished() { return gameFinished; }

    public void setGameFinished() { gameFinished = true;}

    public infoPanel getInfoPanel() { return infoPanel; }

    private ArrayList<Integer> coordinatesArrayList;
    private ArrayList<Character> contents, contents2;
    private HashMap<Integer, GUICard> coordinatesOfCards;

    int getTempKey() {
        return tempKey;
    }

    HashMap<Integer, GUICard> getCoordinatesOfCards() {
        return coordinatesOfCards;
    }

    ArrayList<Integer> getCoordinatesArrayList() {
        return coordinatesArrayList;
    }

    ArrayList<Player> getPAIKTES() {
        return PAIKTES;
    }

    HashMap<Integer, Character> getMemoryHash() {
        return MemoryHash;
    }

    int getZeygariaKartwn() {
        return zeygariaKartwn;
    }

    int getPlayerNumber() {
        return PlayerNumber;
    }

    void setPlayerNumber(int playerNumber) {
        PlayerNumber = playerNumber;
    }

    String getGameMode() {
        return gameMode;
    }

    HashMap<Integer, Character> getMemoryHash1() {
        return MemoryHash1;
    }

    int getAllagh_gyrou() {
        return allagh_gyrou;
    }

    void setAllagh_gyrou(int allagh_gyrou) {
        this.allagh_gyrou = allagh_gyrou;
    }

    void setEpiloghPaikth(boolean epiloghPaikth) {
        this.epiloghPaikth = epiloghPaikth;
    }

    void setTempKey(int tempKey) {
        this.tempKey = tempKey;
    }

    /**
     * The constructor of class 'PlayerPanel', which takes as arguments the number of rows and columns, the number of
     * total players as well as the number of bots, the ArrayList with all the players' names, the gamemode an then
     * the information panel. First, it checks which gamemode has been selected, it initialises the number of cards
     * accordingly and then it creates the table with the card-buttons flipped down. Then, it assigns actionlisteners
     * to the cards and at the end, it puts every players name in a new ArrayList.
     *
     * @param rows the number of lines in the table
     * @param cols the number of rows in the table
     * @param numberOfBotPlayers  the number of bots in the game
     * @param numberOfPlayers the total number of players (both bots and humans)
     * @param allNames an ArrayList with the players' names
     * @param gameMode the gamemode of the game
     * @param infoPanel the information panel displayed next to the cards
     */

    public PlayerPanel(int rows, int cols, int numberOfPlayers, int numberOfBotPlayers, ArrayList<String> allNames, String gameMode, infoPanel infoPanel) {


        this.infoPanel = infoPanel;
        this.numberOfBotPlayers = numberOfBotPlayers;
        this.numberOfPlayers = numberOfPlayers;
        PlayerNumber = 0;
        this.gameMode = gameMode;
        this.rows = rows;
        this.cols = cols;
        PAIKTES = new ArrayList();
        String currentPlayer = allNames.get(0);
        setLayout(new GridLayout(rows, cols));
        coordinatesOfCards = new HashMap<>();
        MemoryHash = new HashMap<>();
        MemoryHash1 = new HashMap<>();
        contents = new ArrayList<>();
        contents2 = new ArrayList<>();
        coordinatesArrayList = new ArrayList<>();
        //table = new GUICard[rows][cols];
        //buildPanel();
        makeTable();


        addActionListenerToCards();
        setVisible(true);
        if (gameMode.equals("Basic") || gameMode.equals("Double")) {
            this.counter = 1;
            if (gameMode.equals("Basic")) {
                zeygariaKartwn = 12;
            } else
                zeygariaKartwn = 24;
        } else {
            zeygariaKartwn = 12;
            this.counter = 2;
        }

        if (gameMode.equals("Duel"))
            zeygariaKartwn = 24;

        for (int i = 0; i < this.numberOfPlayers - this.numberOfBotPlayers; i++) {
            Player player = new Player(allNames.get(i));
            PAIKTES.add(player);

        }
        for (int i = this.numberOfPlayers - this.numberOfBotPlayers; i < this.numberOfPlayers; i++) {
            Computer computer = new Computer(allNames.get(i));
            PAIKTES.add(computer);
        }

    }

    /**
     * This is the method that creates the table with the cards of the game, depending on the gamemode, the columns
     * and the rows.
     *
     */

    private void makeTable() {
        int key = 11;
        if (rows == 4 && cols == 6) {
            for (int i = 65; i < 77; i++) {
                contents.add((char) i);
                contents.add((char) i);
            }
            Collections.shuffle(contents);
            for (Character ch : contents) {
                if (key % 10 == 7) {
                    key += 4;
                }
                GUICard aCard = new GUICard(ch);
                aCard.setCoordinate(key);
                coordinatesOfCards.put(key, aCard);
                coordinatesArrayList.add(key);
                add(coordinatesOfCards.get(key));
                key++;
            }
        }
        if (rows == 6 && cols == 8 && gameMode.equals("Double")) {
            for (int i = 65; i < 89; i++) {
                contents.add((char) i);
                contents.add((char) i);
            }
            Collections.shuffle(contents);
            for (Character ch : contents) {
                if (key % 10 == 9) {
                    key += 2;
                }
                GUICard aCard = new GUICard(ch);
                aCard.setCoordinate(key);
                coordinatesOfCards.put(key, aCard);
                coordinatesArrayList.add(key);
                add(coordinatesOfCards.get(key));
                key++;
            }
        }
        if (rows == 6 && cols == 8 && gameMode.equals("Duel")) {
            for (int i = 65; i < 89; i++) {
                contents.add((char) i);
                contents2.add((char) i);
            }
            Collections.shuffle(contents);
            Collections.shuffle(contents2);
            counter = 1;
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 4; j++) {
                    GUICard aCard = new GUICard(contents.get(counter - 1));
                    aCard.setCoordinate(i * 10 + j);
                    coordinatesOfCards.put(i * 10 + j, aCard);
                    coordinatesArrayList.add(i * 10 + j);
                    if (duelkeno == 8) {
                        add(Box.createHorizontalStrut(20));
                        duelkeno = 0;
                    }
                    duelkeno++;
                    add(coordinatesOfCards.get(i * 10 + j));
                    counter++;
                }
                counter = counter - 4;
                for (int j = 1; j <= 4; j++) {

                    GUICard aCard = new GUICard(contents2.get(counter - 1));
                    aCard.setCoordinate(i * 10 + j + 4);
                    coordinatesOfCards.put(i * 10 + j + 4, aCard);
                    coordinatesArrayList.add(i * 10 + j + 4);
                    if (duelkeno == 8) {
                        add(Box.createHorizontalStrut(20));
                        duelkeno = 0;
                    }
                    duelkeno++;
                    add(coordinatesOfCards.get(i * 10 + j + 4));
                    counter++;
                }

            }
        } else if (rows == 6 && cols == 6) {
            for (int i = 65; i < 77; i++) {
                contents.add((char) i);
                contents.add((char) i);
                contents.add((char) i);
            }
            Collections.shuffle(contents);
            for (Character ch : contents) {
                if (key % 10 == 7) {
                    key += 4;
                }
                GUICard aCard = new GUICard(ch);
                aCard.setCoordinate(key);
                coordinatesOfCards.put(key, aCard);
                coordinatesArrayList.add(key);
                add(coordinatesOfCards.get(key));
                key++;
            }
        }
    }

    /**
     * This method assigns actionlisteners to every card-button on the game.
     *
     */

    private void addActionListenerToCards(){
        for(int key : coordinatesOfCards.keySet()){
            coordinatesOfCards.get(key).card.addActionListener(actionListener);
        }
    }

    /**
     * Depending on the gamemode, this method calls the appropriate function which implements the chosen gamemode.
     */

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameMode.equals("Basic") || gameMode.equals("Double")) {
                BasicOrDoubleActions();
            }else if (gameMode.equals("Triple")){
                TripleActions();
            }else if(gameMode.equals("Duel")) {
                DuelActions();
            }
        }
    };

    /**
     * This method initialises a new Thread which utilises the bots' functions.
     */

    private void ThreadStart(){
        Thread tbot = new Thread(new BotRunnable(this));
        tbot.start();
    }



    /**
     * It runs through the whole main hash-map and checks every card's Exist value. It then
     * removes from the table every card that has been collected. After that, it shows the new
     * updated table
     */
    public void showCards() {
        for (int key : coordinatesOfCards.keySet()) {
            if (!(coordinatesOfCards.get(key).getExist())) {
                coordinatesOfCards.get(key).setUnvisible();
            }
        }
    }

    /**
     * The method to compare 2 or 3 cards.
     * @param coor1 coordinates of first card
     * @param coor2  coordinates of second card
     * @param coor3 cordinates of third card (or empty)
     * @return returns true if they are all the same, otherwise false.
     */
    public Boolean compareCards(int coor1, int coor2, int coor3) {
        if (coordinatesOfCards.get(coor1).getContent() == coordinatesOfCards.get(coor2).getContent()) {
            if (coor3 == 0) {
                return true;
            } else if (coordinatesOfCards.get(coor1).getContent() == coordinatesOfCards.get(coor3).getContent()) {
                return true;
            } else {
                return false;
            }
        } else
            return false;
    }

    /**
     * This method is used for the Basic or the Double gamemode.
     *
     * First, this method increases the current player's tries by one. Then it compares the 2 cards given as argument
     * using the compareCards method. If the cards are the same, they are removed from the table and the current player's
     * correct guesses are increased by one. If they are different, they are turned face down again and it's now
     * the next players turn. The information panel is updated based on the result of the card comparison.
     *
     * @param coor1 coordinates of first card
     * @param coor2  coordinates of second card
     */
    void collect2Cards(int coor1, int coor2) throws InterruptedException {
        PAIKTES.get(PlayerNumber).addTries();
        if (compareCards(coor1, coor2, 0)) {
            PAIKTES.get(PlayerNumber).addCorrectGuesses();
            Thread.sleep(600);
            coordinatesOfCards.get(coor1).setExist(false);
            coordinatesOfCards.get(coor2).setExist(false);
            MemoryHash.remove(coor1);
            MemoryHash.remove(coor2);
            zeygariaKartwn--;
            if (zeygariaKartwn == 0) {
                PAIKTES.sort(Comparator.comparingInt(Player::getCorrectGuesses).reversed());
                JOptionPane.showMessageDialog(null,
                        "WINNER: " + PAIKTES.get(0).getName(),"END GAME",
                        JOptionPane.INFORMATION_MESSAGE);
                if(!(PAIKTES.get(0) instanceof Computer)){
                    makeHighScore();
                }
                setGameFinished();
                infoPanel.getHome().doClick();
            }
            showCards();
        } else {
            Thread.sleep(600);
            coordinatesOfCards.get(coor1).turnCardBackwards();
            coordinatesOfCards.get(coor2).turnCardBackwards();
            if (PlayerNumber < numberOfPlayers - 1) {
                PlayerNumber++;

            } else {
                PlayerNumber = 0;
            }
            infoPanel.updatePlayerInfo(PAIKTES.get(PlayerNumber).getName(), PAIKTES.get(PlayerNumber).getTries());

        }

    }

    /**
     * This method is used for the Duel gamemode (for humans).
     *
     * First, this method increases the current player's tries by one. Then it compares the 2 cards given as argument
     * using the compareCards method. If the cards are the same, they are removed from the table and the current player's
     * correct guesses are increased by one. If they are different, they are turned face down again and it's now
     * the next players turn. The information panel is updated based on the result of the card comparison.
     *
     * @param coor1 coordinates of first card
     * @param coor2  coordinates of second card
     */

    private void collect2CardsDuel(int coor1, int coor2) throws InterruptedException {
        PAIKTES.get(PlayerNumber).addTries();
        if (compareCards(coor1, coor2, 0)) {
            PAIKTES.get(PlayerNumber).addCorrectGuesses();
            Thread.sleep(600);
            coordinatesOfCards.get(coor1).setExist(false);
            coordinatesOfCards.get(coor2).setExist(false);
            MemoryHash1.remove(coor2);
            zeygariaKartwn--;
            infoPanel.updatePlayerInfo(PAIKTES.get(PlayerNumber).getName(), PAIKTES.get(PlayerNumber).getTries());
            if (zeygariaKartwn == 0) {
                PAIKTES.sort(Comparator.comparingInt(Player::getCorrectGuesses).reversed());
                JOptionPane.showMessageDialog(null,
                        "WINNER: " + PAIKTES.get(0).getName(),"END GAME",
                        JOptionPane.INFORMATION_MESSAGE);
                if(!(PAIKTES.get(0) instanceof Computer)){
                    makeHighScore();
                }
                setGameFinished();
                infoPanel.getHome().doClick();
            }
            showCards();
        } else {
            Thread.sleep(600);
            coordinatesOfCards.get(coor1).turnCardBackwards();
            coordinatesOfCards.get(coor2).turnCardBackwards();
            /*if (PlayerNumber==) {
                PlayerNumber++;

            } else {
                PlayerNumber = 0;
            }*/
            infoPanel.updatePlayerInfo(PAIKTES.get(PlayerNumber).getName(), PAIKTES.get(PlayerNumber).getTries());

        }

    }

    /**
     * This method is used for the Duel gamemode (for bots).
     *
     * First, this method increases the current player's tries by one. Then it compares the 2 cards given as argument
     * using the compareCards method. If the cards are the same, they are removed from the table and the current player's
     * correct guesses are increased by one. If they are different, they are turned face down again and it's now
     * the next players turn. The information panel is updated based on the result of the card comparison.
     *
     * @param coor1 coordinates of first card
     * @param coor2  coordinates of second card
     */

    void collect2CardsDuelBot(int coor1, int coor2) throws InterruptedException {       //private
        PAIKTES.get(PlayerNumber).addTries();
        if (compareCards(coor1, coor2, 0)) {
            PAIKTES.get(PlayerNumber).addCorrectGuesses();
            Thread.sleep(600);
            coordinatesOfCards.get(coor1).setExist(false);
            coordinatesOfCards.get(coor2).setExist(false);
            // MemoryHash0.remove(coor1);
            MemoryHash1.remove(coor2);
            zeygariaKartwn--;
            infoPanel.updatePlayerInfo(PAIKTES.get(PlayerNumber).getName(), PAIKTES.get(PlayerNumber).getTries());
            if (zeygariaKartwn == 0) {
                PAIKTES.sort(Comparator.comparingInt(Player::getCorrectGuesses).reversed());
                JOptionPane.showMessageDialog(null,
                        "WINNER: " + PAIKTES.get(0).getName(),"END GAME",
                        JOptionPane.INFORMATION_MESSAGE);
                if(!(PAIKTES.get(0) instanceof Computer)){
                    makeHighScore();
                }
                setGameFinished();
                infoPanel.getHome().doClick();
            }
            showCards();
        } else {
            Thread.sleep(600);
            coordinatesOfCards.get(coor1).turnCardBackwards();
            coordinatesOfCards.get(coor2).turnCardBackwards();
            infoPanel.updatePlayerInfo(PAIKTES.get(PlayerNumber).getName(), PAIKTES.get(PlayerNumber).getTries());

        }
        allagh_gyrou++;

    }


    /**
     * This method is used for the Triple gamemode.
     *
     * First, this method increases the current player's tries by one. Then it compares the 3 cards given as argument
     * using the compareCards method. If the cards are the same, they are removed from the table and the current player's
     * correct guesses are increased by one. If they are different, they are turned face down again and it's now
     * the next players turn. The information panel is updated based on the result of the card comparison.
     *
     * @param coor1 coordinates of first card
     * @param coor2 coordinates of second card
     * @param coor3 coordinates of third card
     */

    void collect3Cards(int coor1, int coor2, int coor3) throws InterruptedException {

        PAIKTES.get(PlayerNumber).addTries();
        if (compareCards(coor1, coor2, coor3)) {
            Thread.sleep(600);
            PAIKTES.get(PlayerNumber).addCorrectGuesses();
            coordinatesOfCards.get(coor1).setExist(false);
            coordinatesOfCards.get(coor2).setExist(false);
            coordinatesOfCards.get(coor3).setExist(false);
            MemoryHash.remove(coor1);
            MemoryHash.remove(coor2);
            MemoryHash.remove(coor3);
            zeygariaKartwn--;
            if (zeygariaKartwn == 0) {
                infoPanel.updatePlayerInfo(PAIKTES.get(PlayerNumber).getName(), PAIKTES.get(PlayerNumber).getTries());
                endGame();
            } else {
                infoPanel.updatePlayerInfo(PAIKTES.get(PlayerNumber).getName(), PAIKTES.get(PlayerNumber).getTries());
            }
            showCards();
        } else {
            Thread.sleep(600);
            coordinatesOfCards.get(coor1).turnCardBackwards();
            coordinatesOfCards.get(coor2).turnCardBackwards();
            coordinatesOfCards.get(coor3).turnCardBackwards();
            if (PlayerNumber < numberOfPlayers - 1) {
                PlayerNumber++;
            } else {
                PlayerNumber = 0;
            }
            infoPanel.updatePlayerInfo(PAIKTES.get(PlayerNumber).getName(), PAIKTES.get(PlayerNumber).getTries());
        }

    }

    /**
     * This method is called if the gamemode is either Basic or Double. Inside this method is implemented
     * every human player's moves. When the last player's turn is finished, then the bot Thread is
     * called in order for bots to play.
     */

    private void BasicOrDoubleActions(){
        if (!(PAIKTES.get(PlayerNumber) instanceof Computer)) {

            timesClicked++;
            for (int key : coordinatesOfCards.keySet()) {
                if (coordinatesOfCards.get(key).card.getModel().isArmed()) {
                    CoordsClicked[timesClicked - 1] = key;
                    MemoryHash.put(key, coordinatesOfCards.get(key).getContent());
                }
            }
            ////////////////////////////////doCLick
            if (timesClicked == 1) {
                coordinatesOfCards.get(CoordsClicked[0]).ACTION();
            }
            if (timesClicked == 2) {
                if (CoordsClicked[0] == CoordsClicked[1]) {
                    timesClicked--;
                }
            }
            if (timesClicked == 2) {
                coordinatesOfCards.get(CoordsClicked[1]).ACTION();
            }
            if (timesClicked == 2) {
                coordinatesOfCards.get(CoordsClicked[1]).card.doClick();
            }
            if (timesClicked == 3) {
                try {
                    collect2Cards(CoordsClicked[0], CoordsClicked[1]);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                CoordsClicked[0] = 0;
                CoordsClicked[1] = 0;
                timesClicked = 0;
                if ((PAIKTES.get(PlayerNumber) instanceof Computer)) {
                    PlayerNumber = numberOfPlayers - numberOfBotPlayers;
                    ThreadStart();
                }

            }
        }
    }

    /**
     * This method is called if the gamemode is Triple. Inside this method is implemented
     * every human player's moves. When the last player's turn is finished, then the bot Thread is
     * called in order for bots to play.
     */

    private void TripleActions() {
        if (!(PAIKTES.get(PlayerNumber) instanceof Computer)) {

            timesClicked++;
            for (int key : coordinatesOfCards.keySet()) {
                if (coordinatesOfCards.get(key).card.getModel().isArmed()) {
                    CoordsClicked[timesClicked - 1] = key;
                    MemoryHash.put(key, coordinatesOfCards.get(key).getContent());
                }
            }
            if (timesClicked == 1) {
                coordinatesOfCards.get(CoordsClicked[0]).ACTION();
            }
            if (timesClicked == 2) {
                if (CoordsClicked[0] == CoordsClicked[1]) {
                    timesClicked--;
                }
            }
            if (timesClicked == 2) {
                coordinatesOfCards.get(CoordsClicked[1]).ACTION();
            }

            if (timesClicked == 3) {
                if (CoordsClicked[0] == CoordsClicked[2] || CoordsClicked[1] == CoordsClicked[2]) {
                    timesClicked--;
                }
            }
            if (timesClicked == 3) {
                coordinatesOfCards.get(CoordsClicked[2]).ACTION();
                coordinatesOfCards.get(CoordsClicked[2]).card.doClick();
            }
            if (timesClicked == 4) {
                try {
                    collect3Cards(CoordsClicked[0], CoordsClicked[1], CoordsClicked[2]);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                CoordsClicked[0] = 0;
                CoordsClicked[1] = 0;
                CoordsClicked[2] = 0;
                timesClicked = 0;
                if ((PAIKTES.get(PlayerNumber) instanceof Computer)) {
                    PlayerNumber = numberOfPlayers - numberOfBotPlayers;
                    ThreadStart();
                }
            }
        }
    }

    /**
     * This method is called if the gamemode is Duel. Inside this method is implemented
     * every human player's moves. If it is one player vs one bot, this method also calls the
     * bot Thread for the bot to play.
     */

    private void DuelActions(){
        if (!epiloghPaikth) {
            timesClicked++;
            boolean syn8hkh = false;
            for (int key : coordinatesOfCards.keySet()) {
                if (coordinatesOfCards.get(key).card.getModel().isArmed()) {
                    if (PlayerNumber == 0) {
                        if (key % 10 > 4) {
                            timesClicked--;
                            syn8hkh = true;
                        } else {
                            CoordsClicked[timesClicked - 1] = key;
                        }
                    }
                    if (PlayerNumber == 1) {
                        if (key % 10 <= 4) {
                            timesClicked--;
                            syn8hkh = true;
                        } else {
                            CoordsClicked[timesClicked - 1] = key;
                            MemoryHash1.put(key, coordinatesOfCards.get(key).getContent());
                        }
                    }
                }
            }
            if (timesClicked == 1 && !syn8hkh) {
                coordinatesOfCards.get(CoordsClicked[0]).ACTION();
                if (PlayerNumber == 0) {
                    PlayerNumber++;
                } else {
                    PlayerNumber--;
                }
                if (PAIKTES.get(PlayerNumber) instanceof Computer) {
                    tempKey = CoordsClicked[0];
                    timesClicked=0;
                    ThreadStart();
                }
            }

            if (timesClicked == 2) {
                if (CoordsClicked[0] == CoordsClicked[1]) {
                    timesClicked--;
                }
            }
            if (timesClicked == 2) {
                coordinatesOfCards.get(CoordsClicked[1]).ACTION();
            }
            if (timesClicked == 2) {
                coordinatesOfCards.get(CoordsClicked[1]).card.doClick();
            }
            if (timesClicked == 3) {
                try {
                    collect2CardsDuel(CoordsClicked[0], CoordsClicked[1]);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                CoordsClicked[0] = 0;
                CoordsClicked[1] = 0;
                timesClicked = 0;

            }
        } else {
            timesClicked++;
            for (int key : coordinatesOfCards.keySet()) {
                if (coordinatesOfCards.get(key).card.getModel().isArmed()) {
                    if (key % 10 > 4) {
                        timesClicked--;
                        epiloghPaikth = true;
                    } else {
                        CoordsClicked[timesClicked - 1] = key;
                    }
                }
            }
            if (timesClicked==1){
                coordinatesOfCards.get(CoordsClicked[0]).ACTION();
                        /*try {
                            collect2CardsDuel(CoordsClicked[0], tempKey);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }*/
            }

            if (timesClicked == 1) {
                coordinatesOfCards.get(CoordsClicked[0]).card.doClick();
                epiloghPaikth = false;
            }
            if (timesClicked == 2) {
                try {
                    collect2CardsDuel(CoordsClicked[0], tempKey);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                timesClicked=0;
            }
        }
    }

    /**
     * This method is called in order to check and insert the game's winner name in the HighScore table if
     * needed. If the players score is among the top 10 best, it puts his name in the HighScore and removes the last
     * one.
     */


    void makeHighScore(){
        String filename = "";
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> score = new ArrayList<>();
        Player player;
        int i = 0;
        if(numberOfPlayers == 1){
            filename = gameMode + "SScore.txt";
            try {
                Scanner inputStream = new Scanner(new FileInputStream(filename));
                //Player player;
                while (inputStream.hasNextLine()) {
                    names.add(inputStream.nextLine());
                    score.add(inputStream.nextLine());
                    if(!(names.get(i).equals("null"))){
                        if(PAIKTES.get(0).getName().equals(names.get(i))){
                            if(Integer.parseInt(score.get(i)) < PAIKTES.get(0).getTries())
                                PAIKTES.get(0).setTries(Integer.parseInt(score.get(i)));
                        }
                        else{
                            player = new Player(names.get(i));
                            player.setTries(Integer.parseInt(score.get(i)));
                            PAIKTES.add(player);
                        }
                    }
                    i++;
                }
                inputStream.close();
            } catch(FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null,
                        "File was not found or could not be opened", "Missing File",
                        JOptionPane.ERROR_MESSAGE);
            }
            PAIKTES.sort(Comparator.comparingInt(Player::getTries));

            if(PAIKTES.size() != 10){
                int temp = 10-PAIKTES.size();
                for(int j = 0; j < temp; j++){
                    player = new Player("null");
                    player.setTries(-1);
                    PAIKTES.add(player);
                }
            }

            try{
                PrintWriter pw = new PrintWriter(filename);
                for(i = 0; i < 10; i++){
                    pw.println(PAIKTES.get(i).getName());
                    pw.println(PAIKTES.get(i).getTries());
                }
                pw.close();
            }catch(FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null,
                        "File was not found", "Missing File",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            filename = gameMode + "Score.txt";
            PAIKTES.sort(Comparator.comparingInt(Player::getCorrectGuesses).reversed());
            int size = PAIKTES.size();
            for(int q = 1; q < size;q++){
                PAIKTES.remove(q);
            }
            if(!(PAIKTES.get(0) instanceof Computer)){
                try {
                    Scanner inputStream = new Scanner(new FileInputStream(filename));

                    //Player player;
                    i = 0;
                    boolean playerExist = false;
                    while (inputStream.hasNextLine()) {
                        names.add(inputStream.nextLine());
                        score.add(inputStream.nextLine());
                        if(!(names.get(i).equals("null"))){
                            if(PAIKTES.get(0).getName().equals(names.get(i))){
                                PAIKTES.get(0).setWins(Integer.parseInt(score.get(i)));
                                PAIKTES.get(0).addWin();
                                playerExist = true;
                            }
                            else{
                                player = new Player(names.get(i));
                                player.setWins(Integer.parseInt(score.get(i)));
                                PAIKTES.add(player);
                            }
                        }
                        i++;
                    }
                    if(!playerExist){
                        PAIKTES.get(0).addWin();
                    }
                    inputStream.close();
                } catch(FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,
                            "File was not found or could not be opened", "Missing File",
                            JOptionPane.ERROR_MESSAGE);
                }
                PAIKTES.sort(Comparator.comparingInt(Player::getWins).reversed());

                if(PAIKTES.size() != 10){
                    int temp = 10-PAIKTES.size();
                    for(int j = 0; j < temp; j++){
                        player = new Player("null");
                        player.setWins(-1);
                        PAIKTES.add(player);
                    }
                }

                try{
                    PrintWriter pw = new PrintWriter(filename);
                    for(i = 0; i < 10; i++){
                        pw.println(PAIKTES.get(i).getName());
                        pw.println(PAIKTES.get(i).getWins());
                    }
                    pw.close();
                }catch(FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,
                            "File was not found", "Missing File",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * This method is called when a game is finished. It compares all the players' correct guesses
     * and shows a message with the name of the player who won. If needed, it also puts the winner's name
     * in the HighScore table. If the match ends as a draw, then nothing happens.
     */

    public void endGame(){
        if(numberOfPlayers == 1){
            JOptionPane.showMessageDialog(null,
                    "CONGRATS YOU WON","END GAME",
                    JOptionPane.INFORMATION_MESSAGE);
            makeHighScore();

        }else{
            PAIKTES.sort(Comparator.comparingInt(Player::getCorrectGuesses).reversed());
            if(PAIKTES.get(0).getCorrectGuesses() == PAIKTES.get(1).getCorrectGuesses()) {
                JOptionPane.showMessageDialog(null,
                        "DRAW","END GAME",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null,
                        "WINNER: " + PAIKTES.get(0).getName(),"END GAME",
                        JOptionPane.INFORMATION_MESSAGE);
                makeHighScore();
            }

        }
        gameFinished = true;
        infoPanel.getHome().doClick();
    }

    public void setZeygariaKartwn(int i) {
        zeygariaKartwn=i;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
