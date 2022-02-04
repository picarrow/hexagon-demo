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
import java.util.Map;
import java.util.HashMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class Main
    extends Application
{
    private Text mouseLabel;
    private Set<Hex> hexes;

    public void start(
        Stage stage)
    {
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

        Pane canvas = new Pane();
        Scene scene = new Scene(canvas);

        mouseLabel = new Text();
        mouseLabel.setX(10);
        mouseLabel.setY(10);
        canvas.setOnMouseMoved(e -> displayMousePos(e));

        System.out.println("Add Hex Check");
        Set<Hexagon> hexagons = new HashSet<>();
        Set<Text> hexLabels = new HashSet<>();
        double horSpacing = 17.3205080757;
        double verSpacing = 30;

        for(Hex h : hexes)
        {
            double r = h.getR(); // row
            double d = h.getD(); // diagonal
            double c = 2 * d + r; // column
            double x = 680 + horSpacing * c;
            double y = 350 + verSpacing * r;
            hexagons.add(new Hexagon(x, y));
            hexLabels.add(new Text(x - 10, y + 10, h.toString()));
            System.out.println("Hexagon Added");
        }

        // stage.setMaxWidth(1920);
        // stage.setMaxHeight(1080);
        canvas.getChildren().add(mouseLabel);
        canvas.getChildren().addAll(hexagons);
        canvas.getChildren().addAll(hexLabels);
        stage.setTitle("JavaFX Experiment");
        stage.setScene(scene);
        stage.show();
    }

    public void displayMousePos(
        MouseEvent e)
    {
        double x = e.getSceneX();
        double y = e.getSceneY();
        // Find Hex from coordinates
        double c = (x - 680) / 17.3205080757;
        double r = (y - 350) / 30;
        double d = (c - r) / 2;
        int row = (int)(Math.round(r));
        int diagonal = (int)(Math.round(d));
        Hex h = new Hex(row, diagonal);
        
        // If condition currently does not work, key is never found
        if(hexes.contains(h))
        {
            mouseLabel.setText("Mouse Pos: " + x + ", " + y + "\nHex: " + row + ", " + diagonal);
            System.out.println("Hex detected" + "   " + row + ", " + diagonal);
        }
        else
        {
            mouseLabel.setText("Mouse Pos: " + x + ", " + y + "\nNo Hex Detected");
        }
    }

    public static void main(
        String[] args)
    {
        Application.launch(args);
    }
}
