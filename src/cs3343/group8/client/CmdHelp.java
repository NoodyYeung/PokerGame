package cs3343.group8.client;

public class CmdHelp extends Command {

	private String detailsMessage;
	private String key;
	private String description;

	public CmdHelp(){

	}

	public CmdHelp(String detailsMessage, String key, String description) {
		this.detailsMessage = detailsMessage;
		this.key = key;
		this.description = description;
	}

	@Override
	public String description() {
		return description != null ? description :  "Help";
	}

	@Override
	public String key() {
		// TODO Auto-generated method stub
		return key != null ? key : "h";
	}

	@Override
	public void execute() {
		System.out.println(detailsMessage != null ? detailsMessage : "[CmdHelp execute()] Need help? ");
	}

}
