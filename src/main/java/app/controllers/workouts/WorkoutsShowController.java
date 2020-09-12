package app.controllers.workouts;

import app.dao.WorkoutDao;
import app.models.Workout;
import app.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Controller to handle the display of one workout.
 */
public class WorkoutsShowController implements Handler {
    private static final String TEMPLATE = "/views/workouts/show.html";

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Long id = ctx.pathParam("id", Long.class).get();

        Workout workout = WorkoutDao.INSTANCE.get(id);
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("workout", workout);
        ctx.render(TEMPLATE,model);
    }
}
