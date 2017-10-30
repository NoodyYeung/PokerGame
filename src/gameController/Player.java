package gameController;

import cards.Card;

import java.util.List;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public abstract class Player {

    protected String name;
    protected List<Card> hand;
    protected GameController gameController;


    public abstract void setHand(List<Card> hand) ;

    public abstract void yourTurnToChooseToBeALandLord();
    public abstract void informYouPlayerChooseToBeALandLord(Player theOneCalledToBeLandLord);

    public abstract void yourTurnToGrapLandLord();
    public abstract void informYouPlayerGrapToBeALandLord(Player theOneGrapedToBeLandLord);

    public abstract void yourTurnToPlayCard(List<Card> lastTurnCards);
    public abstract void informPlayerPlayedCards(List<Card> cardsPlayed, Player thePlayerPlayedCard);

    public abstract void endGame();


    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
