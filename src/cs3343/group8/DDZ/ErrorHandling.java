package cs3343.group8.DDZ;


public class ErrorHandling {
	public static void handle(String err, Exception givenException){
		System.out.println(err);
		givenException.printStackTrace();
	}
}
