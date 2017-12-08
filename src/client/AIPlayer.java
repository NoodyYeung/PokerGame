package client;

import DDZ.DDZ;
import cards.Card;
import cards.Cards;
import gameController.Player;
import pattern.Pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public class AIPlayer extends Player {
    public AIPlayer() {
        id = playerIdForLocalAndAI++;
    }

    @Override
    public List<Card> yourTurnToPlayCard(Cards lastTurnCards, List<Card> yourCard) {
        System.out.println("AI is selecting card ...");
        return autoPlaysCard(yourCard,lastTurnCards);
    }

    @Override
    public void waitPlayerToPlayerCard(Player playerToPlay, boolean isLandLordToPlayCard) {

    }

    @Override
    public void youAreFarmer(Player teammates) {

    }

    @Override
    public void youAreLandLord() {

    }

    @Override
    public List<Card> yourTurnToPlayCardOrSkipCard(List<Card> cardsThatThePlayerHave, Cards lastTurnCards) {
        System.out.println("AI is selecting card ...");
        return autoPlaysCard(cardsThatThePlayerHave,lastTurnCards);
    }

    @Override
    public void youWin() {

    }

    @Override
    public void youLose() {

    }

    @Override
    public void pleaseMakeAValidPlay() {

    }

    @Override
    public void playerCardCountInfo(List<String> playerCountsInfo) {

    }


    private boolean autoPlaysCardR(List<Card> cardInHand, Cards lastTurnCard, int depth, List<Card> canPlayedList, DDZ ddz){
        if(depth > 5) {
//            System.out.println("[DEbug] : autoPlaysCardR return FALSE");
            return false;
        }
        Pattern p = ddz.validateDDZ(canPlayedList, lastTurnCard);
        if(p != null){
            if(lastTurnCard != null){
                System.out.println("[Debug] : lastTurnCard "+ Cards.toStringWithSymbol(lastTurnCard.getCards()));
                System.out.println("[Debug] : lastTurnCard pattern "+ lastTurnCard.getPattern());
            }
            System.out.println("[Debug] : found play " + p.getClass().getName() );
        }
        if(canPlayedList.size() > 0 && ddz.validateDDZ(canPlayedList, lastTurnCard) != null){
            System.out.println("[DEbug] : autoPlaysCardR return TRUE");
            return true;
        }
        for(int i = 0; i < cardInHand.size(); i ++){
            Card tempCard = cardInHand.get(i);
            if(canPlayedList.indexOf(tempCard)== -1){
                canPlayedList.add(tempCard);
                if(autoPlaysCardR(cardInHand, lastTurnCard, depth+ 1, canPlayedList, ddz)){
                    System.out.println("[DEbug] : autoPlaysCardR return TRUE");
                    return true;
                };
                canPlayedList.remove(tempCard);
            }
        }
//        System.out.println("[DEbug] : autoPlaysCardR return FALSE");
        return false;
    }


    /**
     *
     * @param cardsInHand
     * @param lastTureCards
     * @return the played card, null for no combination
     */
    public List<Card> autoPlaysCard(List<Card> cardsInHand, Cards lastTureCards){
        DDZ ddz = new DDZ();
        List<Card> combination = new ArrayList<>();
        autoPlaysCardR(cardsInHand, lastTureCards, 0, combination, ddz);

        System.out.println("[Debug] AI Auto play size :" + combination.size());
        System.out.println("[Debug] AI Auto play :" + Cards.toStringWithSymbol(combination));

        return combination.size() == 0 ? null : combination;
    }

}
