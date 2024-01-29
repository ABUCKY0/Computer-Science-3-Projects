public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;
    /*
     * NOEXIT has an intenger value which is convienient when initializing the
     * 'EXIT' fields in Room objects. All valid exits indicate a Room number
     * whereas NOEXIT is -1;
     */
    public static final int NOEXIT = -1;
    public static final int NOTALLOWED = -2;
    public static final int BLOCKED = -3;
}
