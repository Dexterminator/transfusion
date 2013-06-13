import java.util.ArrayList;
import java.util.Iterator;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // All the items currently carried by the player
    private ArrayList<Item> items;
    private int blood;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        items = new ArrayList<Item>();
        blood = 30;
    }

    /**
     * Returns the list of items carried by the player.
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Returns the item specified if it is in the list.
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
     * Removes the specified item from the player.
     */
    public void removeItem(Item itemToRemove) {
        for(Iterator<Item> i = items.iterator(); i.hasNext();) {
            Item checkedItem = i.next();

            if(checkedItem == itemToRemove){
                i.remove();
            }
        }
    }

    /**
     * Prints a list of the items currently carried by the player.
     */
    public void getItemDescriptions() {
        if(items.isEmpty() != true){ //The player has one or more items.
            System.out.println("Your items are:");
            for(Item item : items) {
                System.out.println(item.getName() + ": " + item.getDescription());
            }
        }else{  // The player has no items.
            System.out.println("You are not carrying any items!");
        }
    }

    /**
     * Adds an item to the player's list of items.
     */
    public void addItem(Item itemToAdd) {
        items.add(itemToAdd);
    }

    /**
     * The player is healed, and the "Game Over" counter(blood) is increased.
     */
    public void heal() {
        blood += 2;
    }

    /**
     * The player bleeds, i.e, the "Game Over" counter (blood) moves one step closer to zero.
     */
    public void bleed() {
        blood--;
        if(blood == 24){
            System.out.println("Your status: Blood is slowly dripping out of your wound.");
        }else if(blood == 18){
            System.out.println("Your status: The blood loss is making you feel nauseous.");
        }else if(blood == 10) {
            System.out.println("Your status: Your skin looks pale and you are feeling dizzy.");
        }else if(blood == 5) {
            System.out.println("Your status: You know you are close to death. Your vision is getting blurry.");
        }else if(blood == 0) {
            System.out.println("Your status: Your legs cannot carry you anymore\nand you collapse in a pool of blood.");
        }
    }

    public boolean isDead() {
        boolean dead = false;
        if(blood == 0){
            dead = true;
        }
        return dead;
    }
}
