import java.awt.*;

public class ColoredButton {
    protected Color color; // stored color
    private int x;
    private int y;
    private int width;
    private int height;
    private int stroke;
    private Color clickedColor; // stroke color after clicked
    private boolean pressed = false;
    private boolean hover = false;
    protected Color shapeColor;
    private Tooltip tooltip;
    protected String text;

    public ColoredButton(int x, int y, int width, int height, Color color) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.stroke = 2;
        this.clickedColor = color;
        this.tooltip = Tooltip.getInstance();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isHover() {
        return hover;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public ColoredButton()
    {

    }

    public Boolean IsPressed()
    {
        return pressed;
    }

    public void SetPressed(boolean pressed)
    {
        this.pressed = pressed;
    }

    public void IsClicked(int x, int y)
    {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
        {
            pressed = true;
            shapeColor = color;
        }
        else
        {
            pressed = false;
        }
    }

    public void paint(Graphics g)
    {
        if (this.IsPressed() || this.hover)
        {
            stroke = 2;

            clickedColor = Color.cyan;
            g.setColor(clickedColor);
            g.fillRect(x, y, width, height);

            g.setColor(color);
            g.fillRect(x+stroke, y+stroke, width-(stroke*2), height-(stroke*2));

        }

        else if (!this.IsPressed())
        {
            clickedColor = Color.lightGray;
            g.setColor(clickedColor);
            g.fillRect(x, y, width, height);

            g.setColor(color);
            g.fillRect(x+stroke, y+stroke, width-(stroke*2), height-(stroke*2));
        }
    }

    public void gradientPaint(Graphics g)
    {
        if (this.IsPressed() || this.hover)
        {
            stroke = 2;

            g.setColor(Color.cyan);
            g.fillRect(x, y, width, height);

            g.setColor(color);
            g.fillRect(x+stroke, y+stroke, width-(stroke*2), height-(stroke*2));

        }

        else if (!this.IsPressed() || !hover)
        {
            g.setColor(clickedColor);
            g.fillRect(x, y, width, height);

            g.setColor(color);
            g.fillRect(x+stroke, y+stroke, width-(stroke*2), height-(stroke*2));
        }
    }
    // button is pressed, until manually unpressed
    public void PressedUntilChanged(int x, int y)
    {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
        {
            pressed = true;
        }
    }

    public void StateChange(int x, int y)
    {
        if (!pressed) {
            if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
                hover = true;
            } else
            {
                hover = false;
            }
        }
        if (pressed)
        {
            hover = false;
        }
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
