public class AxialHexPoint
    extends HexPoint
{
    public AxialHexPoint(
        int x,
        int y)
    {
        super(x, y);
    }

    public DoubledHexPoint toDoubled()
    {
        final int x = getX();
        final int y = getY();
        return new DoubledHexPoint(2 * x + y, y);
    }
}
