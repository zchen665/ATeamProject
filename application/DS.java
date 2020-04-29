package application;

import java.text.ParseException;
import java.util.*;

public class DS {
  public List<String> farmNames;//leave empty if no such data
  public List<Double> farmWeight;
  public List<String> month; //leave empty if no such data
  public Map<String, List<ReportForTheDay>> farmReportDaily;
  public Map<String, List<ReportForTheFarm>> dailyReportForTheFarms;

  public DS() {
    farmNames = new LinkedList<>();
    farmWeight = new LinkedList<>();
    farmReportDaily = new HashMap<>(); //Farm-Based Report for each day
    dailyReportForTheFarms = new HashMap<>(); //Daily Report for each farm
  }

  /**
   * Adds a daily report for a specific farm
   *
   * @param farmName
   * @param date
   * @param weight
   */
  public void addFarmReportForTheDay(String farmName, String date, String weight) {
    if (!farmReportDaily.containsKey(farmName)) {
      if (!farmNames.contains(farmName)) {
        farmNames.add(farmName);
      }
      List<ReportForTheDay> reports = new LinkedList<>();
      farmReportDaily.put(farmName, reports);
      try {
        Double weightVal = Double.parseDouble(weight);
        reports.add(new ReportForTheDay(date, weightVal));
      } catch (Exception ignored) {
        /* Terminates the method*/
      }
    } else {
      List<ReportForTheDay> reports = farmReportDaily.get(farmName);
      try {
        Double weightVal = Double.parseDouble(weight);
        reports.add(new ReportForTheDay(date, weightVal));
      } catch (Exception ignored) {
        /* Terminates the method*/
      }
    }
  }

  public void addDailyReportForTheFarm(String date, String farmName, String weight) {
    if (!dailyReportForTheFarms.containsKey(date)) {
      if (!farmNames.contains(farmName)) {
        farmNames.add(farmName);
      }
      List<ReportForTheFarm> reports = new LinkedList<>();
      dailyReportForTheFarms.put(date, reports);
      try {
        Double weightVal = Double.parseDouble(weight);
        reports.add(new ReportForTheFarm(farmName, weightVal));
      } catch (Exception ignored) {
        /* Terminates the method*/
      }
    } else {
      List<ReportForTheFarm> reports = dailyReportForTheFarms.get(date);
      try {
        Double weightVal = Double.parseDouble(weight);
        reports.add(new ReportForTheFarm(farmName, weightVal));
      } catch (Exception ignored) {
        /* Terminates the method*/
      }
    }
  }

  private class ReportForTheFarm {
    Map<String, Double> farmReport = new HashMap<>();

    private ReportForTheFarm(String farmName, Double weight) {
      farmReport.put(farmName, weight);
    }
  }


  private class ReportForTheDay {
    Map<String, Double> dailyReport = new HashMap<>();

    private ReportForTheDay(String date, Double weight) {
      dailyReport.put(date, weight);
    }
  }
}
