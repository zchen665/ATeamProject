package application; /**
 * A data structure that can store the farm and milk information as needed
 */

/**
 * @author Jiahe Wang
 * 
 * @param <K> The key must not be null and must be Comparable.
 * @param <V> The data value associated with a given key.
 *
 */
public interface dataStruct<K extends Comparable<K>, V> {
	/**
	 * this method helps insert a new set of data into record.
	 * 
	 * @param date  in form of "yyyy-mm-dd" or will be recorded as missing or error
	 * @param id	in form of "Farm ##"
	 * @param weight in form of "####"
	 * @return	true if successfully added the data.
	 */
	boolean insertData(K date, K id, K weight);
	
	
	/**
	 * this method helps edit an existing set of data in the record.
	 * 
	 * @param dataset: must be in form of [old date, old farmID, old weight, new date,
	 * 										new farmID, new date]
	 * @return	true if successfully edited the data. 
	 * 			false if the format is wrong.
	 */
	boolean editSingleData(K[] dataset);
	
	
	/**
	 * remove all data on a specific date
	 * 
	 * @param date in form of "yyyy-mm-dd"
	 * @return true if successfully removed the data. 
	 */
	boolean clearDate(K date);
	
	
	/**
	 * remove all data of a specific farm
	 * 
	 * @param farm in form of "Farm #"
	 * @return true if successfully removed the data. 
	 */
	boolean clearFarm(K farm);
	
	
	/**
	 * remove a specific data
	 * 
	 * @param date in form of "yyyy-mm-dd"
	 * @param farm in form of "Farm #"
	 * @param weight in form of "#" (number)
	 * @return true if successfully removed the data. 
	 */
	boolean removeSingleData(K date, K farm, K weight);
	
	/**
	 * calculates the sum of milk of a specific farm
	 * 
	 * @param farmID in form of "farm #"
	 * @return -1 if farmid is not correct
	 * 			0 if no data is found on the specific date;
	 * 			sum  the correct sum if works correctly
	 */
	double getSumFarm(K farmID);
	
	/**
	 * calculates the sum of milk of a specific date
	 * 
	 * @param date   in form of "yyyy-mm-dd"
	 * @return -1 if date is not entered correctly;
	 * 			0 if no data is found on the specific date;
	 * 			sum  the correct sum if works correctly
	 */
	double getSumDate(K date);
	
	/**
	 * calculates the amount of milk of a specific month
	 * 
	 * 
	 * @param month in form of "#" a number between 1-12
	 * @return -1 if date is not entered correctly or not in correct range
	 * 			-2 if month is not in the correct format
	 * 			0 if no data is found on the specific month;
	 * 			sum  the correct sum if works correctly
	 */
	double getSumMonth(K month);
	
	
	/**
	 * calculates the amount of milk of a specific year
	 * 
	 * 
	 * @param year in form of "#" a number representing a year
	 * @return -1 if date is not entered correctly
	 * 			0 if no data is found on the specific year;
	 * 			sum  the correct sum if works correctly
	 */
	double getSumYear(K year);
	
	
	/**
	 * checks if existing error data. 
	 * @return true if error detected. false if all data seems good.
	 */
	boolean checkForError();
	
	
	/**
	 * checks if existing missing data. 
	 * @return true if missing detected. false if all data seems good.
	 */
	boolean checkForMissing();
	
	/**
	 * helps get all data out at once
	 * 
	 * @return 2-D array containing all data in form of
	 * 	{ 
	 * 	  {"yyyy-mm-dd"},{"farm id"},{"weight"}
	 * 	  {"yyyy-mm-dd"},{"farm id"},{"weight"}
	 *    {"yyyy-mm-dd"},{"farm id"},{"weight"}
	 *    ...
	 *  }
	 */
	String[][] getAllData();
}
