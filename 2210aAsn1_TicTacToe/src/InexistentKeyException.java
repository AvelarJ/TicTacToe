//Made by Jordan Avelar
//CS2210a October 18 2020
//Student number 251083623

public class InexistentKeyException extends RuntimeException{
	
	public InexistentKeyException(String key) {
		super("The key, " + key + " does not exist");
	}

}
