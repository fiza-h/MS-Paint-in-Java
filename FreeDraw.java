import java.awt.*;
import java.util.ArrayList;
import java.util.Currency;

public class FreeDraw implements DrawableShapes
{
    protected ArrayList<Circle> stackFreeDraw; // arraylist of circles

    public FreeDraw()
    {
        stackFreeDraw = new ArrayList<>();
    }

    public void draw(Graphics g)
    {
        for (Circle circle : stackFreeDraw)
        {
            circle.draw(g);
        }
    }

    public ArrayList<Circle> getStackFreeDraw() {
        return stackFreeDraw;
    }

    public void setStackFreeDraw(ArrayList<Circle> stackFreeDraw) {
        this.stackFreeDraw = stackFreeDraw;
    }
}
