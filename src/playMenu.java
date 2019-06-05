import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The class that is the panel we show when button Play is pressed.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
public class playMenu extends JPanel {
    static final String PLAY_MENU = "Play Menu";

    private JSpinner numOfPl;
    private JSpinner numOfBPl;

    private String modeContent = "Basic";

    private JLabel numberOfBotPlayers;

    private GUI gui;

    private JPanel buttonPanel;

    /**
     * The constructor of the class 'playMenu'. Creation of the panel, layouts and size. Adding components
     *  to the panel.
     *
     * @param gui the class that contains all the panels so the panels can communicate.
     */
    public playMenu(GUI gui) {
        setName(PLAY_MENU);
        this.gui = gui;

        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        addComponents();

        setOpaque(false);
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * The method to add components to the panel. First we initialize the components (labels and buttons)
     * and at the same time we add actions to the ones that will perform actions and also we use a ResourceBundle
     * so the names will change when the language does. Then we add the components
     * after we have customize them.
     */
    private void addComponents() {

        JLabel numberOfPlayers = new JLabel(gui.getBundle().getString("numberOfPlayers"));
        JLabel gameMode = new JLabel(gui.getBundle().getString("gameMode"));
        numberOfBotPlayers = new JLabel(gui.getBundle().getString("numberOfBots"));

        JButton cancel = new JButton(new cancelAction(gui.getBundle().getString("cancel")));
        JButton start = new JButton(new startAction(gui.getBundle().getString("start")));

        String[] formats = {gui.getBundle().getString("basic"),gui.getBundle().getString("double"),
                gui.getBundle().getString("triple")};
        JComboBox<String> mode = new JComboBox<>(formats);
        // ActionListener to take the content of the ComboBox.
        mode.addActionListener(e -> {
            JComboBox combo = (JComboBox)e.getSource();
            modeContent = (String)combo.getSelectedItem();
            // The following code lines is to change the variable modeContent back to the english
            // if the language is greek so we can use it.
            assert modeContent != null;
            if(modeContent.equals("Βασικό"))
                modeContent = "Basic";
            if(modeContent.equals("Διπλό"))
                modeContent = "Double";
            if(modeContent.equals("Τριπλό"))
                modeContent = "Triple";
        });
        numOfPl = new JSpinner(new SpinnerNumberModel(1, 1, 4, 1));
        // ActionListener to know when to show the the Spinner numOfBPl.
        numOfPl.addChangeListener(e -> {
            numOfBPl.setValue(0);

            if ((int) numOfPl.getValue() != 1) {
                numOfBPl.setModel(new SpinnerNumberModel(0, 0,
                        (int) numOfPl.getValue() - 1, 1));
                numberOfBotPlayers.setVisible(true);
                numOfBPl.setVisible(true);

            } else if ((int) numOfPl.getValue() == 1) {
                numOfBPl.setVisible(false);
                numberOfBotPlayers.setVisible(false);

            }
        });

        numOfBPl = new JSpinner();

        //blank_space vol0
        buttonPanel.add(Box.createVerticalStrut(30));

        //Game Mode
        gameMode.setFont(new Font("Tahoma", Font.BOLD, 20));
        addALabel(gameMode);

        //blank_space vol1
        buttonPanel.add(Box.createVerticalStrut(5));

        mode.setPreferredSize(new Dimension(130, 40));
        mode.setMaximumSize(mode.getPreferredSize());
        addAComboBox(mode);

        //blank_space vol2
        buttonPanel.add(Box.createVerticalStrut(20));

        //Number of Players
        numberOfPlayers.setFont(new Font("Tahoma", Font.BOLD, 20));
        addALabel(numberOfPlayers);

        //blank_space vol3
        buttonPanel.add(Box.createVerticalStrut(5));

        numOfPl.setPreferredSize(new Dimension(150, 40));
        numOfPl.setMaximumSize(numOfPl.getPreferredSize());
        addASpinner(numOfPl);

        //blank_space vol4
        buttonPanel.add(Box.createVerticalStrut(20));

        //Number of Bot Players
        numberOfBotPlayers.setFont(new Font("Tahoma", Font.BOLD, 20));
        addALabel(numberOfBotPlayers);

        //blank_space vol5
        buttonPanel.add(Box.createVerticalStrut(5));

        numOfBPl.setPreferredSize(new Dimension(150, 40));
        numOfBPl.setMaximumSize(numOfBPl.getPreferredSize());
        addASpinner(numOfBPl);
        numberOfBotPlayers.setVisible(false);
        numOfBPl.setVisible(false);

        //blank_space vol6
        buttonPanel.add(Box.createVerticalStrut(20));

        //start
        start.setBackground(new Color(147, 119, 182));
        start.setForeground(Color.WHITE);
        start.setFocusPainted(false);
        start.setFont(new Font("Tahoma", Font.BOLD, 20));
        addAButton(start);
        start.setVisible(true);

        //blank_space vol6
        buttonPanel.add(Box.createVerticalStrut(20));

        //cancel
        cancel.setBackground(new Color(182, 31, 13));
        cancel.setForeground(Color.WHITE);
        cancel.setFocusPainted(false);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 20));
        addAButton(cancel);
    }

    /**
     * Adds a label in the BoxLayout after customizing the alignments.
     *
     * @param label the label we add.
     */
    private void addALabel(JLabel label) {
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.TOP_ALIGNMENT);
        buttonPanel.add(label);
    }

    /**
     * Adds a button in the BoxLayout after customizing the alignments.
     *
     * @param button the button we add.
     */
    private void addAButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(130,40));
        button.setMaximumSize(button.getMinimumSize());
        button.setPreferredSize(button.getMinimumSize());
        buttonPanel.add(button);
    }

    /**
     * Adds a spinner in the BoxLayout after customizing the alignments.
     *
     * @param spinner the Spinner we add.
     */
    private void addASpinner(JSpinner spinner) {
        spinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        spinner.setAlignmentY(Component.CENTER_ALIGNMENT);
        buttonPanel.add(spinner);
    }

    /**
     * Adds a combo box in the BoxLayout after customizing the alignments.
     *
     * @param comboBox the ComboBox we add.
     */
    private void addAComboBox(JComboBox<String> comboBox) {
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboBox.setAlignmentY(Component.CENTER_ALIGNMENT);
        buttonPanel.add(comboBox);
    }

    /**
     * Method that refreshes the panel.
     */
    private void refresh() {
        numOfPl.setValue(1);
        numOfBPl.setValue(0);
        numOfBPl.setVisible(false);
        numberOfBotPlayers.setVisible(false);
    }

    private JFrame frame;

    JFrame getFrame() {
        return frame;
    }

    /**
     * Method that creates a new window, with a frame that contains panel type playerName.
     */
    private void createWindow() {
        frame = new JFrame();
        playerName playerName = new playerName(gui, (int) numOfPl.getValue(), (int) numOfBPl.getValue(),
                modeContent, getFrame());
        frame.add(playerName);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    int getNumOfPl() {
        return (int) numOfPl.getValue();
    }

    int getNumOfBPl() {
        return (int) numOfBPl.getValue();
    }

    String getModeContent() {
        return modeContent;
    }

    /**
     * A private class that extends the AbstractAction and make the button that is added an action.
     * This action is to switch the card (from playMenu panel to StartMenu) and also to refresh the panel
     * playMenu.
     */
    private class cancelAction extends AbstractAction {
        cancelAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            refresh();
            gui.showCard(StartMenu.START_MENU);
        }
    }

    /**
     * A private class that extends the AbstractAction and make the button that is added an action.
     * This action is to call the method createWindow(). In that window the players can insert their names
     * and/or select the level of the bots.
     */
    private class startAction extends AbstractAction {
        startAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            createWindow();
        }

    }
}
