import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.beans.XMLEncoder;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RibbonBar implements PortionListener
{
    protected ArrayList<Button> rButtons = new ArrayList<>();
    protected static ArrayList<Button> fDropDown = new ArrayList<>();
    protected ArrayList<Button> eDropDown = new ArrayList<>();
    private boolean showFDropDown = false;
    private boolean showEDropDown = false;
    protected static boolean showSaveBox = false;
    protected static boolean showFiles = false;
    protected saveWindowSingleton saveWindow = saveWindowSingleton.getInstance();
    protected openWindowSingleton openWindow = openWindowSingleton.getInstance();
//    private String timeStamp;
    protected boolean undo = false;
    protected boolean redo = false;
    public RibbonBar()
    {
        setButtons();
    }

    private void setButtons()
    {
        int width = 50;
        int height = 20;
        ImageIcon file_dep = new ImageIcon("./src/ribbonButtons/1.png");
        ImageIcon file_pre = new ImageIcon("./src/ribbonButtons/3.png");
        ImageIcon edit_dep = new ImageIcon("./src/ribbonButtons/2.png");
        ImageIcon edit_pre = new ImageIcon("./src/ribbonButtons/4.png");

        rButtons.add(new Button(0, 0, width, height,"file", file_dep.getImage(), file_pre.getImage()));
        rButtons.add(new Button(width, 0, width, height, "edit", edit_dep.getImage(), edit_pre.getImage()));

        int d_width = 100;
        int d_height = 40;
        ImageIcon new_dep = new ImageIcon("./src/ribbonButtons/12.png");
        ImageIcon new_pre = new ImageIcon("./src/ribbonButtons/13.png");
        ImageIcon open_dep = new ImageIcon("./src/ribbonButtons/10.png");
        ImageIcon open_pre = new ImageIcon("./src/ribbonButtons/11.png");
        ImageIcon save_dep = new ImageIcon("./src/ribbonButtons/8.png");
        ImageIcon save_pre = new ImageIcon("./src/ribbonButtons/9.png");

        fDropDown.add(new Button(0, height, d_width, d_height, "new file; ctrl+n", new_dep.getImage(), new_pre.getImage()));
        fDropDown.add(new Button(0, height+d_height, d_width, d_height, "open file; ctrl+o", open_dep.getImage(), open_pre.getImage()));
        fDropDown.add(new Button(0, height+(d_height*2), d_width, d_height, "save file; ctrl+s", save_dep.getImage(), save_pre.getImage()));

        ImageIcon undo_dep = new ImageIcon("./src/ribbonButtons/File.png");
        ImageIcon undo_pre = new ImageIcon("./src/ribbonButtons/7.png");
        ImageIcon redo_dep = new ImageIcon("./src/ribbonButtons/5.png");
        ImageIcon redo_pre = new ImageIcon("./src/ribbonButtons/6.png");
        eDropDown.add(new Button(width, height, d_width, d_height, "undo; ctrl+z", undo_dep.getImage(), undo_pre.getImage()));
        eDropDown.add(new Button(width, height+d_height, d_width, d_height, "redo; ctrl+y", redo_dep.getImage(), redo_pre.getImage()));
    }

    public void paint(Graphics g, ImageObserver observer)
    {
        int stroke = 2;
        g.setColor(new Color(183, 202, 232));
        g.fill3DRect(0, 0, 1200, 30, true);
        g.setColor(Color.white);
        g.fill3DRect(stroke, stroke, 1200-(stroke*2), 30-(stroke*2), true);

        //paints file and edit buttons
        for (Button b : rButtons)
        {
            b.paint(g, observer);
        }

        for (Button b : rButtons)
        {
            if (b.isHover())
            {
                b.getTooltip().paintToolTip(g);
            }
        }

        // file drop down
        if (showFDropDown)
        {
            for (Button b : fDropDown)
            {
                b.paint(g, observer);
            }
            for (Button b : fDropDown) {
                if (b.isHover()) {
                    b.getTooltip().paintToolTip(g);
                }
            }
        }

        // edit drop down
        if (showEDropDown)
        {
            for (Button b : eDropDown)
            {
                b.paint(g, observer);
            }
            for (Button b : eDropDown) {
                if (b.isHover()) {
                    b.getTooltip().paintToolTip(g);
                }
            }

        }

        if (showSaveBox)
        {
            saveWindow.draw(g, observer);
        }

        if (showFiles)
        {
            openWindow.draw(g, observer);
        }

    }
    @Override
    public void onClick(int x, int y)
    {
        undo = false;
        redo = false;

        for (Button b : rButtons)
        {
            b.IsClicked(x, y);
            if (rButtons.get(0).IsPressed())
            {
                showFDropDown = true;
                showEDropDown = false;
                showFiles = false;
                showSaveBox = false;
            }
            if (rButtons.get(1).IsPressed())
            {
                showFDropDown = false;
                showEDropDown = true;
            }
        }

        if (showFDropDown)
        {
            for (Button b: fDropDown)
            {
                b.IsClicked(x, y);
                if (fDropDown.get(0).IsPressed())
                {
                    canvasClear();
                    showFDropDown = false;
                }
                if (fDropDown.get(1).IsPressed())
                {
                    showFiles = true;
                }
                if (fDropDown.get(2).IsPressed())
                {
                    saveWindow.saveToFile();
                    showSaveBox = true;
                }
            }
        }

        if (showEDropDown)
        {
            for (Button b: eDropDown)
            {
                b.IsClicked(x, y);
                if (eDropDown.get(0).IsPressed())
                {
                    undo = true;
                    redo = false;
                    if (LayerPanel.layersInPanel.get(0).storedShapes.size()!=0) {
                        DrawableShapes a = LayerPanel.layersInPanel.get(0).storedShapes.pop();
                        LayerPanel.layersInPanel.get(0).undoRedo.push(a);
                    }
                }
                if (eDropDown.get(1).IsPressed())
                {
                    undo = false;
                    redo = true;
                    if (LayerPanel.layersInPanel.get(0).undoRedo.size()!=0) {
                        LayerPanel.layersInPanel.get(0).storedShapes.push(LayerPanel.layersInPanel.get(0).undoRedo.pop());
                    }
                }
            }
        }

        if (!rButtons.get(0).IsPressed() && !rButtons.get(1).IsPressed())
        {
            showFDropDown = false;
            showEDropDown = false;
        }

        if (showSaveBox)
        {
            saveWindow.onClick(x, y);
        }

        if (showFiles)
        {
            openWindow.onClick(x, y);
        }
    }


    @Override
    public void onMove(int x, int y)
    {
        for (Button b : rButtons)
        {
            b.ImageChange(x, y);
            if (b.isHover())
            {
                b.getTooltip().setX(x);
                b.getTooltip().setY(y);
                b.getTooltip().setTooltip(b.getText());
            }
        }

        if (rButtons.get(0).IsPressed())
        {
            for (Button b : fDropDown)
            {
                b.ImageChange(x, y);
                if (b.isHover())
                {
                    b.getTooltip().setX(x);
                    b.getTooltip().setY(y);
                    b.getTooltip().setTooltip(b.getText());
                }
            }
        }

        if (rButtons.get(1).IsPressed())
        {
            for (Button b : eDropDown)
            {
                b.ImageChange(x, y);
                if (b.isHover())
                {
                    b.getTooltip().setX(x);
                    b.getTooltip().setY(y);
                    b.getTooltip().setTooltip(b.getText());
                }
            }
        }
    }

    public void canvasClear()
    {
        LayerPanel.layersInPanel.clear();
        LayerPanel.layersInPanel.add(new Layer(1100, 30, 20, 20));
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
