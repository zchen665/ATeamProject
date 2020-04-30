package application;

import java.io.*;
import java.util.*;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class File_Manager {
	PrintWriter pw = null;
	List<File> files = new LinkedList<>(); // All files in a particular folder
	static cheeseFactory cf = new cheeseFactory();
	boolean writeToFile(String outputFile) {
		System.out.println("Call writeTofile in fManager");
		try {
			pw = new PrintWriter(new File("results.csv"));
			pw.println(outputFile);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Private helper method that lists all files inside a given folder
	 * 
	 * @param folder
	 * @return
	 */
	public static List<File> enlistAllFiles(Stage primaryStage, List<File> fileList, Label file,
			ListView<String> listView, ObservableList<String> list) {
		FileChooser fileChooser = new FileChooser();
		// Open directory from existing directory
		if (fileList != null) {
			if (!fileList.isEmpty()) {
				File existDirectory = fileList.get(0).getParentFile();
				fileChooser.setInitialDirectory(existDirectory);
			}
		}

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("AVI files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show open file dialog, with primaryStage blocked.
		fileList = fileChooser.showOpenMultipleDialog(primaryStage);

		list.clear();
		for (int i = 0; i < fileList.size(); i++) {
			list.add(fileList.get(i).getPath());
		}
		// System.out.println(list);
		listView.setItems(list);
		ArrayList<ArrayList<String>> allData = CSV2Array(list.get(0));

		ArrayList<String> dates = new ArrayList<String>();
		for (int i = 0; i < allData.size(); i++) {
			dates.add(allData.get(i).get(0));
		}
		// System.out.println(dates);

		ArrayList<String> ids = new ArrayList<String>();
		for (int i = 0; i < allData.size(); i++) {
			ids.add(allData.get(i).get(1));
		}
		// System.out.println(ids);

		ArrayList<String> weights = new ArrayList<String>();
		for (int i = 0; i < allData.size(); i++) {
			weights.add(allData.get(i).get(2));
		}
		// System.out.println(weights);

		// insert into DS
//		cheeseFactory cf = new cheeseFactory();
		for (int i = 0; i < allData.size(); i++) {
				cf.insertData(dates.get(i), ids.get(i), weights.get(i));
		}
		// System.out.println(cf.getSumMonth("5"));
		return fileList;

	}

	public static ArrayList<ArrayList<String>> CSV2Array(String path) {
		try {
			// BufferedReader in =Files.newBufferedReader(Paths.get(list));
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
			ArrayList<ArrayList<String>> alldata = new ArrayList<ArrayList<String>>();
			String line;
			String[] oneRow;
			while ((line = br.readLine()) != null) {
				oneRow = line.split(",");
				List<String> onerowlist = Arrays.asList(oneRow);
				ArrayList<String> onerowArrayList = new ArrayList<String>(onerowlist);
				alldata.add(onerowArrayList);
			}
			br.close();

			return alldata;
		} catch (Exception e) {
			return null;
		}
	}

}

//Don't know if needed
//String getFileContents(String fileName);
