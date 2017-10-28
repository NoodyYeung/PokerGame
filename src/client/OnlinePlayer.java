package client;

import cards.Card;
import gameController.Player;

import java.util.List;

/**
 * Online Player is used to communicate with server But not the local game controller (I think >.<)
 * Created by yeungchunyin on 30/10/2017.
 */
public class OnlinePlayer extends Player {
    @Override
    public void setHand(List<Card> hand) {

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
    public void yourTurnToPlayCard(List<Card> lastTurnCards) {

    }

    @Override
    public void informPlayerPlayedCards(List<Card> cardsPlayed, Player thePlayerPlayedCard) {

    }

    @Override
    public void endGame() {

    }
}
