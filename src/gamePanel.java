import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The class that is the panel we show when the game starts.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
public class gamePanel extends JPanel {
    static final String GAME_PANEL = "Game Panel";
    private GUI gui;

    private int rows,cols,numberOfPlayers,numberOfBotPlayers;
    private ArrayList<String> allNames;
    private String gameMode;

    /**
     * The constructor of class gamePanel. It initiates some important things for the panel.
     * @param gui the class that contains all the panels so the panels can communicate.
     */
    public gamePanel(GUI gui){
        setName(GAME_PANEL);
        this.gui = gui;
        setLayout(new FlowLayout());
        setOpaque(false);
    }

    /**
     * The method 'build' creates an infoPanel and a PlayerPanel and add them to gamePanel.
     */
    void build(){
        infoPanel infoPanel = new infoPanel(allNames, gameMode, gui);
        infoPanel.setPreferredSize(new Dimension(200,130));
        PlayerPanel playerPanel = new PlayerPanel(rows, cols, numberOfPlayers, numberOfBotPlayers, allNames,
                gameMode, infoPanel);
        add(playerPanel);
        add(infoPanel);
        playerPanel.setOpaque(false);
        infoPanel.setOpaque(false);
        //Add ActionListeners to infoPanel's buttons.
        infoPanel.getHome().addActionListener(e -> {
            if (playerPanel.getGameFinished()) {
                gui.showCard(StartMenu.START_MENU);
                removeAll();
            }
            else {
                int input = JOptionPane.showOptionDialog(null,
                        gui.getBundle().getString("gmWarning"), gui.getBundle().getString("gmWarningT"),
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        null, null);
                if (input == JOptionPane.OK_OPTION) {
                gui.showCard(StartMenu.START_MENU);
                removeAll();
                }
            }
        });
        infoPanel.getRestart().addActionListener(e -> {
            removeAll();
            revalidate();
            build();
        });
    }

    void setRows(int rows){
        this.rows = rows;
    }

    void setCols(int cols){
        this.cols = cols;
    }

    void setNumberOfPlayers(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
    }

    void setNumberOfBotPlayers(int numberOfBotPlayers){
        this.numberOfBotPlayers = numberOfBotPlayers;
    }

    void setAllNames(ArrayList<String> allNames){
        this.allNames = allNames;
    }

    void setGameMode(String gameMode){
        this.gameMode = gameMode;
    }

}
