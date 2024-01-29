public class Room extends ThingHolder {

    private int n, s, w, e;
    private boolean isAccessable;

    public Room(String aName, String desc, int aN, int aS, int aW, int aE, ThingList tl, boolean accessable) {
        super(aName, desc, tl);
        this.n = aN;
        this.s = aS;
        this.w = aW;
        this.e = aE;
        this.isAccessable = accessable;
    }
    public Room(String aName, String desc, int aN, int aS, int aW, int aE, ThingList tl) {
        super(aName, desc, tl);
        this.n = aN;
        this.s = aS;
        this.w = aW;
        this.e = aE;
        this.isAccessable = true;
    }

    public int getN() {
        return this.n;
    }

    public int getS() {
        return this.s;
    }

    public int getE() {
        return this.e;
    }

    public int getW() {
        return this.w;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setS(int s) {
        this.s = s;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setE(int e) {
        this.e = e;
    }
    public void setAccessable(boolean accessable) {
        this.isAccessable = accessable;
    }
    public boolean getAccessable() {
        return this.isAccessable;
    }
}