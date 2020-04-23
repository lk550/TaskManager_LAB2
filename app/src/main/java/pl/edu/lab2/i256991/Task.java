package pl.edu.lab2.i256991;

public class Task {
    String title;
    String type;
    String description;
    String date;
    String status;
    int id;

    Task(String title, String type, String desc, String date, int id){
        this.title=title;
        this.type=type;
        description=desc;
        this.date=date;
        this.id=id;
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

    public int getId() { return id; }
}
