package alvarez;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;


public class JMCS {
	private File inputFile;
	private ArrayList <String> catalog;
	
	public JMCS(File inputFile) {
		this.inputFile = inputFile;
		catalog = new ArrayList<>();
	}
	
	public void buildCatalog() {
		Scanner fileInputScan = null;
		
		try {
			fileInputScan = new Scanner(inputFile);
			search(fileInputScan);
		} 
		catch (FileNotFoundException e) {
			System.out.println("Error: This file could not be found.");
		} 
		finally {
			if (fileInputScan != null) {
				fileInputScan.close();
			}
		}
	}
	
	public String toString() {
		String MCSString = "";
		for (int i = 0; i < catalog.size(); i++) {
			MCSString += catalog.get(i) + "\n";
		}
		return MCSString;
	}
	
	private void search(Scanner fileInputScan) {
		catalog = new ArrayList<String>();
		Stack<String> stack = new Stack<>();
		String codeLine = "";
		String possibleMCS = "";
		
		while (fileInputScan.hasNextLine()) {
			codeLine = fileInputScan.nextLine();
			codeLine = codeLine.trim();
			
			if (codeLine.length() > 0) {
				if (!codeLine.equals("{") && !codeLine.equals("}")) {
					possibleMCS = codeLine;
				} else if (codeLine.equals("{")) {
					stack.push(possibleMCS);
				} else {
					catalog.add(0, stack.pop());
				}
			}
		}
		
	}
	
}
