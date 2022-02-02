// Hexagon Grid
// Authors:
// Giuseppe Guerini - Main structure
// Daniil Novikov - Javafx

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application
{
    public void start(Stage primaryStage)
    {
    	System.out.println("start check");
    	Set<Hex> hexes = null;
	Map<Hexagon, Hex> hexagonMap = new Map<Hexagon, Hex>();//Mapping displayed hexagons to respective hex objects
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

        //Javafx stuff
    	primaryStage.setTitle("JavaFX Experiment");//Window
        Pane pane = new Pane();//Pane contained in scene
        Scene scene = new Scene(pane, 1360, 700);//Set scene size
	
    	double horSpacing = 17.3205080757;
        double verSpacing = 30;

    	for(Hex h : hexes)
    	{
    		double r = h.getR();//row
    		double d = h.getD();//diagonal
    		double c = 2*d + r;//column
    		double x = 680+horSpacing*c;
    		double y = 350+verSpacing*r;
    		Hexagon hexagon = new Hexagon(x,y);
		hexagonMap.put(hexagon, h);
		pane.getChildren().add(hexagon);	
		pane.getChildren().add(new Text(x-10,y+10,h.toString()));
		System.out.println("Adding Hexagon");
    	}

        primaryStage.setScene(scene);//Add scene to window
        primaryStage.show();//Display window
    }

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
        System.out.println("main check");
        launch(args);
    }
}
