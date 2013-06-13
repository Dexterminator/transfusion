import java.util.ArrayList;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room firstRoom, infirmary, staffRoom, corridor1, corridor2, corridor3, corridor4, corridor5,
        corridor6, operatingRoom, waitingRoom, emergencyRoom, entrance, pharmacy, corridor7, doctorsOffice,
        doctorsOffice2, recoveryRoom, ward1, ward2, ward3, outside;

        // create the rooms
        firstRoom = new Room("in an abandoned hospital room");
        infirmary = new Room("in an infirmary");
        staffRoom = new Room("in a staff room. The walls are covered in blood");
        corridor1 = new Room("in a corridor");
        corridor2 = new Room("in dark corridor. It is very hard to see,\nand you better keep track"
            + " of which direction you came from");
        corridor3 = new Room("in a dark corridor");
        corridor4 = new Room("in a dark corridor");
        corridor5 = new Room("in a dark corridor. It is a little brighter here,\nand you"
            + " can see a door to the north");
        corridor6 = new Room("in the end of the corridor.\nYou see a a flight of stairs leading down");
        operatingRoom = new Room("in an operating room");
        waitingRoom = new Room("in a waiting room. The door leading to the stairs\ndoes not have a handle on this side");
        emergencyRoom = new Room("in an emergency room");
        entrance = new Room("at the hospital entrance. The front door is to the east,\nbut something"
            +" is blocking it from the outside");
        pharmacy = new Room("in a pharmacy");
        corridor7 = new Room("in a corridor");
        doctorsOffice = new Room("in a doctor's office");
        doctorsOffice2 = new Room("in another doctor's office");
        recoveryRoom = new Room("in a recovery room");
        ward1 = new Room("in a hospital ward");
        ward2 = new Room("in a hospital ward.\nLight is coming from beneath the north door");
        ward3 = new Room("in a hospital ward.\nThere is an open window in the room");
        outside = new Room("outside");

        // initialise room exits and items    
        firstRoom.setExit("north", corridor1);
        corridor1.setExit("west", infirmary);
        corridor1.setExit("east", staffRoom);
        corridor1.setExit("north", corridor2);
        corridor1.setExit("south", firstRoom);

        infirmary.setExit("east", corridor1);

        Item key1 = new Item("key", "A rusty key");
        staffRoom.addItem(key1);
        staffRoom.setExit("west", corridor1);

        corridor2.lock();
        corridor2.setExit("south", corridor1);
        corridor2.setExit("north", corridor3);

        corridor3.setExit("south", corridor2);
        corridor3.setExit("east", corridor4);

        Item bandage = new Item("bandage", "A bandage used for shallow wounds.", 1);
        corridor4.addItem(bandage);
        corridor4.setExit("west", corridor3);
        corridor4.setExit("east", corridor5);

        Item pills = new Item("pills", "A bottle of brightly colored pills.", -1);
        corridor5.addItem(pills);
        corridor5.setExit("west", corridor4);
        corridor5.setExit("north", operatingRoom);
        corridor5.setExit("south", corridor6);

        Item syringe = new Item("syringe", "A syringe filled with morfine.", 1);
        Character doctor = new Character("A man in a doctor's uniform is lying on the floor.\nHe is badly injured.", 
                "- You have to get out of here! It is too late for me...", syringe);
        operatingRoom.addCharacter(doctor);
        operatingRoom.setExit("south", corridor5);

        corridor6.setExit("north", corridor5);
        corridor6.setExit("down", waitingRoom);

        waitingRoom.setExit("west", corridor7);
        waitingRoom.setExit("north", emergencyRoom);

        emergencyRoom.setExit("north", entrance);
        emergencyRoom.setExit("south", waitingRoom);

        entrance.setExit("north", pharmacy);
        entrance.setExit("south", emergencyRoom);

        Item key2 = new Item("key", "A key labeled: offices");
        pharmacy.addItem(key2);
        pharmacy.setExit("south", entrance);

        corridor7.lock();
        corridor7.setExit("west", doctorsOffice);
        corridor7.setExit("east", waitingRoom);

        Item key3 = new Item("key", "A blood-stained key");
        Item painKillers = new Item("painkillers", "A box of painkillers", 1);
        doctorsOffice.addItem(key3);
        doctorsOffice.addItem(painKillers);
        doctorsOffice.setExit("west", doctorsOffice2);
        doctorsOffice.setExit("east", corridor7);
        doctorsOffice.setExit("north", ward1);

        doctorsOffice2.setExit("west", recoveryRoom);
        doctorsOffice2.setExit("east", doctorsOffice);

        Item key4 = new Item("key", "A shiny key");
        Character cat = new Character("A cat is sitting in the room.", "- Meeeoow!", key4);
        recoveryRoom.addCharacter(cat);
        recoveryRoom.setExit("east", doctorsOffice2);

        ward1.setExit("south", doctorsOffice);
        ward1.setExit("north", ward2);

        ward2.lock();
        ward2.setExit("south", ward1);
        ward2.setExit("north", ward3);

        ward3.lock();
        ward3.setExit("south", ward2);
        ward3.setExit("out", outside);

        outside.setExit("none!", outside);

        
        
        currentRoom = firstRoom;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;

        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if(player.isDead()) {
                finished=true;
            }
            if(currentRoom.getShortDescription() == "outside"){
                System.out.println("You finally manage to get out of the hospital!\nYou breathe a sigh"
                    +" of relief.");
                finished = true;
            }
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();       
        System.out.println("Welcome to Transfusion!\nType '" + CommandWord.HELP + "' if you need help.\n");        
        System.out.println("You wake up in a hospital bed. No one is around and you have an eerie feeling"
            + "\nthat something is wrong. You realize that you have a gaping wound.\nMoving makes it even worse." +
            "\nYou need to find a way out!");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.LOOK) {
            look();
        }
        else if (commandWord == CommandWord.ITEMS) {
            items();
        }
        else if (commandWord == CommandWord.TAKE) {
            take(command);
        }
        else if (commandWord == CommandWord.SEARCH) {
            search();
        }
        else if (commandWord == CommandWord.UNLOCK) {
            unlock(command);
        }
        else if (commandWord == CommandWord.TALK) {
            talk();
        }
        else if (commandWord == CommandWord.USE) {
            use(command);
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no accessible door!");
        }
        else if(nextRoom.isLocked()){
            System.out.println("The door is locked!");
        }else{
            currentRoom = nextRoom;
            Character person = currentRoom.getCharacter();
            System.out.println(currentRoom.getLongDescription());
            if(person != null){
                System.out.println(person.getDescription());
            }
            if(currentRoom.getShortDescription() != "outside"){
                player.bleed();
            }
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Prints the description of the room again.
     */
    private void look(){
        Character person = currentRoom.getCharacter();
        System.out.println(currentRoom.getLongDescription());
        if(person != null){
            System.out.println(person.getDescription());
        }        
    }

    /**
     * Prints a list of items currently carried by the player, and their descriptions.
     */
    private void items() {
        player.getItemDescriptions();
    }

    /**
     * Lets a player search the current room, and prints a list of the items in it.
     */
    private void search() {
        System.out.println("You search the room.");
        String itemList = currentRoom.getItemDescriptions();
        if(!itemList.equals("You see:")){ // There are items in the room
            System.out.println(itemList);
        }else{ // There are no items in the room
            System.out.println("You don't find anything.");
        }
    }

    /**
     * Lets the player take an item from a room and add it to the 
     * player's list of items
     */
    private void take(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        String itemToTake = command.getSecondWord();

        if(currentRoom.getItem(itemToTake) != null){
            Item taken = currentRoom.getItem(itemToTake);
            player.addItem(taken);
            currentRoom.removeItem(taken);
            System.out.println("You pick up the " + itemToTake + ".");
        }else{
            System.out.println("There is no such item here!");
        }
    }

    /**
     * Unlocks the door in the specified direction if the player has a key.
     */
    private void unlock(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Unlock the door in which direction?");
            return;
        }
        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door to unlock!");
        }
        else if(!nextRoom.isLocked()){
            System.out.println("The door is not locked!");
        }else{
            ArrayList<Item> items = player.getItems();
            for(Item item : items){
                if(item.getName() == "key"){
                    System.out.println("You succesfully unlocked the door!" + "\n" +
                        "The key was removed from your items.");
                    nextRoom.unLock();
                    items.remove(item); //Works, since the iteration stops here
                    return;
                }
            }
            System.out.println("You don't have a key!");
        }
    }

    /**
     * Interacts with a character if there is one in the room.
     */
    private void talk() {
        Character roomChar = currentRoom.getCharacter();
        if(roomChar != null) { 
            roomChar.talk();
            Item charItem = roomChar.getItem();
            if(charItem != null) {
                player.addItem(charItem);
                roomChar.removeItem();
                System.out.println("You were given:\n" + charItem.getDescription());
            }
        }else{
            System.out.println("There is no one to talk to!");
        }
    }

    /**
     * Lets the player use an item. If this is successful, the player is either
     * healed or damaged, depending on the used item.
     */
    private void use(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Use what?");
            return;
        }
        String itemToUse = command.getSecondWord();

        if(player.getItem(itemToUse) != null){
            Item used = player.getItem(itemToUse);
            if(used.getHealing() == 0){
                System.out.println("You can't use that!");
            }
            else if(used.getHealing() >= 1){
                System.out.println("You use the " + used.getName() + ".\nYou feel slightly better.");
                player.heal();
                player.removeItem(used);
            }
            else if(used.getHealing() <= -1) {
                System.out.println("You use the " + used.getName() + ".\nYou feel even worse than before.");
                player.bleed();
                player.removeItem(used);
            }
        }
        else{
            System.out.println("You don't have that item!");
        }
    }
}