package cs3343.group8.client;

import java.util.ArrayList;

public class CardController {

    private static CardController ourInstance = new CardController();

    private static ArrayList<String> cards = new ArrayList<>();
    private static ArrayList<String> usedCards = new ArrayList<>();

    public static CardController getInstance() {
        return ourInstance;
    }

    private CardController() {
    }

    public static ArrayList<String> getCards() {
        return cards;
    }

    public static void setCards(ArrayList<String> cards) {
        CardController.cards = cards;
    }

    public static ArrayList<String> getUsedCards() {
        return usedCards;
    }

    public static void setUsedCards(ArrayList<String> usedCards) {
        CardController.usedCards = usedCards;
    }
}

