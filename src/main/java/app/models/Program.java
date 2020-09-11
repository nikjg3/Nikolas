package app.models;

/**
 * @author Xinyu Chen, 2020. email: s3798356@student.rmit.edu.au
 */
public class Program {
    private String name = null;
    private Long id = null;
    private String info = null;
    private String date = null;

    public Program() {

    }

    public Program(String name, String data, String info) {
        this.name = name;
        this.date = data;
        this.info = info;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getInfo() { return info; }

    public void setInfo(String info) { this.info = info; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info=" + info +
                ", date='" + date + '\'' +
                '}';
    }

}
