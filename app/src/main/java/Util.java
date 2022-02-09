import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Util
{
    private Util() {}

    /**
     * Uses a BufferedReader to read hex coordinates, then transforms those
     * coordinates into hexes.
     */
    public static Set<Hex> hexesOf(
        String file)
        throws IOException
    {
        Set<Hex> hexes = new HashSet<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line = br.readLine();

            while(line != null)
            {
                String[] tokens = line.split(" ");

                if(tokens.length != 2)
                {
                    throw new IllegalArgumentException();
                }

                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                hexes.add(new Hex(x, y));

                line = br.readLine();
            }
        }
        catch(FileNotFoundException e)
        {
            throw e;
        }
        catch(IOException e)
        {
            throw e;
        }

        return hexes;
    }
    public static Hexagon createHexagon(Hex h)//Converts Hex to Hexagon
    {
        return new Hexagon(680 + 17.3205080757 * h.getC(), 350 + 30 * h.getR());
    }
    public static int[] getHexCoords(double x, double y)//Finds coordinates of hex in given location on screen
    {
        double c = (x - 680) / 17.3205080757;
        double r = (y - 350) / 30;
        double d = (c - r) / 2;
        return new int[]{(int)(Math.round(r)), (int)(Math.round(d))};
    }
    public static double[] getHexagonCoords(Hex h)//Converts Hex to Hexagon
    {
        return new double []{680 + 17.3205080757 * h.getC(), 350 + 30 * h.getR()};
    }
}
