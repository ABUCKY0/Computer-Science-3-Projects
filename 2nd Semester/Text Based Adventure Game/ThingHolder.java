public class ThingHolder extends Thing {

    private ThingList things;
    
    public ThingHolder (String aName, String desc) {
        super(aName, desc);
        things = new ThingList();
    }

    public ThingHolder (String aName, String desc, ThingList tl) {
        super(aName, desc);
        things = tl;
    }

    public ThingList getThings() {
        return this.things;
    }

    public void setThings(ThingList newThings) {
        this.things = newThings;
    }
}
