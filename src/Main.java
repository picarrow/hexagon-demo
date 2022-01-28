//Hexagon Grid
//Authors: 
//Giuseppe Guerini - Main structure
//Daniil Novikov - Javafx

//Datastructure and filereading imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//Javafx and graphics imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{
    /***
     * Uses a BufferedReader to read hex coordinates, then transforms
     * those coordinates into hexes.
    ***/
	
    public void start(Stage primaryStage)
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

        //Javafx stuff
	primaryStage.setTitle("JavaFX Experiment");//Window
        Pane pane = new Pane();//Pane contained in scene
        Scene scene = new Scene(pane, 1360, 700);//Set scene size
	
	for(Hex h:hexes)
	{
		double x = 10*h.getX();
		double y = 10*h.getY();
		pane.getChildren().add(new Hexagon(x,y));	
	}
        primaryStage.setScene(scene);//Add scene to window
        primaryStage.show();//Display window
    }
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

    public static void main(String[] args)
    {
        launch(args);
	System.exit(0);
    }
}
