public class Treasure extends Thing {
    
    private double value;
    private double rarity;

    public Treasure (String aName, String desc, double aValue, double rarity) {
        super(aName, desc);
        this.value = aValue;
        this.rarity = rarity;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double newValue) {
        this.value = newValue;
    }

    public double getRarity() {
        return this.rarity;
    }

    public void setRarity(double newRarity) {
        this.rarity = newRarity;
    }
}
