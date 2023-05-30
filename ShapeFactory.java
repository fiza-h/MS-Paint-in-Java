public class ShapeFactory
{
    public Shape getShape(String shapeType)
    {
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();

        }
        else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();

        }
        else if(shapeType.equalsIgnoreCase("HEXAGON")){
            return new Hexagon();
        }
        else if(shapeType.equalsIgnoreCase("PENTAGRAM")){
            return new Pentagram();
        }
        else if(shapeType.equalsIgnoreCase("TRIANGLE")){
            return new Triangle();
        }
        else if(shapeType.equalsIgnoreCase("RIGHT TRIANGLE")){
            return new Triangle();
        }

        return null;
    }
}
