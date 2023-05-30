import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class ShapeBar implements PortionListener
{
    protected static ArrayList<Button> sButtons = new ArrayList<>();

    public ShapeBar()
    {
        setButtons();
    }

    public void setButtons()
    {
        int width = 45;
        int height = 45;
        int start_x = 220;
        int start_y = 60;

        ImageIcon circle_dep = new ImageIcon("./src/shapeButtons/5.png");
        ImageIcon circle_pre = new ImageIcon("./src/shapeButtons/6.png");
        ImageIcon eqTri_dep = new ImageIcon("./src/shapeButtons/11.png");
        ImageIcon eqTri_pre = new ImageIcon("./src/shapeButtons/12.png");
        ImageIcon hex_dep = new ImageIcon("./src/shapeButtons/9.png");
        ImageIcon hex_pre = new ImageIcon("./src/shapeButtons/10.png");
        ImageIcon pent_dep = new ImageIcon("./src/shapeButtons/7.png");
        ImageIcon pent_pre = new ImageIcon("./src/shapeButtons/8.png");
        ImageIcon rect_dep = new ImageIcon("./src/shapeButtons/3.png");
        ImageIcon rect_pre = new ImageIcon("./src/shapeButtons/4.png");
        ImageIcon tri_dep = new ImageIcon("./src/shapeButtons/1.png");
        ImageIcon tri_pre = new ImageIcon("./src/shapeButtons/2.png");

        sButtons.add(new Button(start_x, start_y, width, height, "circle",circle_dep.getImage(), circle_pre.getImage()));
        sButtons.add(new Button(start_x+width, start_y, width, height,"right triangle", eqTri_dep.getImage(), eqTri_pre.getImage()));
        sButtons.add(new Button(start_x+width*2, start_y, width, height, "hexagon", hex_dep.getImage(), hex_pre.getImage()));
        sButtons.add(new Button(start_x, start_y+height, width, height,"pentagram", pent_dep.getImage(), pent_pre.getImage()));
        sButtons.add(new Button(start_x+width, start_y+height, width, height,"rectangle", rect_dep.getImage(), rect_pre.getImage()));
        sButtons.add(new Button(start_x+width*2, start_y+height, width, height,"triangle", tri_dep.getImage(), tri_pre.getImage()));
    }

    public void paint(Graphics g, ImageObserver observer)
    {
        g.setColor(Color.black);
        Font font = new Font("SanSerif", Font.PLAIN, 17);
        g.setFont(font);
        g.drawString("Shapes", 260, 50);

        int stroke = 2;
        g.setColor(new Color(183, 202, 232));
        g.fillRect(220-stroke, 60-stroke, 135+(stroke*2), 90+(stroke*2));

        for (Button b : sButtons)
        {
            b.paint(g, observer);
        }

        for (Button b : sButtons)
        {
            if (b.isHover())
            {
                b.getTooltip().paintToolTip(g);
            }
        }
    }
    @Override
    public void onClick(int x, int y)
    {
        if (x > 220 && x < 355 && y > 60 && y < 150)
        {
            for (Button b : sButtons) {
                if (b.IsPressed())
                {
                    b.SetPressed(false);
                }
                else {
                    b.IsClicked(x, y);
                }
            }

            BrushPanel.freeDraw.SetPressed(false);
        }
        else {
            for (Button b : sButtons) {
                b.SetPressed(false);
            }
        }
    }

    @Override
    public void onMove(int x, int y)
    {
        for (Button b : sButtons)
        {
            if (!b.IsPressed()) {
                b.ImageChange(x, y);
                if (b.isHover()) {
                    b.getTooltip().setX(x);
                    b.getTooltip().setY(y);
                    b.getTooltip().setTooltip("draw a " + b.getText());
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
}
