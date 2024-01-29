import java.util.*;

public class Game {
    private ArrayList<Room> map;
    private ArrayList<Room> school;
    private Actor player;
    private ThingList playerInventory;

    List<String> commands = new ArrayList<>(
            Arrays.asList("take", "drop", "look", "n", "s", "e", "w", "peek", "whereami"));
    List<String> objects = new ArrayList<>(Arrays.asList("sword", "ring", "bow", "arrow", "glock"));

    ThingList eFieldList, sFieldList, toolShedList, schoolList;
    Treasure machete, glock, crowbar, key, doorcode; // Doorcode is a "treasure"
    Room eField, sField, toolShed, schoolRoom;

    public Game() {
        this.map = new ArrayList<Room>();


        // Create rooms/areas
        this.eFieldList = new ThingList();
        this.sFieldList = new ThingList();
        this.toolShedList = new ThingList();
        this.schoolList = new ThingList();

        // Add Rooms to Map ArrayList
        // 0 - eField
        // 1 - sField
        // 2 - toolShed
        // 3 - schoolRoom

        /*
         *  none  -x- School -x- East FootBall Field
         *               \     /                                        
         *  toolShed <--> South FootBall Field
         */

        // Create eField
        this.eField = new Room("East FootBall Field", "You are on the East FootBall Field", Direction.NOEXIT, 1, Direction.NOEXIT, Direction.NOEXIT, this.eFieldList);
        this.map.add(this.eField);

        // Create sField
        this.sField = new Room("South FootBall Field", "You are on the South FootBall Field", 3, Direction.NOEXIT, 2, 0, this.sFieldList); //3 shouldn't allow you to move there
        this.map.add(this.sField);

        // Create toolShed
        this.toolShed = new Room("Tool Shed", "You are in the Tool Shed", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, 1, this.toolShedList);
        this.map.add(this.toolShed);

        // Create schoolRoom (Special Room that it actually changes the map to the school map)
        this.schoolRoom = new Room("School", "You are in the School", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, this.schoolList, false);
        this.map.add(this.schoolRoom);


        this.playerInventory = new ThingList();

        this.player = new Actor("Player", "The player", this.playerInventory, map.get(0));
    }

    public ArrayList<Room> getMap() {
        return this.map;
    }

    private void setMap(ArrayList<Room> map) {
        this.map = map;
    }

    public Actor getPlayer() {
        return this.player;
    }

    private void setPlayer(Actor player) {
        this.player = player;
    }

    public ThingList getPlayerInventory() {
        return this.playerInventory;
    }

    // move a person, typically the player to a room
    public void moveActorTo(Actor player, Room aRoom) {
        player.setLocation(aRoom);
    }

    // move an actor in direction dir
    private int moveTo(Actor anActor, Direction dir) {
        // return: constant representing the room number moved to
        // or noexit
        //
        // try ot move any person (typically but not nessarily player)
        // in direction dir
        int exit;
        Room currentRoom = anActor.getLocation();
        switch (dir) {
            case NORTH:
                if (this.map.get(currentRoom.getN()).getAccessable() == false) {
                    return Direction.BLOCKED;
                }
                exit = currentRoom.getN();
                break;
            case SOUTH:
                if (this.map.get(currentRoom.getS()).getAccessable() == false) {
                    return Direction.BLOCKED;
                }
                exit = currentRoom.getS();
                break;
            case EAST:
                if (this.map.get(currentRoom.getE()).getAccessable() == false) {
                    return Direction.BLOCKED;
                }
                exit = currentRoom.getE();
                break;
            case WEST:
                if (this.map.get(currentRoom.getW()).getAccessable() == false) {
                    return Direction.BLOCKED;
                }
                exit = currentRoom.getW();
                break;
            default:
                exit = Direction.NOEXIT;
                break;
        }
        if (exit != Direction.NOEXIT) {
            this.moveActorTo(anActor, this.map.get(exit));
        }
        return exit;
    }

    public int movePlayerTo(Direction dir) {
        // return Constant representing the room number moved to
        // or noexit (See MoveTo)
        return moveTo(this.player, dir);
    }

    private void goN() {
        this.updateOutput(movePlayerTo(Direction.NORTH));
    }

    private void goS() {
        this.updateOutput(movePlayerTo(Direction.SOUTH));
    }

    private void goE() {
        this.updateOutput(movePlayerTo(Direction.EAST));
    }

    private void goW() {
        this.updateOutput(movePlayerTo(Direction.WEST));
    }

    private void updateOutput(int roomNumber) {
        // if roomNumber = NOEXIT, display a special message, otherwise
        // display text describing the room
        String s;
        if (roomNumber == Direction.NOEXIT) {
            s = "No Exit!";
        } 
        else if (roomNumber == Direction.BLOCKED) {
            s = "You can't go that way! Look around for a crowbar to pry open the door.";
        }
        else {
            Room r = this.getPlayer().getLocation();
            s = "You are in " + r.getName() + ". " + r.getDescription();
        }
        System.out.println(s);
    }

    public String processVerb(List<String> wordlist) {
        String verb;
        String msg = "";

        verb = wordlist.get(0);
        if (!commands.contains(verb)) {
            msg = verb + " is not a known verb! ";
        } else {
            switch (verb) {
                case "n":
                    this.goN();
                    break;
                case "s":
                    this.goS();
                    break;
                case "e":
                    this.goE();
                    break;
                case "w":
                    this.goW();
                    break;
                case "peek":
                    Room r = this.getPlayer().getLocation();
                    System.out.println(
                            "North: " + (r.getN() == Direction.NOEXIT ? "Nothing" : this.map.get(r.getN()).getName()));
                    System.out.println(
                            "South: " + (r.getS() == Direction.NOEXIT ? "Nothing" : this.map.get(r.getS()).getName()));
                    System.out.println(
                            "East: " + (r.getE() == Direction.NOEXIT ? "Nothing" : this.map.get(r.getE()).getName()));
                    System.out.println(
                            "West: " + (r.getW() == Direction.NOEXIT ? "Nothing" : this.map.get(r.getW()).getName()));
                    break;
                case "whereami":
                    Room r2 = this.getPlayer().getLocation();
                    System.out.println("You are in " + r2.getName());
                    System.out.println(
                            "North: " + (r2.getN() == Direction.NOEXIT ? "Nothing" : this.map.get(r2.getN()).getName()));
                    System.out.println(
                            "South: " + (r2.getS() == Direction.NOEXIT ? "Nothing" : this.map.get(r2.getS()).getName()));
                    System.out.println(
                            "East: " + (r2.getE() == Direction.NOEXIT ? "Nothing" : this.map.get(r2.getE()).getName()));
                    System.out.println(
                            "West: " + (r2.getW() == Direction.NOEXIT ? "Nothing" : this.map.get(r2.getW()).getName()));
                    break;
                default:
                    msg = "not implemented yet";
                    break;
            }
        }
        return msg;
    }

    public String processVerbNoun(List<String> wordlist) {
        String verb;
        String noun;
        String msg = "";

        verb = wordlist.get(0);
        noun = wordlist.get(1);
        if (!commands.contains(verb)) {
            msg = verb + " is not a known verb! ";
        }
        if (!objects.contains(noun)) {
            msg = noun + " is not a known noun! ";
        }
        msg += "not implemented yet";
        return msg;
    }

    public String parseCommand(List<String> wordlist) {
        String msg = "";
        if (wordlist.size() == 1) {
            msg = this.processVerb(wordlist);
        } else if (wordlist.size() == 2) {
            msg = this.processVerbNoun(wordlist);
        } else {
            msg = "Only 1 or 2 word commands are allowed!";
        }
        return msg;
    }

    public static List<String> wordList(String input) {
        String delims = "[ \t,.:;?!\"']+";
        // Explanation of delims:
        // [ \t,.:;?!\"']+ means space, tab, comma, period, colon, semicolon, question
        // mark, exclamation point, double quote, single quote, or one or more of any of
        // them.
        List<String> wordlist = new ArrayList<String>();
        String[] words = input.split(delims);
        for (String word : words) {
            wordlist.add(word);
        }
        return wordlist;
    }

    public void showIntro() {
        String s;
        s = " ";
        System.out.println(s);
    }

    public String runCommand(String inputstr) {
        List<String> wordlist;
        String msg = "";
        String lowstr = inputstr.trim().toLowerCase();
        if (!lowstr.equals("q")) {
            if (lowstr.equals("")) {
                msg = "You must enter a command!";
            } else {
                wordlist = wordList(lowstr);
                msg = parseCommand(wordlist);
            }

        }
        return msg;
    }

}
