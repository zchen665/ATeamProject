/**
 * Title:            Milk Weight Analyzer
 * Files:            File_Manager.java
 * Semester:         CS400, Spring 2020
 * Authors:          Shuyi Li
 * Lecturer's Name:  Deppler
 * Lecture Number:   001
 */
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
	//initiate a print writer for future write data to file
	PrintWriter pw = null;
	// all files in a particular location
	List<File> files = new LinkedList<>(); 
	//instantiate a cheeseFactory object
	static cheeseFactory cf = new cheeseFactory();

	/**
	 * This method is used for write the calculated data to csv files
	 * @param fileName the file name will be given to the output file
	 * @param strToWrite the content to put in the output file
	 * @return true if no error occur, else return false
	 */
	boolean writeToFile(String fileName, String strToWrite) {
		try {
			//create files to store output data
			pw = new PrintWriter(new File(fileName + ".txt"));
			//write in the content
			pw.println(strToWrite);
			pw.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}

	/**
	 * This method is used for getting the csv files that users select
	 * @param primaryStage
	 * @param fileList
	 * @param listView
	 * @param list
	 * @return
	 */
	public static boolean enlistAllFiles(Stage primaryStage, List<File> fileList, ListView<String> listView,
			ObservableList<String> list) {
		FileChooser fileChooser = new FileChooser();
		// open directory from existing directory
		if (fileList != null) {
			if (!fileList.isEmpty()) {
				File existDirectory = fileList.get(0).getParentFile();
				fileChooser.setInitialDirectory(existDirectory);
			}
		}

		// set file filter
		FileChooser.ExtensionFilter extFilter = 
				new FileChooser.ExtensionFilter("AVI files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);

		// show open file dialog
		fileList = fileChooser.showOpenMultipleDialog(primaryStage);

		list.clear();
		try {
			for (int i = 0; i < fileList.size(); i++) {
				list.add(fileList.get(i).getPath());
			}
		} catch (NullPointerException e) {
			return false;
		}

		//take the content from input file and put in arrays
		listView.setItems(list);
		ArrayList<ArrayList<String>> allData = CSV2Array(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			allData.addAll(CSV2Array(list.get(i)));
		}

		//add date data to data arraylist except for the first row
		ArrayList<String> dates = new ArrayList<String>();
		for (int i = 0; i < allData.size(); i++) {
			if (!allData.get(i).get(0).contains("date"))
				dates.add(allData.get(i).get(0));
		}

		//add ID data to ID arraylist except for the first row
		ArrayList<String> ids = new ArrayList<String>();
		for (int i = 0; i < allData.size(); i++) {
			if (!allData.get(i).get(1).contains("farm_id"))
				ids.add(allData.get(i).get(1));
		}

		//add weight data to weight arraylist except for the first row
		ArrayList<String> weights = new ArrayList<String>();
		for (int i = 0; i < allData.size(); i++) {
			if (!allData.get(i).get(2).contains("weight"))
				weights.add(allData.get(i).get(2));
		}

		// insert data into data structure
		for (int i = 0; i < dates.size(); i++) {
			cf.insertData(dates.get(i), ids.get(i), weights.get(i));
		}
		if (allData != null && allData.size() > 0) {
			return true;
		}

		return false;
	}

	/**
	 * This method is used for converting data in csv files into arraylist
	 * @param path
	 * @return
	 */
	public static ArrayList<ArrayList<String>> CSV2Array(String path) {
		try {
			//create buffer reader to read the local file with certain path
			BufferedReader br = new BufferedReader(new InputStreamReader
					(new FileInputStream(path), "UTF-8"));
			//create arraylist to store data
			ArrayList<ArrayList<String>> alldata = 
					new ArrayList<ArrayList<String>>();
			String line;
			String[] oneRow;
			while ((line = br.readLine()) != null) {
				//separate different parts by comma and store
				oneRow = line.split(",");
				List<String> onerowlist = Arrays.asList(oneRow);
				ArrayList<String> onerowArrayList = 
						new ArrayList<String>(onerowlist);
				alldata.add(onerowArrayList);
			}
			br.close();

			return alldata;
		} catch (Exception e) {
			return null;
		}
	}

}
