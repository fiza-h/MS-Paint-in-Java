import java.awt.*;

public class Circle implements Shape, DrawableShapes{

    private int x;
    private int x2;
    private int y;
    private int y2;
    private Color color;
    private Color strokeColor;
    private int stroke;

    public Circle()
    {

    }

    @Override
    public String toString() {
        return "Circle," + x +
                "," + y +
                "," + x2 +
                "," + y2 +
                "," + color.getRed() + "," + color.getGreen() +
                "," + color.getBlue();
    }

    /**
     * constructor
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @param c
     */
    public Circle(int x, int y, int x2, int y2, Color c, Color strokeColor, int stroke) {
        this.x = x;
        this.x2 = x2;
        this.y = y;
        this.y2 = y2;
        this.color = c;
        this.strokeColor = strokeColor;
        this.stroke = stroke;
    }

    @Override
    public void draw(Graphics g)
    {
        int px = Math.min(x,x2);
        int py = Math.min(y,y2);
        int diameter=Math.max(Math.abs(x-x2), Math.abs(y-y2));

        g.setColor(strokeColor);
        g.fillOval(px-stroke, py-stroke, diameter+(stroke*2), diameter+(stroke*2));
        // draws shape
        g.setColor(color);
        g.fillOval(px, py, diameter, diameter);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
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
