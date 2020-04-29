package application;

import java.util.Arrays;
import java.util.List;

public class DS {
  public List<String> farmNames;//leave empty if no such data
  public List<Double> farmWeight;
  public List<String> month; //leave empty if no such data
  
  public DS(){
  this.farmNames = Arrays.asList("Farm 01", "Farm 02", "Farm 03");
  this.farmWeight = Arrays.asList(1.0,2.0,3.0);
  }

}