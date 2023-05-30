import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class gridSingleton implements PortionListener
{
    private static gridSingleton grid = new gridSingleton();
    private ArrayList<ImageIcon> images = new ArrayList<>();
    private  Button gridButton;
    private int clickCount = 0;

    private gridSingleton()
    {
        setButtons();
    }

    private void setButtons()
    {
        images.add(new ImageIcon("./src/gridButtons/1.png"));
        images.add(new ImageIcon("./src/gridButtons/2.png"));
        images.add(new ImageIcon("./src/gridButtons/3.png"));
        images.add(new ImageIcon("./src/gridButtons/4.png"));
        images.add(new ImageIcon("./src/gridButtons/5.png"));
        images.add(new ImageIcon("./src/gridButtons/6.png"));
        images.add(new ImageIcon("./src/gridButtons/7.png"));

        gridButton = new Button(895, 55, 100, 100, images.get(0).getImage(), images);

    }

    public void draw(Graphics g, ImageObserver observer)
    {
        gridButton.paint(g, observer);

        if (gridButton.GetImage().equals(images.get(1).getImage()))
        {
            // Set the color to light gray
            g.setColor(Color.LIGHT_GRAY);

//            // Draw vertical grid lines
//            for (int x = 0; x <= 432; x += 216) {
//                g.drawLine(x, 168, x, 600);
//            }
//
//            // Draw horizontal grid lines
//            for (int y = 168; y <= 600; y += 216) {
//                g.drawLine(0, y, 432, y);
//            }

            for (int x = 0; x < 1000; x+=2)
            {
                for (int y = 168; y < 600; y+=2)
                {
                    g.drawRect(x, y, 2, 2);
                }
            }

            DrawingBoard.gridON = true;
        }

        if (gridButton.GetImage().equals(images.get(2).getImage()))
        {
            // Set the color to light gray
            g.setColor(Color.LIGHT_GRAY);

//            // Draw vertical grid lines
//            for (int x = 0; x <= 432; x += 108) {
//                g.drawLine(x, 168, x, 600);
//            }
//
//            // Draw horizontal grid lines
//            for (int y = 168; y <= 600; y += 108) {
//                g.drawLine(0, y, 432, y);
//            }
//
//            g.setColor(Color.BLACK);
//            g.fillRect(432, 168, 568, 432);

            for (int x = 0; x < 1000; x+=4)
            {
                for (int y = 168; y < 600; y+=4)
                {
                    g.drawRect(x, y, 4, 4);
                }
            }

            DrawingBoard.gridON = true;
        }

        if (gridButton.GetImage().equals(images.get(3).getImage()))
        {
            // Set the color to light gray
            g.setColor(Color.LIGHT_GRAY);

            // Draw vertical grid lines
//            for (int x = 0; x <= 432; x += 54) {
//                g.drawLine(x, 168, x, 600);
//            }
//
//            // Draw horizontal grid lines
//            for (int y = 168; y <= 600; y += 54) {
//                g.drawLine(0, y, 432, y);
//            }
//
//            g.setColor(Color.BLACK);
//            g.fillRect(432, 168, 568, 432);

            for (int x = 0; x < 1000; x+=8)
            {
                for (int y = 168; y < 600; y+=8)
                {
                    g.drawRect(x, y, 8, 8);
                }
            }

            DrawingBoard.gridON = true;
        }

        if (gridButton.GetImage().equals(images.get(4).getImage()))
        {
            // Set the color to light gray
            g.setColor(Color.LIGHT_GRAY);

            // Draw vertical grid lines
//            for (int x = 0; x <= 432; x += 27) {
//                g.drawLine(x, 168, x, 600);
//            }
//
//            // Draw horizontal grid lines
//            for (int y = 168; y <= 600; y += 27) {
//                g.drawLine(0, y, 432, y);
//            }
//
//            g.setColor(Color.BLACK);
//            g.fillRect(432, 168, 568, 432);

            for (int x = 0; x < 1000; x+=16)
            {
                for (int y = 168; y < 600; y+=16)
                {
                    g.drawRect(x, y, 16, 16);
                }
            }

            DrawingBoard.gridON = true;
        }

        if (gridButton.GetImage().equals(images.get(5).getImage()))
        {
            // Set the color to light gray
            g.setColor(Color.LIGHT_GRAY);

            // Draw vertical grid lines
//            for (int x = 0; x <= 432; x += 13) {
//                g.drawLine(x, 168, x, 600);
//            }
//
//            // Draw horizontal grid lines
//            for (int y = 168; y <= 600; y += 13) {
//                g.drawLine(0, y, 432, y);
//            }
//
//            g.setColor(Color.BLACK);
//            g.fillRect(432, 168, 568, 432);

            for (int x = 0; x < 1000; x+=32)
            {
                for (int y = 168; y < 600; y+=32)
                {
                    g.drawRect(x, y, 32, 32);
                }
            }

            DrawingBoard.gridON = true;
        }

        if (gridButton.GetImage().equals(images.get(6).getImage()))
        {
            // Set the color to light gray
            g.setColor(Color.LIGHT_GRAY);

            // Draw vertical grid lines
//            for (int x = 0; x <= 432; x += 7) {
//                g.drawLine(x, 168, x, 600);
//            }
//
//            // Draw horizontal grid lines
//            for (int y = 168; y <= 600; y += 13) {
//                g.drawLine(0, y, 432, y);
//            }
//            g.setColor(Color.BLACK);
//            g.fillRect(432, 168, 568, 432);

            for (int x = 0; x < 1000; x+=64)
            {
                for (int y = 168; y < 600; y+=64)
                {
                    g.drawRect(x, y, 64, 64);
                }
            }
            DrawingBoard.gridON = true;
        }
    }

    public static gridSingleton getInstance()
    {
        return grid;
    }

    @Override
    public void onClick(int x, int y)
    {
        gridButton.IsClicked(x, y);
        if (gridButton.IsPressed())
        {
            clickCount++;
        }

        if (clickCount<=6)
        {
            gridButton.setCurrent_image(gridButton.images.get(clickCount).getImage());
        }

        if (clickCount > 6)
        {
            clickCount = 0;
            gridButton.setCurrent_image(gridButton.images.get(0).getImage());
        }
    }

    @Override
    public void onMove(int x, int y) {

    }

    @Override
    public void onPress(int x, int y) {

    }

    @Override
    public void onDrag(int x, int y) {

    }

    @Override
    public void onRelease(int x, int y) {

    }

}
