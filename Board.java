import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class Board extends JPanel implements ActionListener, MouseInputListener, MouseListener, MouseMotionListener, KeyListener
{
    private final int B_WIDTH = 1200;
    private final int B_HEIGHT = 600;
    private final int DELAY = 20;
    private Timer timer;
    private RibbonBar ribbon;
    protected ShapeBar shapes;
    protected ColorBar colorBar;
    private BrushPanel brushPanel;
    private LayerPanel layerPanel;
    private DrawingBoard drawingBoard;
    private gridSingleton GridSingleton;

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

        }

        /**
         * handles key presses
         * @param e the event to be processed
         */
        @Override
        public void keyPressed(KeyEvent e)
        {
            if (e.getKeyCode() == KeyEvent.VK_S && e.isControlDown())
            {
                ribbon.saveWindow.saveToFile();
                ribbon.showSaveBox = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_O && e.isControlDown())
            {
//                ribbon.readFromFile();
//                ribbon.showFiles = true;
                RibbonBar.showFiles = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_Z && e.isControlDown())
            {
                ribbon.undo = true;
                ribbon.redo = false;
                if (LayerPanel.layersInPanel.get(0).storedShapes.size()!=0) {
                    DrawableShapes a = LayerPanel.layersInPanel.get(0).storedShapes.pop();
                    LayerPanel.layersInPanel.get(0).undoRedo.push(a);
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_Y && e.isControlDown())
            {
                ribbon.undo = false;
                ribbon.redo = true;
                if (LayerPanel.layersInPanel.get(0).undoRedo.size()!=0) {
                    LayerPanel.layersInPanel.get(0).storedShapes.push(LayerPanel.layersInPanel.get(0).undoRedo.pop());
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_N && e.isControlDown())
            {
                ribbon.canvasClear();
            }
        }
    }

    public Board()
    {
        initBoard();
    }

    private void InitializeAssets()
    {
        ribbon = new RibbonBar();
        shapes = new ShapeBar();
        colorBar = new ColorBar();
        brushPanel = new BrushPanel();
        layerPanel = new LayerPanel();
        drawingBoard = new DrawingBoard();
        GridSingleton = gridSingleton.getInstance();
    }

    private void initBoard()
    {
        addMouseListener( this );
        addMouseMotionListener( this );
        addKeyListener(new TAdapter());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);

        InitializeAssets();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int stroke = 2;
        g.setColor(new Color(183, 202, 232));
        g.fill3DRect(0, 28, 1200, 140, true);
        g.setColor(new Color(245, 246, 247));
        g.fill3DRect(stroke, 28+stroke, 1200-(stroke*2), 140-(stroke*2), true);

        drawingBoard.paint(g);
        GridSingleton.draw(g, this);
        shapes.paint(g, this);
        colorBar.paint(g, this);
        brushPanel.paint(g, this);
        ribbon.paint(g, this);
        layerPanel.paint(g, this);
    }

    // mouse listeners
    @Override
    public void mouseClicked(MouseEvent e)
    {
        ribbon.onClick(e.getX(), e.getY());
        shapes.onClick(e.getX(), e.getY());
        colorBar.onClick(e.getX(), e.getY());
        brushPanel.onClick(e.getX(), e.getY());
        layerPanel.onClick(e.getX(), e.getY());

        if (colorBar.showGradient)
        {
            colorBar.colorGradientWindow.onClick(e.getX(), e.getY());
        }

        GridSingleton.onClick(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        drawingBoard.onPress(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        drawingBoard.onDrag(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        drawingBoard.onRelease(e.getX(), e.getY());
    }

    // refreshing
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        ribbon.onMove(e.getX(), e.getY());
        shapes.onMove(e.getX(),e.getY());
        colorBar.onMove(e.getX(), e.getY());
        brushPanel.onMove(e.getX(), e.getY());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}