package pl.edu.lab2.i256991;



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
        status="not done";

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

}
