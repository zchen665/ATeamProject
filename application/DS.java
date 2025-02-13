package application;

import java.util.*;

public class DS {
    public List<String> farmNames;//leave empty if no such data
    public List<Double> farmWeight;
    public List<String> dates; //leave empty if no such data
    public Map<String, List<ReportForTheDay>> farmReportDaily;
    public Map<String, List<ReportForTheFarm>> dailyReportForTheFarms;

    /**
     *
     */
    public DS() {
        farmNames = new ArrayList<>();
        farmWeight = new ArrayList<>();
        dates = new ArrayList<>();
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
            if (!dates.contains(date)) {
                dates.add(date);
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

    /**
     * Add a farm-based report for a specific day
     *
     * @param date
     * @param farmName
     * @param weight
     */
    public void addDailyReportForTheFarm(String date, String farmName, String weight) {
        if (!dailyReportForTheFarms.containsKey(date)) {
            if (!farmNames.contains(farmName)) {
                farmNames.add(farmName);
            }
            if (!dates.contains(date)) {
                dates.add(date);
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

    /**
     *
     */
    public class ReportForTheFarm {
        public String farmName;
        public Double weight;

        public ReportForTheFarm(String farmName, Double weight) {
            this.farmName = farmName;
            this.weight = weight;
        }
    }


    /**
     *
     */
    public class ReportForTheDay {
        public String date;
        public Double weight;

        public ReportForTheDay(String date, Double weight) {
            this.date = date;
            this.weight = weight;
        }
    }
}
