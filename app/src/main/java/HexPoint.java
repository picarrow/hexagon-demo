public abstract class HexPoint
{
    private final int x, y;

    public HexPoint(
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
            && getClass() == o.getClass()
            && this.x == ((HexPoint)o).x
            && this.y == ((HexPoint)o).y;
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
}
