import java.awt.*;

public class Pentagram implements Shape, DrawableShapes {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;
    private Color strokeColor;
    private int stroke;

    /**
     * Constructor for Star class
     * @param x The x-coordinate of the top left corner of the bounding rectangle
     * @param y The y-coordinate of the top left corner of the bounding rectangle
     * @param width The width of the bounding rectangle
     * @param height The height of the bounding rectangle
     * @param c The fill color of the Star
     * @param strokeColor The color of the outline of the Star
     * @param stroke The width of the outline of the Star
     */
    public Pentagram(int x, int y, int width, int height, Color c, Color strokeColor, int stroke) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = c;
        this.strokeColor = strokeColor;
        this.stroke = stroke;
    }

    public Pentagram() {

    }

    @Override
    public void draw(Graphics g)
    {
        int[] xPoints = new int[10];
        int[] yPoints = new int[10];

        // Calculate the points of the larger star shape
        int radius = Math.min(width, height) / 2;
        int strokeOffset = stroke / 2;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                xPoints[i] = (int) (x + width / 2 + (radius + strokeOffset) * Math.cos(i * Math.PI / 5));
                yPoints[i] = (int) (y + height / 2 + (radius + strokeOffset) * Math.sin(i * Math.PI / 5));
            } else {
                xPoints[i] = (int) (x + width / 2 + (radius / 2 + strokeOffset) * Math.cos(i * Math.PI / 5));
                yPoints[i] = (int) (y + height / 2 + (radius / 2 + strokeOffset) * Math.sin(i * Math.PI / 5));
            }
        }

        // Draw the larger star shape with the stroke color
        g.setColor(strokeColor);
        g.fillPolygon(xPoints, yPoints, 10);

        // Calculate the points of the smaller star shape
        radius = Math.min(width, height) / 2;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                xPoints[i] = (int) (x + width / 2 + radius * Math.cos(i * Math.PI / 5));
                yPoints[i] = (int) (y + height / 2 + radius * Math.sin(i * Math.PI / 5));
            } else {
                xPoints[i] = (int) (x + width / 2 + radius / 2 * Math.cos(i * Math.PI / 5));
                yPoints[i] = (int) (y + height / 2 + radius / 2 * Math.sin(i * Math.PI / 5));
            }
        }

        // Draw the smaller star shape with the fill color
        g.setColor(color);
        g.fillPolygon(xPoints, yPoints, 10);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }
}
