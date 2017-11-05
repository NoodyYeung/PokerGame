package command;

import client.ClientConnection;
import client.Main;
import message.ClientMessage;

/**
 * Created by yeungchunyin on 5/11/2017.
 */
public class MenuRoomSelection extends Menu {

    public boolean userSelectedCorrectCmd = false;
    public boolean goBack = false;
    public String invalidCommandKey;

    @Override
    public String description() {
        return "Select room or Create room";
    }

    @Override
    public String key() {
        return null;
    }


    /**
     * Handle the case if user want to join a room
     */
    public void askForRoomId(){
        try {
            ClientMessage.Builder builder = new ClientMessage.Builder();

            System.out.println("Enter the room number: (Enter \"quit\" to go back)");

            String roomNumberStr = Main.systemIn.nextLine();
            if(roomNumberStr.equals("quit")) {
                goBack =true;
                return;
            }


            int roomNumber = Integer.parseInt(roomNumberStr);
            builder = new ClientMessage.Builder();
            ClientMessage message = builder.prepareJoinRoomMessage(roomNumber);
            message.sendMessageToServer(ClientConnection.getInstance());
        }catch (NumberFormatException e){
            System.out.println("Invalid number");
            askForRoomId();
        }
    }

    @Override
    public Menu execute(String commandKey) {
        ClientMessage.Builder builder = new ClientMessage.Builder();
        ClientMessage message;
        switch (commandKey){
            case "1":
                goBack = false;
                userSelectedCorrectCmd = true;
                System.out.println("Enter the room name: (Enter \"quit\" to go back)");
                String roomName = Main.systemIn.nextLine();
                if(roomName.equals("quit")){
                    goBack = true;
                    break;
                }
                message = builder.prepareCreateNewRoomMsg(roomName);
                message.sendMessageToServer(ClientConnection.getInstance());
                break;

            case "2":
                goBack = false;
                userSelectedCorrectCmd = true;
                askForRoomId();
                if(goBack)
                    break;
                break;

            default:
                invalidCommandKey = commandKey;
                userSelectedCorrectCmd = false;
                break;

        }
        return null;
    }

    @Override
    public void displayCommandList() {
        System.out.println("1. Create Room");
        System.out.println("2. Join Room");
    }


}
