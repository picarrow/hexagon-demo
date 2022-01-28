public class Hex
{
    private final int x;
    private final int y;

    // PUBLIC INTERFACE

    public Hex(
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
            && o instanceof Hex
            && this.x == ((Hex)o).x
            && this.y == ((Hex)o).y;
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
        return "H" + x + y;
    }
}
