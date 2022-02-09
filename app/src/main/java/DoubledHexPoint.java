public class DoubledHexPoint
{
    private final int x, y;

    public DoubledHexPoint(
        int x,
        int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public boolean equals(
        Object o)
    {
        return o != null
            && o instanceof DoubledHexPoint
            && this.x == ((DoubledHexPoint)o).x
            && this.y == ((DoubledHexPoint)o).y;
    }

    @Override
    public int hashCode()
    {
        int hash = 17;
        hash = 31 * hash + x;
        hash = 31 * hash + y;
        return hash;
    }

    @Override
    public String toString()
    {
        return "(" + x + " " + y + ")";
    }

    public AxialHexPoint toAxial()
    {
        final int x = getX();
        final int y = getY();
        return new AxialHexPoint((x - y) / 2, y);
    }
}
