package application;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

/**
 * @param <K> The key must not be null and must be Comparable.
 * @param <V> The data value associated with a given key.
 * @author Jiahe Wang
 */
public class cheeseFactory<K extends Comparable<K>, V> implements dataStruct<K, V> {
	// private global list that will record all the data of each farm
	private final ArrayList<farm> factory = new ArrayList<>();

	@Override
	public boolean insertData(K date, K id, K weight) {
		farm f1 = new farm(date, id, weight);
		int a = factory.size() + 1;
		factory.add(f1);
		// check if added successfully
		return factory.size() == a;
	}

	public int size() {
		return factory.size();
	}

	@Override
	public boolean clearDate(K date) {
		boolean flag = false;
		// condition checking
		if (date == null)
			return false;
		// looping through the list and condition checking for if successfully removed
		for (int i = factory.size() - 1; i >= 0; i--) {
			if (factory.get(i).getDate().compareTo(date) == 0) {
				flag = true;
				factory.remove(i);
			}
		}
		// true if removed. false if no data detected.
		return flag;
	}

	@Override
	public boolean clearFarm(K farmid) {
		boolean flag = false;
		// condition checking
		if (farmid == null)
			return false;
		// looping through the list and condition checking for if successfully removed
		for (int i = factory.size() - 1; i >= 0; i--) {
			if (factory.get(i).getID().compareTo(farmid) == 0) {
				factory.remove(i);
				flag = true;
			}
		}
		// true if removed. false if no corresponding data found.
		return flag;
	}

	@Override
	public boolean removeSingleData(K date, K farmid, K weight) {
		boolean flag = false;
		// condition checking
		if (date == null || farmid == null || weight == null)
			return false;
		// looping through the list and condition checking for if successfully removed
		for (int i = factory.size() - 1; i >= 0; i--) {
			if (factory.get(i).getDate().compareTo(date) == 0)
				if (factory.get(i).getID().compareTo(farmid) == 0)
					if (factory.get(i).getWeight().compareTo(weight) == 0) {
						factory.remove(i);
						flag = true;
					}
		}
		// true if removed. false if no corresponding data found
		return flag;
	}

	@Override
	public boolean editSingleData(K[] dataset) {
		boolean flag = false;
		// condition checking
		if (dataset == null || dataset.length != 6)
			return false;

		// looping through the list and condition checking for if successfully removed
		for (int i = factory.size() - 1; i >= 0; i--) {
			if (factory.get(i).getDate().compareTo(dataset[0]) == 0)
				if (factory.get(i).getID().compareTo(dataset[1]) == 0)
					if (factory.get(i).getWeight().compareTo(dataset[2]) == 0) {
						factory.get(i).setDate(dataset[3]);
						factory.get(i).setID(dataset[4]);
						factory.get(i).setWeight(dataset[5]);
						flag = true;
					}
		}
		return false;
	}

	@Override
	public boolean checkForError() {
		// looping through the list to check if any error exists.
		for (int i = 0; i < factory.size(); i++)
			if (factory.get(i).getDate().compareTo((K) "error") == 0
					|| factory.get(i).getID().compareTo((K) "error") == 0
					|| factory.get(i).getWeight().compareTo((K) "error") == 0) {
				return true;
			}
		return false;
	}

	@Override
	public boolean checkForMissing() {
		// looping through the list to check if any missing exists.
		for (int i = 0; i < factory.size(); i++)
			if (factory.get(i).getDate().compareTo((K) "missing") == 0
					|| factory.get(i).getID().compareTo((K) "missing") == 0
					|| factory.get(i).getWeight().compareTo((K) "missing") == 0) {
				return true;
			}
		return false;
	}

	@Override
	public double getSumFarm(K farmID) {
		// condition checking
		if (farmID == null)
			return -1;
		// recording data from selected farm
		double sum = 0;
		// calculating the weight by looping through the list
		for (int i = 0; i < factory.size(); i++) {
			if (factory.get(i).getID().compareTo(farmID) == 0) {
				sum += factory.get(i).weight;
			}
		}
		return sum;
	}

	@Override
	public double getSumDate(K date) {
		// condition checking
		if (date == null)
			return -1;
		// recording data from selected date
		double sum = 0;
		// calculating the weight by looping through the list
		for (int i = 0; i < factory.size(); i++) {
			if (factory.get(i).getDate().compareTo(date) == 0) {
				sum += factory.get(i).weight;
			}
		}
		return sum;
	}

	@Override
	public double getSumMonth(K month) {
		// condition checking
		if (month == null)
			return -1;
		// recording data from selected farm
		double sum = 0;
		int mon = 0;
		try {
			mon = Integer.parseInt(month.toString());
		} catch (NumberFormatException e) {
			return -2;
		}
		if (mon < 1 || mon > 12)
			return -1;
		// calculating the weight by looping through the list
		for (int i = 0; i < factory.size(); i++) {
			if (factory.get(i).month == mon) {
				sum += factory.get(i).weight;
			}
		}
		return sum;
	}

	@Override
	public double getSumYear(K year) {
		// condition checking
		if (year == null)
			return -1;
		// recording data from selected farm
		double sum = 0;
		int yr = 0;
		try { // make sure the data is transfered
			yr = Integer.parseInt(year.toString());
		} catch (NumberFormatException e) {
			return -2;
		}
		// calculating the weight by looping through the list
		for (int i = 0; i < factory.size(); i++) {
			if (factory.get(i).year == yr) {
				sum += factory.get(i).weight;
			}
		}
		return sum;
	}

	@Override
	public String[][] getAllData() {
		// array that will store all data
		String[][] allData = new String[factory.size()][3];
		for (int i = 0; i < factory.size(); i++) { // looping the entire list
			allData[i][0] = factory.get(i).getDate().toString(); // store date
			allData[i][1] = factory.get(i).getID().toString(); // store farmID
			allData[i][2] = factory.get(i).getWeight().toString(); // store weight
		}
		return allData;
	}

	/**
	 * inner class that records each piece of information
	 */
	private class farm {
		String farmID; // records farmid
		String date; // records date
		String weightStr; // records weight in string
		int year; // the year from date
		int month; // month from date
		int day; // day from date
		int ID; // the number after "farm"
		double weight; // stores the weight in double

		// the default constructor. Should never be called.
		public farm() {
			farmID = "missing";
			date = "missing";
			weightStr = "missing";
		}

		// constructor that records all information and make sure in correct format
		public farm(K D, K iD, K W) {
			// covnertring from K to String
			String d = D.toString();
			String id = iD.toString();
			String w = W.toString();
			// formating
			d = d.trim();
			id = id.trim();
			w = w.trim();
			// recording to local
			this.date = d;
			this.farmID = id;
			this.weightStr = w;
			boolean flag = true;
			String[] temp = new String[3];

			// checking for missing information
			if (d.trim().isEmpty() || d.trim().equalsIgnoreCase("-")) {
				this.date = "missing";
			} else {
				// Converting the date into 3 int variables and prevent exceptions occur
				try { // prevent if the data is not in the form of "yyyy-mm-dd"
					temp = date.split("-");
				} catch (PatternSyntaxException e) {
					date = "error";
					flag = false;
				}
				if (flag) {
					try { // prevent the data is not in the form that could be converted into int
						year = Integer.parseInt(temp[0]);
						month = Integer.parseInt(temp[1]);
						day = Integer.parseInt(temp[2]);
						if (temp[1].length() < 2) {
							temp[1] = "0" + temp[1];
							this.date = temp[0] + "-" + temp[1] + "-" + temp[2];
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						// make sure if month or date is missing still works
					}
				}
			}

			if (id.trim().isEmpty() || id.trim().equalsIgnoreCase("-")) {
				this.farmID = "missing";
			} else {
				// recording the id in FarmID
				temp = new String[2];
				// accepting all forms of "farm" that's considered in
				if (!this.farmID.contains("farm") && !this.farmID.contains("Farm") && !this.farmID.contains("FARM")) {
					this.farmID = "error";
					flag = false;
				}
				try { // prevent if the data is not in the form of "Farm #"
					temp = id.split(" ");
				} catch (PatternSyntaxException e) {
					flag = false;
					id = "error";
				}

				if (flag) { // all condition satisfies and the input is acceptable
					try { // prevent the data is not in the form that could be converted into int
						ID = Integer.parseInt(temp[1]);
					} catch (NumberFormatException e) {
						id = "error";
						ID = -1;
					} catch (ArrayIndexOutOfBoundsException e) {
						flag = false;
						id = this.farmID;
					}
				}
			}
			// checking if data is missing or not completed
			if (w.trim().isEmpty() || w.trim().equalsIgnoreCase("-")) {
				this.weightStr = "missing";
			} else {
				// recording the weight
				try { // prevent the data is not in the form that could be converted into double
					weight = Double.parseDouble(w);
				} catch (NumberFormatException e) {
					weightStr = "error";
					weight = -1;
				}
			}
		}

		// clears all the data in such farm
		private boolean clear() {
			farmID = "";
			date = "";
			weightStr = "";
			year = 0;
			month = 0;
			day = 0;
			ID = 0;
			weight = 0.0;
			return true;
		}

		// below are the setter and getter methods for convenience
		private K getDate() {
			return (K) date;
		}

		private void setDate(K dateN) {
			this.date = dateN.toString();
		}

		private K getID() {
			return (K) farmID;
		}

		private void setID(K idN) {
			this.farmID = idN.toString();
		}

		private K getWeight() {
			return (K) weightStr;
		}

		private void setWeight(K weightN) {
			this.weightStr = weightN.toString();
		}
	}

}
