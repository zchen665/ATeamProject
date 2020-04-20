package application;

import java.io.FileInputStream;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    // store any command-line arguments that were entered.
    // NOTE: this.getParameters().getRaw() will get these also
    private List<String> args;

    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 550;
    private static final String APP_TITLE = "Milk Weight Analyzer";
    
    @Override
    public void start(Stage primaryStage) throws Exception {
            BorderPane root = new BorderPane();
            Font textFont = new Font("Ariel", 18);
            
            // Add Title 
            Label title = new Label(APP_TITLE);
            title.setFont(new Font("Ariel",32));
            title.setAlignment(Pos.CENTER);
            
            //initialization for essential elements
            Label farmID = new Label("Farm ID: ");
            Label month = new Label("Month: ");
            Label year = new Label("Year: ");
            Label date1 = new Label("Start Date: ");
            Label date2 = new Label("End Date: ");
            Label reportLabel = new Label("Choose report Type: ");
            TextField message = new TextField("DISPLAY MESSAGE");
            farmID.setFont(textFont);
            month.setFont(textFont);
            year.setFont(textFont);
            date1.setFont(textFont);
            date2.setFont(textFont);
            reportLabel.setFont(new Font(16));;
            TextField tf1 = new TextField();
            TextField tf2 = new TextField();
            TextField tf3 = new TextField();
            TextField tf4 = new TextField();
            TextField tf5 = new TextField();
            message.setEditable(false);
            message.setPrefSize(10, 300);//need to resize
            
            //combo box for type of report needed
            ObservableList<String> options = 
            	    FXCollections.observableArrayList(
            	        "Farm Report",
            	        "Annual Report",
            	        "Monthly Report",
            	        "Date Range Report"
            	    );
            ComboBox reportCbox = new ComboBox(options);
            
            //button to submit info entered
            Button sumbitBtn = new Button("sumbit all");
            
            //vbox for textField labels
            VBox vbox1 = new VBox();
            vbox1.getChildren().addAll(farmID, month, year, date1, date2);
            
            //vbox for textField
            VBox vbox2 = new VBox();
            vbox2.setSpacing(2);
            vbox2.getChildren().addAll(tf1,tf2,tf3,tf4,tf5);
            
            //vbox for choosing report type and sumbit button
            VBox vbox3 = new VBox();
            vbox3.setSpacing(2);
            vbox3.getChildren().addAll(reportLabel, reportCbox, sumbitBtn);
            vbox3.setPadding(new Insets(53,10,0,0));
            
            //hbox to manage layout
            HBox hbox = new HBox();
            hbox.setPadding(new Insets(15,12,15,12));
            hbox.setSpacing(30);
            hbox.getChildren().addAll(vbox1,vbox2,vbox3);
            
            //vbox for text fields
            VBox outer_vbox = new VBox();
            outer_vbox.setSpacing(40);
            outer_vbox.setPadding(new Insets(20,10,0,0));
            outer_vbox.getChildren().addAll(hbox, message);

            
            root.setLeft(outer_vbox);
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