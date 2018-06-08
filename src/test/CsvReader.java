package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.print.attribute.standard.MediaName;

public class CsvReader {
	
	
	private static final String[] IndexOutOfBoundsException = null;
	private static ArrayList<String[]> csvlist = new ArrayList<>();
	private static int n = 0;
	public int size;

	public CsvReader(){
		
		InputStream csv = this.getClass().getResourceAsStream("/taxi data.csv"); 
		BufferedReader br = new BufferedReader(new InputStreamReader(csv));
		
 		String line = "";
 		String[] linedata = null;

		try {
			line = br.readLine();
			while((line = br.readLine()) != null){
				 linedata = line.split(",");
				 csvlist.add(linedata);
			}
			br.close();
			size = csvlist.size();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*test
	public static void main(String[] args) {
		CsvReader csvReader = new CsvReader();
		System.out.println(csvReader.getcsv(34));

		}
	public String[] getcsv (int n) {
		if (n < csvlist.size()) {
			return csvlist.get(n);
		}else{
			return null;
		}
	}
		*/
	
	public String[] readcsv(){
		if (n < csvlist.size()) {
			return csvlist.get(n++);
		}
		else{
			return null;
		}
	}
}
