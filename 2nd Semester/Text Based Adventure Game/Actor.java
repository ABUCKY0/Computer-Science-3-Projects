import java.util.ArrayList;

public class Actor extends ThingHolder {
    private Room currentRoom;
    private ArrayList<Room> map;

    public Actor(String name, String description, ThingList tl, Room room, ArrayList<Room> map) {
        super(name, description, tl);
        this.currentRoom = room;
        this.map = map;
    }
    public Room getLocation() {
        return this.currentRoom;
    }
    public ArrayList<Room> getMap() {
        return this.map;
    }
    public void setMap(ArrayList<Room> map) {
        this.map = map;
    }
    public void setLocation(Room aRoom) {
        this.currentRoom = aRoom;
    }
}
