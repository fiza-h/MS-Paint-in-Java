import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class colorGradientSingleton implements PortionListener
{
    private static colorGradientSingleton colorGradient = new colorGradientSingleton();

    ColoredButton[][] buttons = new ColoredButton[256][256];
    protected ColoredButton selectedColor = new ColoredButton(600, 200, 50, 50, Color.BLACK);
    protected Button addColor;
    protected Button cancel;
    protected boolean showGradient = false;

    private colorGradientSingleton()
    {
        ImageIcon add_dep = new ImageIcon("./src/colorGradient/3.png");
        ImageIcon add_pre = new ImageIcon("./src/colorGradient/3.png");

        addColor = new Button(600, 400, 100, 40, add_dep.getImage(), add_pre.getImage());

        ImageIcon cancel_img = new ImageIcon("./src/colorGradient/5.png");

        cancel = new Button(600, 450, 100, 40, cancel_img.getImage(), cancel_img.getImage());

        int x = 300;
        int y = 200;
        int width = 20;
        int height = 20;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                float hue = (float)i / 255f;
                float saturation = (float)j / 255f;
                float brightness = 0.9f; // adjust brightness value
                if (i == 0 && j == 0) { // handle black color
                    brightness = 0f;
                }
                Color color = Color.getHSBColor(hue, saturation, brightness);
                buttons[i][j] = new ColoredButton(x, y, width, height, color);
                x++;
            }
            y++;
            x=300;
        }

        for (int i = 0; i < 256; i++)
        {
            for (int j = 0; j < 256; j++)
            {
                buttons[i][j].text = Integer.toHexString(buttons[i][j].color.getRGB() & 0xffffff);
            }

        }
    }

    public static colorGradientSingleton getInstance()
    {
        return colorGradient;
    }

    public void paint(Graphics g, ImageObserver observer)
    {
        g.setColor(Color.black); // set beige color
        g.fillRect(285, 175, 450+(10), 320+10); // fill rectangle with the same color

        g.setColor(new Color(245, 245, 220));
        g.fillRect(290, 180, 450, 320);

        g.setColor(Color.BLACK);
        Font font = new Font("Monospaced", Font.PLAIN, 17);
        g.setFont(font);
        g.drawString("Color Picker", 290, 160);

        selectedColor.paint(g);
        addColor.paint(g, observer);
        cancel.paint(g,observer);

        for (int i = 0; i < 256; i++)
        {
            for (int j = 0; j < 256; j++)
            {
                buttons[i][j].gradientPaint(g);
            }
        }

        if (!addColor.IsPressed() || !cancel.IsPressed()) {
            for (int i = 0; i < 256; i++) {
                for (int j = 0; j < 256; j++) {
                    if (buttons[i][j].isHover()) {
                        buttons[i][j].getTooltip().paintToolTip(g);
                    }
                }
            }
        }


    }
    @Override
    public void onClick(int x, int y)
    {
        for (int i = 0; i < 256; i++)
        {
            for (int j = 0; j < 256; j++)
            {
                buttons[i][j].IsClicked(x, y);
                if (buttons[i][j].IsPressed())
                {
                    selectedColor.color = buttons[i][j].getColor();
                }
            }
        }

        addColor.IsClicked(x, y);
        if (addColor.IsPressed())
        {
            showGradient = false;

        } else if (!addColor.IsPressed())
        {
            showGradient = true;
        }

        cancel.IsClicked(x, y);
        if (cancel.IsPressed())
        {
            showGradient = false;
        }
    }

    @Override
    public void onMove(int x, int y)
    {
        if (showGradient) {
            for (int i = 0; i < 256; i++) {
                for (int j = 0; j < 256; j++) {
                    buttons[i][j].StateChange(x, y);
                    if (buttons[i][j].isHover()) {
                        buttons[i][j].getTooltip().setX(x);
                        buttons[i][j].getTooltip().setY(y);
                        buttons[i][j].getTooltip().setTooltip(buttons[i][j].getText());
                    }
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
