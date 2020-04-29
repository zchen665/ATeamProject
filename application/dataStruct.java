package application; /**
 * A data structure that can store the farm and milk information as needed
 */


/**
 * @param <K> The key must not be null and must be Comparable.
 * @param <V> The data value associated with a given key.
 * @author Jiahe Wang
 */
public interface dataStruct<K extends Comparable<K>, V> {
  /**
   * this method helps insert a new set of data into record.
   *
   * @param date   in form of "yyyy-mm-dd" or will be recorded as missing or error
   * @param id     in form of "Farm ##"
   * @param weight in form of "####"
   * @return true if successfully added the data.
   */
  boolean insertData(K date, K id, K weight);


  /**
   * this method helps edit an existing set of data in the record.
   *
   * @param dataset: must be in form of [old date, old farmID, old weight, new date,
   *                 new farmID, new date]
   * @return true if successfully edited the data.
   * false if the format is wrong.
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
   * @param date   in form of "yyyy-mm-dd"
   * @param farm   in form of "Farm #"
   * @param weight in form of "#" (number)
   * @return true if successfully removed the data.
   */
  boolean removeSingleData(K date, K farm, K weight);

  /**
   * checks if existing error data.
   *
   * @return true if error detected. false if all data seems good.
   */
  boolean checkForError();

  /**
   * checks if existing missing data.
   *
   * @return true if missing detected. false if all data seems good.
   */
  boolean checkForMissing();
}
