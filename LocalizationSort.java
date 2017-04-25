/**
 * Class sorted words from data.txt file, based on localization
 * @author: Jakub Pyda
 * Politechnika Koszalińska, Wydział Elektroniki i Informatyki, II rok, IV semestr
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Collator;

public class LocalizationSort {
	
	private FileReader fileRead = null;
	private BufferedReader buffor;
	private static String source;
	private String line;
	private String[] words;
	private Collator collator;
	
	public FileReader openFile() {
		try {
			fileRead = new FileReader(source);
		} catch (FileNotFoundException e) {
			System.err.println("Błąd przy otwieraniu pliku");
		}
		return fileRead;
	}
	
	public void closeFile(FileReader fileToClose) {
		try {
			fileToClose.close();
		} catch (IOException ex) {
			System.err.println("Błąd przy zamykaniu pliku");
		}
	}
	
	public void createTableWithSingleWords(String line, String[] tmp) {
		tmp = words;
		String[] wordInLine = line.split(" ");
		int index = wordInLine.length + tmp.length;
		words = new String[index];
		int j = 0;
		for (int k = 0; k < (wordInLine.length + tmp.length); k++) {
			if (k < tmp.length) {
				words[k] = tmp[k];
			} else {
				words[k] = wordInLine[j];
				j++;
			} 
		}
	}
	
	public void createSortedTable(Collator collator) {
		String tmp2;
	    for (int i = 0; i < words.length; i++) {
	        for (int j = i + 1; j < words.length; j++) {
	            if (collator.compare(words[i], words[j]) > 0) {
	                tmp2 = words[i];
	                words[i] = words[j];
	                words[j] = tmp2;
	            }
	        }
	    }
	}
	
	public void displayWords(String[] words) {
		for (String ss : words) {
			System.out.println(ss);
		}
	}
	
	public void getDataFromFile() {
		fileRead = openFile();
		buffor = new BufferedReader(fileRead);
		words = new String[0];
		String[] tmp = new String[1];
		collator = Collator.getInstance();
		try {
			for (int i = 0; (line = buffor.readLine()) != null; i++) {
				createTableWithSingleWords(line, tmp);
			}
			createSortedTable(collator);
		    displayWords(words);
		} catch (IOException e) {
			System.err.println("Błąd przy odczycie z pliku");
		}
		closeFile(fileRead);
	}

	public static void main(String[] args) {
		LocalizationSort ls = new LocalizationSort();
		source = args[0];
		ls.getDataFromFile();
	}

}
