import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class ColorBar implements PortionListener
{
    protected ArrayList<ColoredButton> cButtons = new ArrayList<>();
    protected static ColoredButton stroke;
    protected static ColoredButton fill;
    protected Button colorGradient;
    protected colorGradientSingleton colorGradientWindow = colorGradientSingleton.getInstance();
    protected static boolean showGradient = false;
    private boolean colorAdded = false;

    private int filledColorInt = 9;

    public ColorBar()
    {
        setCButtons();
    }

    public void setCButtons()
    {
        int x = 400;
        int width = 70;
        int y = 60;

        //stroke and fill buttons
        fill = new ColoredButton(x, y, width, width, Color.RED);
        fill.SetPressed(true); // fill button is clicked initially

        stroke = new ColoredButton(x+width+5, y, width, width, Color.BLACK);

        int color_x = 815;
        int color_y = 55;
        int c_width = 75;
        ImageIcon grad_dep = new ImageIcon("./src/colorGradientButton/1.png");
        ImageIcon grad_pre = new ImageIcon("./src/colorGradientButton/2.png");
        colorGradient = new Button(color_x, color_y, c_width, c_width, grad_dep.getImage(), grad_pre.getImage());

        int x1 = 565;
        int y1 = 65;
        int width1 = 25;
        // color buttons
        cButtons.add(new ColoredButton(x1, y1, width1, width1, Color.BLACK));
        cButtons.add(new ColoredButton(x1+width1+5, y1, width1, width1, Color.red));
        cButtons.add(new ColoredButton(x1+(width1+5)*2, y1, width1, width1, Color.BLUE));
        cButtons.add(new ColoredButton(x1+(width1+5)*3, y1, width1, width1, Color.green.darker()));
        cButtons.add(new ColoredButton(x1+(width1+5)*4, y1, width1, width1, Color.MAGENTA));
        cButtons.add(new ColoredButton(x1+(width1+5)*5, y1, width1, width1, Color.PINK));
        cButtons.add(new ColoredButton(x1+(width1+5)*6, y1, width1, width1, Color.yellow));
        cButtons.add(new ColoredButton(x1+(width1+5)*7, y1, width1, width1, Color.orange));

        cButtons.add(new ColoredButton(x1, width1+y1+5, width1, width1, Color.red.darker()));
        cButtons.add(new ColoredButton(x1+width1+5, width1+y1+5, width1, width1, Color.cyan.darker()));
        cButtons.add(new ColoredButton(x1+(width1+5)*2, width1+y1+5, width1, width1, Color.white));
        cButtons.add(new ColoredButton(x1+(width1+5)*3, width1+y1+5, width1, width1, Color.white));
        cButtons.add(new ColoredButton(x1+(width1+5)*4, width1+y1+5, width1, width1, Color.white));
        cButtons.add(new ColoredButton(x1+(width1+5)*5, width1+y1+5, width1, width1, Color.white));
        cButtons.add(new ColoredButton(x1+(width1+5)*6, width1+y1+5, width1, width1, Color.white));
        cButtons.add(new ColoredButton(x1+(width1+5)*7, width1+y1+5, width1, width1, Color.white));

        for (ColoredButton b : cButtons)
        {
            b.text = Integer.toHexString(b.color.getRGB() & 0xffffff);
        }

    }

    public void paint(Graphics g, ImageObserver observer)
    {
        stroke.paint(g);
        fill.paint(g);
        colorGradient.paint(g, observer);

        g.setColor(Color.BLACK);
        Font font = new Font("SanSerif", Font.PLAIN, 14);
        g.setFont(font);
        g.drawString("Fill", 415, 150);

        g.setColor(Color.BLACK);
        Font font1 = new Font("SanSerif", Font.PLAIN, 14);
        g.setFont(font1);
        g.drawString("Stroke", 500, 150);

        g.setColor(Color.BLACK);
        Font font3 = new Font("SanSerif", Font.PLAIN, 14);
        g.setFont(font3);
        g.drawString("More Colors", 815, 150);

        g.setColor(Color.black);
        Font font2 = new Font("SanSerif", Font.PLAIN, 17);
        g.setFont(font2);
        g.drawString("Colors", 550, 50);

        if (showGradient && colorGradientWindow.showGradient)
        {
            colorGradientWindow.paint(g, observer);
        }

        if (colorAdded) {
            cButtons.get(filledColorInt).setColor(colorGradientWindow.selectedColor.getColor());
            colorAdded = false;
        }

        for (ColoredButton a : cButtons)
        {
            a.paint(g);
        }

        for (ColoredButton b : cButtons)
        {
            if (b.isHover())
            {
                b.getTooltip().paintToolTip(g);
            }
            if (!b.isHover())
            {

            }
        }
    }

    @Override
    public void onClick(int x, int y) {
        colorGradient.PressedUntilChanged(x, y);
        if (colorGradient.IsPressed()) {
            colorGradientWindow.onClick(x, y);
        }

        if (colorGradientWindow.addColor.IsPressed()) {
            if (filledColorInt < 15) {
                filledColorInt++;
            } else filledColorInt = 10;

            colorAdded = true;
            showGradient = false;
            colorGradientWindow.showGradient = false;
            colorGradientWindow.addColor.SetPressed(false);
            colorGradient.SetPressed(false);
        }

        if (colorGradientWindow.cancel.IsPressed()) {
            showGradient = false;
            colorGradientWindow.showGradient = false;
            colorGradientWindow.addColor.SetPressed(false);
            colorGradient.SetPressed(false);
        }

        if (colorGradient.IsPressed()) {
            showGradient = true;
        }

        if (x > 400 && x < 545 && y > 60 && y < 130) {
            // one always remains selected
            fill.IsClicked(x, y);
            stroke.IsClicked(x, y);
        }

        if (x > 565 && x < 800 && y > 65 && y < 120) {
            for (ColoredButton b : cButtons) {
                b.IsClicked(x, y);
                if (b.IsPressed()) {
                    if (fill.IsPressed()) {
                        fill.color = b.color;
                        b.setPressed(false);
                    } else if (stroke.IsPressed()) {
                        stroke.color = b.color;
                        b.setPressed(false);
                    }
                }
            }
        }
    }

    @Override
    public void onMove(int x, int y)
    {
        if (x > 565 && x < 800 && y > 65 && y < 120)
        {
            for (ColoredButton b : cButtons) {
                b.StateChange(x, y);
                if (b.isHover())
                {
                    b.getTooltip().setX(x);
                    b.getTooltip().setY(y);
                    b.getTooltip().setTooltip(b.getText());
                }
            }
        }
        colorGradientWindow.onMove(x, y);
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
