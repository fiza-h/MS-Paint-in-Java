import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class DrawingBoard implements PortionListener {
    private ShapeFactory shapeFactory = new ShapeFactory();
    private String shapeType = "";
    private int x1, y1, x2, y2 = 0;
    private Shape shape;
    private boolean shapeButtonClicked = false;
    private boolean freeDrawClicked = false;
    private boolean tempShape = false;
    //draws stroke
    private int stroke = 0;
    private FreeDraw freeDraw;
    protected static boolean gridON = false;

    public DrawingBoard()
    {
        this.shape = new NullShape();
        this.freeDraw = new FreeDraw();
    }

    public void paint(Graphics g)
    {

        Layer a;

        for (int i = LayerPanel.layersInPanel.size() - 1; i >= 0; i--) {
            a = LayerPanel.layersInPanel.get(i);
            if (!a.layerButtons.get(0).IsPressed())
            {
                for (DrawableShapes b : a.storedShapes)
                {
                    if (b instanceof Shape)
                    {
                        ((Shape) b).draw(g);
                    } else if (b instanceof FreeDraw)
                    {
                        ((FreeDraw) b).draw(g);
                    }
                }
            }
        }

        if (tempShape)
        {
            shape.draw(g);
            freeDraw.draw(g);
        }
    }

    /**
     * gets initial coordinates
     * @param x
     * @param y
     */
    public void setStartPoint(int x, int y)
    {
        x1 = x;
        y1 = y;
    }

    /**
     * gets final coordinates
     * @param x
     * @param y
     */
    public void setEndPoint(int x, int y)
    {
        x2 = x;
        y2 = y;
    }

    @Override
    public void onClick(int x, int y) {

    }

    @Override
    public void onMove(int x, int y) {

    }

    @Override
    public void onPress(int x, int y)
    {
        if (x > 0 && x < 1000 && y > 168 && y < 600)
        {
            //purge stack
            for (Layer a : LayerPanel.layersInPanel)
            {
                a.undoRedo = new Stack<>();
            }

            setStartPoint(x, y);
            for (Button b : ShapeBar.sButtons) {
                if (b.IsPressed()) {
                    shapeType = b.getText();
                    shapeButtonClicked = true;
                    ColorBar.showGradient = false;
                    freeDrawClicked = false;
                    BrushPanel.freeDraw.SetPressed(false);
                    shape = shapeFactory.getShape(shapeType);
                }
            }

            if (BrushPanel.freeDraw.IsPressed()) {
                //free drawing
                freeDrawClicked = true;
                shapeButtonClicked = false;
                freeDraw = new FreeDraw();
            }
        }

        // sets stroke size
        if (BrushPanel.strokeButtons.get(0).IsPressed())
        {
            stroke = 2;
        } else if (BrushPanel.strokeButtons.get(1).IsPressed()) {
            stroke = 4;
        } else if (BrushPanel.strokeButtons.get(2).IsPressed()) {
            stroke = 6;
        } else if (BrushPanel.strokeButtons.get(3).IsPressed()) {
            stroke = 8;
        }

        tempShape = true;
    }

    @Override
    public void onDrag(int x, int y)
    {
        if (x > 0 && x < 1000 && y > 168 && y < 600) {
            ColorBar.showGradient = false;
            if (shapeButtonClicked) {
                setEndPoint(x, y);
                if (shapeType.equals("rectangle")) {
                    shape = new Rectangle(x1, y1, x2, y2, ColorBar.fill.color, ColorBar.stroke.color, stroke);
                } else if (shapeType.equals("circle")) {
                    shape = new Circle(x1, y1, x2, y2, ColorBar.fill.color, ColorBar.stroke.color, stroke);
                } else if (shapeType.equals("hexagon")) {
                    shape = new Hexagon(x1, y1, x2 - x1, y2 - y1, ColorBar.fill.color, ColorBar.stroke.color, stroke);
                } else if (shapeType.equals("pentagram")) {
                    shape = new Pentagram(x1, y1, x2 - x1, y2 - y1, ColorBar.fill.color, ColorBar.stroke.color, stroke);
                } else if (shapeType.equals("triangle")) {
                    shape = new Triangle(x1, y1, x2 - x1, y2 - y1, ColorBar.fill.color, ColorBar.stroke.color, stroke);
                } else if (shapeType.equals("right triangle")) {
                    shape = new RightTriangle(x1, y1, x2 - x1, y2 - y1, ColorBar.fill.color, ColorBar.stroke.color, stroke);
                }

                tempShape = true;
            }

            if (freeDrawClicked) {
                Circle circle = new Circle(x - stroke, y - stroke, x + stroke, y + stroke, ColorBar.fill.color, ColorBar.fill.color, stroke);
                freeDraw.stackFreeDraw.add(circle);
                tempShape = true;
            }
        }

    }

    @Override
    public void onRelease(int x, int y)
    {
        if (x > 0 && x < 1000 && y > 168 && y < 600) {
            if (shapeButtonClicked) {
                if (!(shape instanceof NullShape)) {
                    LayerPanel.layersInPanel.get(0).storedShapes.push((DrawableShapes) shape);
                }
                shape = new NullShape();
                tempShape = false;
            }

            if (freeDrawClicked)
            {
                LayerPanel.layersInPanel.get(0).storedShapes.push(freeDraw);
                freeDraw = new FreeDraw();
                tempShape = false;
            }
        }
    }
}
