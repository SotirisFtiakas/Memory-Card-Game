import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The class that is the panel we show when button Play is pressed.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
public class infoPanel extends JPanel {

    private JLabel playerLabel;
    private JLabel movesLabel;

    private JPanel movesPanel;
    private JPanel playerPanel;

    private JButton home;
    private JButton restart;

    private ArrayList<String> allNames;

    private String gameMode;

    private GUI gui;

    /**
     * The constructor of the class 'infoPanel'.
     *
     * @param allNames an ArrayList containing the names of all players.
     * @param gameMode the mode of the game.
     * @param gui the class that contains all the panels so the panels can communicate.
     */
    public infoPanel(ArrayList<String> allNames, String gameMode, GUI gui){
        this.allNames = allNames;
        this.gameMode = gameMode;
        this.gui = gui;
        buildCore();
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setVisible(true);
    }

    /**
     * The method 'buildCore' add the labels and the buttons to the panel
     */
    private void buildCore(){
        JLabel gameModeLabel = new JLabel(gameMode);
        home = new JButton(gui.getBundle().getString("home"));
        restart = new JButton(gui.getBundle().getString("restart"));

        addALabel(gameModeLabel);

        buildPlayerPanel();
        add(playerPanel);

        buildMovePanel();
        add(movesPanel);

        addAButton(home);
        addAButton(restart);

    }

    /**
     * Method that creates a panel for the players' names.
     */
    private void buildPlayerPanel() {
        playerPanel = new JPanel(new FlowLayout());
        playerLabel = new JLabel();
        playerLabel.setText(gui.getBundle().getString("playingNow") + allNames.get(0));
        playerLabel.setMinimumSize(new Dimension(180,15));
        playerLabel.setMaximumSize(playerLabel.getMinimumSize());
        playerLabel.setPreferredSize(playerLabel.getMinimumSize());
        playerPanel.add(playerLabel);
    }

    /**
     * Method that creates a panel for players' moves.
     */
    private void buildMovePanel(){
        movesPanel = new JPanel(new FlowLayout());
        movesLabel = new JLabel(gui.getBundle().getString("moves0"));
        movesPanel.add(movesLabel);
    }

    /**
     * Method that updates the panel with name of the player, playing at the time, as his moves.
     * @param name name of player that plays at specific moment.
     * @param moves the moves of that player.
     */
    void updatePlayerInfo(String name, int moves){
        playerLabel.setText(gui.getBundle().getString("playingNow") + name);
        movesLabel.setText(gui.getBundle().getString("moves") + moves);
    }

    /**
     * Adds a label in the BoxLayout after customizing the alignments
     *
     * @param label the label we add
     */
    private void addALabel(JLabel label) {
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(label);
    }

    /**
     * Adds a button in the BoxLayout after customizing the alignments
     *
     * @param button the button we add.
     */
    private void addAButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(button);
    }

    JButton getHome(){
        return this.home;
    }

    JButton getRestart(){
        return restart;
    }
}
