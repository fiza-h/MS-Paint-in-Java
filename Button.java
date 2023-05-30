import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Button
{
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image_depressed;
    private Image image_pressed;
    private Image current_image;
    private boolean pressed;
    private String text;
    protected ArrayList<ImageIcon> images = new ArrayList<>();
    private Tooltip tooltip;
    private boolean isHover;

    // image button
    public Button(int x, int y, int width, int height, Image i_depressed, Image i_pressed)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image_depressed = i_depressed.getScaledInstance(width, height, Image.SCALE_FAST);
        image_pressed = i_pressed.getScaledInstance(width, height, Image.SCALE_FAST);
        current_image = image_depressed;
        this.isHover = false;
        this.tooltip = Tooltip.getInstance();
    }

    public boolean isHover() {
        return isHover;
    }

    public void setHover(boolean hover) {
        isHover = hover;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public Button()
    {

    }

    public Button(int x, int y, int width, int height, String text, Image i_depressed, Image i_pressed)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image_depressed = i_depressed.getScaledInstance(width, height, Image.SCALE_FAST);
        image_pressed = i_pressed.getScaledInstance(width, height, Image.SCALE_FAST);
        current_image = image_depressed;
        this.text = text;
        this.tooltip = Tooltip.getInstance();
    }

    public Button(int x, int y, int width, int height, Image current_image, ArrayList<ImageIcon> images)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.current_image = current_image.getScaledInstance(width, height, Image.SCALE_FAST);

        this.images = images;

        for (ImageIcon a : images)
        {
            a.getImage().getScaledInstance(width, height, Image.SCALE_FAST);
        }
    }

    public Image GetImage()
    {
        return current_image;
    }

    // checks whether the button is pressed or not
    public Boolean IsPressed()
    {
        return pressed;
    }

    // manually set button state, resets image accordingly too
    public void SetPressed(boolean pressed)
    {
        this.pressed = pressed;
        if (pressed) {
            current_image = image_pressed;
        }
        else current_image = image_depressed;
    }

    // checks whether button is clicked or not
    public void IsClicked(int x, int y)
    {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
        {
            pressed = true;
            current_image = image_pressed;
        }
        else {
            pressed = false;
            current_image = image_depressed;
        }
    }

    // button is pressed, until manually unpressed
    public void PressedUntilChanged(int x, int y)
    {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
        {
            pressed = true;
            current_image = image_pressed;
        }
    }

    // allows hover functionality, only when button is unpressed
    public void ImageChange(int x, int y)
    {
        if (!pressed) {
            if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
                isHover = true;
                if (current_image == image_depressed) {
                    current_image = image_pressed;
                }
            } else {
                current_image = image_depressed;
                isHover = false;
            }
        }
        if (pressed)
        {
            isHover = false;
        }
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics graphics, ImageObserver observer)
    {
        graphics.drawImage(current_image, x, y, observer);
    }

    public void paintTextButton(Graphics g, ImageObserver observer)
    {
        g.drawImage(current_image, x, y, observer);

        Font font = new Font("Arial", Font.PLAIN, 13);
        g.setFont(font);
        g.setColor(Color.black);
        FontMetrics m = g.getFontMetrics();
        int s_wdith = m.stringWidth(text);
        int s_height = m.getAscent() - m.getDescent();
        g.drawString(text, x + width / 2 - s_wdith / 2, y + height / 2 + s_height / 2);

    }

    // image pressed until clicked again
    public void KeepClicked(int x, int y)
    {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
        {
            if (current_image.equals(image_pressed))
            {
                current_image = image_depressed;
                pressed = false;
            } else if (current_image.equals(image_depressed)) {

                current_image = image_pressed;
                pressed = true;
            }
        }
    }

    public String getText() {
        return text;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setCurrent_image(Image current_image) {
        this.current_image = current_image;
    }
}
