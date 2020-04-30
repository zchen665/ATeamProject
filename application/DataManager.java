package application;

import java.util.*;

/**
 * Defines operation on data manipulations and forming the required data for the visualizer
 */
public class DataManager {

    private final List<Double> list = new ArrayList<>(12);
    private final DS ds = new DS();
    private final cheeseFactory<String, Double> cf;

    /**
     * @param cf
     */
    public DataManager(cheeseFactory<String, Double> cf) {
        this.cf = cf;
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
            double total = cf.getSumFarm(farmName);
            double avg = total / days;
            ds.farmWeight.add(avg);
        }
        return ds;
    }

    /**
     * @param month
     * @param year
     * @return
     */
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
        Collections.sort(ds.farmNames);
        for (String farmName : ds.farmNames) {
            List<DS.ReportForTheDay> DailyReports = ds.farmReportDaily.get(farmName);
            double min = DailyReports.get(0).weight;
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

    /**
     * @param month
     * @param year
     * @return
     */
    public DS getMonthlyMax(String month, String year) {
        ds.farmWeight.clear();
        Collections.sort(ds.farmNames);
        for (String farmName : ds.farmNames) {
            List<DS.ReportForTheDay> DailyReports = ds.farmReportDaily.get(farmName);
            double max = DailyReports.get(0).weight;
            for (DS.ReportForTheDay reportForTheDay : DailyReports) {
                String date = reportForTheDay.date;
                if (date.contains(month) && date.contains(year)) {
                    if (max <= reportForTheDay.weight) {
                        max = reportForTheDay.weight;
                    }
                }
            }
            ds.farmWeight.add(max);
        }
        return ds;
    }

    /**
     * @param month
     * @param year
     * @return
     */
    public DS getMonthlyReport(String month, String year) {
        ds.farmWeight.clear();
        Collections.sort(ds.farmNames);
        for (String farmName : ds.farmNames) {
            double total = cf.getSumFarm(farmName);
            ds.farmWeight.add(total);
        }
        return ds;
    }

    /**
     * Given a farm id and a year, displays the total weight of milk of this farm for each month
     *
     * @param farmName
     * @param year
     * @return
     */
    public List<Double> getFarmReport(String farmName, String year) {
        clear(list);
        List<DS.ReportForTheDay> reports = ds.farmReportDaily.get(farmName);
        for (int i = 0; i < list.size(); i++) {
            int month = i + 1;
            Double current = list.get(i);
            for (DS.ReportForTheDay report : reports) {
                if (report.date.contains(year) && report.date.contains("-" + month + "-")) {
                    list.set(i, current + report.weight);
                }
            }
        }
        return list;
    }

    /**
     * Helper method that clears list
     *
     * @param list
     */
    private void clear(List<Double> list) {
        for (Double d : list)
            d = 0.0;
    }

    /**
     * @param farmName
     * @param year
     * @return
     */
    public List<Double> getMonthlyAverageForFarm(String farmName, String year) {
        clear(list);
        List<Double> tmpList = new ArrayList<>(getFarmReport(farmName, year));
        for (int i = 0; i < list.size(); i++) {
            int month = i + 1;
            int days = getDaysForTheMonth(Integer.toString(month), year);
            list.set(i, tmpList.get(i) / days);
        }
        return list;
    }

    /**
     * @param farmName
     * @param year
     * @return
     */
    //get monthlymin returns a list of information on min daily milk in each month for
    //that farm requested.
    public List<Double> getMonthlyMinForFarm(String farmName, String year) {
        clear(list);
        for (int i = 0; i < list.size(); i++) {
            int month = i + 1;
            List<Double> monthlyReport = getMonthlyFarmReport(farmName, year, month);
            double min = monthlyReport.get(0);
            for (double d : monthlyReport) {
                if (min >= d) {
                    min = d;
                }
            }
            list.set(i, min);
        }
        return list;
    }

    /**
     * @param farmName
     * @param year
     * @param month
     * @return
     */
    private List<Double> getMonthlyFarmReport(String farmName, String year, int month) {
        List<Double> reportForTheMonth = new ArrayList<>();
        List<DS.ReportForTheDay> completeFarmReport = ds.farmReportDaily.get(farmName);
        for (DS.ReportForTheDay report : completeFarmReport) {
            if (report.date.contains(year) && report.date.contains("-" + month + "-")) {
                reportForTheMonth.add(report.weight);
            }
        }
        return reportForTheMonth;
    }

    public List<Double> getMonthlyMaxForFarm(String farmName, String year) {
        clear(list);
        for (int i = 0; i < list.size(); i++) {
            int month = i + 1;
            List<Double> monthlyReport = getMonthlyFarmReport(farmName, year, month);
            double max = monthlyReport.get(0);
            for (double d : monthlyReport) {
                if (max <= d) {
                    max = d;
                }
            }
            list.set(i, max);
        }
        return list;
    }

    /**
     * @return
     */
    public List<Double> getDataSortedByField() {
        // TODO: Aborted
        return null;
    }

    /**
     * @param year
     * @return
     */
    public DS getAnnual(String year) {
        ds.farmWeight.clear();
        Collections.sort(ds.farmNames);
        for (String farmName : ds.farmNames) {
            Double sum = 0.0;
            List<Double> weightReportForTheYear = getAnnualForFarm(farmName, year);
            for (Double d : weightReportForTheYear) {
                sum += d;
            }
            ds.farmWeight.add(sum);
        }
        return ds;
    }

    /**
     * @param farmName
     * @param year
     * @return
     */
    private List<Double> getAnnualForFarm(String farmName, String year) {
        List<DS.ReportForTheDay> reports = ds.farmReportDaily.get(farmName);
        List<Double> reportForTheYear = new ArrayList<>();
        for (DS.ReportForTheDay report : reports) {
            if (report.date.contains(year + "-")) {
                reportForTheYear.add(report.weight);
            }
        }
        return reportForTheYear;
    }

    /**
     * Prompt user for start date (year-month-day) and end month-day,
     * Then display the total milk weight per farm and the percentage of the total for each farm over that date range.
     * The list must be sorted by Farm ID, or you can prompt for ascending or descending order by weight or percentage.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    //changed from average to total based on the specification
    // sort by total milk weight over the data range
    public DS getTotalInDateRange(String startDate, String endDate) {
        ds.farmWeight.clear();
        Collections.sort(ds.farmNames);
        Map<Double, String> corresMap = new HashMap<>();
        for (String farmName : ds.farmNames) {
            Double sum = 0.0;
            List<Double> weightReportForTheRange = getSpecifiedRangeReportForFarm(startDate, endDate, farmName);
            for (Double d : weightReportForTheRange) {
                sum += d;
            }
            ds.farmWeight.add(sum);
            corresMap.put(sum, farmName);
        }
        // Sort by total milk weight
        Collections.sort(ds.farmWeight);
        ds.farmNames.clear();
        for (Double weight : ds.farmWeight) {
            ds.farmNames.add(corresMap.get(weight));
        }
        return ds;
    }

    /**
     * @param startDate
     * @param endDate
     * @param farmName
     * @return
     */
    private List<Double> getSpecifiedRangeReportForFarm(String startDate, String endDate, String farmName) {
        List<DS.ReportForTheDay> reports = ds.farmReportDaily.get(farmName);
        List<Double> reportForTheYear = new ArrayList<>();
        for (DS.ReportForTheDay report : reports) {
            if (report.date.compareTo(startDate) >= 0 && report.date.compareTo(endDate) <= 0) {
                reportForTheYear.add(report.weight);
            }
        }
        return reportForTheYear;
    }
}
