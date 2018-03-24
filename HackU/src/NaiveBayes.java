import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NaiveBayes {
	
	private String processMessage(String msg, long threshold, HashMap<String, Long> reference) {
		return null;
	}
	
	
	public HashMap<String, String> readStems(String filename) {
		HashMap<String, String> ret = new HashMap<String, String>();
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				String[] words = line.split("\t");
				if(words.length < 2) {
					continue;
				}
				ret.put(words[1], words[0]);
			}
			return ret;
		} catch (FileNotFoundException e) {
            e.printStackTrace();
		}
		return ret;
	}
	public ArrayList<String> readStopWords(String filename) {
		ArrayList<String> ret = new ArrayList<String>();
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				ret.add(line);
			}
			return ret;
		} catch (FileNotFoundException e) {
            e.printStackTrace();
		}
		return ret;
	}
	public HashMap<String, HashMap<String, Long>> readProbabilities(String filename){
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(filename));
			JSONObject jsonObject = (JSONObject) obj;
			return jsonObject;
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return null;
	}
	public void convertStemsToJSON(String filename1, String filename2) {
		JSONObject jsonObject = new JSONObject();
		try {
			File file = new File(filename1);
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				String[] words = line.split("\t");
				if(words.length < 2) {
					continue;
				}
				jsonObject.put(words[1], words[0]);
				
			}
			FileWriter fw = new FileWriter(filename2);
	        fw.write(jsonObject.toJSONString());
	        fw.flush();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void convertStopWordsToJSON(String filename1, String filename2) {
		JSONArray jsonArray = new JSONArray();
		try {
			File file = new File(filename1);
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				jsonArray.add(line);
			}
			FileWriter fw = new FileWriter(filename2);
	        fw.write(jsonArray.toJSONString());
	        fw.flush();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	public static void main(String[] args) {
		
	}
}
