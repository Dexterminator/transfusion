import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;              // stores the items in this room.
    private boolean locked;
    private Character character;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
        locked = false;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Returns an item in the room.
     */
    public Item getItem(String name) {
        for(Item item : items) {
            if(name.equals(item.getName())){
                return item;
            }
        }
        // No such item exists
        return null;
    }
    
    /**
     * Adds an item to the room.
     */
    public void addItem(Item itemToAdd) {
        items.add(itemToAdd);
    }
    
    /**
     * Removes the specified item from the room.
     */
    public void removeItem(Item itemToRemove) {
        for(Iterator<Item> i = items.iterator(); i.hasNext();) {
            Item checkedItem = i.next();
            
            if(checkedItem == itemToRemove) {
                i.remove();
            }
        }
    }
    
    /**
     * Returns the descriptions of all the items in the room.
     */
    public String getItemDescriptions() {
        String description = "You see:";
        for(Item item : items) {
            description = description + "\n" + item.getDescription();
        }
        return description;
    }
    
    /**
     * Adds a character to the room.
     */
    public void addCharacter(Character characterToAdd) {
        character = characterToAdd;
    }
    
    /**
     * Returns the character in the room. Returns null if there are no characters in the room.
     */
    public Character getCharacter() {
        return character;
    }
    
    /**
     * Locks the room.
     */
    public void lock() {
        locked = true;
    }
    
    /**
     * Unlocks the room.
     */
    public void unLock() {
        locked = false;
    }
    
    /**
     * Returns true if the door is locked.
     */
    public boolean isLocked() {
        return locked;
    }
}

