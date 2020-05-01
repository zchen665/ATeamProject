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

	boolean writeToFile(String fileName, String strToWrite) {
		try {
			pw = new PrintWriter(new File(fileName + ".txt"));
			pw.println(strToWrite);
			pw.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}

	/**
	 * Private helper method that lists all files inside a given folder
	 * 
	 * @param folder
	 * @return
	 */
	public static boolean enlistAllFiles(Stage primaryStage, List<File> fileList, ListView<String> listView,
			ObservableList<String> list) {
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
		try {
			for (int i = 0; i < fileList.size(); i++) {
				list.add(fileList.get(i).getPath());
			}
		} catch (NullPointerException e) {
			return false;
		}
		
		listView.setItems(list);
		ArrayList<ArrayList<String>> allData = CSV2Array(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			allData.addAll(CSV2Array(list.get(i)));
		}

		ArrayList<String> dates = new ArrayList<String>();
		for (int i = 0; i < allData.size(); i++) {
			if (!allData.get(i).get(0).contains("date"))
				dates.add(allData.get(i).get(0));
		}

		ArrayList<String> ids = new ArrayList<String>();
		for (int i = 0; i < allData.size(); i++) {
			if (!allData.get(i).get(1).contains("farm_id"))
				ids.add(allData.get(i).get(1));
		}

		ArrayList<String> weights = new ArrayList<String>();
		for (int i = 0; i < allData.size(); i++) {
			if (!allData.get(i).get(2).contains("weight"))
				weights.add(allData.get(i).get(2));
		}

		// insert into DS
		for (int i = 0; i < dates.size(); i++) {
			cf.insertData(dates.get(i), ids.get(i), weights.get(i));
		}
		if (allData != null && allData.size() > 0) {
			return true;
		}

		return false;
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
