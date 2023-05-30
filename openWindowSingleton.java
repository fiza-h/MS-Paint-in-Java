import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

public class openWindowSingleton implements PortionListener
{
    private static openWindowSingleton openWindow = new openWindowSingleton();
    protected ArrayList<Button> savedFiles = new ArrayList<>();
    private Button clickedFile = new Button();
    private boolean checkClick = false;
    private LayerPanel layerPanel = new LayerPanel(" ");
    private openWindowSingleton()
    {
        ImageIcon open = new ImageIcon("./src/ribbonButtons/img.png");
        ImageIcon cancel = new ImageIcon("./src/ribbonButtons/cross.png");

        savedFiles.add(new Button(200, 160, 100, 40, open.getImage(), open.getImage()));
        savedFiles.add(new Button(300, 160, 100, 40, cancel.getImage(), cancel.getImage()));
    }

    public void fileReader()
    {
        String folderPath = "./src/savedFiles";
        File folder = new File(folderPath);

        int x = 200;
        int y = 200;
        int width = 600;
        int height = 30;
        ImageIcon button_dep = new ImageIcon("./src/ribbonButtons/depressfile.png");
        ImageIcon button_pre = new ImageIcon("./src/ribbonButtons/pressfile.png");

        File[] fileList = folder.listFiles();

        for (int i = 0; i < fileList.length; i++)
        {
            savedFiles.add( new Button(x, y, width, height, fileList[i].getName(), button_dep.getImage(), button_pre.getImage()));
            y+=height;
        }
    }

    public static openWindowSingleton getInstance()
    {
        return openWindow;
    }

    public void draw(Graphics g, ImageObserver observer)
    {
        fileReader();
        for (int i = 0; i < savedFiles.size(); i++)
        {
            if (i > 1)
            {
                savedFiles.get(i).paintTextButton(g, observer);
            } else {
                savedFiles.get(i).paint(g, observer);
            }
        }
        checkClick = true;
    }

    public void readFromFile()
    {
        XMLDecoder decoder = null;
        try {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("./src/savedFiles/" + clickedFile.getText())));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        ArrayList<Stack> temp = (ArrayList<Stack>) decoder.readObject();
        LayerPanel.layersInPanel.clear();
        layerPanel.recreateLayers(temp);
        System.out.println("xml file decoded");
    }

    @Override
    public void onClick(int x, int y)
    {
        if (checkClick) {
            for (int i = 2; i < savedFiles.size(); i++) {
                savedFiles.get(i).IsClicked(x, y);
                if (savedFiles.get(i).IsPressed()) {
                    clickedFile = savedFiles.get(i);
                }
            }
            for (int i = 0; i < 2; i++) {
                savedFiles.get(i).IsClicked(x, y);
                if (savedFiles.get(0).IsPressed()) {
                    readFromFile();
                    RibbonBar.showFiles = false;
                }
                if (savedFiles.get(1).IsPressed())
                {
                    RibbonBar.showFiles = false;
                }
            }
        }
    }

    @Override
    public void onMove(int x, int y) {

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
