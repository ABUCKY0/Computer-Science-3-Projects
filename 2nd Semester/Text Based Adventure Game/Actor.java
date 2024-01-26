public class Actor extends ThingHolder {
    private Room currentRoom;

    public Actor(String name, String description, ThingList tl, Room room) {
        super(name, description, tl);
        this.currentRoom = room;
    }
    public Room getLocation() {
        return this.currentRoom;
    }

    public void setLocation(Room aRoom) {
        this.currentRoom = aRoom;
    }
}
