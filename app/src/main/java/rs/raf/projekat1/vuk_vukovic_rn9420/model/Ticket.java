package rs.raf.projekat1.vuk_vukovic_rn9420.model;


public class Ticket{

    private int id;
    private String title;
    private String description;
    private int estimation;
    private int loggedTime;
    private Type type;
    private Priority priority;

    public Ticket(String title, String description, int estimation, Type type, Priority priority) {
        this.title = title;
        this.description = description;
        this.estimation = estimation;
        this.loggedTime = 0;
        this.type = type;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getEstimation() {
        return estimation;
    }

    public int getLoggedTime() {
        return loggedTime;
    }

    public Type getType() {
        return type;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public void setLoggedTime(int loggedTime) {
        this.loggedTime = loggedTime;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
