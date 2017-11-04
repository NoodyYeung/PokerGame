package command;

public interface Running {
	// Stuff to do
	public void execute();
	
	// Description
  public String description();
  
  // Unique shortcut letter
  public String key();
  
  // Display on console
  public String toString();
}
