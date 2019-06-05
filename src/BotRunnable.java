import javax.swing.*;
import java.util.*;

/**
 * The class where the bot moves are being made.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */

public class BotRunnable implements Runnable {

    private int[] CoordsClicked = {0, 0, 0, 0};
    private PlayerPanel playerPanel;
    private HashMap<Integer, Character> TempMemoryHash;

    /**
     * The constructor of this class takes as an argument the playerPanel where the game is happening and
     * initialises an empty HashMap for the implementation of the different bots' memory levels.
     */

    BotRunnable(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
        TempMemoryHash = new HashMap<>();
    }

    /**
     * Inside this run method happen all the bots' moves. It first checks for the gamemode and
     * then identifies the current bot-player's memory level. Then it either opens random cards or if
     * it knows the coordinates of the 2 or 3 same cards, it opens these.
     */

    @Override
    public void run() {
        int key1;
        int key2;
        if (playerPanel.getGameMode().equals("Basic") || playerPanel.getGameMode().equals("Double")) {

            // findBotLevel();

            do {
                findBotLevel();
                for (int key : TempMemoryHash.keySet()) {
                    for (int key2nd : TempMemoryHash.keySet()) {
                        if (key != key2nd) {
                            if (TempMemoryHash.get(key).equals(TempMemoryHash.get(key2nd))) {
                                CoordsClicked[0] = key;
                                CoordsClicked[1] = key2nd;
                            }
                        }
                    }
                }

                if (CoordsClicked[0] == 0) {

                    Random r = new Random();
                    do {
                        key1 = playerPanel.getCoordinatesArrayList().get(r.nextInt(playerPanel.getCoordinatesArrayList().size()));
                    } while (!playerPanel.getCoordinatesOfCards().get(key1).getExist());

                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    playerPanel.getCoordinatesOfCards().get(key1).ACTION();
                    playerPanel.getMemoryHash().put(key1, playerPanel.getCoordinatesOfCards().get(key1).getContent());

                    Random m = new Random();
                    do {
                        key2 = playerPanel.getCoordinatesArrayList().get(m.nextInt(playerPanel.getCoordinatesArrayList().size()));
                    } while ((key1 == key2) || (!playerPanel.getCoordinatesOfCards().get(key2).getExist()));

                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    playerPanel.getCoordinatesOfCards().get(key2).ACTION();
                    playerPanel.getMemoryHash().put(key2, playerPanel.getCoordinatesOfCards().get(key2).getContent());

                    try {
                        playerPanel.collect2Cards(key1, key2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CoordsClicked[0] = 0;
                    CoordsClicked[1] = 0;
                } else {
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    playerPanel.getCoordinatesOfCards().get(CoordsClicked[0]).ACTION();
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    playerPanel.getCoordinatesOfCards().get(CoordsClicked[1]).ACTION();
                    try {
                        collect2Cards(CoordsClicked[0], CoordsClicked[1]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CoordsClicked[0] = 0;
                    CoordsClicked[1] = 0;

                }


            } while (playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()) instanceof Computer);
        } else if (playerPanel.getGameMode().equals("Triple")) {
            do {
                findBotLevel();
                for (int key : TempMemoryHash.keySet()) {
                    for (int key2nd : TempMemoryHash.keySet()) {
                        if (key != key2nd) {
                            for (int key3rd : TempMemoryHash.keySet()) {
                                if (key != key3rd && key2nd != key3rd)
                                    if (TempMemoryHash.get(key).equals(TempMemoryHash.get(key2nd)) && TempMemoryHash.get(key).equals(TempMemoryHash.get(key3rd))) {
                                        CoordsClicked[0] = key;
                                        CoordsClicked[1] = key2nd;
                                        CoordsClicked[2] = key3rd;
                                    }
                            }
                        }
                    }
                }

                if (CoordsClicked[0] == 0) {

                    Random r = new Random();
                    do {
                        key1 = playerPanel.getCoordinatesArrayList().get(r.nextInt(playerPanel.getCoordinatesArrayList().size()));
                    } while (!playerPanel.getCoordinatesOfCards().get(key1).getExist());

                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    playerPanel.getCoordinatesOfCards().get(key1).ACTION();
                    playerPanel.getMemoryHash().put(key1, playerPanel.getCoordinatesOfCards().get(key1).getContent());

                    Random m = new Random();
                    do {
                        key2 = playerPanel.getCoordinatesArrayList().get(m.nextInt(playerPanel.getCoordinatesArrayList().size()));
                    } while ((key1 == key2) || (!playerPanel.getCoordinatesOfCards().get(key2).getExist()));


                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    playerPanel.getCoordinatesOfCards().get(key2).ACTION();
                    playerPanel.getMemoryHash().put(key2, playerPanel.getCoordinatesOfCards().get(key2).getContent());

                    Random l = new Random();
                    int key3;
                    do {
                        key3 = playerPanel.getCoordinatesArrayList().get(l.nextInt(playerPanel.getCoordinatesArrayList().size()));
                    }
                    while ((key1 == key3) || (key2 == key3) || (!playerPanel.getCoordinatesOfCards().get(key3).getExist()));

                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    playerPanel.getCoordinatesOfCards().get(key3).ACTION();
                    playerPanel.getMemoryHash().put(key3, playerPanel.getCoordinatesOfCards().get(key3).getContent());

                    try {
                        playerPanel.collect3Cards(key1, key2, key3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CoordsClicked[0] = 0;
                    CoordsClicked[1] = 0;
                    CoordsClicked[2] = 0;

                } else {
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    playerPanel.getCoordinatesOfCards().get(CoordsClicked[0]).ACTION();
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    playerPanel.getCoordinatesOfCards().get(CoordsClicked[1]).ACTION();
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    playerPanel.getCoordinatesOfCards().get(CoordsClicked[2]).ACTION();
                    try {
                        playerPanel.collect3Cards(CoordsClicked[0], CoordsClicked[1], CoordsClicked[2]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CoordsClicked[0] = 0;
                    CoordsClicked[1] = 0;
                    CoordsClicked[2] = 0;
                }

            } while (playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()) instanceof Computer);

        } else if (playerPanel.getGameMode().equals("Duel")) {
            do {
                findDuelBotLevel();
                if (playerPanel.getAllagh_gyrou() == 0) {
                    for (int key : TempMemoryHash.keySet()) {
                        if (TempMemoryHash.get(key).equals(playerPanel.getCoordinatesOfCards().get(playerPanel.getTempKey()).getContent())) {
                            CoordsClicked[0] = key;
                        }

                    }


                    if (CoordsClicked[0] == 0) {

                        Random r = new Random();
                        do {

                            key1 = playerPanel.getCoordinatesArrayList().get(r.nextInt(playerPanel.getCoordinatesArrayList().size()));
                        } while (!playerPanel.getCoordinatesOfCards().get(key1).getExist() || (key1 % 10 <= 4));

                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        playerPanel.getCoordinatesOfCards().get(key1).ACTION();
                        playerPanel.getMemoryHash1().put(key1, playerPanel.getCoordinatesOfCards().get(key1).getContent());

                        try {

                            playerPanel.collect2CardsDuelBot(playerPanel.getTempKey(), key1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        CoordsClicked[0] = 0;
                    } else {

                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        playerPanel.getCoordinatesOfCards().get(CoordsClicked[0]).ACTION();

                        try {
                            playerPanel.collect2CardsDuelBot(playerPanel.getTempKey(), CoordsClicked[0]);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        CoordsClicked[0] = 0;

                    }
                } else {
                    playerPanel.setAllagh_gyrou(0);
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Random k = new Random();
                    do {
                        key1 = playerPanel.getCoordinatesArrayList().get(k.nextInt(playerPanel.getCoordinatesArrayList().size()));
                    }
                    while (((key1 % 10 <= 4) || !playerPanel.getCoordinatesOfCards().get(key1).getExist()) && playerPanel.getZeygariaKartwn() != 0);
                    if (playerPanel.getZeygariaKartwn() == 0) {
                        playerPanel.getPAIKTES().sort(Comparator.comparingInt(Player::getCorrectGuesses).reversed());
                        JOptionPane.showMessageDialog(null,
                                "WINNER: " + playerPanel.getPAIKTES().get(0).getName(), "END GAME",
                                JOptionPane.INFORMATION_MESSAGE);
                        if (!(playerPanel.getPAIKTES().get(0) instanceof Computer)) {
                            playerPanel.makeHighScore();
                        }
                        playerPanel.setGameFinished();
                        playerPanel.getInfoPanel().getHome().doClick();
                    }

                    playerPanel.getCoordinatesOfCards().get(key1).ACTION();
                    playerPanel.setTempKey(key1);
                    playerPanel.getMemoryHash1().put(key1, playerPanel.getCoordinatesOfCards().get(key1).getContent());
                    playerPanel.setPlayerNumber(0);
                    playerPanel.setEpiloghPaikth(true);


                }

            } while (playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()) instanceof Computer);
        }
    }

    /**
     * This method recognises the memory level of the current bot-player and initialises its memory-Hashmap.
     * It works for any of the Basic, Double or Triple game modes.
     */

    private void findBotLevel() {

        if(playerPanel.getZeygariaKartwn()!=0) {
            if ((playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).getName().contains("Gold"))) {

            } else if ((playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).getName().contains("Kang"))) {

                boolean tempbool = true;
                for (int key : playerPanel.getMemoryHash().keySet()) {
                    if (tempbool) {
                        TempMemoryHash.put(key, playerPanel.getMemoryHash().get(key));
                        tempbool = false;
                    } else {
                        tempbool = true;
                    }
                }
            } else if ((playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).getName().contains("El"))) {

                TempMemoryHash = playerPanel.getMemoryHash();
            }
        }
    }

    /**
     * This method recognises the memory level of the current bot-player and initialises its memory-Hashmap.
     * It works for the Duel game mode.
     */

    private void findDuelBotLevel() {

        if ((playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).getName().contains("Gold"))) {

        } else if ((playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).getName().contains("Kang"))) {

            boolean tempbool = true;
            for (int key : playerPanel.getMemoryHash1().keySet()) {
                if (tempbool) {
                    TempMemoryHash.put(key, playerPanel.getMemoryHash1().get(key));
                    tempbool = false;
                } else {
                    tempbool = true;
                }
            }
        } else if ((playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).getName().contains("El"))) {
            TempMemoryHash = playerPanel.getMemoryHash1();
        }
    }


    void collect2Cards(int coor1, int coor2) throws InterruptedException {
        playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).addTries();
        if (playerPanel.compareCards(coor1, coor2, 0)) {
            playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).addCorrectGuesses();
            Thread.sleep(600);
            playerPanel.getCoordinatesOfCards().get(coor1).setExist(false);
            playerPanel.getCoordinatesOfCards().get(coor2).setExist(false);
            playerPanel.getMemoryHash().remove(coor1);
            TempMemoryHash.remove(coor1);
            playerPanel.getMemoryHash().remove(coor2);
            TempMemoryHash.remove(coor2);
            playerPanel.setZeygariaKartwn(playerPanel.getZeygariaKartwn()-1);
            if (playerPanel.getZeygariaKartwn() == 0) {
                playerPanel.getInfoPanel().updatePlayerInfo(playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).getName(), playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).getTries());
                playerPanel.endGame();
            } else {
                playerPanel.getInfoPanel().updatePlayerInfo(playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).getName(), playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).getTries());
            }
            playerPanel.showCards();
        } else {
            Thread.sleep(600);
            playerPanel.getCoordinatesOfCards().get(coor1).turnCardBackwards();
            playerPanel.getCoordinatesOfCards().get(coor2).turnCardBackwards();
            if (playerPanel.getPlayerNumber() < playerPanel.getNumberOfPlayers() - 1) {
                playerPanel.setPlayerNumber(playerPanel.getPlayerNumber()+1);

            } else {
                playerPanel.setPlayerNumber(0);
            }
            playerPanel.getInfoPanel().updatePlayerInfo(playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).getName(), playerPanel.getPAIKTES().get(playerPanel.getPlayerNumber()).getTries());

        }
}}