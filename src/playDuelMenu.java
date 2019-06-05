import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
public class playDuelMenu extends JPanel {
    static final String PLAY_DUEL_MENU = "Play Duel Menu";

    private JComboBox<String> player2;

    private JLabel type;
    private JLabel playerLabel1;
    private JLabel playerLabel2;
    private JLabel versusLabel;

    private JButton start;
    private JButton cancel;

    private JFrame frame;

    private GUI gui;
    private int player2Content=0;

    /**
     * The constructor of the class playDuelMenu. It creates the panel with the help of the methods
     * 'initComponents' & 'addComponents'.
     *
     * @param gui the class that contains all the panels so the panels can communicate.
     */
    public playDuelMenu(GUI gui){
        setName(PLAY_DUEL_MENU);
        this.gui = gui;

        initComponents();
        addComponents();
        setOpaque(false);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    /**
     * The meyhod 'initComponents' initialize and customize our components.
     */
    private void initComponents(){
        type = new JLabel(gui.getBundle().getString("choose2ndPlayersType"));
        type.setFont(new Font("Tahoma", Font.BOLD, 25));
        type.setAlignmentX(Component.CENTER_ALIGNMENT);
        type.setAlignmentY(Component.TOP_ALIGNMENT);
        type.setOpaque(false);

        versusLabel = new JLabel(gui.getBundle().getString("vs"));
        versusLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        versusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        versusLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        playerLabel1 = new JLabel(gui.getBundle().getString("playerNo1"));
        playerLabel1.setFont(new Font("Tahoma", Font.BOLD, 40));
        playerLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerLabel1.setAlignmentY(Component.CENTER_ALIGNMENT);

        playerLabel2 = new JLabel(gui.getBundle().getString("playerNo2"));
        playerLabel2.setFont(new Font("Tahoma", Font.BOLD, 27));
        playerLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerLabel2.setAlignmentY(Component.CENTER_ALIGNMENT);

        start = new JButton(new startAction(gui.getBundle().getString("start")));
        start.setBackground(new Color(1, 120, 1));
        start.setForeground(Color.WHITE);
        start.setFocusPainted(false);
        start.setFont(new Font("Tahoma", Font.BOLD, 20));
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.setAlignmentY(Component.CENTER_ALIGNMENT);
        start.setMinimumSize(new Dimension(200,40));
        start.setMaximumSize(start.getMinimumSize());
        start.setPreferredSize(start.getMinimumSize());

        cancel = new JButton(new cancelAction(gui.getBundle().getString("cancel")));
        cancel.setBackground(new Color(182, 31, 13));
        cancel.setForeground(Color.WHITE);
        cancel.setFocusPainted(false);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 20));
        cancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancel.setAlignmentY(Component.CENTER_ALIGNMENT);
        cancel.setMinimumSize(new Dimension(200,40));
        cancel.setMaximumSize(cancel.getMinimumSize());
        cancel.setPreferredSize(cancel.getMinimumSize());

        String[] format = {gui.getBundle().getString("human"), "Co-op"};

        player2 = new JComboBox<>(format);
        //ActionListener so we can take the content of the ComboBox.
        player2.addActionListener(e -> {
            JComboBox combo = (JComboBox)e.getSource();
            String temp = (String)combo.getSelectedItem();
            assert temp != null;
            if(temp.equals("Άνθρωπος")){
                temp = "Human";
            }
            if(!(temp.equals("Human"))){
                player2Content=1;
            }
            else{
                player2Content=0;
            }
        });
        player2.setAlignmentX(Component.CENTER_ALIGNMENT);
        player2.setAlignmentY(Component.CENTER_ALIGNMENT);
        player2.setMaximumSize(new Dimension(150,60));
    }

    /**
     * The method 'addComponents' adds the components in the panel.
     */
    private void addComponents(){
        add(type);

        add(Box.createVerticalStrut(40));
        add(playerLabel1);

        add(Box.createVerticalStrut(25));
        add(versusLabel);

        add(Box.createVerticalStrut(23));
        add(playerLabel2);
        add(player2);

        add(Box.createVerticalStrut(20));
        add(start);

        add(Box.createVerticalStrut(15));
        add(cancel);
    }

    private JFrame getFrame() {
        return frame;
    }

    /**
     * A private class that extends the AbstractAction and make the button that is added an action.
     * This action creates a new window that the players can insert their names
     * and/or select the level of the bots.
     */
    private class startAction extends AbstractAction {
        startAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame = new JFrame();
            playerName playerName = new playerName(gui, 2, player2Content, getFrame());
            frame.add(playerName);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

    /**
     * A private class that extends the AbstractAction and make the button that is added an action.
     * This action is to switch the card (from playDuelMenu panel to StartMenu).
     */
    private class cancelAction extends AbstractAction {
        cancelAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.showCard(StartMenu.START_MENU);
        }
    }
}
