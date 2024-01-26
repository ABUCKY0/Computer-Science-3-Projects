public class Thing {
    private String name; 
    private String description;

    /**A *thing*
     * 
     * @param name The name of the Thing
     * @param description The description of the thing
     */
    public Thing(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
}