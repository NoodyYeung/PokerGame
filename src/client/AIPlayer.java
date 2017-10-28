package client;

import cards.Card;
import gameController.Player;

import java.util.List;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public class AIPlayer extends Player {
    public AIPlayer() {
    }


    @Override
    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    @Override
    public void yourTurnToPlayCard(List<Card> lastTurnCards) {

    }

    @Override
    public void yourTurnToChooseToBeALandLord() {

    }

    @Override
    public void informYouPlayerChooseToBeALandLord(Player theOneCalledToBeLandLord) {

    }

    @Override
    public void yourTurnToGrapLandLord() {

    }

    @Override
    public void informYouPlayerGrapToBeALandLord(Player theOneGrapedToBeLandLord) {

    }

    @Override
    public void informPlayerPlayedCards(List<Card> cardsPlayed, Player thePlayerPlayedCard) {

    }

    @Override
    public void endGame() {

    }
}
