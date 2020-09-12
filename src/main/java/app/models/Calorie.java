package app.models;

public class Calorie {

    private Long id = null;
    private String food = null;
    private int calories = 0;

    public Calorie(String food, int calories) {
        this.food = food;
        this.calories = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getCalories() { return calories; }

    public void setCalories(int calories) {
        this.calories= 0;
    }
}


