// Hexagon Grid
// Authors:
// Giuseppe Guerini - Main structure
// Daniil Novikov - Javafx


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public final class Main
    extends Application
{
    public void init()
    {
        // some code that gets called automatically before start()
    }

    public void stop()
    {
        // some code that gets called after Platform.exit() is invoked.
    }

    public void start(
        Stage stage)
    {
        Button gameButton = new Button("START!");
        gameButton.setOnAction(e -> loadNextScene(stage));

        StackPane pane = new StackPane();
        pane.getChildren().add(gameButton);

        stage.setTitle("JavaFX Experiment");
        stage.setScene(new Scene(pane));
        stage.show();
    }
    
    private void loadNextScene( //Reads file and draws 
        Stage stage)
    {
        Set<Hex> hexes = null;

        try
        {
            hexes = Util.hexesOf("hex-example-1.txt");
        }
        catch(IOException e)
        {
            System.out.println("Unexpected IO exception.");
            Platform.exit();
            return;
        }

        Text mouseLabel = new Text();
        mouseLabel.setX(10);
        mouseLabel.setY(10);

        System.out.println("Add Hex Check");
        Set<Hexagon> hexagons = new HashSet<>();
        Set<Text> hexLabels = new HashSet<>();

        for(Hex h : hexes)
        {
            double[] coords = Util.getHexagonCoords(h);
            hexagons.add(Util.createHexagon(h));
            hexLabels.add(new Text(coords[0]- 10, coords[1] + 10, h.toString()));
            System.out.println("Hexagon Added");
        }

        Pane pane = new Pane();
        final Set<Hex> hexes1 = hexes;
        pane.setOnMouseMoved(e -> updateMouseLabel(e, hexes1, mouseLabel));
        pane.getChildren().add(mouseLabel);
        pane.getChildren().addAll(hexagons);
        pane.getChildren().addAll(hexLabels);

        stage.setScene(new Scene(pane));
    }

    public void updateMouseLabel(
        MouseEvent e,
        Set<Hex> hexes,
        Text mouseLabel)
    {
        int [] coords = Util.getHexCoords(e.getSceneX(), e.getSceneY());
        Hex h = new Hex(coords[0], coords[1]);
        
        // If condition currently does not work, key is never found
        if(hexes.contains(h))
        {
            mouseLabel.setText("Mouse Pos: " + e.getSceneX() + ", " + e.getSceneY() + "\nHex: " + coords[0] + ", " + coords[1]);
            System.out.println("Hex detected" + "   " + coords[0] + ", " + coords[1]);
        }
        else
        {
            mouseLabel.setText("Mouse Pos: " + e.getSceneX() + ", " + e.getSceneY() + "\nNo Hex Detected");
        }
    }

    public static void main(
        String[] args)
    {
        Application.launch(args);
    }
}
