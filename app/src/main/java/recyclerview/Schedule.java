package recyclerview;

/**
 * Created by Mister_Brown on 12/27/2016.
 */

public class Schedule {
    private int id;
    private String time;
    private String subject;
    private String room;
    private String professor;
    private String proImage;

    public String type;

    public Schedule(String type) {
        this.type = type;
    }

    public Schedule(int id, String time, String subject, String room, String professor, String proImage) {
        this.id = id;
        this.time = time;
        this.subject = subject;
        this.room = room;
        this.professor = professor;
        this.proImage = proImage;
    }

    public Schedule(String time, String subject, String room, String professor) {

        this.time = time;
        this.subject = subject;
        this.room = room;
        this.professor = professor;
        this.proImage = "professor";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }
}
