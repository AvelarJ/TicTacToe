//Made by Jordan Avelar
//CS2210a October 18 2020
//Student number 251083623

public class Data {

	private int dataScore, dataLevel;
	private String dataKey;
	
	//Constructor for object Data
	public Data(String key, int score, int level) {
		
		dataKey = key;
		dataScore = score;
		dataLevel = level;
		
	}
	
	//Getter methods for Data
	public String getKey() {
		return dataKey;
	}
	
	public int getScore() {
		return dataScore;
	}
	
	public int getLevel() {
		return dataLevel;
	}
}
