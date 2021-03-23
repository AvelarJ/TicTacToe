//Made by Jordan Avelar
//CS2210a October 18 2020
//Student number 251083623

public class DuplicatedKeyException extends RuntimeException{
	
	public DuplicatedKeyException(String key) {
		super("The key, " + key + " is already in use");
	}

}
