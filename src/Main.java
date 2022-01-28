import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main
{
    /***
     * Uses a BufferedReader to read hex coordinates, then transforms
     * those coordinates into hexes.
    ***/
    private static Set<Hex> hexesFrom(
        String fileName)
        throws IOException
    {
        Set<Hex> hexes = new HashSet<>();

        try(BufferedReader br = new BufferedReader(new FileReader(fileName)))
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
        catch(IOException e)
        {
            throw e;
        }

        return hexes;
    }

    public static void main(
        String[] args)
    {
        Set<Hex> hexes = null;

        try
        {
            hexes = hexesFrom("hex-example-1.txt");
        }
        catch(Exception e)
        {
            return;
        }

        HexGraph graph = new HexGraph(hexes);
        Hex src = new Hex(0, 2);
        Hex dst = new Hex(2, 0);
        List<Hex> path = graph.getPath(src, dst);

        System.out.println("Hexes: " + hexes);
        System.out.println("Graph: " + graph);
        System.out.println("Path (H02 -> H20): " + path);
    }
}
