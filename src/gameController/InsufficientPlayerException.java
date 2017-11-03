package gameController;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public class InsufficientPlayerException extends Throwable {
    public InsufficientPlayerException(String s) {
        super(s);
    }
}