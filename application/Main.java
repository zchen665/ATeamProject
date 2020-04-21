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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
  
  public class Row {
    private String column1;
    private String column2;
    private String column3;

    public Row (String column1, String column2, String column3) {
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
    }
    

    public String getColumn1() {
      return column1;
    }

    public String getColumn2() {
      return column2;
    }

    public String getColumn3() {
      return column3;
    }

}
  
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
            Label sortedLabel = new Label("Sorted by: ");
            Label file = new Label("File Name: ");
            Label reportLabel = new Label("Choose report Type: ");
            TextArea message = new TextArea("DISPLAY MESSAGE:\n"
            		+ "Farm report: Fill FarmID and Year \n"
            		+ "Monthly report: Fill year and month\n"
            		+ "Annual report: Fill year\n"
            		+ "Date range report: Fill both start and end dates.\n"
            		+ "min max, and average for monthly and farm report will be listed here.");
            farmID.setFont(textFont);
            month.setFont(textFont);
            year.setFont(textFont);
            date1.setFont(textFont);
            date2.setFont(textFont);
            sortedLabel.setFont(textFont);
            file.setFont(textFont);
            reportLabel.setFont(new Font(16));;
            TextField tf1 = new TextField();
            TextField tf2 = new TextField();
            TextField tf3 = new TextField();
            TextField tf4 = new TextField();
            TextField tf5 = new TextField();
            TextField fileName = new TextField();
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
            Button loadFile = new Button("load");
            Button saveFile = new Button("save");

            //hbox to add load/save bottons
            HBox hboxBtn = new HBox();
            hboxBtn.getChildren().addAll(loadFile,saveFile);
            
            //vbox for textField labels
            VBox vbox1 = new VBox();
            vbox1.getChildren().addAll(file, farmID, month, year, date1, date2);
            
            //vbox for textField
            VBox vbox2 = new VBox();
            vbox2.setSpacing(2);
            vbox2.getChildren().addAll(fileName, tf1,tf2,tf3,tf4,tf5);
            
            //vbox for choosing report type and sumbit button
            VBox vbox3 = new VBox();
            vbox3.setSpacing(2);
            vbox3.getChildren().addAll(hboxBtn,reportLabel, reportCbox, sumbitBtn);
//            vbox3.setPadding(new Insets(53,10,0,0));
            
            
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
            
            //create items for table view
            TableView table = new TableView();
            
            Label tableTitle = new Label("Report");
            tableTitle.setFont(textFont);
            
            TableColumn farm = new TableColumn("Farm ID");
            farm.setCellValueFactory(new PropertyValueFactory<>("column1"));
            TableColumn weight = new TableColumn("Total Weight");
            weight.setCellValueFactory(new PropertyValueFactory<>("column2"));
            TableColumn percentage = new TableColumn("Percentage");
            percentage.setCellValueFactory(new PropertyValueFactory<>("column3"));
            
            table.getColumns().addAll(farm, weight, percentage);
            
            Row row1 = new Row("Farm1", "100", "25");
            Row row2 = new Row("Farm2", "80", "20");
            Row row3 = new Row("Farm3", "40", "10");
            Row row4 = new Row("Farm4", "20", "5");
            Row row5 = new Row("Farm5", "160", "40");
            
            table.getItems().addAll(row1, row2, row3, row4, row5);
            
            //hboxTable for sorting options
            HBox hboxTable = new HBox();
            ObservableList<String> sortingOptions = 
            	    FXCollections.observableArrayList(
            	    	"default",
            	        "weight",
            	        "percentage",
            	        "farm ID"
            	    );
            ComboBox sortingcbox = new ComboBox(sortingOptions);
            sortingcbox.getSelectionModel().select(0);
            VBox vbox = new VBox();
            hboxTable.getChildren().addAll(sortedLabel,sortingcbox);
            hboxTable.setSpacing(10);
            
            ToggleGroup tgSorting = new ToggleGroup();;
            RadioButton sortAscending = new RadioButton("Ascending order");
            RadioButton sortDescending= new RadioButton("Descending order");
            sortAscending.setToggleGroup(tgSorting);
            sortDescending.setToggleGroup(tgSorting);
            sortAscending.setSelected(true);
            
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll(tableTitle, hboxTable, sortAscending, sortDescending, table);
            
            root.setRight(vbox);
            root.setPadding(new Insets(0,100,0, 0));
     
            
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