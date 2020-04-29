package application;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUI extends BorderPane {
	FileManagerDummy fManager = new FileManagerDummy();
	DataManagerDummy dManager = new DataManagerDummy();

	Font textFont = new Font("Ariel", 18);

	// Add Title

	// initialization for essential elements

	Label farmID = new Label("Farm ID: ");
	Label month = new Label("Month: ");
	Label year = new Label("Year: ");
	Label date1 = new Label("Start Date: ");
	Label date2 = new Label("End Date: ");
	Label sortedLabel = new Label("Sorted by: ");
	Label file = new Label("File Name: ");
	Label reportLabel = new Label("Choose report Type: ");
	TextArea message = new TextArea(
			"DISPLAY MESSAGE:\n" + "Farm report: Fill FarmID and Year \n" + "Monthly report: Fill year and month\n"
					+ "Annual report: Fill year\n" + "Date range report: Fill both start and end dates.\n"
					+ "min max, and average for monthly and farm report will be listed here.");

	TextField tf1 = new TextField();// farm ID
	TextField tf2 = new TextField();// month
	TextField tf3 = new TextField();// year
	TextField tf4 = new TextField();// start date
	TextField tf5 = new TextField();// end date
	TextField fileName = new TextField();

	public class Row {
		private String column1;
		private String column2;
		private String column3;

		public Row(String column1, String column2, String column3) {
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

	GUI() {
    farmID.setFont(textFont);
    month.setFont(textFont);
    year.setFont(textFont);
    date1.setFont(textFont);
    date2.setFont(textFont);
    sortedLabel.setFont(textFont);
    file.setFont(textFont);
    reportLabel.setFont(new Font(16));
    message.setFont(textFont);
    message.setEditable(false);
    message.setPrefSize(10, 300);// need to resize

    TableView table = new TableView();
    Label tableTitle = new Label("Report");

    // combo box for type of report needed
    ObservableList<String> options = FXCollections.observableArrayList("Farm Report(max)",
        "Farm Report(average)", "Farm Report(min)", "Annual Report", "Monthly Report(max)",
        "Monthly Report(average)", "Monthly Report(min)", "Date Range (Max) Report",
        "Date Range (Min) Report", "Date Range (Average) Report");

    ComboBox reportCbox = new ComboBox(options);

    // button to submit info entered
    Button submitBtn = new Button("sumbit all");
    Button loadFile = new Button("load");
    Button saveFile = new Button("save");

    loadFile.setOnAction(e -> {
      if (fManager.readFile(fileName.getText())) {
        message.appendText("\nFile loaded");
      }
    }) ;
      
    saveFile.setOnAction(e -> {
      if (fManager.writeToFile(fileName.getText())) {
        message.appendText("\nFile saved");
    }
    });
    // request corresponding
    // report.//////////////////////////////////////////////////////
    // note!!!!!!!!!!need to throw exceptions for incorrect text input
    submitBtn.setOnAction(e -> {
      displayTable(reportCbox, options);
    });

    // hbox to add load/save bottons
    HBox hboxBtn = new HBox();
    hboxBtn.getChildren().addAll(loadFile, saveFile);

    // vbox for textField labels
    VBox vbox1 = new VBox();
    vbox1.getChildren().addAll(file, farmID, month, year, date1, date2);

    // vbox for textField
    VBox vbox2 = new VBox();
    vbox2.setSpacing(2);
    vbox2.getChildren().addAll(fileName, tf1, tf2, tf3, tf4, tf5);

    // vbox for choosing report type and sumbit button
    VBox vbox3 = new VBox();
    vbox3.setSpacing(2);
    vbox3.getChildren().addAll(hboxBtn, reportLabel, reportCbox, submitBtn);


    // hbox to manage layout
    HBox hbox = new HBox();
    hbox.setPadding(new Insets(15, 12, 15, 12));
    hbox.setSpacing(30);
    hbox.getChildren().addAll(vbox1, vbox2, vbox3);

    // vbox for text fields
    VBox outer_vbox = new VBox();
    outer_vbox.setSpacing(40);
    outer_vbox.setPadding(new Insets(20, 10, 0, 0));
    outer_vbox.getChildren().addAll(hbox, message);

    this.setCenter(outer_vbox);

    // create items for table view

    // hboxTable for sorting options
    HBox hboxTable = new HBox();
    ObservableList<String> sortingOptions =

      FXCollections.observableArrayList("default", "weight", "percentage", "farm ID");

    ComboBox sortingcbox = new ComboBox(sortingOptions);
    sortingcbox.getSelectionModel().select(0);
    VBox vbox = new VBox();
    hboxTable.getChildren().addAll(sortedLabel, sortingcbox);
    hboxTable.setSpacing(10);

    ToggleGroup tgSorting = new ToggleGroup();

    RadioButton sortAscending = new RadioButton("Ascending order");
    RadioButton sortDescending = new RadioButton("Descending order");
    sortAscending.setToggleGroup(tgSorting);
    sortDescending.setToggleGroup(tgSorting);
    sortAscending.setSelected(true);

    vbox.setSpacing(5);
    vbox.setPadding(new Insets(10, 0, 0, 10));
    vbox.getChildren().clear();
    vbox.getChildren().addAll(tableTitle, hboxTable, sortAscending, sortDescending, table);

//    this.setRight(vbox);
    this.setPadding(new Insets(0, 100, 0, 0));
  }
    

	public void monthlyReport(List<Double> list, TableView table) {
    table.refresh();
    table.getItems().clear();
    TableColumn farm = new TableColumn("Month");
    farm.setCellValueFactory(new PropertyValueFactory<>("column1"));
    TableColumn weight = new TableColumn("Total Weight");
    weight.setCellValueFactory(new PropertyValueFactory<>("column2"));
    TableColumn percentage = new TableColumn("Percentage");
    percentage.setCellValueFactory(new PropertyValueFactory<>("column3"));

    table.getColumns().addAll(farm, weight, percentage);

    double sum = 0;

    for (int i = 0; i < list.size(); i++) {
      sum = sum + list.get(i);
    }
    int month = 1;
    double monthlyWeight = 0;
    double monthlyPercentage = 0;
    for (int j = 0; j < list.size(); j++) {
      String row = "row";
      monthlyWeight = list.get(j);
      monthlyPercentage = (double) monthlyWeight / (double) sum;
      row = row.concat(Integer.toString(j));
      
      Row rows = new Row(Integer.toString(month), Double.toString(monthlyWeight),
          Double.toString(monthlyPercentage));
      table.getItems().add(rows);
      month++;
    }
  }

	public void nameReport(application.DS ds, TableView table) {
		table.refresh();
		table.getItems().clear();
		TableColumn farm = new TableColumn("Farm Name");
		farm.setCellValueFactory(new PropertyValueFactory<>("column1"));
		TableColumn weight = new TableColumn("Total Weight");
		weight.setCellValueFactory(new PropertyValueFactory<>("column2"));
		TableColumn percentage = new TableColumn("Percentage");
		percentage.setCellValueFactory(new PropertyValueFactory<>("column3"));

		table.getColumns().addAll(farm, weight, percentage);

		double sum = 0;
		for (int i = 0; i < ds.farmWeight.size(); i++) {
			sum = sum + ds.farmWeight.get(i);
		}
		double monthlyWeight = 0;
		double monthlyPercentage = 0;
		for (int j = 0; j < ds.farmWeight.size(); j++) {
			monthlyWeight = ds.farmWeight.get(j);
			monthlyPercentage = (double) monthlyWeight / (double) sum;
			Row rows = new Row(ds.farmNames.get(j), Double.toString(monthlyWeight), Double.toString(monthlyPercentage));
			table.getItems().add(rows);
		}
	}

	public void displayTable(ComboBox reportCbox, ObservableList<String> options) {
    Stage pop = new Stage();
    pop.initModality(Modality.APPLICATION_MODAL);
    pop.setTitle("Report");
    Button closeBtn = new Button("Close");
    closeBtn.setOnAction(e -> pop.close());

    TableView table = new TableView();

    if (reportCbox.getValue() == null) {
      message.appendText("\nPlease select the report type first.");
    } else if (reportCbox.getValue().equals(options.get(0))) {
      if (tf1.getText().length() != 0 && tf3.getText().length() != 0) {
        monthlyReport(dManager.getMonthlyMaxForFarm(tf1.getText(), tf3.getText()), table);
      } else
        message.appendText("\nPlease fill Farm ID and Year fields");
    } else if (reportCbox.getValue().equals(options.get(1))) {
      if (tf1.getText().length() != 0 && tf3.getText().length() != 0)
        monthlyReport(dManager.getMonthlyAverageForFarm(tf1.getText(), tf3.getText()),
            table);
      else
        message.appendText("\nPlease fill Farm ID and Year fields");
    } else if (reportCbox.getValue().equals(options.get(2))) {
      if (tf1.getText().length() != 0 && tf3.getText().length() != 0)
        monthlyReport(dManager.getMonthlyMinForFarm(tf1.getText(), tf3.getText()), table);
      else
        message.appendText("\nPlease fill Farm ID and Year fields");
    } else if (reportCbox.getValue().equals(options.get(3))) {
      if (tf3.getText().length() != 0)
        monthlyReport(dManager.getAnnual(tf3.getText()), table);
      else
        message.appendText("\nPlease fill Year field");
    } else if (reportCbox.getValue().equals(options.get(4))) {
      if (tf2.getText().length() != 0 && tf3.getText().length() != 0)
        nameReport(dManager.getMonthlyMax(tf2.getText(), tf3.getText()), table);
      else
        message.appendText("\nPlease fill Year and Month fields");
    } else if (reportCbox.getValue().equals(options.get(5))) {
      if (tf2.getText().length() != 0 && tf3.getText().length() != 0)
        nameReport(dManager.getMonthlyAverage(tf2.getText(), tf3.getText()), table);
      else
        message.appendText("\nPlease fill Year and Month fields");
    } else if (reportCbox.getValue().equals(options.get(6))) {
      if (tf2.getText().length() != 0 && tf3.getText().length() != 0)
        nameReport(dManager.getMonthlyMin(tf2.getText(), tf3.getText()), table);
      else
        message.appendText("\nPlease fill Year and Month fields");
    } else if (reportCbox.getValue().equals(options.get(9))) {
      if (tf4.getText().length() != 0 && tf5.getText().length() != 0)
        nameReport(dManager.getTotalInDateRange(tf4.getText(), tf5.getText()), table);
      else
        message.appendText("\nPlease fill start and end date fields");
    }

    BorderPane root = new BorderPane();
    root.setCenter(table);
    Scene popup = new Scene(root, 500, 300);
    pop.setScene(popup);
    pop.showAndWait();
  }
}
