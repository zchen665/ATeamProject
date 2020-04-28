package application;

public class FileManagerDummy {
	
	boolean readFile(String inputFile) {
		System.out.println("Call readFile in fManager");
		return true;
	};
	
	boolean writeToFile(String outputFile) {
		System.out.println("Call writeTofile in fManager");
		return true;
	};
	
	//Don't know if needed
	//String getFileContents(String fileName);
}
