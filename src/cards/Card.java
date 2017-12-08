package cards;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card implements Comparable<Card> {

    public static List<String> cardType = new ArrayList(Arrays.asList("3", "4", "5", "6", "7", "8", "9", "10", "J", "Q" ,"K" ,"A" ,"2"));
    public static List<Integer> cardValues = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)); // joker value

    public static List<String> cardSuitStr = new ArrayList(Arrays.asList("C", "S" , "D", "H", "JB", "JR"));
    public static List<Suit> cardSuit  = new ArrayList<>(Arrays.asList(Suit.CLUB, Suit.SPADE, Suit.DIAMOND, Suit.HEART, Suit.JOKER_BLACK, Suit.JOKER_RED));


    // Q rather than Queen
    private Suit suit;
    private String type;
    /**
     * Joker value is hard-coded to 53 (red) and 54 (black)
     */
    private int value = 0;
    private int facevalue = 0;

    public Card(String shortForm) throws ExCardNoExists {
        if(shortForm.equals("JB") ) {
            this.facevalue = 53;
            this.value = this.facevalue;
            this.suit = Suit.JOKER_BLACK;
            return;
        }
        if(shortForm.equals("JR") ) {
            this.facevalue = 54;
            this.value = this.facevalue;
            this.suit = Suit.JOKER_RED;
            return;
        }

        // Handle the case with type equals 10
        String number = ""+shortForm.charAt(1) + (shortForm.length() == 3 ? shortForm.charAt(2) :  "");
        String suit = ""+shortForm.charAt(0);

        // Set number
        this.setType(number);

        // Set suit
        int suitIndex = cardSuitStr.indexOf(suit);
        if(suitIndex == -1) {
            throw new ExCardNoExists("Card does not exist. Suit \""+ suit +"\" does not exist in card");
        }
        this.suit = cardSuit.get(suitIndex);
        this.setValue();

    }

    public void setType(String type) throws ExCardNoExists {
        int typeIndex = cardType.indexOf(type);
        if(typeIndex == -1) {
            throw new ExCardNoExists("Card does not exist. Type \"" + type + "\" does not exist in card");
        }
        this.facevalue = cardValues.get(typeIndex);
        this.type = type;
    }
    
    
    /**
     * 
     * @return String card to string
     */
    @Override
    public String toString(){
        if(this.type == null ) return this.suit.toString();
        // UTF8 suit types
        //Window -> Preferences -> Workspace -> text file encoding -> Other -> UTF-8
        String symbol = "";
        switch(this.suit) {
            case CLUB:
            symbol = "\u2663";
            break;
            case SPADE: 
                symbol = "\u2660"; 
                break;
            case HEART:
                symbol = "\u2665"; 
                break;
            case DIAMOND: 
                symbol = "\u2666"; 
                break;
            default: 
                break;
        }
        return symbol + this.suit +  this.type;
    }

    public String getInputString(){
        if(this.type == null ) {
        	return this.suit.toString();
        }
        return (this.suit + this.type);
    }


    /**
     * 
     * 
     */
    public void setValue() {
        if(this.suit.equals(Suit.JOKER_RED) || suit.equals(Suit.JOKER_BLACK))
            this.value =  this.facevalue;
        else{
            int minus = 0;
            switch (suit){
                case CLUB:
                    minus = 3;
                    break;
                case DIAMOND:
                    minus = 4;
                    break;
                case HEART:
                    minus = 2;
                    break;
                case SPADE:
                    minus = 1;
                    break;
            }
            this.value = (this.facevalue * 4) - minus;
        }
    }
    
    /**
     * @return int value
     */
    public int getValue() 
    {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Card) {
            return obj.toString().equals(toString());
        }
        else {
            return false;
        }
            
    }


    @Override
    public int compareTo(Card o) {
        return getValue() - o.getValue();
    }
}
