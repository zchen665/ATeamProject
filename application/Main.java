/**
 * Title:            Milk Weight Analyzer
 * Files:            Main.java
 * Semester:         CS400, Spring 2020

 * Authors:           Zhengzhi chen jiong chen
 * Lecturer's Name:  Deppler
 * Lecture Number:   001
 */
package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * class implementing the gui interface
 * 
 * @author
 *
 */
public class Main extends Application {
	File file;
	ListView<String> listView;
	ObservableList<String> list;
	List<File> fileList;

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

	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 550;
	private static final String APP_TITLE = "Milk Weight Analyzer";

	@Override
	public void start(Stage primaryStage) throws Exception {
		GUI2 root = new GUI2();
		Font textFont = new Font("Ariel", 18);

		
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
//public void start(Stage primaryStage) throws Exception {
//	GUI2 root = new GUI2();
//	Font textFont = new Font("Ariel", 18);
//
//	
////	final Label labelFile = new Label();
////	loadFile.setOnAction(e ->{File_Manager.enlistAllFiles(primaryStage,fileList,file,listView,list);});
//	
//
//	// Create Scene
//	Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
//
//	// Add the stuff and set the primary stage
//	primaryStage.setTitle(APP_TITLE);
//	primaryStage.setScene(mainScene);
//	primaryStage.show();
//}