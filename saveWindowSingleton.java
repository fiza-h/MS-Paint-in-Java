import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Stack;

/**
 * pops up a singleton window whenever the save button is clicked
 */
public class saveWindowSingleton implements PortionListener
{
    private static saveWindowSingleton saveWindow = new saveWindowSingleton();
    private String timestamp;
    private Button close;
    private Button okay;
    private saveWindowSingleton()
    {
        int width = 20;
        int height = 20;
        ImageIcon closeImg = new ImageIcon("./src/windowButtons/1.png");
        ImageIcon ok = new ImageIcon("./src/windowButtons/2.png");

        close = new Button(680, 225, width, height, closeImg.getImage(), closeImg.getImage());
        okay = new Button(680, 480, width, height, ok.getImage(), ok.getImage());
    }

    public static saveWindowSingleton getInstance()
    {
        return saveWindow;
    }

    public void draw(Graphics g, ImageObserver observer)
    {
        g.setColor(new Color(183, 202, 232));
        g.fillRect(250, 250, 450, 100);

        g.setColor(Color.BLACK);
        Font font = new Font("Monospaced", Font.PLAIN, 18);
        g.setFont(font);
        g.drawString("file saved successfully", 300, 300);
        g.drawString("filename: " + this.timestamp + ".txt", 300, 330);

        close.paint(g, observer);
    }

    public void saveToFile()
    {
        // Create a filename based on the current timestamp
        timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        String file_name = "./src/savedFiles/" + timestamp + ".txt";

        XMLEncoder encoder = null;

        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file_name)));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        ArrayList<Stack> shapes = new ArrayList<>();

        for (Layer a: LayerPanel.layersInPanel)
        {
            shapes.add(a.storedShapes);
        }

        encoder.writeObject(shapes);
        encoder.close();
        System.out.println("xml file saved");
    }

    @Override
    public void onClick(int x, int y)
    {
        close.IsClicked(x, y);
        if (close.IsPressed())
        {
            RibbonBar.showSaveBox = false;
            RibbonBar.fDropDown.get(2).SetPressed(false);
        }
    }

    @Override
    public void onMove(int x, int y)
    {

    }

    @Override
    public void onPress(int x, int y) {

    }

    @Override
    public void onDrag(int x, int y) {

    }

    @Override
    public void onRelease(int x, int y) {

    }
}
