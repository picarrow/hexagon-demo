// Author: Daniil Novikov
// Hexagon
// Description: Prefabed Hexagon inheriting from Javafx Polygon

import java.util.List;
import javafx.scene.shape.Polyline;

public class Hexagon
    extends Polyline
{
    private double centerX, centerY;
    private final double radius = 20; // radius of circle hexagon is inscribed in

    public Hexagon(
        double x,
        double y)
    {
        centerX = x;
        centerY = y;
        Double[] p = new Double[14];

        for(int i = 0; i < 7; i++)
        {
            p[2 * i] = centerX + radius * Math.cos(Math.toRadians(30 + i * 60));
            p[2 * i + 1] = centerY + radius * Math.sin(Math.toRadians(30 + i * 60));
        }

        getPoints().addAll(p);
    }

    public boolean isInside(
        double x,
        double y)//check if a point is inside the hexagon
    {
        List<Double> points = getPoints();
        double check = (y - points.get(1)) * (points.get(2) - points.get(0)) - (x - points.get(0)) * (points.get(3) - points.get(1));
        double check2 = 0;

        for(int i = 2; i < points.size()-2; i+=2)
        {
            check2 = (y - points.get(i + 1)) * (points.get(i + 2) - points.get(i)) - (x - points.get(i)) * (points.get(i+3) - points.get(i + 1));

            if(check2 / check < 0)
            {
                return false; //If all the values calculated do not have the same sign, the point is outside the hexagon
            }
        }

        return true;
    }
    public double getX()
    {
        return centerX;
    }
    public double getY()
    {
        return centerY;
    }
}
