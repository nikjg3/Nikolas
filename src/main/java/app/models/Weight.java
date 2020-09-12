package app.models;

import java.util.Date;

public class Weight {
    private Long id = null;
    private String name = null;
    private String wtime = null;
    //weight change diagram

    public Weight(String name, String wtime) {
        this.name = name;
        this.wtime = wtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getwtime() {
        return wtime;
    }

    public void setwtime(String wtime) {
        this.wtime = wtime;
    }
}
