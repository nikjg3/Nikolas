package app.controllers.workouts;

import app.dao.WorkoutDao;
import app.models.Workout;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class WorkoutsUpdateController implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Workout workout = WorkoutDao.INSTANCE.get(ctx.pathParam("id", Long.class).get());
        workout.setName(ctx.formParam("name"));
        workout.setDescription(ctx.formParam("description"));
        WorkoutDao.INSTANCE.update(workout);
        ctx.redirect("/workouts/" + workout.getId());
    }
}
