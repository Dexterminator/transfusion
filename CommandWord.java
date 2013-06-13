/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), LOOK("look"), ITEMS("items"), 
    TAKE("take"), SEARCH("search"), UNLOCK("unlock"),  TALK("talk"), USE("use");
    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command word.
     * @param commandWord The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
