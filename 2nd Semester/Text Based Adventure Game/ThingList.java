import java.util.ArrayList;
public class ThingList extends ArrayList<Thing>{
    public String describeThings() {
        String result = "";
        if (this.size() == 0) {
            result = "nothing. \n";
        }
        else {
            for (Thing thing: this) {
                result = result + thing.getName() + ": "
                + thing.getDescription() + "\n"
                ;
            }
        }
        return result;
    }

    public Thing thisObj(String aName) {
        String thingName = "";
        String aNameLower = aName.trim().toLowerCase();
        for (Thing thing: this) {
            thingName = thing.getName().trim().toLowerCase();
            if (thingName.equals(aNameLower)) {
            return thing;
            }
        }
        return null;
    }
}
