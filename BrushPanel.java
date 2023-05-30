import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class BrushPanel implements PortionListener
{
    protected static ArrayList<Button> strokeButtons = new ArrayList<>();
    protected static Button strokeSize;
    protected static Button freeDraw;
    protected static Button curveDraw;
    protected boolean showStroke = false;
    private Button selected = new Button();
    private int clickCount = 0;

    public BrushPanel()
    {
        setStrokeButtons();
        strokeButtons.get(0).SetPressed(true);
    }

    public void setStrokeButtons()
    {
        int x_s = 50;
        int y_s = 115;

        // stroke size buttons
        ImageIcon stroke_dep = new ImageIcon("./src/strokeButtons/14.png");
        ImageIcon stroke_pre = new ImageIcon("./src/strokeButtons/15.png");
        strokeSize = new Button(x_s, y_s, 100, 40, "stroke", stroke_dep.getImage(), stroke_pre.getImage());

        ImageIcon big_dep = new ImageIcon("./src/strokeButtons/16.png");
        ImageIcon big_pre = new ImageIcon("./src/strokeButtons/17.png");
        ImageIcon small_dep = new ImageIcon("./src/strokeButtons/18.png");
        ImageIcon small_pre = new ImageIcon("./src/strokeButtons/19.png");
        ImageIcon smaller_dep = new ImageIcon("./src/strokeButtons/20.png");
        ImageIcon smaller_pre = new ImageIcon("./src/strokeButtons/21.png");
        ImageIcon smallest_dep = new ImageIcon("./src/strokeButtons/22.png");
        ImageIcon smallest_pre = new ImageIcon("./src/strokeButtons/23.png");

        strokeButtons.add(new Button(x_s, y_s+40,100, 40, smallest_dep.getImage(),smallest_pre.getImage()));
        strokeButtons.add(new Button(x_s, y_s+(40*2),100, 40, smaller_dep.getImage(),smaller_pre.getImage()));
        strokeButtons.add(new Button(x_s, y_s+(40*3),100, 40, small_dep.getImage(),small_pre.getImage()));
        strokeButtons.add(new Button(x_s, y_s+(40*4),100, 40, big_dep.getImage(),big_pre.getImage()));

        int x = 50;
        int y = 60;

        ImageIcon freeDraw_dep = new ImageIcon("./src/strokeButtons/2.png");
        ImageIcon freeDraw_pre = new ImageIcon("./src/strokeButtons/5.png");
        freeDraw = new Button(x, y, 45, 45,"free draw", freeDraw_dep.getImage(), freeDraw_pre.getImage());

        ImageIcon curveDraw_dep = new ImageIcon("./src/strokeButtons/3.png");
        ImageIcon curveDraw_pre = new ImageIcon("./src/strokeButtons/4.png");
        curveDraw = new Button(x+55, y, 45, 45,"bezier curve", curveDraw_dep.getImage(), curveDraw_pre.getImage());
    }

    public void paint(Graphics g, ImageObserver observer)
    {
        g.setColor(Color.black);
        Font font = new Font("SanSerif", Font.PLAIN, 17);
        g.setFont(font);
        g.drawString("Brushes", 75, 50);

        int stroke = 2;
        g.setColor(new Color(183, 202, 232));
        g.fillRect(50-stroke, 60-stroke, 45+(stroke*2), 45+(stroke*2));

        g.setColor(new Color(183, 202, 232));
        g.fillRect(105-stroke, 60-stroke, 45+(stroke*2), 45+(stroke*2));

        g.setColor(new Color(183, 202, 232));
        g.fillRect(50-stroke, 115-stroke, 100+(stroke*2), 40+(stroke*2));


        freeDraw.paint(g, observer);
        if (freeDraw.isHover()) {
            freeDraw.getTooltip().paintToolTip(g);
        }
        curveDraw.paint(g, observer);
        if (curveDraw.isHover()) {
            curveDraw.getTooltip().paintToolTip(g);
        }
        strokeSize.paint(g, observer);
        if (strokeSize.isHover()) {
            strokeSize.getTooltip().paintToolTip(g);
        }

        if (strokeSize.IsPressed()) {
            showStroke = true;
            for (Button b : strokeButtons) {
                b.paint(g, observer);
            }
        }
    }

    @Override
    public void onClick(int x, int y)
    {
        freeDraw.KeepClicked(x, y);

        if (freeDraw.IsPressed())
        {
            for (Button b : ShapeBar.sButtons)
            {
                b.SetPressed(false);
            }
        }

        curveDraw.IsClicked(x, y);
        strokeSize.IsClicked(x, y);

        if (strokeSize.IsPressed())
        {
            showStroke = true;
        }

        if (showStroke) {
            for (Button b : strokeButtons) {
                b.PressedUntilChanged(x, y);
                if (b.IsPressed())
                {
                    selected = b;
                    for (Button A : strokeButtons)
                    {
                        if (!A.equals(selected))
                        {
                            A.SetPressed(false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onMove(int x, int y)
    {
        freeDraw.ImageChange(x, y);
        if (freeDraw.isHover()) {
            freeDraw.getTooltip().setX(x);
            freeDraw.getTooltip().setY(y);
            freeDraw.getTooltip().setTooltip(freeDraw.getText());
        }
        curveDraw.ImageChange(x, y);
        if (curveDraw.isHover()) {
            curveDraw.getTooltip().setX(x);
            curveDraw.getTooltip().setY(y);
            curveDraw.getTooltip().setTooltip(curveDraw.getText());
        }
        strokeSize.ImageChange(x, y);
        if (strokeSize.isHover()) {
            strokeSize.getTooltip().setX(x);
            strokeSize.getTooltip().setY(y);
            strokeSize.getTooltip().setTooltip(strokeSize.getText());
        }
        for (Button b : strokeButtons)
        {
            b.ImageChange(x, y);
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
}
