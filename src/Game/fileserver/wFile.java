package Game.fileserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class wFile {
	public static String STRING_IN = "Dulieu.txt";
	
	// ============= Đọc dữ liệu từ file txt ra 1 ArrayList<String>
	
	public static ArrayList<String> ReadFile(){
		ArrayList<String> List = new ArrayList<>();
		File fileIn = new File("DuLieu.txt");
		
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(fileIn);
			br = new BufferedReader(fr);

			String line;
			while ((line = br.readLine()) != null) {

				//System.out.println(line);
				String[] strs = line.split(" ");
				for (String string : strs) {
					List.add(string);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		return List;
	}
	
	//=========== Ghi dữ liệu lên file txt================
	
	public static void writeFile(String time, String Count) {

		File fileOut = null;

		try {
			fileOut = new File("KetQua.txt");
			FileWriter fw = new FileWriter(fileOut, true);
			// BufferedWriter writer give better performance
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(time + "(s) - " + Count + " Clicks \n");
			// Closing BufferedWriter Stream
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> a = ReadFile();
		for (String string : a) {
			System.out.println(string);
		}
	}
	
}
