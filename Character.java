
/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character
{
    // instance variables - replace the example below with your own
    private String description; // What will be printed as the player enters the room of the character
    private Item item;
    private String talkString;  //What the character will say
    
    /**
     * Constructor for objects of class Character
     */
    public Character(String description, String whatToSay, Item item) {
        this.description = description;
        this.item = item;
        talkString = whatToSay;
    }

    /**
     * Return the item carried by the character. Returns null if the character is not carrying any items.
     */
    public Item getItem() {
        return item;    
    }
    
    /**
     * Removes the item carried by the character.
     */
    public void removeItem() {
        item = null;
    }
    
    /**
     * Returns the description of the character.
     */
    public String getDescription() {
        return description;   
    }
    
    /**
     * Prints the string specified in the constructor, simulating that the character is talking.
     */
    public void talk() {
        System.out.println(talkString);    
    }
}
