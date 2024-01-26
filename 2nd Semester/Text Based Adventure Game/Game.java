import java.util.*;

public class Game {
    private ArrayList<Room> map;
    private Actor player;
    private ThingList playerInventory;

    List<String> commands = new ArrayList<>(
            Arrays.asList("take", "drop", "look", "n", "s", "e", "w", "peek", "whereami"));
    List<String> objects = new ArrayList<>(Arrays.asList("sword", "ring", "bow", "arrow", "glock"));

    ThingList bankList, bedroomList, bathroomList, lootroomList, atticList;
    Treasure bolt, glock, cellphone, painting, dime;
    Room bank, bedroom, attic, bathroom, lootroom;

    public Game() {
        this.map = new ArrayList<Room>();

        // lists
        this.bankList = new ThingList();
        this.bedroomList = new ThingList();
        this.bathroomList = new ThingList();
        this.lootroomList = new ThingList();
        this.atticList = new ThingList();

        // treasures
        // bolt, glock, cellphone, painting, dime

        this.bolt = new Treasure("bolt", "a bolt", 47, 0.50);
        this.cellphone = new Treasure("iPhone15", "Find your way around", 800, 0.75);
        this.painting = new Treasure("Mona Lisa", "rare painting", 1000000, 0.01);
        this.dime = new Treasure("dime", "a dime", 0.10, 0.99);
        /*
         * Rooms
         * bedroom = 1; bathroom = 2; attic = 3; bank = 4; lootroom = 5;
         * 
         * 
         * start: bedroom ->(N)attic
         * start: bedroom -> (E)bathroom
         * start: bedroom ->(S)bank
         * start: bedroom ->(W) NOEXIT
         * 
         * attic -> (S)bedroom
         * attic -> (OTHER)noexit
         * 
         * bathroom -> (W)bedroom; everywhere else noexit
         * 
         * bank -> (N)bedroom;
         * bank -> lootroom; everywhere else NOEXIT
         */
        this.bank = new Room("bank", "North is bedroom and East is lootroom", 0, Direction.NOEXIT, Direction.NOEXIT, 4,
                bankList);
        this.bedroom = new Room("bedroom", "The bedroom has 3 exits: N, E, S", 2, 3, Direction.NOEXIT, 1, bedroomList);
        this.bathroom = new Room("bathroom", "A basic bathroom with a boosted glock spawn chance", Direction.NOEXIT,
                Direction.NOEXIT, 0, Direction.NOEXIT, bathroomList);
        this.attic = new Room("attic", "Mystery room that contains a key", Direction.NOEXIT, Direction.NOEXIT, 0,
                Direction.NOEXIT, lootroomList);
        this.lootroom = new Room("lootroom", "A room with a painting and a dime", Direction.NOEXIT, Direction.NOEXIT, 3,
                Direction.NOEXIT, atticList);

        // add rooms to map

        this.map.add(this.bedroom);
        this.map.add(this.bank);
        this.map.add(this.bathroom);
        this.map.add(this.attic);
        this.map.add(this.lootroom);

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
                exit = currentRoom.getN();
                break;
            case SOUTH:
                exit = currentRoom.getS();
                break;
            case EAST:
                exit = currentRoom.getE();
                break;
            case WEST:
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
        } else {
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
