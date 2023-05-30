import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Stack;

/**
 * makes an object of a single layer
 * has 4 buttons: up, down, remove, visibility
 */
public class Layer
{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int layerNumber;
    protected ArrayList<Button> layerButtons = new ArrayList<>();
    protected Stack<DrawableShapes> storedShapes; // stack to store newly drawn shapes
    protected Stack<DrawableShapes> undoRedo; // stack to store undo-ed shapes
    protected boolean removed;

    public Layer(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.layerNumber = 1;
        this.storedShapes = new Stack();
        this.undoRedo = new Stack();
        this.removed = false;

        setLayerButtons();
    }

    public Layer() {}

    private void setLayerButtons()
    {
        ImageIcon visible_dep = new ImageIcon("./src/singleLayerButtons/3.png");
        ImageIcon visible_pre = new ImageIcon("./src/singleLayerButtons/4.png");
        ImageIcon up_dep = new ImageIcon("./src/singleLayerButtons/7.png");
        ImageIcon up_pre = new ImageIcon("./src/singleLayerButtons/8.png");
        ImageIcon down_dep = new ImageIcon("./src/singleLayerButtons/9.png");
        ImageIcon down_pre = new ImageIcon("./src/singleLayerButtons/10.png");
        ImageIcon cancel_dep = new ImageIcon("./src/singleLayerButtons/5.png");
        ImageIcon cancel_pre = new ImageIcon("./src/singleLayerButtons/6.png");

        layerButtons.add(new Button(x, y, width, height,"show layer", visible_dep.getImage(), visible_pre.getImage()));
        layerButtons.add(new Button(x+width+5, y, width, height,"move up", up_dep.getImage(), up_pre.getImage()));
        layerButtons.add(new Button(x+(width*2)+10, y, width, height,"move down", down_dep.getImage(), down_pre.getImage()));
        layerButtons.add(new Button(x+(width*3)+15, y, width, height, "remove layer", cancel_dep.getImage(), cancel_pre.getImage()));
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getY()
    {
        return y;
    }

    public void setLayerNumber(int layerNumber)
    {
        this.layerNumber = layerNumber;
    }

    public int getLayerNumber()
    {
        return layerNumber;
    }

    public void paint(Graphics g, ImageObserver observer, int start_y)
    {
        g.setColor(Color.YELLOW);
        g.fillRect(1000, start_y, 200, 40);

        g.setColor(Color.BLACK);
        Font font1 = new Font("Monospaced", Font.ITALIC, 17);
        g.setFont(font1);

        g.drawString("Layer" + this.layerNumber, 1020, start_y + 25);

        for(Button b: layerButtons)
        {
            b.paint(g,observer);
        }
    }

    @Override
    public String toString() {
        return "Layer{" +
                "layerNumber=" + layerNumber +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<Button> getLayerButtons() {
        return layerButtons;
    }

    public void setLayerButtons(ArrayList<Button> layerButtons) {
        this.layerButtons = layerButtons;
    }

    public Stack<DrawableShapes> getStoredShapes() {
        return storedShapes;
    }

    public void setStoredShapes(Stack<DrawableShapes> storedShapes) {
        this.storedShapes = storedShapes;
    }

    public Stack<DrawableShapes> getUndoRedo() {
        return undoRedo;
    }

    public void setUndoRedo(Stack<DrawableShapes> undoRedo) {
        this.undoRedo = undoRedo;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
