public class AxialHexPoint
{
    private final int x, y;

    public AxialHexPoint(
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
            && o instanceof AxialHexPoint
            && this.x == ((AxialHexPoint)o).x
            && this.y == ((AxialHexPoint)o).y;
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

    public DoubledHexPoint toDoubled()
    {
        final int x = getX();
        final int y = getY();
        return new DoubledHexPoint(2 * x + y, y);
    }
}
