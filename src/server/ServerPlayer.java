package server;

import cards.Card;
import cards.Cards;
import cards.ExCardNoExists;
import gameController.Player;
import message.Message;
import message.ServerMessage;
import org.json.JSONException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Server Player is the player on  the server
 * This class should extends Player.java. For development, i will add it later.
 */
public class ServerPlayer  extends Player{

    private static int id = 1;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;
    private boolean gameStarted = false;
    private ServerMessage.Builder serverMessageBuilder = new ServerMessage.Builder();

    public ServerPlayer(Socket socket) throws IOException {
        this.setId(id);
        this.name = "Player" + id++;
        this.socket = socket;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());

    }

    public void write(String s) {
        try {
            outputStream.writeUTF(s);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public List<Card> yourTurnToPlayCard(Cards lastTurnCards, List<Card> yourCard) {
        while (true) {
            try {
                serverMessageBuilder.prepareYourTurnToPlayCard(lastTurnCards, yourCard).sendToClient(outputStream);
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String responseText = null;
        List<Card> cards = null;
        while(true){
            try {
                if(inputStream.available() <= 0)
                    continue;
                responseText = inputStream.readUTF();
//                System.out.println("[DEBUG] yourTurnToPlayCard getMessage : " + responseText);
                if(responseText.equals("") || responseText.equals("\n")) {
//                    System.out.println("[DEBUG]  empty row received : " );
                    continue;
                }
                responseText = new Message(responseText).getJSONObject().getString("raw");
//                System.out.println("[DEBUG]  rawmessage : " + responseText );
                cards = Cards.createCardsListFromString(responseText);
//                System.out.println("[DEBUG]  Break : " + responseText );
                break;
            } catch(EOFException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ExCardNoExists exCardNoExists) {
                try {
                    serverMessageBuilder.prepareInvalidPlaysMessage().sendToClient(getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                exCardNoExists.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }   catch (ExInsuffientData exInsuffientData) {
                exInsuffientData.printStackTrace();
            }
        }
        return cards;
    }

    @Override
    public void waitPlayerToPlayerCard(Player playerToPlay, boolean isLandLordToPlayCard) {
        while(true) {
            try {
                serverMessageBuilder.prepareWaitPlayerToPlayerCardMessage(playerToPlay, isLandLordToPlayCard).sendToClient(getOutputStream());
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void youAreFarmer(Player teammates) {
        while(true) {
            try {
                serverMessageBuilder.prepareYouAreFarmerMessage(this, teammates).sendToClient(getOutputStream());
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void youAreLandLord() {
        while(true) {
            try {
                serverMessageBuilder.prepareYouLandLordMessage().sendToClient(getOutputStream());
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Card> yourTurnToPlayCardOrSkipCard(List<Card> cardsThatThePlayerHave, Cards lastTurnCards) {
        while(true) {
            try {
                serverMessageBuilder.prepareYourTurnToPlayCardOrSkipCard(cardsThatThePlayerHave, lastTurnCards).sendToClient(getOutputStream());
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String responseText = null;
        List<Card> cards = null;
        while(true){
            try {
                if(inputStream.available() <= 0)
                    continue;
                responseText = inputStream.readUTF();
                responseText = new Message(responseText).getJSONObject().getString("raw");
                if(responseText.equals("SKIP")){
//                    System.out.println("[DEBUG]  Break1 : " + responseText );

                    break;
                }
//                System.out.println("[DEBUG]  rawmessage : " + responseText );
                cards = Cards.createCardsListFromString(responseText);
//                System.out.println("[DEBUG]  Break2 : " + responseText );

                break;
            }  catch (IOException e) {
                e.printStackTrace();
            } catch (ExCardNoExists exCardNoExists) {
                try {
                    serverMessageBuilder.prepareInvalidPlaysMessage().sendToClient(getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                exCardNoExists.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ExInsuffientData exInsuffientData) {
                exInsuffientData.printStackTrace();
            }
        }
        return cards;
    }

    @Override
    public void youWin() {
        while(true) {
            try {
                serverMessageBuilder.prepareYouWinMessage().sendToClient(getOutputStream());
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void youLose() {
        while(true) {
            try {
                serverMessageBuilder.prepareYouLoseMessage().sendToClient(getOutputStream());
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void pleaseMakeAValidPlay() {
        while(true) {
            try {
                serverMessageBuilder.preparePleaseMakeAValidPlayMessage().sendToClient(getOutputStream());
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void playerCardCountInfo(List<String> playerCountsInfo) {
        while(true) {
            try {
                for(String s: playerCountsInfo)
                    serverMessageBuilder.prepareCountInfoMessage(s).sendToClient(getOutputStream());
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }
}
