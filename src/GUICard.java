import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class GUICard extends JPanel {

    JButton card;

    private Icon backimage;
    private Icon frontimage;

    private Character content;

    private int coordinate;

    private boolean exist;

    public GUICard(Character content){

        exist = true;
        this.content = content;
        backimage = new ImageIcon(getClass().getResource("images/CARDS/gif.gif"));

        frontimage = new ImageIcon(getClass().getResource("images/CARDS/marioimages/image" + content + ".png"));
        card = new JButton();
        card.setIcon(backimage);
        card.setPreferredSize(new Dimension(100,100));
        add(card);
        setOpaque(false);
        setVisible(true);
    }

    void setUnvisible() { setVisible(false); }

    Character getContent() {
        return content;
    }

    Boolean getExist() { return exist; }

    void setExist(boolean exist) { this.exist = exist; }

    void setCoordinate(int coordinate) { this.coordinate = coordinate; }


    void turnCardBackwards(){
        card.setIcon(backimage);
    }

    void ACTION(){
        card.setIcon(frontimage);
        try {
            File soundFile = new File((getClass().getResource("sounds/Box.wav")).toURI());
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();

            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
