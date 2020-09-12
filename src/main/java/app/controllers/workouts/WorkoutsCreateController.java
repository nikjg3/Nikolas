package app.controllers.workouts;

import app.dao.WorkoutDao;
import app.models.Workout;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class WorkoutsCreateController implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Workout workout = new Workout(
                ctx.formParam("name"),
                ctx.formParam("description")
        );



        workout = WorkoutDao.INSTANCE.create(workout);
        ctx.redirect("/workouts/" + workout.getId());
    }
}
