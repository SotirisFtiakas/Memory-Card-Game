import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Locale;

/**
 * The class that is the panel we show when button Language is pressed.
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
class LanguageMenu extends JPanel {
    static final String LANGUAGE_MENU = "Language";

    /**
     * The constructor of the class 'LanguageMenu'. Creation of the panel, layouts and size. Adding components
     *  to the panel.
     *
     * @param gui the class that contains all the panels so the panels can communicate.
     */
    LanguageMenu(GUI gui){
        setName(LANGUAGE_MENU);
        setOpaque(false);
        JButton enButton = new JButton();
        enButton.setMinimumSize(new Dimension(225,150));
        enButton.setMaximumSize(enButton.getMinimumSize());
        enButton.setPreferredSize(enButton.getMinimumSize());
        JButton grButton = new JButton();
        grButton.setMinimumSize(new Dimension(225,150));
        grButton.setMaximumSize(grButton.getMinimumSize());
        grButton.setPreferredSize(grButton.getMinimumSize());

        //Adding ActionListeners to enButton and grButton. When the button is pressed the game's language
        //changes from greek to english or from english to greek and go back to the StartMenu.
        //To do so after the locale is changed, we have to rebuilt the original panel (GUI).
        enButton.addActionListener(e -> {
            gui.setLocale(new Locale("en", "US"));
            gui.removeAll();
            gui.revalidate();
            gui.build();
            gui.showCard(StartMenu.START_MENU);
        });

        grButton.addActionListener(e -> {
            gui.setLocale(new Locale("el", "GR"));
            gui.removeAll();
            gui.revalidate();
            gui.build();
            gui.showCard(StartMenu.START_MENU);
        });

        //blank_space vol0
        add(Box.createVerticalStrut(60));

        loadImage("images/UK.png", enButton);
        enButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        enButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(enButton);

        //blank_space vol1
        add(Box.createVerticalStrut(80));

        loadImage("images/Greece.png", grButton);
        grButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        grButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(grButton);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    /**
     * Method that loads an image to a button.
     *
     * @param filename the path we follow in order to find the image
     * @param button the button we want to customize
     */
    private void loadImage(String filename, JButton button) {
        URL imageURL = this.getClass().getResource(filename);
        button.setIcon(new ImageIcon(imageURL));
    }
}