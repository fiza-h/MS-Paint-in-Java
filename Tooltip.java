import java.awt.*;

public class Tooltip {
    private static Tooltip instance;
    private String text;
    private int x;
    private int y;

    private Tooltip()
    {
        // Private constructor to prevent instantiation from outside the class
    }

    public static Tooltip getInstance()
    {
//        return instance;
        if (instance == null) {
            instance = new Tooltip();
        }
        return instance;
    }

    public void paintToolTip(Graphics g)
    {
//        FontMetrics m = g.getFontMetrics();
//        int s_width = m.stringWidth(text);
//        int s_height = m.getAscent() - m.getDescent(); // Get the height of the text
//
//        g.setColor(Color.LIGHT_GRAY); // Set color to light gray
//        g.fillRect(x, y, s_width - 5, s_height + 5); // Draw a rectangle with dimensions (textWidth + 20, textHeight + 10)

//        FontMetrics fm = g.getFontMetrics();
//        int width = fm.stringWidth(text);
//        int height = fm.getHeight();
//        int x = this.x;
//        int y = this.y + height;
//
//        g.setColor(Color.LIGHT_GRAY);
//        g.fillRect(x, y - height, width, height);
//        g.setColor(Color.black);
//        g.drawString(text, x, y);


        // Get the font metrics to measure the text size
        FontMetrics fm = g.getFontMetrics();

        // Calculate the size of the rectangle to fit the text
        int rectWidth = fm.stringWidth(text);
        int rectHeight = fm.getHeight();

        // Calculate the bottom left corner of the rectangle
        int rectX = x - rectWidth;
        int rectY = y - rectHeight;

        // Draw the rectangle
        g.setColor(Color.lightGray);
        g.fillRect(rectX, rectY, rectWidth, rectHeight);

        // Draw the text inside the rectangle
        g.setColor(Color.black);
        g.drawString(text, rectX, rectY + fm.getAscent());
    }

    public void setTooltip(String text)
    {
        this.text = text;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}