package application;

import java.util.Arrays;
import java.util.List;

public class DataManagerDummy {
  
  List<Double> list=Arrays.asList(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0);
  DS ds = new DS();
	//get monthlyaverage returns a list of information on daily average 
	//by month of each farm in that month
	public DS getMonthlyAverage(String month, String year) {
		return ds;
	};
	//get monthlymin returns a list of information on min daily milk weight
	//in that month for each farm
	public DS getMonthlyMin(String month, String year) {
		return ds;
	}

	public DS getMonthlyMax(String month, String year) {
		return ds;
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

	public DS getMinInDateRange(String startDate, String endDate) {
		return ds;
	}

	public DS getMaxInDateRange(String startDate, String endDate) {
		return ds;
	}
}
