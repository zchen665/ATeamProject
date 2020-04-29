package application;

import java.util.Arrays;
import java.util.List;

public class DataManagerDummy {

  List<Integer> list=Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
	//get monthlyaverage returns a list of information on daily average 
	//by month of each farm in that month
	public List<Integer> getMonthlyAverage(String month, String year) {
		return list;
	};
	//get monthlymin returns a list of information on min daily milk weight
	//in that month for each farm
	public List<Integer> getMonthlyMin(String month, String year) {
		return list;
	}

	public List<Integer> getMonthlyMax(String month, String year) {
		return list;
	}

	public List<Integer> getMonthlyAverageForFarm(String farmName, String year) {
		return list;
	}

	//get monthlymin returns a list of information on min daily milk in each month for
	//that farm requested.
	public List<Integer> getMonthlyMinForFarm(String farmName, String year) {
		return list;
	}

	public List<Integer> getMonthlyMaxForFarm(String farmName, String year) {
		return list;
	}

	public List<Integer> getDataSortedByField() {
		return null;
	}

	public List<Integer> getAnnual(String year) {
		return list;
	}

	//changed from average to total based on the specification
	// sort by total milk weight over the data range
	public List<Integer> getTotalInDateRange(String startDate, String endDate) {
		return list;
	}

	public List<Integer> getMinInDateRange(String startDate, String endDate) {
		return list;
	}

	public List<Integer> getMaxInDateRange(String startDate, String endDate) {
		return list;
	}

}
