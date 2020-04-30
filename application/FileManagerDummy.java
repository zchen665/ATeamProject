package application;

import java.io.*;
import java.util.*;

public class FileManagerDummy {

    List<File> files = new LinkedList<>(); // All files in a particular folder

    boolean readFile(String inputFile) {
        System.out.println("Call readFile in fManager");
        return true;
    }

    boolean writeToFile(String outputFile) {
        System.out.println("Call writeTofile in fManager");
        return true;
    }

    /**
     * Private helper method that tells whether a line in a .csv file is valid
     *
     * @param line
     * @return
     */
    private boolean checkLine(String line) {
        return true;
    }

    /**
     * Private helper method that lists all files inside a given folder
     *
     * @param folder
     * @return
     */
    public List<File> enlistAllFiles(File folder) {
        return null;
    }

    //Don't know if needed
    //String getFileContents(String fileName);
}
