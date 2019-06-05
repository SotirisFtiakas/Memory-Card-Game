import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * The class that is the window that contains the high scores.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
class HighScoreTable extends JPanel {

    private String filename;
    private GUI gui;

    /**
     * The constructor of the class HighScoreTable. Creation of the panel, layouts and size. Adding components
     *  to the panel.
     *
     * @param data the 2D array that contains the date we export from the score files
     * @param filename the file that has already been read and now it helps in case we want to delete the contents
     *                 of it.
     * @param gui the class that contains all the panels so the panels can communicate in this case it helps us with
     *            the language change.
     */
    HighScoreTable(Object[][] data, String filename, GUI gui){
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.filename = filename;
        this.gui = gui;

        String[] columnNames = {gui.getBundle().getString("rank"),
                gui.getBundle().getString("plsName"), gui.getBundle().getString("score")};

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500,200));
        table.setFillsViewportHeight(true);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        JButton delete = new JButton(new deleteAction(gui.getBundle().getString("deleteScore")));
        delete.setBackground(new Color(182, 31, 13));
        delete.setForeground(Color.WHITE);
        delete.setFocusPainted(false);
        add(delete);
    }

    /**
     * A private class that extends the AbstractAction and make the button that is added an action.
     * This action is to delete the content of the file by just opening it with a PrintWriter and then closing it.
     */
    private class deleteAction extends AbstractAction{
        deleteAction(String name){
            super(name);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                PrintWriter pw = new PrintWriter(filename);
                pw.close();
            }catch(FileNotFoundException ex) {
                //Error message to display when there is a problem with the file.
                JOptionPane.showMessageDialog(null,
                        gui.getBundle().getString("errorM"), gui.getBundle().getString("errorMT"),
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    static void createAndShowGUI(Object[][] data, String filename, GUI gui){
        JFrame frame = new JFrame(gui.getBundle().getString("highScoreTable"));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        HighScoreTable newContentPane = new HighScoreTable(data, filename,gui);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setMinimumSize(new Dimension(400,200));
        frame.setMaximumSize(frame.getMinimumSize());
        frame.setPreferredSize(frame.getMinimumSize());
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}