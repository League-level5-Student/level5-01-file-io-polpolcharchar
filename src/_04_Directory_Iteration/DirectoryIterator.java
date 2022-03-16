package _04_Directory_Iteration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DirectoryIterator {
	public static void main(String[] args) throws IOException {
		/* 
		 * The following is an example of how to list all of the files in a directory.
		 * Once the program is running, the directory is chosen using the JFileChooser.
		 */
//		JFileChooser jfc = new JFileChooser();
//		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//		int returnVal = jfc.showOpenDialog(null);
//		if (returnVal == JFileChooser.APPROVE_OPTION) {
//			File directory = jfc.getSelectedFile();
//			File[] files = directory.listFiles();
//			if(files != null) {
//				for(File f : files) {
//				  System.out.println(f.getAbsolutePath());
//				}
//			}
//		}
		fileSearch(new File("C:\\Users\\HomePC\\git\\level5-01-file-io-polpolcharchar\\src"));
		
		/*
		 * Your task is to write a program that iterates through the src folder of this current Java Project. 
		 * For every .java file it finds, the program will add a (non-legally binding) copyright statement at the bottom.
		 * Be aware of possible directories inside of directories.
		 * (e.g //Copyright © 2019 FirstName LastName)
		 */
	}
	
	
	public static void fileSearch(File base) throws IOException {
		File files[] = base.listFiles();
		for(int i = 0; i < files.length; i++) {//loop through all files inside of src
			for(int j = 0; j < files[i].listFiles().length; j++) {//loop through files inside of current files
				BufferedReader b = new BufferedReader(new FileReader(files[i].listFiles()[j]));
				String result = "";
				String next = b.readLine();
				do {
					result += next + "\n";
					next = b.readLine();
				}while(next != null);
				result += "//Property of Jack Fitterer\\";

				FileWriter f = new FileWriter(files[i].listFiles()[j]);
				f.write(result);
				f.close();
				
				
				b.close();
			}
			
			
		}
	}
}
//Property of Jack Fitterer\