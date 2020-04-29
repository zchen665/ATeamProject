
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

/**
 * @author Jiahe Wang
 * 
 * @param <K> The key must not be null and must be Comparable.
 * @param <V> The data value associated with a given key.
 *
 */
public class cheeseFactory <K extends Comparable<K>, V> implements dataStruct<K, V>{
	/**
	 *  inner class that records each piece of information
	 */
	private class farm{
		String farmID;
		String date;
		String weightStr;
		int year;
		int month;
		int day;
		int ID;
		double weight;
		
		//the default constructor. Should never be called.
		public farm() {
			farmID = "Dummy";
			date = "Dummy";
			weightStr = "Dummy";
		}
		
		//constructor that records all information and make sure in correct format
		public farm(K D, K iD, K W) {
			//covnertring from K to String
			String d = D.toString();
			String id = iD.toString();
			String w = W.toString();
			//formating
			d = d.trim();
			id = id.trim();
			w = w.trim();
			//recording to local
			this.date = d;
			this.farmID = id;
			this.weightStr = w;
			boolean flag = true;
			String[] temp = new String[3];
			
			
			//checking for missing information
			if(d.trim().isEmpty()||d.trim().equalsIgnoreCase("-")) {
				this.date = "missing";
			}else {
				//Converting the date into 3 int variables and prevent exceptions occur
				try {	//prevent if the data is not in the form of "yyyy-mm-dd"
					temp = date.split("-");
				}
				catch(PatternSyntaxException e) {
					date = "error";
					flag = false;
				}
				if(flag) {
					try { //prevent the data is not in the form that could be converted into int
						year = Integer.parseInt(temp[0]);
						month = Integer.parseInt(temp[1]);
						day = Integer.parseInt(temp[2]);
					}
					catch(NumberFormatException e) {
						date = "error";
						year = -1;
						month = -1;
						day = -1;
					}
				}
			}
			
			if(id.trim().isEmpty()||id.trim().equalsIgnoreCase("-")) {
				this.farmID = "missing";
			}else {
				//recording the id in FarmID
				temp = new String[2];
				try {	//prevent if the data is not in the form of "Farm #"
					temp = id.split(" ");
				}
				catch(PatternSyntaxException e) {
					flag = false;
					id = "error";
				}
				if(flag) {
					try { //prevent the data is not in the form that could be converted into int
						ID = Integer.parseInt(temp[1]);
					}
					catch(NumberFormatException e) {
						id = "error";
						ID = -1;
					}
				}
			}
			
			if(w.trim().isEmpty()||w.trim().equalsIgnoreCase("-")) {
				this.weightStr = "missing";
			}else {
				//recording the weight
				try { //prevent the data is not in the form that could be converted into double
					weight = Double.parseDouble(w);
				}
				catch(NumberFormatException e) {
					weightStr = "error";
					weight = -1;
				}
			}			
		}
		//clears all the data in such farm
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
		//below are the setter and getter methods
		private void setDate(K dateN) {
			this.date = dateN.toString();
		}
		private void setID(K idN) {
			this.farmID = idN.toString();
		}
		private void setWeight(K weightN) {
			this.weightStr = weightN.toString();
		}
		
		private K getDate() {
			return (K)date;
		}
		
		private K getID() {
			return (K)farmID;
		}
		
		private K getWeight() {
			return (K)weightStr;
		}
	}
	
	//private global list that will record all the data of each farm
	private ArrayList<farm> factory = new ArrayList<>();
	
	@Override
	public boolean insertData(K date, K id, K weight) {
		farm f1 = new farm(date, id, weight);
		int a = factory.size() + 1;
		factory.add(f1);
		//check if added successfully
		if(factory.size() == a)
			return true;
		return false;
	}


	@Override
	public boolean clearDate(K date) {
		boolean flag = false;
		//condition checking
		if(date == null)
			return false;
		//looping through the list and condition checking for if successfully removed
		for(int i = factory.size() - 1; i >= 0; i--) {
			if(factory.get(i).getDate().compareTo(date) == 0) {
				flag = true;
				factory.remove(i);
			}
		}
		//true if removed. false if no data detected.
		if(flag)
			return true;
		else
			return false;
	}


	@Override
	public boolean clearFarm(K farmid) {
		boolean flag = false;
		//condition checking
		if(farmid == null)
			return false;
		//looping through the list and condition checking for if successfully removed
		for(int i = factory.size() - 1; i >= 0; i--) {
			if(factory.get(i).getID().compareTo(farmid) == 0) {
				factory.remove(i);
				flag = true;
			}
		}
		//true if removed. false if no corresponding data found.
		if(flag)
			return true;
		else
			return false;
	}


	@Override
	public boolean removeSingleData(K date, K farmid, K weight) {
		boolean flag = false;
		//condition checking
		if(date == null || farmid == null || weight == null)
			return false;
		//looping through the list and condition checking for if successfully removed
		for(int i = factory.size() - 1; i >= 0; i--) {
			if(factory.get(i).getDate().compareTo(date) == 0)
				if(factory.get(i).getID().compareTo(farmid) == 0)
					if(factory.get(i).getWeight().compareTo(weight) == 0) {
						factory.remove(i);
						flag = true;
					}
		}
		//true if removed. false if no corresponding data found
		if(flag)
			return true;
		else
			return false;
	}


	@Override
	public boolean editSingleData(K[] dataset) {
		boolean flag = false;
		//condition checking
		if(dataset == null || dataset.length != 6)
			return false;
		
		//looping through the list and condition checking for if successfully removed
		for(int i = factory.size() - 1; i >= 0; i--) {
			if(factory.get(i).getDate().compareTo(dataset[0]) == 0)
				if(factory.get(i).getID().compareTo(dataset[1]) == 0)
					if(factory.get(i).getWeight().compareTo(dataset[2]) == 0) {
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
		//looping through the list to check if any error exists.
		for(int i = 0; i < factory.size();i++)
			if(factory.get(i).getDate().compareTo((K) "error") == 0 ||
				factory.get(i).getID().compareTo((K) "error") == 0 ||
				factory.get(i).getWeight().compareTo((K) "error") == 0) {
					return true;
				}
		return false;
	}


	@Override
	public boolean checkForMissing() {
		//looping through the list to check if any missing exists.
		for(int i = 0; i < factory.size();i++)
			if(factory.get(i).getDate().compareTo((K) "missing") == 0 ||
				factory.get(i).getID().compareTo((K) "missing") == 0 ||
				factory.get(i).getWeight().compareTo((K) "missing") == 0) {
					return true;
				}
		return false;
	}

	




	
}
