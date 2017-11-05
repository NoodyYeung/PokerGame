package client;

import command.CommandController;
import command.ExInvalidCommand;
import command.MenuRoomSelection;
import message.Message;
import org.json.JSONException;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public class OnConnectedListener{
    private CommandController commandController;
    public OnConnectedListener(){
        commandController = CommandController.createSubCommandController();
    }

    public void onSuccessConnected(Message msg) {
        try {
            switch (msg.getEvent()) {
                case Message.SERVER_CONNECTED: // The call mapped to ServerMessage.class. Function prepareClientConnectedMessage
                    String message = msg.getJSONObject().getString("message");
                    String roomInformation = msg.getJSONObject().getString("roomInfo");
                    System.out.println(message);
                    System.out.println(roomInformation);

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
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onMessageReceived(Message receivedMessage) {
        System.out.println("[Debug] ReceivedMessage : " + receivedMessage);
        switch (receivedMessage.getEvent()){

        }

    }
    void onFailConnected(){

    };
}
