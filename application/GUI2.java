package application;

import java.io.File;
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
import javafx.scene.control.ListView;
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

public class GUI2 extends BorderPane {
//    FileManagerDummy fManager = new FileManagerDummy();
	File_Manager fManager = new File_Manager();
	DataManager dManager;
	String outputStr = "";

	File file;
	ListView<String> listView;
	ObservableList<String> list;
	List<File> fileList;
	Font textFont = new Font("Ariel", 18);

	// initialization for essential elements
	Label farmID = new Label("Farm ID: ");
	Label month = new Label("Month: ");
	Label year = new Label("Year: ");
	Label date1 = new Label("Start Date: ");
	Label date2 = new Label("End Date: ");
	Label sortedLabel = new Label("Sorted by: ");
	Label fileLabel = new Label("File Name: ");
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
	// button to submit info entered
	Button submitBtn = new Button("sumbit all");
	Button loadFile = new Button("load");
	Button saveFile = new Button("save");

	/**
	 * Constructor of GUI 
	 */
	GUI2() {
	    // set the font for each element
		farmID.setFont(textFont);
		month.setFont(textFont);
		year.setFont(textFont);
		date1.setFont(textFont);
		date2.setFont(textFont);
		sortedLabel.setFont(textFont);
		fileLabel.setFont(textFont);
		reportLabel.setFont(new Font(16));
		message.setFont(textFont);
		message.setEditable(false);
		message.setPrefSize(10, 300);// need to resize

		// initialize the tableview
		TableView table = new TableView();
		Label tableTitle = new Label("Report");
		list = FXCollections.observableArrayList();
		listView = new ListView();
		// combo box for type of report needed
		ObservableList<String> options = FXCollections.observableArrayList("Farm Report", "Farm Report(detailed)",
				"Monthly Report", "Monthly Report(detailed)", "Annual Report", "Date Range Report");

		ComboBox reportCbox = new ComboBox(options);
		
		// set action for loading the file
		loadFile.setOnAction(e -> {
			try {
			  //reads the file
				File_Manager.enlistAllFiles(new Stage(), fileList, listView, list);
				dManager = new DataManager(File_Manager.cf);
				message.appendText("\nFile read");
			} catch (NumberFormatException e1) {
				message.appendText("\n!!!Error reading file!!!");
			}
		});
		// set action for saving the file
		saveFile.setOnAction(e -> { 
		  // save the file
			if (fManager.writeToFile(fileName.getText(), outputStr)) {
				message.appendText("\nFile saved");
			}
		});
		// request corresponding
		// report.//////////////////////////////////////////////////////
		// note!!!!!!!!!!need to throw exceptions for incorrect text input
		submitBtn.setOnAction(e -> {
			if (dManager == null)
				message.appendText("\nPlease load data first.");
			else if (reportCbox.getValue() == null) {
				message.appendText("\nPlease select the report type first.");
			} else if (reportCbox.getValue().equals(options.get(0))) {
			  //farm report
				if (tf1.getText().length() != 0 && tf3.getText().length() != 0) {
				  // Check if the input is missing
					displayTable(tf1.getText(), tf2.getText(), options.get(0));
				} else
					message.appendText("\nPlease fill Farm ID and Year fields");
			} else if (reportCbox.getValue().equals(options.get(1))) {
			  //farm report detailed
				if (tf1.getText().length() != 0 && tf3.getText().length() != 0) {
				  // Check if the input is missing
					displayTable(tf1.getText(), tf2.getText(), options.get(1));
				} else
					message.appendText("\nPlease fill Farm ID and Year fields");
			} else if (reportCbox.getValue().equals(options.get(2))) {
			  //Monthly Report
				if (tf2.getText().length() != 0 && tf3.getText().length() != 0) {
				  // Check if the input is missing
					displayTable(tf2.getText(), tf3.getText(), options.get(2));
				} else
					message.appendText("\nPlease fill month and Year fields");
			} else if (reportCbox.getValue().equals(options.get(3))) {
			  //Monthly report detailed
				if (tf2.getText().length() != 0 && tf3.getText().length() != 0) {
				  // Check if the input is missing
					displayTable(tf2.getText(), tf3.getText(), options.get(3));
				} else
					message.appendText("\nPlease fill month and Year fields");
			} else if (reportCbox.getValue().equals(options.get(4))) {
			  //Annual report
				if (tf3.getText().length() != 0) {
				  // Check if the input is missing
					displayTable(null, tf3.getText(), options.get(4));
				} else
					message.appendText("\nPlease fill Year field");
			} else if (reportCbox.getValue().equals(options.get(5))) {
			  //Date range report
				if (tf4.getText().length() != 0 && tf5.getText().length() != 0) {
				  // Check if the input is missing
					displayTable(tf4.getText(), tf5.getText(), options.get(5));
				} else
					message.appendText("\nPlease fill start and end date fields");
			}
		});

		// hbox to add load/save bottons
		HBox hboxBtn = new HBox();
		hboxBtn.getChildren().addAll(loadFile, saveFile);

		// vbox for textField labels
		VBox vbox1 = new VBox();
		vbox1.getChildren().addAll(fileLabel, farmID, month, year, date1, date2);

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
		this.setPadding(new Insets(0, 100, 0, 0));
	}

	/**
	 * Methods to add the monthly information into the tableview
	 * @param list
	 * @param table
	 */
	public void month_weight_percentReport(List<Double> list, TableView table) {
	    //refresh table each time call the meothod
		table.refresh();
		table.getItems().clear();
		
		//Initialize the three column title for the table 
		TableColumn farm = new TableColumn("Month");
		farm.setCellValueFactory(new PropertyValueFactory<>("column1"));
		TableColumn weight = new TableColumn("Total Weight");
		weight.setCellValueFactory(new PropertyValueFactory<>("column2"));
		TableColumn percentage = new TableColumn("Percentage");
		percentage.setCellValueFactory(new PropertyValueFactory<>("column3"));

		table.getColumns().addAll(farm, weight, percentage);
		outputStr += "Month, Weight, percentage\n";
		double sum = 0;
		//calculating the sum of weight for later calculating the percentage
		for (int i = 0; i < list.size(); i++) {
			sum = sum + list.get(i);
		}
		int month = 1;
		double monthlyWeight = 0;
		double monthlyPercentage = 0;
		// loop through each element to get the weight and percentage
		for (int j = 0; j < list.size(); j++) {
			String row = "row"; //set up the name for each object
			monthlyWeight = list.get(j); //store the weight
			monthlyPercentage = monthlyWeight / sum; //store the percentage
			row = row.concat(Integer.toString(j));
			outputStr += Integer.toString(month) + ", " + Double.toString(monthlyWeight) + ", "
					+ Double.toString(monthlyPercentage) + "\n";
			Row rows = new Row(Integer.toString(month), Double.toString(monthlyWeight),
					Double.toString(monthlyPercentage));
			table.getItems().add(rows); // add the row into the table
			month++; // increment the month 
		}
		outputStr += "End of Report \n";
	}

	/**
	 * The method to display the weight and percentage of farm
	 * @param ds
	 * @param table
	 */
	public void id_weight_percentReport(application.DS ds, TableView table) {
	    //refresh the table each time when call the method
		table.refresh();
		table.getItems().clear();
		TableColumn farm = new TableColumn("Farm Name");
		farm.setCellValueFactory(new PropertyValueFactory<>("column1"));
		TableColumn weight = new TableColumn("Total Weight");
		weight.setCellValueFactory(new PropertyValueFactory<>("column2"));
		TableColumn percentage = new TableColumn("Percentage");
		percentage.setCellValueFactory(new PropertyValueFactory<>("column3"));

		outputStr += "farmID, Weight, percentage\n";

		table.getColumns().addAll(farm, weight, percentage);

		double sum = 0;
		for (int i = 0; i < ds.farmWeight.size(); i++) {
			sum = sum + ds.farmWeight.get(i);
		}
		double monthlyWeight = 0;
		double monthlyPercentage = 0;
		for (int j = 0; j < ds.farmWeight.size(); j++) {
			monthlyWeight = ds.farmWeight.get(j);
			monthlyPercentage = monthlyWeight / sum;
			outputStr += ds.farmNames.get(j) + ", " + Double.toString(monthlyWeight) + ", "
					+ Double.toString(monthlyPercentage) +"\n";
			Row rows = new Row(ds.farmNames.get(j), Double.toString(monthlyWeight), Double.toString(monthlyPercentage));
			table.getItems().add(rows);
		}
		outputStr += "End of Report\n";
	}

	/**
	 * assuming all double listed from month 1 to 12 sorted
	 *
	 * @param id
	 * @param year
	 * @param table
	 */
	public void month_min_ave_maxReport(String id, String year, TableView table) {
		List<Double> min = dManager.getMonthlyMinForFarm(id, year);
		List<Double> ave = dManager.getMonthlyAverageForFarm(id, year);
		List<Double> max = dManager.getMonthlyMaxForFarm(id, year);

		TableColumn month = new TableColumn("Month");
		month.setCellValueFactory(new PropertyValueFactory<>("column1"));
		TableColumn minCol = new TableColumn("Min");
		minCol.setCellValueFactory(new PropertyValueFactory<>("column2"));
		TableColumn aveCol = new TableColumn("Average");
		aveCol.setCellValueFactory(new PropertyValueFactory<>("column3"));
		TableColumn maxCol = new TableColumn("Max");
		maxCol.setCellValueFactory(new PropertyValueFactory<>("column4"));
		table.getColumns().addAll(month, minCol, aveCol, maxCol);

		for (int i = 0; i < min.size(); i++) {
			four_col_Row rows = new four_col_Row(Integer.toString(i + 1), Double.toString(min.get(i)),
					Double.toString(ave.get(i)), Double.toString(max.get(i)));
			table.getItems().add(rows);
		}
	}

	public void id_min_ave_maxReport(String month, String year, TableView table) {
		DS min = dManager.getMonthlyMin(month, year);
		DS ave = dManager.getMonthlyAverage(month, year);
		DS max = dManager.getMonthlyMax(month, year);

		TableColumn farmID = new TableColumn("Month");
		farmID.setCellValueFactory(new PropertyValueFactory<>("column1"));
		TableColumn minCol = new TableColumn("Min");
		minCol.setCellValueFactory(new PropertyValueFactory<>("column2"));
		TableColumn aveCol = new TableColumn("Average");
		aveCol.setCellValueFactory(new PropertyValueFactory<>("column3"));
		TableColumn maxCol = new TableColumn("Max");
		maxCol.setCellValueFactory(new PropertyValueFactory<>("column4"));
		table.getColumns().addAll(farmID, minCol, aveCol, maxCol);

		for (int i = 0; i < min.farmNames.size(); i++) {
			four_col_Row rows = new four_col_Row(min.farmNames.get(i), Double.toString(min.farmWeight.get(i)),
					Double.toString(ave.farmWeight.get(i)), Double.toString(max.farmWeight.get(i)));
			table.getItems().add(rows);
		}
	}

	public void displayTable(String param1, String param2, String option) {
		Stage pop = new Stage();
		pop.initModality(Modality.APPLICATION_MODAL);
		Button closeBtn = new Button("Close");
		closeBtn.setOnAction(e -> pop.close());

		TableView table = new TableView();

		if (option.equals("Farm Report")) {
			outputStr += "\n-Farm Report for " + param1 + " " + param2 + "\n";
			month_weight_percentReport(dManager.getFarmReport(param1, param2), table);
			pop.setTitle("Farm Report");
		} else if (option.equals("Farm Report(detailed)")) {
			month_min_ave_maxReport(param1, param2, table);
			pop.setTitle("Farm Report(detailed)");
		} else if (option.equals("Monthly Report")) {
			outputStr += "\n-Monthly Report for " + param1 + " " + param2+ "\n";
			id_weight_percentReport(dManager.getMonthlyReport(param1, param2), table);
			pop.setTitle("Monthly Report");
		} else if (option.equals("Monthly Report(detailed)")) {
			id_min_ave_maxReport(param1, param2, table);
			pop.setTitle("Monthly Report(detailed)");
		} else if (option.equals("Annual Report")) {
			outputStr += "\n-Annual Report for " + param2+ "\n";
			id_weight_percentReport(dManager.getAnnual(param2), table);
			pop.setTitle("Annual Report");
		} else if (option.equals("Date Range Report")) {
			outputStr += "\n-Date Range Report for " + param1 + " to " + param2 + "\n";
			id_weight_percentReport(dManager.getTotalInDateRange(param1, param2), table);
			pop.setTitle("Date Range Report");
		} else {
			message.appendText("Unknown report type requested when dispay");
		}

		BorderPane root = new BorderPane();
		root.setCenter(table);
		Scene popup = new Scene(root, 500, 300);
		pop.setScene(popup);
		pop.showAndWait();
	}

	public class Row {
		private final String column1;
		private final String column2;
		private final String column3;

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

	public class four_col_Row {
		private final String column1;
		private final String column2;
		private final String column3;
		private final String column4;

		public four_col_Row(String column1, String column2, String column3, String column4) {
			this.column1 = column1;
			this.column2 = column2;
			this.column3 = column3;
			this.column4 = column4;
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

		public String getColumn4() {
			return column4;
		}
	}
}
