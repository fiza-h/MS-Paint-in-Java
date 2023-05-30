import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Stack;

/**
 * layer toolbar
 * keeps track of multiple layers
 */
public class LayerPanel implements PortionListener
{
    protected static ArrayList<Layer> layersInPanel = new ArrayList<>();
    private int start_y = 30;
    private int addY = 40;
    private Button addLayerButton;
    private Layer layer = new Layer();

    public LayerPanel()
    {
        setAddLayerButton();
    }

    public LayerPanel(String text)
    {

    }

    public void setAddLayerButton()
    {
        ImageIcon add_dep = new ImageIcon("./src/singleLayerButtons/1.png");
        ImageIcon add_pre = new ImageIcon("./src/singleLayerButtons/2.png");
        addLayerButton = new Button(1180, 0, 20, 20,"add layer", add_dep.getImage(), add_pre.getImage());

        layersInPanel.add(new Layer(1100, start_y, 20, 20));
    }

    public void paint(Graphics g, ImageObserver observer)
    {
        g.setColor(Color.GRAY);
        g.fillRect(1000, 0, 200, 600);

        g.setColor(Color.lightGray);
        g.fillRect(1000, 0, 200, 20);

        g.setColor(Color.BLACK);
        Font font = new Font("Monospaced", Font.BOLD, 17);
        g.setFont(font);
        g.drawString("Layer Panel", 1020, 15);

        addLayerButton.paint(g, observer);

        int y = 20;
        updateButtonPosition();

        for (Layer b : layersInPanel)
        {
            b.paint(g, observer, y);
            y+=addY;
        }

        if (addLayerButton.isHover())
        {
            addLayerButton.getTooltip().paintToolTip(g);
        }

        for (int i = 0; i < layersInPanel.size(); i++) {
            Layer a;
            a = layersInPanel.get(i);
            for (Button b : a.layerButtons) {
                if (b.isHover()) {
                    b.getTooltip().paintToolTip(g);
                }
            }
        }
    }

    public void updateButtonPosition()
    {
        int x = start_y;
        for (Layer b : layersInPanel)
        {
            for (int i = 0; i < 4; i++)
            {
                b.layerButtons.get(i).setY(x);
            }
            x+=addY;
        }
    }

    public void layerAdder()
    {
        layer = new Layer(1100, layersInPanel.get(layersInPanel.size()-1).getY() + addY, 20, 20);

        int number = 1;
        for (int i = 0; i < layersInPanel.size(); i++)
        {
            if (layersInPanel.get(i).getLayerNumber() > number)
            {
                number = layersInPanel.get(i).getLayerNumber();
            }
        }
        layer.setLayerNumber(number + 1);
        layersInPanel.add(layer);
        updateButtonPosition();
    }

    public void layerMoveUp(int position, Layer a)
    {
        if (position > 0) {
            layersInPanel.remove(position);
            layersInPanel.add(position - 1, a);
            updateButtonPosition();
        }
        if (position == 0)
        {
            layersInPanel.add(0, a);
        }
    }

    public void layerMoveDown(int position, Layer a)
    {
        if (position < layersInPanel.size()-1) {
            layersInPanel.remove(position);
            layersInPanel.add(position + 1, a);
            updateButtonPosition();
        }
    }

    public void layerRemove(int position)
    {
        if (layersInPanel.size() > 1)
        {
            layersInPanel.remove(position);
            updateButtonPosition();
        }
    }
    @Override
    public void onClick(int x, int y)
    {
        addLayerButton.IsClicked(x, y);
        {
            if (addLayerButton.IsPressed())
            {
                layerAdder();
            }
        }

        int i = 0;
        for (Layer b : layersInPanel)
        {
            b.layerButtons.get(0).KeepClicked(x, y);

            b.layerButtons.get(1).IsClicked(x,y);
            if (b.layerButtons.get(1).IsPressed() && layersInPanel.size()>1)
            {
                layerMoveUp(i, b);
                break;
            }

            b.layerButtons.get(2).IsClicked(x,y);
            if (b.layerButtons.get(2).IsPressed())
            {
                layerMoveDown(i, b);
                break;
            }

            b.layerButtons.get(3).IsClicked(x,y);
            if (b.layerButtons.get(3).IsPressed())
            {
                layerRemove(i);
                break;
            }

            i++;
        }
    }

    public void recreateLayers(ArrayList<Stack> shapes)
    {

        layersInPanel.add(new Layer(1100, start_y, 20, 20));

        while (layersInPanel.size()!=shapes.size())
        {
            layerAdder();
        }

        for (int i = 0; i < LayerPanel.layersInPanel.size(); i++)
        {
            layersInPanel.get(i).storedShapes = shapes.get(i);
        }
    }

    @Override
    public void onMove(int x, int y)
    {
        addLayerButton.ImageChange(x, y);
        if (addLayerButton.isHover()) {
            addLayerButton.getTooltip().setX(x);
            addLayerButton.getTooltip().setY(y);
            addLayerButton.getTooltip().setTooltip(addLayerButton.getText());
        }

        for (int i = 0; i < layersInPanel.size(); i++) {
            Layer a;
            a = layersInPanel.get(i);
            for (Button b : a.layerButtons)
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

    @Override
    public void onPress(int x, int y) {

    }

    @Override
    public void onDrag(int x, int y) {

    }

    @Override
    public void onRelease(int x, int y) {

    }

    public static ArrayList<Layer> getLayersInPanel() {
        return layersInPanel;
    }

    public static void setLayersInPanel(ArrayList<Layer> layersInPanel) {
        LayerPanel.layersInPanel = layersInPanel;
    }

    public int getStart_y() {
        return start_y;
    }

    public void setStart_y(int start_y) {
        this.start_y = start_y;
    }

    public int getAddY() {
        return addY;
    }

    public void setAddY(int addY) {
        this.addY = addY;
    }

    public Button getAddLayerButton() {
        return addLayerButton;
    }

    public void setAddLayerButton(Button addLayerButton) {
        this.addLayerButton = addLayerButton;
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }
}
