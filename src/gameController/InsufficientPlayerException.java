package gameController;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public class InsufficientPlayerException extends Throwable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientPlayerException(String s) {
        super(s);
    }
}
