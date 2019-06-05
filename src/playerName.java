import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * The class that is the panel we show in a new frame before the game start
 * so the players can insert their names.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
public class playerName extends JPanel {
    private static final String PLAYER_NAME = "Player Name";
    private JButton save;
    private String gameMode;

    private textFieldName[] panelarray;
    private JLabel[] labelarray;
    private JComboBox[] comboBoxes;
    private String[] comboBoxesContent = {"0","0","0"};

    private JPanel textFieldPanel;
    private JPanel botPanel;

    private int numberOfPlayers;
    private int numberOfBotPlayers;
    private JFrame playerNameFrame;

    private ArrayList<String> allNames;

    private GUI gui;

    /**
     * The first constructor of the class 'playMenu'. Creation of the panel, layouts and size.
     * Adding components to the panel. We use it for the Duel Mode.
     *
     * @param gui the class that contains all the panels so the panels can communicate.
     * @param numberOfPlayers the number of players.
     * @param numberOfBotPlayers the number of bot players.
     * @param frame the new frame that contains this panel.
     */
    public playerName(GUI gui,int numberOfPlayers, int numberOfBotPlayers, JFrame frame) {
        setName(PLAYER_NAME);
        playerNameFrame = frame;
        this.gui = gui;
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfBotPlayers = numberOfBotPlayers;
        this.gameMode = "Duel";

        allNames = new ArrayList<>();

        save = new JButton(new saveAction("Save"));

        textFieldPanel = new JPanel();
        botPanel = new JPanel();

        panelarray = new textFieldName[2];
        labelarray = new JLabel[1];
        comboBoxes = new JComboBox[1];

        String name,name2,botname;

        String[] levels = {"Goldfish", "Kangaroo", "Elephant"};

        if(numberOfBotPlayers!=0){
            name = gui.getBundle().getString("nameDuel");
            panelarray[0] = new textFieldName(name);
            textFieldPanel.add(panelarray[0]);
            botname = "Co-op";
            labelarray[0] = new JLabel(botname);
            comboBoxes[0] = new JComboBox<>(levels);
            comboBoxesContent[0]="Goldfish";
            //ActionListener to take the content of the ComboBox
            comboBoxes[0].addActionListener(e -> {
                JComboBox combo = (JComboBox)e.getSource();
                comboBoxesContent[0] = (String)combo.getSelectedItem();
            });
            botPanel.add(labelarray[0]);
            botPanel.add(comboBoxes[0]);
            botPanel.setLayout(new FlowLayout());

            add(textFieldPanel);
            add(botPanel);
        }else{
            name = gui.getBundle().getString("name1");
            name2 = gui.getBundle().getString("name2");
            panelarray[0] = new textFieldName(name);
            textFieldPanel.add(panelarray[0]);
            panelarray[1] = new textFieldName(name2);
            textFieldPanel.add(panelarray[1]);
            add(textFieldPanel);
        }
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel,BoxLayout.PAGE_AXIS));
        add(save);
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
    }

    /**
     * The constructor of the class 'playMenu'. Creation of the panel, layouts and size. Adding components
     *  to the panel. We use it for the basic,double and triple mode.
     *
     * @param gui the class that contains all the panels so the panels can communicate.
     * @param numberOfPlayers the number of players.
     * @param numberOfBotPlayers the number of bot players.
     * @param gameMode the game's mode
     * @param frame the new frame that contains this panel.
     */
    public playerName(GUI gui,int numberOfPlayers, int numberOfBotPlayers, String gameMode, JFrame frame){
        setName(PLAYER_NAME);
        playerNameFrame = frame;
        this.gui = gui;
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfBotPlayers = numberOfBotPlayers;
        this.gameMode = gameMode;

        allNames = new ArrayList<>();

        save = new JButton(new saveAction(gui.getBundle().getString("save")));

        String[] levels = {gui.getBundle().getString("goldfish"),
                gui.getBundle().getString("kangaroo"), gui.getBundle().getString("elephant")};

        textFieldPanel = new JPanel();
        botPanel = new JPanel();

        //An array of objects that will help us take the players' names.
        panelarray = new textFieldName[4];

        labelarray = new JLabel[3];
        comboBoxes = new JComboBox[3];

        String name;
        String botname;

        for(int i = 0; i < numberOfPlayers-numberOfBotPlayers; i++){
            name = gui.getBundle().getString("name") + (i+1) ;
            panelarray[i] = new textFieldName(name);
            textFieldPanel.add(panelarray[i]);
        }

        add(textFieldPanel);

        for (int j = 0; j < numberOfBotPlayers; j++){
            botname = "Co-op No" + (j+1);
            labelarray[j] = new JLabel(botname);
            comboBoxes[j] = new JComboBox<>(levels);
            comboBoxesContent[j]=gui.getBundle().getString("goldfish");
            //ActionListeners to take the contents of the ComboBoxes
            if(j==0){
                comboBoxes[0].addActionListener(e -> {
                    JComboBox combo = (JComboBox)e.getSource();
                    comboBoxesContent[0] = (String)combo.getSelectedItem();
                });
            }
            if(j==1){
                comboBoxes[1].addActionListener(e -> {
                    JComboBox combo = (JComboBox)e.getSource();
                    comboBoxesContent[1] = (String)combo.getSelectedItem();
                });
            }
            if(j==2){
                comboBoxes[2].addActionListener(e -> {
                    JComboBox combo = (JComboBox)e.getSource();
                    comboBoxesContent[2] = (String)combo.getSelectedItem();
                });
            }
            for(int i = 0; i < numberOfBotPlayers; i++){
                if(comboBoxesContent[i].equals("Χρυσόψαρο"))
                    comboBoxesContent[i] = "Goldfish";
                if(comboBoxesContent[i].equals("Καγκουρό"))
                    comboBoxesContent[i] = "Kangaroo";
                if(comboBoxesContent[i].equals("Ελέφαντας"))
                    comboBoxesContent[i] = "Elephant";
            }
            botPanel.add(labelarray[j]);
            botPanel.add(comboBoxes[j]);
        }
        botPanel.setLayout(new FlowLayout());

        add(botPanel);

        textFieldPanel.setLayout(new BoxLayout(textFieldPanel,BoxLayout.PAGE_AXIS));
        add(save);
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
    }


    /**
     * A private class that creates a panel containing a textField.
     */
    private class textFieldName extends JPanel{

        private JTextField textField;

        textFieldName(String name) {
            setLayout(new GridLayout(1, 2));
            JLabel userLabel = new JLabel(name + " : ");
            textField = new JTextField(10);
            textField.setDocument(new JTextFieldLimit(10));

            add(userLabel);
            add(textField);
        }

        String getText() { return textField.getText(); }

        /**
         * A private inner class that help us put a limit in acceptable characters in the
         * names of players.
         */
        private class JTextFieldLimit extends PlainDocument{
            private int limit;

            JTextFieldLimit(int limit){
                super();
                this.limit = limit;
            }

            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
                if (str == null)
                    return;
                if((getLength() + str.length()) <= limit){
                    super.insertString(offset,str,attr);
                }
            }
        }
    }

    /**
     * A private class that extends the AbstractAction and make the button that is added an action.
     * This action checks if the players' names are correct, meaning no blank names,no spaces & no symbols
     * like !,@,#,$,%,^.....etc. After that we initiate the game.
     */
    private class saveAction extends AbstractAction {
        saveAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean boolIfEmpty = true; //If 'boolIfEmpty' is false means that there is al least one blank
                                        // field in the names.
            boolean boolIfWrongInput = true;//If 'boolIfWrongInput' is false means that there is at least
                                            //one forbidden symbol in at least one of the names.
            for(int w = 0; w < numberOfPlayers - numberOfBotPlayers; w++){
                if(panelarray[w].getText().trim().isEmpty()){
                    boolIfEmpty = false;
                }
                else {
                    //Using the values from the ASCII Table we check for the unwanted symbols
                    for(int q = 32; q < 48; q++){
                        if(panelarray[w].getText().contains(String.valueOf((char) q)))
                            boolIfWrongInput = false;
                    }
                    for(int q = 58; q < 65; q++){
                        if(panelarray[w].getText().contains(String.valueOf((char) q)))
                            boolIfWrongInput = false;
                    }
                    for(int q = 91; q < 97; q++){
                        if(panelarray[w].getText().contains(String.valueOf((char) q)))
                            boolIfWrongInput = false;
                    }
                    for(int q = 123; q < 127; q++){
                        if(panelarray[w].getText().contains(String.valueOf((char) q)))
                            boolIfWrongInput = false;
                    }
                }
            }
            //If the names are ok we initiates the game
            if(boolIfEmpty && boolIfWrongInput){
                for (int i = 0; i < numberOfPlayers - numberOfBotPlayers; i++) {
                    allNames.add(panelarray[i].getText());
                }
                if (numberOfBotPlayers != 0) {
                    for (int j = 0; j < numberOfBotPlayers; j++) {
                        allNames.add(labelarray[j].getText() + "-" + comboBoxesContent[j]);
                    }
                }

                int rows;
                int cols;
                switch (gameMode) {
                    case "Basic":
                        rows = 4;
                        cols = 6;
                        break;
                    case "Double":
                        rows = 6;
                        cols = 8;
                        break;
                    case "Duel":
                        rows = 6;
                        cols = 8;
                        break;
                    default:
                        rows = 6;
                        cols = 6;
                        break;
                }
                //Depending on the game mode we start the matching game
                gui.getGamePanel().setRows(rows);
                gui.getGamePanel().setCols(cols);
                gui.getGamePanel().setNumberOfPlayers(numberOfPlayers);
                gui.getGamePanel().setNumberOfBotPlayers(numberOfBotPlayers);
                gui.getGamePanel().setAllNames(allNames);
                gui.getGamePanel().setGameMode(gameMode);
                gui.getGamePanel().build();
                playerNameFrame.setVisible(false);
                gui.showCard(gamePanel.GAME_PANEL);
            }
            else{
                //If there is a problem with the players' names we show a warning.
                if(!boolIfEmpty){
                    JOptionPane.showMessageDialog(null,
                            gui.getBundle().getString("warningM1"),
                            gui.getBundle().getString("warningMT"),
                            JOptionPane.WARNING_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null,
                            gui.getBundle().getString("warningM2"),
                            gui.getBundle().getString("warningMT"),
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}