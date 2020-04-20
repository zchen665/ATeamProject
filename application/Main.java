package application;

import java.io.FileInputStream;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    // store any command-line arguments that were entered.
    // NOTE: this.getParameters().getRaw() will get these also
    private List<String> args;

    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 600;
    private static final String APP_TITLE = "   ";
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Main layout is Border Pane example (top,left,center,right,bottom)
            BorderPane root = new BorderPane();
            
            // Add Title 
            Label title = new Label(APP_TITLE);
            title.setFont(new Font("Ariel",32));
            title.setAlignment(Pos.CENTER);

        // Add the combo box to the left of the root pane
           root.setLeft(leftPart);
           
        // Add the ImageView box to the center of the root pane
            root.setCenter(centerPart);
            
        // Add the vertical box to the right of the root pane
            root.setRight(rightPart);
            
            
        // Create Scene
            Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Add the stuff and set the primary stage
            primaryStage.setTitle(APP_TITLE);
            primaryStage.setScene(mainScene);
            primaryStage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
           launch(args);
    }
}


