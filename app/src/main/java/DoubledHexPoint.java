public class DoubledHexPoint
    extends HexPoint
{
    public DoubledHexPoint(
        int x,
        int y)
    {
        super(x, y);
    }

    public AxialHexPoint toAxial()
    {
        final int x = getX();
        final int y = getY();
        return new AxialHexPoint((x - y) / 2, y);
    }
}
