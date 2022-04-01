package rs.raf.projekat1.vuk_vukovic_rn9420.model;

public class Ticket {

    private int id;
    private String title;
    private String description;
    private int estimation;
    private int loggedTime;
    private String type;
    private String priority;

    public Ticket(String title, String description, int estimation, String type, String priority) {
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

    public String getType() {
        return type;
    }

    public String getPriority() {
        return priority;
    }
}
