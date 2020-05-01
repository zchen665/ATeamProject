package application;

import java.util.*;

/**
 * Defines operation on data manipulations and forming the required data for the visualizer
 *
 * @author Jitian Liu
 */
public class DataManager {

    private final DS ds = new DS();
    private final ArrayList<Double> list = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));

    /**
     * Set up the DataManager, given a repository of farms, namely, the cheese factory
     *
     * @param cf the cheese factory to be analyzed
     */
    public DataManager(cheeseFactory<String, Double> cf) {
        String[][] allData = cf.getAllData();
        for (int i = 0; i < allData.length; i++) {
            ds.addFarmReportForTheDay(allData[i][1], allData[i][0], allData[i][2]);
        }
        for (int i = 0; i < allData.length; i++) {
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
        int monthVal = Integer.parseInt(month);
        if (monthVal < 1 || monthVal > 12) {
            throw new IllegalArgumentException("Invalid Month");
        }
        for (String farmName : ds.farmNames) {
            List<DS.ReportForTheDay> DailyReports = ds.farmReportDaily.get(farmName);
            List<Double> dailyReportsForTheMonth = new ArrayList<>();
            for (DS.ReportForTheDay reportForTheDay : DailyReports) {
                String date = reportForTheDay.date;
                if ((date.contains(year) && date.contains("-0" + month + "-"))
                        || (date.contains(year) && date.contains("-" + month + "-"))) {
                    dailyReportsForTheMonth.add(reportForTheDay.weight);
                }
            }
            double sum = 0.0;
            for (Double d : dailyReportsForTheMonth) {
                sum += d;
            }
            ds.farmWeight.add(sum / days);
        }
        return ds;
    }

    /**
     * For a specific month in a year, derive the number of days it contains
     *
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

        return calendar.get(Calendar.DATE);
    }

    /**
     * Returns a list of information on min daily milk weight in that month for each farm
     *
     * @param month
     * @param year
     * @return
     */
    public DS getMonthlyMin(String month, String year) {
        ds.farmWeight.clear();
        Collections.sort(ds.farmNames);
        int monthVal = Integer.parseInt(month);
        if (monthVal < 1 || monthVal > 12) {
            throw new IllegalArgumentException("Invalid Month");
        }
        for (String farmName : ds.farmNames) {
            List<DS.ReportForTheDay> DailyReports = ds.farmReportDaily.get(farmName);
            List<Double> dailyReportsForTheMonth = new ArrayList<>();
            for (DS.ReportForTheDay reportForTheDay : DailyReports) {
                String date = reportForTheDay.date;
                if ((date.contains(year) && date.contains("-0" + month + "-"))
                        || (date.contains(year) && date.contains("-" + month + "-"))) {
                    dailyReportsForTheMonth.add(reportForTheDay.weight);
                }
            }
            double min = dailyReportsForTheMonth.get(0);
            for (Double d : dailyReportsForTheMonth) {
                if (min > d) {
                    min = d;
                }
            }
            ds.farmWeight.add(min);
        }
        return ds;
    }

    /**
     * Returns a list of information on max daily milk weight in that month for each farm
     *
     * @param month
     * @param year
     * @return
     */
    public DS getMonthlyMax(String month, String year) {
        ds.farmWeight.clear();
        Collections.sort(ds.farmNames);
        int monthVal = Integer.parseInt(month);
        if (monthVal < 1 || monthVal > 12) {
            throw new IllegalArgumentException("Invalid Month");
        }
        for (String farmName : ds.farmNames) {
            List<DS.ReportForTheDay> DailyReports = ds.farmReportDaily.get(farmName);
            List<Double> dailyReportsForTheMonth = new ArrayList<>();
            for (DS.ReportForTheDay reportForTheDay : DailyReports) {
                String date = reportForTheDay.date;
                if ((date.contains(year) && date.contains("-0" + month + "-"))
                        || (date.contains(year) && date.contains("-" + month + "-"))) {
                    dailyReportsForTheMonth.add(reportForTheDay.weight);
                }
            }
            double max = dailyReportsForTheMonth.get(0);
            for (Double d : dailyReportsForTheMonth) {
                if (max < d) {
                    max = d;
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
        int monthVal = Integer.parseInt(month);
        if (monthVal < 1 || monthVal > 12) {
            throw new IllegalArgumentException("Invalid Month");
        }
        for (String farmName : ds.farmNames) {
            List<DS.ReportForTheDay> DailyReports = ds.farmReportDaily.get(farmName);
            List<Double> dailyReportsForTheMonth = new ArrayList<>();
            for (DS.ReportForTheDay reportForTheDay : DailyReports) {
                String date = reportForTheDay.date;
                if ((date.contains(year) && date.contains("-0" + month + "-"))
                        || (date.contains(year) && date.contains("-" + month + "-"))) {
                    dailyReportsForTheMonth.add(reportForTheDay.weight);
                }
            }
            double sum = 0.0;
            for (Double d : dailyReportsForTheMonth) {
                sum += d;
            }
            ds.farmWeight.add(sum);
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
        if (!ds.farmNames.contains(farmName)) {
            throw new IllegalArgumentException("Invalid Farm Name!");
        }
        clear(list);
        List<DS.ReportForTheDay> reports = ds.farmReportDaily.get(farmName);
        for (int i = 0; i < list.size(); i++) {
            int month = i + 1;
            Double current = list.get(i);
            for (DS.ReportForTheDay report : reports) {
                if ((report.date.contains(year) && report.date.contains("-0" + month + "-"))
                        || (report.date.contains(year) && report.date.contains("-" + month + "-"))) {
                    current += report.weight;
                }
            }
            list.set(i, current);
        }
        return new ArrayList<>(list);
    }

    /**
     * Helper method that clears list
     *
     * @param list
     */
    private void clear(ArrayList<Double> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, 0.0);
        }
    }

    /**
     * For a specific farm get the average production over each month in a year
     *
     * @param farmName
     * @param year
     * @return
     */
    public List<Double> getMonthlyAverageForFarm(String farmName, String year) {
        clear(list);
        if (!ds.farmNames.contains(farmName)) {
            throw new IllegalArgumentException("Invalid Farm Name!");
        }
        List<Double> tmpList = new ArrayList<>(getFarmReport(farmName, year));
        System.out.println(tmpList.toString());
        for (int i = 0; i < list.size(); i++) {
            int month = i + 1;
            int days = getDaysForTheMonth(Integer.toString(month), year);
            list.set(i, tmpList.get(i) / days);
        }
        return new ArrayList<>(list);
    }

    /**
     * For a specific farm get the minimum production for each month in a year
     *
     * @param farmName
     * @param year
     * @return
     */
    public List<Double> getMonthlyMinForFarm(String farmName, String year) {
        clear(list);
        if (!ds.farmNames.contains(farmName)) {
            throw new IllegalArgumentException("Invalid Farm Name!");
        }
        for (int i = 0; i < list.size(); i++) {
            int month = i + 1;
            List<DS.ReportForTheDay> DailyReports = ds.farmReportDaily.get(farmName);
            List<Double> dailyReportsForTheMonth = new ArrayList<>();
            for (DS.ReportForTheDay reportForTheDay : DailyReports) {
                String date = reportForTheDay.date;
                if ((date.contains(year) && date.contains("-0" + month + "-"))
                        || (date.contains(year) && date.contains("-" + month + "-"))) {
                    dailyReportsForTheMonth.add(reportForTheDay.weight);
                }
            }
            double min = dailyReportsForTheMonth.get(0);
            for (Double d : dailyReportsForTheMonth) {
                if (min > d) {
                    min = d;
                }
            }
            list.set(i, min);
        }
        return new ArrayList<>(list);
    }

    /**
     * For a specific farm get the maximum production for each month in a year
     *
     * @param farmName
     * @param year
     * @return
     */
    public List<Double> getMonthlyMaxForFarm(String farmName, String year) {
        clear(list);
        if (!ds.farmNames.contains(farmName)) {
            throw new IllegalArgumentException("Invalid Farm Name!");
        }
        for (int i = 0; i < list.size(); i++) {
            int month = i + 1;
            List<DS.ReportForTheDay> DailyReports = ds.farmReportDaily.get(farmName);
            List<Double> dailyReportsForTheMonth = new ArrayList<>();
            for (DS.ReportForTheDay reportForTheDay : DailyReports) {
                String date = reportForTheDay.date;
                if ((date.contains(year) && date.contains("-0" + month + "-"))
                        || (date.contains(year) && date.contains("-" + month + "-"))) {
                    dailyReportsForTheMonth.add(reportForTheDay.weight);
                }
            }
            double max = dailyReportsForTheMonth.get(0);
            for (Double d : dailyReportsForTheMonth) {
                if (max < d) {
                    max = d;
                }
            }
            list.set(i, max);
        }
        return new ArrayList<>(list);
    }

    /**
     * Get the annual report for all farms
     *
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
     * Collect the data of production for a specific farm in a specific year
     *
     * @param farmName
     * @param year
     * @return
     */
    private List<Double> getAnnualForFarm(String farmName, String year) {
        if (!ds.farmNames.contains(farmName)) {
            throw new IllegalArgumentException("Invalid Farm Name!");
        }
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
     * Collect the data of production for a specific range of date in a specific year
     *
     * @param startDate
     * @param endDate
     * @param farmName
     * @return
     */
    private List<Double> getSpecifiedRangeReportForFarm(String startDate, String endDate, String farmName) {
        if (!ds.farmNames.contains(farmName)) {
            throw new IllegalArgumentException("Invalid Farm Name!");
        }
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
