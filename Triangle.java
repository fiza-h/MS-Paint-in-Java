import java.awt.*;

public class Triangle implements Shape, DrawableShapes
{
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
    public Triangle(int x, int y, int width, int height, Color c, Color strokeColor, int stroke) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = c;
        this.strokeColor = strokeColor;
        this.stroke = stroke;
    }

    public Triangle() {

    }
    @Override
    public void draw(Graphics g)
    {
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        // Calculate the points of the triangle shape
        xPoints[0] = x + width / 2;
        yPoints[0] = y;
        xPoints[1] = x + width;
        yPoints[1] = y + height;
        xPoints[2] = x;
        yPoints[2] = y + height;

        // Calculate the coordinates of the larger triangle
        int strokeOffset = stroke;
        int[] largerX = {x - strokeOffset, x + width / 2, x + width + strokeOffset};
        int[] largerY = {y + height + strokeOffset, y - strokeOffset, y + height + strokeOffset};

        // Draw the larger triangle with the stroke color
        g.setColor(strokeColor);
        g.fillPolygon(largerX, largerY, 3);

        // Set the fill color and draw the triangle shape
        g.setColor(color);
        g.fillPolygon(xPoints, yPoints, 3);
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
