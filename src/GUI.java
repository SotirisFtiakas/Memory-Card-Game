import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * The class that initiates the graphics of the user interface
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
public class GUI extends JPanel {
    private CardLayout cardLayout = new CardLayout();
    private StartMenu startMenu;
    private playMenu playMenu;
    private LanguageMenu languageMenu;
    private playDuelMenu playDuelMenu;
    private HighScoreMenu highScoreMenu;
    private gamePanel gamePanel;

    private Locale locale;
    private ResourceBundle bundle;

    gamePanel getGamePanel() {
        return gamePanel;
    }

    private playerName playerName;
    private ArrayList<String> allNames;

    /**
     * The constructor of class 'GUI'. It initiates the many panels and add them to itself, as a JPanel.
     * Using the Card Layout we achieve to have different panels created at the same time so can go
     * through them any time.
     */
    public GUI(){
        Locale.setDefault(new Locale("en", "US"));
        locale = new Locale("en","US");
        bundle = ResourceBundle.getBundle("MessageBundle", locale);
        startMenu = new StartMenu(this);
        playMenu = new playMenu(this);
        languageMenu = new LanguageMenu(this);
        playDuelMenu = new playDuelMenu(this);
        highScoreMenu = new HighScoreMenu(this);
        playerName = new playerName(this,playMenu.getNumOfPl(), playMenu.getNumOfBPl(),
                playMenu.getModeContent(), playMenu.getFrame());
        gamePanel = new gamePanel(this);
        allNames = new ArrayList<>();
        allNames.add("");

        setLayout(cardLayout);
        add(startMenu,startMenu.getName());
        add(languageMenu,languageMenu.getName());
        add(playMenu, playMenu.getName());
        add(playDuelMenu,playDuelMenu.getName());
        add(highScoreMenu,highScoreMenu.getName());
        add(playerName, playerName.getName());
        add(gamePanel,gamePanel.getName());
        setOpaque(false);
    }

    /**
     * The method 'build' simulates the operation of the constructor with some differences that help us
     * in the language change
     */
    void build(){
        bundle = ResourceBundle.getBundle("MessageBundle", locale);
        startMenu = new StartMenu(this);
        playMenu = new playMenu(this);
        languageMenu = new LanguageMenu(this);
        playDuelMenu = new playDuelMenu(this);
        highScoreMenu = new HighScoreMenu(this);
        playerName = new playerName(this,playMenu.getNumOfPl(), playMenu.getNumOfBPl(),
                playMenu.getModeContent(), playMenu.getFrame());
        gamePanel = new gamePanel(this);
        allNames = new ArrayList<>();
        allNames.add("");

        setLayout(cardLayout);
        add(startMenu,startMenu.getName());
        add(languageMenu,languageMenu.getName());
        add(playMenu, playMenu.getName());
        add(playDuelMenu,playDuelMenu.getName());
        add(highScoreMenu,highScoreMenu.getName());
        add(playerName, playerName.getName());
        add(gamePanel,gamePanel.getName());
    }

    /**
     * The method that switches the panels.
     * @param key
     */
    void showCard(String key){
        cardLayout.show(this,key);
    }

    /**
     * Method to create and display the frame that contains all the panels.
     */
    static void createAndShowGui(){
        JLabel contentPane = new JLabel();
        Icon backgroundImage = new ImageIcon(GUI.class.getResource("images/startingscreen.gif"));
        contentPane.setIcon(backgroundImage);
        contentPane.setLayout(new BorderLayout());
        JFrame frame = new JFrame("Memory Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(contentPane);
        frame.getContentPane().add(new GUI());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Thread tsoundtrack = new Thread(new Soundtrack());
        tsoundtrack.start();
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    ResourceBundle getBundle() {
        return bundle;
    }
}
