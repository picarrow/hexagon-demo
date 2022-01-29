//Author: Daniil Novikov
//Hexagon
//Description: Prefabed Hexagon inheriting from Javafx Polygon
import javafx.scene.shape.Polyline;
public class Hexagon extends Polyline{
    private double centerX, centerY;
    private final double radius = 10;//radius of circle hexagon is inscribed in
    public Hexagon(double x, double y)
    {
        centerX = x;
        centerY = y;
        Double [] p = new Double[14];
        for(int i = 0; i<=6;i++)
        {
            p[2*i] = centerX+radius*Math.cos(Math.toRadians(30+i*60));
            p[2*i+1] = centerY+radius*Math.sin(Math.toRadians(30+i*60));
        }
        getPoints().addAll(p);
    }
}
