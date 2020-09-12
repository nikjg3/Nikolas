package app.controllers.workouts;

import app.dao.WorkoutDao;
import app.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Controller to handle requests to list all workouts.
 */
public class WorkoutsListController implements Handler {
    private static final String TEMPLATE = "/views/workouts/list.html";

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("workouts", WorkoutDao.INSTANCE.getAll());
        ctx.render(TEMPLATE,model);
    }
}
