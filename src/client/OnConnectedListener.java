package client;

import cards.Card;
import cards.Cards;
import cards.ExCardNoExists;
import command.CommandController;
import command.ExInvalidCommand;
import command.MenuRoomSelection;
import gameController.Player;
import message.ClientMessage;
import message.Message;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public class OnConnectedListener{
    private CommandController commandController;
    public OnConnectedListener(){
        commandController = CommandController.createSubCommandController();
    }

    public void onMessageReceived(Message msg) {
        JSONObject value = msg.getJSONObject();
        String event = msg.getEvent();
        Player tempPlayer = new LocalPlayer();
        ClientMessage.Builder builder = new ClientMessage.Builder();

        try {
            switch (event) {
                case Message.SERVER_CONNECTED: // The call mapped to ServerMessage.class. Function prepareClientConnectedMessage
                    String message = msg.getJSONObject().getString("message");
                    String roomInformation = msg.getJSONObject().getString("roomInfo");
                    System.out.println(message);
                    System.out.println(roomInformation);
                    openRoomSelectionMenu();
                    break;

                case Message.SERVER_RESPONSE_JOINED_ROOM:
                    message = msg.getJSONObject().getString("message");
                    System.out.println(message);
                    break;

                case Message.SERVER_RESPONSE_SOME_USER_JOINED_ROOM:
                    message = msg.getJSONObject().getString("message");
                    System.out.println(message);
                    break;

                case Message.SERVER_RESPONSE_ROOM_IS_FULL:
                    message = msg.getJSONObject().getString("message");
                    System.out.println(message);
                    openRoomSelectionMenu();
                    break;

                case Message.SERVER_RESPONSE_ROOM_IS_NOT_FOUND:
                    message = msg.getJSONObject().getString("message");
                    System.out.println(message);
                    openRoomSelectionMenu();
                    break;

                case Message.SERVER_RESPONSE_ROOM_CREATED:
                    message = msg.getJSONObject().getString("message");
                    System.out.println(message);
                    break;

                case Message.SERVER_START_GAME:
                    message = msg.getJSONObject().getString("message");
                    System.out.print(message);
                    break;

                case Message.SERVER_YOUR_TURN_TO_PLAY:
                    String lastHandStr = msg.getJSONObject().getString("lastTurn");
                    String handString = msg.getJSONObject().getString("yourCard");
//                    System.out.printf("[DEBUG] : %s\n", handString);
                    List<Card> cards;
                    if(lastHandStr.equals("-1")) {
                        cards = tempPlayer.yourTurnToPlayCardOrSkipCard(
                                Cards.createCardsListFromString(handString),
                               null);
                    }else{
                        cards = tempPlayer.yourTurnToPlayCardOrSkipCard(
                                Cards.createCardsListFromString(handString),
                                new Cards(Cards.createCardsListFromString(lastHandStr)));
                    }
                    builder.prepareRawTextMessage(Cards.toString(cards)).sendMessageToServer(ClientConnection.getInstance());
//                    System.out.println("[DEBUG] Message sent to server (your turn to play )");
                    break;

                case Message.SERVER_WAIT_PLAYER_TO_PLAY_CARD:
                    message = msg.getJSONObject().getString("message");
                    System.out.print(message);
                    break;

                case Message.SERVER_YOU_ARE_FARMER:
                    message = msg.getJSONObject().getString("message");
                    System.out.print(message);
                    break;

                case Message.SERVER_YOU_ARE_LANDLORD:
                    message = msg.getJSONObject().getString("message");
                    System.out.print(message);
                    break;

                case Message.SERVER_YOUR_TURN_TO_PLAY_CARD_OR_SKIP:
                    lastHandStr = msg.getJSONObject().getString("lastHand");
                    handString = msg.getJSONObject().getString("hand");
                    if(!lastHandStr.equals("-1")) {
                        cards = tempPlayer.yourTurnToPlayCardOrSkipCard(
                                Cards.createCardsListFromString(handString),
                                new Cards(Cards.createCardsListFromString(lastHandStr)));
                    }else{
                        cards = tempPlayer.yourTurnToPlayCardOrSkipCard(
                                Cards.createCardsListFromString(handString),
                                null);
                    }
                    if(cards != null)
                        builder.prepareRawTextMessage(Cards.toString(cards)).sendMessageToServer(ClientConnection.getInstance());
                    else builder.prepareRawTextMessage("SKIP").sendMessageToServer(ClientConnection.getInstance());


                    break;

                case Message.SERVER_INVALID_PLAY:
                    message = msg.getJSONObject().getString("message");
                    System.out.print(message);
                    break;

                case Message.SERVER_YOU_WIN:
                    message = msg.getJSONObject().getString("message");
                    System.out.print(message);
                    break;

                case Message.SERVER_YOU_LOSE:
                    message = msg.getJSONObject().getString("message");
                    System.out.print(message);
                    break;

                case Message.SERVER_PLEASE_MAKE_A_VALID_PLAY:
                    message = msg.getJSONObject().getString("message");
                    System.out.print(message);
                    break;

                case Message.SERVER_INVALID_CMD:
                    message = msg.getJSONObject().getString("message");
                    System.out.print(message);
                    break;

                case Message.SERVER_CARD_COUNT_MESSAGE:
                    message = msg.getJSONObject().getString("message");
                    System.out.print(message);
                    break;

                default:
//                    System.out.println("[Debug] ReceivedMessage : " + msg);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExCardNoExists exCardNoExists) {
            exCardNoExists.printStackTrace();
        }

    }


    public void openRoomSelectionMenu(){
        MenuRoomSelection menu = new MenuRoomSelection();
        commandController.pushNewMenu(menu);

        while(menu.goBack || !menu.userSelectedCorrectCmd) {
            commandController.display();
            if(!menu.userSelectedCorrectCmd && menu.invalidCommandKey != null){
                try {
                    throw new ExInvalidCommand(menu.invalidCommandKey);
                } catch (ExInvalidCommand exInvalidCommand) {
                    exInvalidCommand.printStackTrace();
                    System.out.println(exInvalidCommand.getMessage());
                }
            }
            String cmd = Main.systemIn.nextLine();
            commandController.execute(cmd);
        }
        commandController.quitMenu(menu);
    }


    void onFailConnected(){

    };
}
