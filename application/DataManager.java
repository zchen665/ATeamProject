package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Defines operation on data manipulations and forming the required data for the visualizer
 */
public class DataManager {

  List<Double> list = Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0);
  DS ds = new DS();

  public DataManager(cheeseFactory cf) {
    String[][] allData = cf.getAllData();
    for (int i = 1; i < allData.length; i++) {
      ds.addFarmReportForTheDay(allData[i][1], allData[i][0], allData[i][2]);
    }
    for (int i = 1; i < allData.length; i++) {
      ds.addDailyReportForTheFarm(allData[i][0], allData[i][1], allData[i][2]);
    }
  }


  //get monthlyaverage returns a list of information on daily average
  //by month of each farm in that month
  public DS getMonthlyAverage(String month, String year) {
    return ds;
  }

  //get monthlymin returns a list of information on min daily milk weight
  //in that month for each farm
  public DS getMonthlyMin(String month, String year) {
    return ds;
  }

  public DS getMonthlyMax(String month, String year) {
    return ds;
  }

  public DS getMonthlyReport(String moth, String year) {
    return ds;
  }

  public List<Double> getFarmReport(String farmName, String year) {
    return list;
  }

  public List<Double> getMonthlyAverageForFarm(String farmName, String year) {
    return list;
  }

  //get monthlymin returns a list of information on min daily milk in each month for
  //that farm requested.
  public List<Double> getMonthlyMinForFarm(String farmName, String year) {
    return list;
  }

  public List<Double> getMonthlyMaxForFarm(String farmName, String year) {
    return list;
  }

  public List<Double> getDataSortedByField() {
    return null;
  }

  public List<Double> getAnnual(String year) {
    return list;
  }

  //changed from average to total based on the specification
  // sort by total milk weight over the data range
  public DS getTotalInDateRange(String startDate, String endDate) {
    return ds;
  }
}
