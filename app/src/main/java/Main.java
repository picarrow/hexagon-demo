// Hexagon Grid
// Authors:
// Giuseppe Guerini - Main structure
// Daniil Novikov - Javafx

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        Button button = new Button("START!");
        button.setOnAction(e -> loadNextScene(stage));

        StackPane pane = new StackPane();
        pane.getChildren().add(button);

        stage.setTitle("JavaFX Experiment");
        stage.setScene(new Scene(pane));
        stage.show();
    }

    private void loadNextScene(
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
