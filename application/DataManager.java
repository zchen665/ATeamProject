package application;

import java.util.List;

public interface DataManager {

  public List<Integer> getMonthlyAverage();
  
  public List<Integer> getMonthlyMin();
  
  public List<Integer> getMonthlyMax();
  
  public List<Integer> getMonthlyAverageForFarm(String farmName);
  
  public List<Integer> getMonthlyMinForFarm(String farmName);
  
  public List<Integer> getMonthlyMaxForFarm(String farmName);
  
  public List<Integer> getDataSortedByField();
  
  public List<Integer> getAverageInDateRange(int startDate, int endDate);
  
  public List<Integer> getMinInDateRange(int startDate, int endDate);
  
  public List<Integer> getMaxInDateRange(int startDate, int endDate);
  
}
