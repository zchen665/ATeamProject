
public class test {

	public static void main(String[] args) {
		cheeseFactory f1 = new cheeseFactory();
		f1.clearFarm(null);
		f1.insertData("2000-1-1", "Farm1", "516");
		f1.insertData("2000-1-1", "farm 2", "516");
		f1.insertData("DBVD", "ASFDB", "SDFBDSB");
		f1.insertData("-", "Farm 2", "56");
		f1.insertData("2000-1-3", "FARM 4", "5656");
		f1.insertData("2000-2-1", "Farm 2", "21");
		f1.insertData("2000-2-1", "Farm 3", "21");
		f1.insertData("2000-2-1", "Farm 3", "1111");
		
		
		
		String[][] temp = f1.getAllData();
		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < temp[i].length; j++) {
				System.out.print(temp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("===========");

		
		System.out.println(f1.checkForError());
		System.out.println(f1.checkForMissing());
		f1.clearDate("error");
		f1.clearDate("missing");
		System.out.println("missing and error eliminated");
		System.out.println(f1.checkForError());
		System.out.println(f1.checkForMissing());
		System.out.println(f1.getSumFarm("Farm 2"));
		f1.clearFarm("Farm 2");
		System.out.println("farm cleared");
		System.out.println(f1.getSumFarm("Farm 2"));
		System.out.println(f1.getSumDate("2000-1-1"));
		f1.clearDate("2000-1-1");
		System.out.println("date cleared");
		System.out.println(f1.getSumDate("2000-1-1"));
		System.out.println(f1.getSumMonth("1"));
		System.out.println(f1.getSumMonth("2"));
		System.out.println(f1.getSumYear("2000"));
		
		System.out.println("===========");
		temp = f1.getAllData();
		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < temp[i].length; j++) {
				System.out.print(temp[i][j] + " ");
			}
			System.out.println();
		}
	}

}
