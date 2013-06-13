
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private String description;
    private int healing;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
    
    /**
     * Constructor for item, if we want to make it possible to use it.
     */
    public Item(String name, String description, int healing) {
        this.name = name;
        this.description = description;
        this.healing = healing;
    }

    /**
     * Returns the name of the item.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the description of the item.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns the value of healing, where zero means that it cannot be used.
     * More than zero heals the player.
     * Less than zero damages the player.
     */
    public int getHealing() {
        return healing;
    }
}
