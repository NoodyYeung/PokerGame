package client;

import command.CommandController;
import command.ExInvalidCommand;
import command.MenuRoomSelection;
import message.Message;
import org.json.JSONException;
import org.json.JSONObject;

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

                default:
                    System.out.println("[Debug] ReceivedMessage : " + msg);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
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
