import java.awt.Color;
import java.awt.Graphics;

public class Hexagon implements Shape, DrawableShapes {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color fillColor;
    private Color strokeColor;
    private int stroke;

    public Hexagon(int x, int y, int width, int height, Color fillColor, Color strokeColor, int stroke) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.stroke = stroke;
    }

    public Hexagon() {
    }

    @Override
    public void draw(Graphics g)
    {
        int[] xpoints = { x, x + width / 4, x + width * 3 / 4, x + width, x + width * 3 / 4, x + width / 4 };
        int[] ypoints = { y + height / 2, y, y, y + height / 2, y + height, y + height };

        // Calculate the points of the larger hexagon
        int strokeOffset = stroke;
        int[] largerXPoints = {
                x - strokeOffset,
                x + width / 4 - strokeOffset,
                x + width * 3 / 4 + strokeOffset,
                x + width + strokeOffset,
                x + width * 3 / 4 + strokeOffset,
                x + width / 4 - strokeOffset
        };
        int[] largerYPoints = {
                y + height / 2 + strokeOffset,
                y - strokeOffset,
                y - strokeOffset,
                y + height / 2 + strokeOffset,
                y + height + strokeOffset,
                y + height + strokeOffset
        };

        // Draw the larger hexagon with the stroke color
        g.setColor(strokeColor);
        g.fillPolygon(largerXPoints, largerYPoints, 6);

        // Draw the smaller hexagon with the fill color
        g.setColor(fillColor);
        g.fillPolygon(xpoints, ypoints, 6);
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

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
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
