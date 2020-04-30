package application;

import java.io.File;
import java.text.ParseException;
import java.util.*;

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
    Collections.sort(ds.farmNames);
  }


  //get monthlyaverage returns a list of information on daily average
  //by month of each farm in that month

  /**
   * For a specific month of a year, return a list of averages (over the month) by farm.
   * The resulting list is, by default, sorted according to the farm names. Notice that
   * the list of farm names is always pre-sorted.
   *
   * @param month
   * @param year
   * @return
   */
  public DS getMonthlyAverage(String month, String year) {
    int days = getDaysForTheMonth(month, year);
    for (String farmName : ds.farmNames) {
      List<DS.ReportForTheDay> DailyReports = ds.farmReportDaily.get(farmName);
      double total = 0.0;
      for (DS.ReportForTheDay reportForTheDay : DailyReports) {
        String date = reportForTheDay.date;
        if (date.contains(month) && date.contains(year)) {
          total += reportForTheDay.weight;
        }
      }
      double avg = total / days;
      ds.farmWeight.add(avg);
    }
    return ds;
  }

  private int getDaysForTheMonth(String month, String year) {

    int monthInt = Integer.parseInt(month);
    int yearInt = Integer.parseInt(year);

    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.YEAR, yearInt);
    calendar.set(Calendar.MONTH, monthInt - 1);
    calendar.set(Calendar.DATE, 1);
    calendar.roll(Calendar.DATE, -1);

    int maxDays = calendar.get(Calendar.DATE);
    return maxDays;
  }

  /**
   * @param month
   * @param year
   * @return
   */

  //get monthlymin returns a list of information on min daily milk weight
  //in that month for each farm
  public DS getMonthlyMin(String month, String year) {
    ds.farmWeight.clear();
    for (String farmName : ds.farmNames) {
      List<DS.ReportForTheDay> DailyReports = ds.farmReportDaily.get(farmName);
      double min = 0.0;
      for (DS.ReportForTheDay reportForTheDay : DailyReports) {
        String date = reportForTheDay.date;
        if (date.contains(month) && date.contains(year)) {
          if (min >= reportForTheDay.weight) {
            min = reportForTheDay.weight;
          }
        }
      }
      ds.farmWeight.add(min);
    }
    return ds;
  }

  public DS getMonthlyMax(String month, String year) {
    ds.farmWeight.clear();
    for (String farmName : ds.farmNames) {
      List<DS.ReportForTheDay> DailyReports = ds.farmReportDaily.get(farmName);
      double min = 0.0;
      for (DS.ReportForTheDay reportForTheDay : DailyReports) {
        String date = reportForTheDay.date;
        if (date.contains(month) && date.contains(year)) {
          if (min <= reportForTheDay.weight) {
            min = reportForTheDay.weight;
          }
        }
      }
      ds.farmWeight.add(min);
    }
    return ds;
  }

  public DS getMonthlyReport(String month, String year) {
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
