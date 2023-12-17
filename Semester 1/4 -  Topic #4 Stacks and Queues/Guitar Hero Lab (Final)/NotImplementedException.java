public class NotImplementedException extends RuntimeException {
    /**
     * Constructs a NotImplementedException with a default message.
     * 
     * Example:
     * throw new NotImplementedException();
     */
    public NotImplementedException() {
        super("This method is not yet implemented.");
    }
}