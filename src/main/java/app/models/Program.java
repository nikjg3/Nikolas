package app.models;

/**
 * @author Xinyu Chen, 2020. email: s3798356@student.rmit.edu.au
 */
public class Program {
    private String name = null;
    private Long id = null;
    private String description = null;
    private String date = null;

    public Program() {

    }

    public Program(String name, String data, String description) {
        this.name = name;
        this.date = data;
        this.description = description;
    }
    public Program(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String info) { this.description = info; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", date='" + date + '\'' +
                '}';
    }

}
