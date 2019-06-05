import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class that is the panel we show when button HighScore is pressed.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
class HighScoreMenu extends JPanel {
    static final String HIGH_SCORE_MENU = "High Score Menu";

    private GUI gui;

    private JPanel origin;

    /**
     * The constructor of the class 'HighScoreMenu'. Creation of the panel, layouts and size. Adding components
     * to the panel.
     *
     * @param gui the class that contains all the panels so the panels can communicate.
     */
    HighScoreMenu(GUI gui){
        setName(HIGH_SCORE_MENU);
        this.gui = gui;

        addComponents();
        add(origin);
        setOpaque(false);
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
    }

    /**
     * The method to add components to the panel. First we initialize the components (labels and buttons)
     * and at the same time we add actions to the ones that will perform actions and also we use a ResourceBundle
     * so the names will change when the language does. Then we add the components after we have customize them.
     */
    private void addComponents(){
        //Add Components to Origin Panel
        origin = new JPanel();
        origin.setLayout(new BoxLayout(origin, BoxLayout.PAGE_AXIS));
        origin.setOpaque(false);

        JButton basicSButton = new JButton(new basicSAction(gui.getBundle().getString("bss")));
        JButton doubleSButton = new JButton(new doubleSAction(gui.getBundle().getString("dss")));
        JButton tripleSButton = new JButton(new tripleSAction(gui.getBundle().getString("tss")));
        JButton duelButton = new JButton(new duelAction(gui.getBundle().getString("duels")));
        JButton basicButton = new JButton(new basicAction(gui.getBundle().getString("bs")));
        JButton doubleButton = new JButton(new doubleAction(gui.getBundle().getString("ds")));
        JButton tripleButton = new JButton(new tripleAction(gui.getBundle().getString("ts")));

        JButton back = new JButton(new backAction(gui.getBundle().getString("back")));

        //blank_space vol0
        origin.add(Box.createVerticalStrut(50));

        //basicSButton
        basicSButton.setBackground(new Color(115, 255, 0));
        basicSButton.setForeground(Color.WHITE);
        basicSButton.setFocusPainted(false);
        basicSButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        addAButton(basicSButton, origin);

        //blank_space vol1
        origin.add(Box.createVerticalStrut(10));

        //doubleSButton
        doubleSButton.setBackground(new Color(126, 235, 0));
        doubleSButton.setForeground(Color.WHITE);
        doubleSButton.setFocusPainted(false);
        doubleSButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        addAButton(doubleSButton, origin);

        //blank_space vol2
        origin.add(Box.createVerticalStrut(10));

        //tripleSButton
        tripleSButton.setBackground(new Color(108, 215, 0));
        tripleSButton.setForeground(Color.WHITE);
        tripleSButton.setFocusPainted(false);
        tripleSButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        addAButton(tripleSButton, origin);

        //blank_space vol3
        origin.add(Box.createVerticalStrut(10));

        //duelButton
        duelButton.setBackground(new Color(99, 195, 0));
        duelButton.setForeground(Color.WHITE);
        duelButton.setFocusPainted(false);
        duelButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        addAButton(duelButton, origin);

        //blank_space vol4
        origin.add(Box.createVerticalStrut(10));

        //basicButton
        basicButton.setBackground(new Color(78, 155, 0));
        basicButton.setForeground(Color.WHITE);
        basicButton.setFocusPainted(false);
        basicButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        addAButton(basicButton, origin);

        //blank_space vol5
        origin.add(Box.createVerticalStrut(10));

        //doubleButton
        doubleButton.setBackground(new Color(69, 135, 0));
        doubleButton.setForeground(Color.WHITE);
        doubleButton.setFocusPainted(false);
        doubleButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        addAButton(doubleButton, origin);

        //blank_space vol6
        origin.add(Box.createVerticalStrut(10));

        //tripleButton
        tripleButton.setBackground(new Color(51, 115, 0));
        tripleButton.setForeground(Color.WHITE);
        tripleButton.setFocusPainted(false);
        tripleButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        addAButton(tripleButton, origin);

        //blank_space vol7
        origin.add(Box.createVerticalStrut(30));

        //back
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentY(Component.CENTER_ALIGNMENT);
        origin.add(back);
    }

    /**
     * Adds a button in the BoxLayout after customizing the alignments
     *
     * @param button
     */
    private void addAButton(JButton button, JPanel panel){
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(250,40));
        button.setMaximumSize(button.getMinimumSize());
        button.setPreferredSize(button.getMinimumSize());
        panel.add(button);
    }

    /**
     * Private classes that extends the AbstractAction and make the button that is added an action.
     * This action is to give variable filename a argument that is also the file that contains the
     * high scores for the specific mode, after it calls method makeHighScoreTable with 'filename' as
     * parameter.
     */
    private class duelAction extends AbstractAction{
        duelAction(String name){
            super(name);
        }
        @Override
        public void actionPerformed (ActionEvent e) {
            String filename = "DuelScore.txt";
            makeHighScoreTable(filename);
        }
    }
    private class basicSAction extends AbstractAction{
        basicSAction(String name){
            super(name);
        }
        @Override
        public void actionPerformed (ActionEvent e) {
            String filename = "BasicSScore.txt";
            makeHighScoreTable(filename);
        }
    }
    private class doubleSAction extends AbstractAction{
        doubleSAction(String name){
            super(name);
        }
        @Override
        public void actionPerformed (ActionEvent e) {
            String filename = "DoubleSScore.txt";
            makeHighScoreTable(filename);
        }
    }
    private class tripleSAction extends AbstractAction{
        tripleSAction(String name){
            super(name);
        }
        @Override
        public void actionPerformed (ActionEvent e) {
            String filename = "TripleSScore.txt";
            makeHighScoreTable(filename);
        }
    }
    private class basicAction extends AbstractAction{
        basicAction(String name){
            super(name);
        }
        @Override
        public void actionPerformed (ActionEvent e) {
            String filename = "BasicScore.txt";
            makeHighScoreTable(filename);
        }
    }
    private class doubleAction extends AbstractAction{
        doubleAction(String name){
            super(name);
        }
        @Override
        public void actionPerformed (ActionEvent e) {
            String filename = "DoubleScore.txt";
            makeHighScoreTable(filename);
        }
    }
    private class tripleAction extends AbstractAction{
        tripleAction(String name){
            super(name);
        }
        @Override
        public void actionPerformed (ActionEvent e) {
            String filename = "TripleScore.txt";
            makeHighScoreTable(filename);
        }
    }

    /**
     * The method that creates a new window containing the scores in correct order. It firsts reads the file
     * and puts its data into two separate ArrayLists, one for the names and one for the scores. Then we
     * place them in a 2D Object array that we set as parameter in an object HighScoreTable.
     *
     * @param filename the file to be read
     */
    private void makeHighScoreTable(String filename) {
        try {
            Scanner inputStream = new Scanner(new FileInputStream(filename));
            ArrayList<String> names = new ArrayList<>();
            ArrayList<String> score = new ArrayList<>();

            while(inputStream.hasNextLine()){
              names.add(inputStream.nextLine());
               score.add(inputStream.nextLine());
          }

          inputStream.close();

            //Because our files have specific arrangement with null in names and -1 in scores
            //so there are always 10 sets we have to find the null one and change the in the ArrayLists.
            if (!(names.isEmpty())) {
                for (int i = 0; i < 10; i++) {
                    if (names.get(i).equals("null")) {
                        names.set(i, "");
                        score.set(i, "");
                    }
                }
            }
            else {
                for(int i = 0; i < 10; i++){
                    names.add("");
                    score.add("");
                }
            }
            //The 2D Object array
            Object[][] data = {{1, names.get(0), score.get(0)},
                    {2, names.get(1), score.get(1)},
                    {3, names.get(2), score.get(2)},
                    {4, names.get(3), score.get(3)},
                    {5, names.get(4), score.get(4)},
                    {6, names.get(5), score.get(5)},
                    {7, names.get(6), score.get(6)},
                    {8, names.get(7), score.get(7)},
                    {9, names.get(8), score.get(8)},
                    {10, names.get(9), score.get(9)}};
            HighScoreTable highScoreTable = new HighScoreTable(data, filename, gui);
            highScoreTable.createAndShowGUI(data, filename, gui);
        }catch(FileNotFoundException ex) {
            //Error message to display when there is a problem with the file.
            JOptionPane.showMessageDialog(null,
                    gui.getBundle().getString("errorM"), gui.getBundle().getString("errorMT"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * A private class that extends the AbstractAction and make the button that is added an action.
     * This action is to switch the card (from HighScoreMenu panel to StartMenu)
     */
    private class backAction extends AbstractAction{
        backAction(String name){
            super(name);
        }
        @Override
        public void actionPerformed (ActionEvent e){
            gui.showCard(StartMenu.START_MENU);
        }
    }
}
