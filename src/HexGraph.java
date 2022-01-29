import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class HexGraph
{
    private final Map<Hex, Set<Hex>> graph;

    // PUBLIC INTERFACE

    public HexGraph(
        Set<Hex> hexes)
    {
        graph = new HashMap<>();

        for(Hex hex : hexes)
        {
            graph.put(hex, new HashSet<>());

            Hex[] neighbors = new Hex[6];
            neighbors[0] = new Hex(hex.getR() - 1, hex.getD() + 0);
            neighbors[1] = new Hex(hex.getR() - 1, hex.getD() + 1);
            neighbors[2] = new Hex(hex.getR() + 0, hex.getD() - 1);
            neighbors[3] = new Hex(hex.getR() + 0, hex.getD() + 1);
            neighbors[4] = new Hex(hex.getR() + 1, hex.getD() - 1);
            neighbors[5] = new Hex(hex.getR() + 1, hex.getD() + 0);

            for(Hex neighbor : neighbors)
            {
                if(hexes.contains(neighbor))
                {
                    neighborsOf(hex).add(neighbor);
                }
            }
        }
    }

    public Set<Hex> getHexes()
    {
        return new HashSet<>(graph.keySet());
    }

    public Set<Hex> getNeighborsOf(
        Hex hex)
    {
        return new HashSet<>(neighborsOf(hex));
    }

    public List<Hex> getPath(
        Hex src,
        Hex dst)
    {
        return new DijkstraResult(src).pathTo(dst);
    }

    @Override
    public String toString()
    {
        return graph.toString();
    }

    // PRIVATE INTERFACE

    private Set<Hex> neighborsOf(
        Hex hex)
    {
        return graph.get(hex);
    }

    private class DijkstraResult
    {
        private final Map<Hex, Double> dist;
        private final Map<Hex, Hex> pred;

        public DijkstraResult(
            Hex src)
        {
            dist = new HashMap<>();
            pred = new HashMap<>();

            Queue<Hex> queue = new PriorityQueue<>((Hex a, Hex b) -> {
                if(dist.get(a) < dist.get(b))
                {
                    return -1;
                }

                if(dist.get(a) > dist.get(b))
                {
                    return 1;
                }

                return 0;
            });

            for(Hex hex : graph.keySet())
            {
                dist.put(hex, Double.POSITIVE_INFINITY);
                pred.put(hex, null);
                queue.add(hex);
            }

            dist.put(src, 0.0);

            while(!queue.isEmpty())
            {
                Hex u = queue.remove();

                for(Hex v : graph.get(u))
                {
                    if(dist.get(v) > dist.get(u) + 1)
                    {
                        dist.put(v, dist.get(u) + 1);
                        pred.put(v, u);
                    }
                }
            }
        }

        public List<Hex> pathTo(
            Hex dst)
        {
            List<Hex> path = new ArrayList<>();
            Hex predecessor = dst;

            while(predecessor != null)
            {
                path.add(0, predecessor);
                predecessor = pred.get(predecessor);
            }

            return path;
        }
    }
}
