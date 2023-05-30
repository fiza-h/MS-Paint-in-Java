//import java.awt.*;
//import java.util.ArrayList;
//
//public class FreeDrawing extends AbstractShape
//{
//    protected ArrayList<FreeDrawCircle> shapes = new ArrayList<>();
//    private Point lastPoint;
//
//    public void addPoint(Point p)
//    {
//        if (lastPoint != null) {
//            FreeDrawCircle circle = new FreeDrawCircle(lastPoint);
//            circle.setColor(color);
//            circle.setStrokeWidth(strokeWidth);
//            shapes.add(circle);
//        }
//        lastPoint = p;
//    }
//
//    public void clear() {
//        shapes.clear();
//    }
//
//    @Override
//    public void draw(Graphics g) {
//        for (FreeDrawCircle shape : shapes)
//        {
//            shape.draw(g);
//        }
//    }
//}
