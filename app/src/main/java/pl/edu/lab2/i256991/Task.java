package pl.edu.lab2.i256991;

//import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Task  implements Serializable {
    String title;
    String type;
    String description;
    String date;
    String status;
   // int id;

    Task(String title, String type, String desc, String date){
        this.title=title;
        this.type=type;
        description=desc;
        this.date=date;
     //   this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /*
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(type);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeString(status);

    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    }; */
    //public int getId() { return id; }
}
