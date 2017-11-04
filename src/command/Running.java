package command;

import cards.ExCardNoExists;

public interface Running {
	// Stuff to do
	public void execute() throws ExCardNoExists;
	
	// Description
  public String description();
  
  // Unique shortcut letter
  public String key();
  
  // Display on console
  public String toString();
}
