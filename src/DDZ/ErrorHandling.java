package DDZ;


public class ErrorHandling {
	public static void handle(String err, Exception givenException){
		System.out.println(err);
		givenException.printStackTrace();
	}
}
