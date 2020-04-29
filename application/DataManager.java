package application;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines operation on data manipulations and forming the required data for the visualizer
 */
public class DataManager {

  List<Double> report = new ArrayList<>();


  public List<Double> getMonthlyAverage(String month, String year) {


    return report;
  }

  //get monthly min returns a list of information on min daily milk weight
  //in that month for each farm

  /**
   * @param month
   * @param year
   * @return
   */
  public List<Double> getMonthlyMin(String month, String year) {

    return report;
  }

  /**
   * @param month
   * @param year
   * @return
   */
  public List<Double> getMonthlyMax(String month, String year) {
    return report;
  }

  /**
   * @param farmName
   * @param year
   * @return
   */
  public List<Double> getMonthlyAverageForFarm(String farmName, String year) {
    return report;
  }

  /**
   * @param farmName
   * @param year
   * @return
   */
  //get monthly min returns a list of information on min daily milk in each month for
  //that farm requested.
  public List<Double> getMonthlyMinForFarm(String farmName, String year) {

    return report;
  }

  /**
   * @param farmName
   * @param year
   * @return
   */
  public List<Double> getMonthlyMaxForFarm(String farmName, String year) {
    return report;
  }

  /**
   * @return
   */
  public List<Double> getDataSortedByField() {
    return null;
  }

  /**
   * @param year
   * @return
   */
  public List<Double> getAnnual(String year) {
    return report;
  }

  /**
   * @param startDate
   * @param endDate
   * @return
   */
  //changed from average to total based on the specification
  // sort by total milk weight over the data range
  public List<Double> getTotalInDateRange(String startDate, String endDate) {
    return report;
  }

  /**
   * @param startDate
   * @param endDate
   * @return
   */
  public List<Double> getMinInDateRange(String startDate, String endDate) {
    return report;
  }

  /**
   * @param startDate
   * @param endDate
   * @return
   */
  public List<Double> getMaxInDateRange(String startDate, String endDate) {
    return report;
  }

  public void sortBy(String rule) {

  }

}
