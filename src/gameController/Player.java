package gameController;

import cards.Card;

import java.util.List;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public abstract class Player {

    protected String name;
    protected List<Card> hand;

    public abstract void  setHand(List<Card> hand) ;
    public abstract void yourTurnToPlayCard(List<Card> lastTurnCards);
}
