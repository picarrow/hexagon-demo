public class Hex
{
    private final int r;
    private final int d;

    // PUBLIC INTERFACE

    public Hex(
        int r,
        int d)
    {
        this.r = r;
        this.d = d;
    }
    
    public int getR()
    {
        return r;
    }

    public int getD()
    {
        return d;
    }

    public int getC()
    {
        return 2 * d + r;
    }

    @Override
    public boolean equals(
        Object o)
    {
        return o != null
            && o instanceof Hex
            && this.r == ((Hex)o).r
            && this.d == ((Hex)o).d;
    }

    @Override
    public int hashCode()
    {
        int hash = 17;
        hash = 31 * hash + r;
        hash = 31 * hash + d;
        return hash;
    }

    @Override
    public String toString()
    {
        return "[" + r + " " + d + "]";
    }
}
