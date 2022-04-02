package rs.raf.projekat1.vuk_vukovic_rn9420.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ticket implements Parcelable {

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

    public Ticket(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        estimation = in.readInt();
        loggedTime = in.readInt();
        type = in.readString();
        priority = in.readString();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

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

    public void setType(String type) {
        this.type = type;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(type);
        parcel.writeString(priority);
        parcel.writeInt(id);
        parcel.writeInt(estimation);
        parcel.writeInt(loggedTime);
    }
}
