import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The class of the Main Menu of the game.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
class StartMenu extends JPanel{
    static final String START_MENU = "Start Menu";
    private GUI gui;

    /**
     * The constructor of the class 'StartMenu'. Creation of the panel, layouts and size. Adding components
     * to the panel.
     *
     * @param gui the class that contains all the panels so the panels can communicate.
     */
    StartMenu(GUI gui){
        setName(START_MENU);
        this.gui = gui;
        setVisible(true);

        addComponents();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(false);
    }

    /**
     * The method to add components to the panel. First we initialize the components (labels and buttons)
     * and at the same time we add actions to the ones that will perform actions and also we use a ResourceBundle
     * so the names will change when the language does. Then we add the components after we have customize them.
     */
    private void addComponents(){
        JLabel introduction = new JLabel(gui.getBundle().getString("greetings"));
        JButton playduel = new JButton(new playduelAction(gui.getBundle().getString("duel")));
        JButton play = new JButton(new playAction(gui.getBundle().getString("play")));
        JButton highscores = new JButton(new highscoresAction(gui.getBundle().getString("highscores")));
        JButton language = new JButton(new languageAction(gui.getBundle().getString("language")));
        JButton exit = new JButton(new exitAction(gui.getBundle().getString("exit")));

        //blank_space vol0
        add(Box.createVerticalStrut(15));

        //introduction
        introduction.setFont(new Font("Tahoma", Font.BOLD, 30));
        addALabel(introduction);

        //blank_space vol1
        add(Box.createVerticalStrut(35));

        //play
        play.setBackground(new Color(43, 60, 182));
        play.setForeground(Color.WHITE);
        play.setFocusPainted(false);
        play.setFont(new Font("Tahoma", Font.BOLD, 20));
        addAButton(play);

        //blank_space vol2
        add(Box.createVerticalStrut(20));

        //playduel
        playduel.setBackground(new Color(255, 165, 0));
        playduel.setForeground(Color.WHITE);
        playduel.setFocusPainted(false);
        playduel.setFont(new Font("Tahoma", Font.BOLD, 20));
        addAButton(playduel);

        //blank_space vol3
        add(Box.createVerticalStrut(20));

        //highscores
        highscores.setBackground(new Color(124, 252, 0));
        highscores.setForeground(Color.WHITE);
        highscores.setFocusPainted(false);
        highscores.setFont(new Font("Tahoma", Font.BOLD, 20));
        addAButton(highscores);

        //blank_space vol4
        add(Box.createVerticalStrut(20));

        //options
        language.setBackground(new Color(182, 9, 136));
        language.setForeground(Color.WHITE);
        language.setFocusPainted(false);
        language.setFont(new Font("Tahoma", Font.BOLD, 20));
        addAButton(language);

        //blank_space vol5
        add(Box.createVerticalStrut(20));

        //exit
        exit.setBackground(new Color(182, 31, 13));
        exit.setForeground(Color.WHITE);
        exit.setFocusPainted(false);
        exit.setFont(new Font("Tahoma", Font.BOLD, 20));
        addAButton(exit);
    }

    /**
     * Adds a label in the BoxLayout after customizing the alignments
     *
     * @param label the label we add.
     */
    private void addALabel(JLabel label) {
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.TOP_ALIGNMENT);
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
        button.setMinimumSize(new Dimension(200,40));
        button.setMaximumSize(button.getMinimumSize());
        button.setPreferredSize(button.getMinimumSize());
        add(button);
    }

    /**
     * A private class that extends the AbstractAction and make the button that is added an action.
     * This action is to switch the card (from Start Menu panel to playDuelMenu)
     */
    private class playduelAction extends AbstractAction {
        playduelAction(String name){
            super(name);
        }

        @Override
        public void actionPerformed (ActionEvent e){
            gui.showCard(playDuelMenu.PLAY_DUEL_MENU);
        }
    }

    /**
     * A private class that extends the AbstractAction and make the button that is added an action.
     * This action is to switch the card (from Start Menu panel to playMenu)
     */
    private class playAction extends AbstractAction {
        playAction(String name){
            super(name);
        }

        @Override
        public void actionPerformed (ActionEvent e){
            gui.showCard(playMenu.PLAY_MENU);
        }
    }

    /**
     * A private class that extends the AbstractAction and make the button that is added an action.
     * This action is to switch the card (from Start Menu panel to HighScoreMenu)
     */
    private class highscoresAction extends AbstractAction {
        highscoresAction(String name){
            super(name);
        }

        @Override
        public void actionPerformed (ActionEvent e){
            gui.showCard(HighScoreMenu.HIGH_SCORE_MENU);
        }
    }

    /**
     * A private class that extends the AbstractAction and make the button that is added an action.
     * This action is to switch the card (from Start Menu panel to LanguageMenu)
     */
    private class languageAction extends AbstractAction {
        languageAction(String name){
            super(name);
        }

        @Override
        public void actionPerformed (ActionEvent e){
            gui.showCard(LanguageMenu.LANGUAGE_MENU);
        }
    }

    /**
     * A private class that extends the AbstractAction and make the button that is added an action.
     * This action is to close the window and exit the program.
     */
    private class exitAction extends AbstractAction {
        exitAction(String name){
            super(name);
        }

        @Override
        public void actionPerformed (ActionEvent e){
            Component component = (Component) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
            System.exit(0);
        }
    }
}