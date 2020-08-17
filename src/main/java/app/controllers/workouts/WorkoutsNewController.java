package app.controllers.workouts;

import app.models.Workout;
import app.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Controller to render the New form. The Model creation (POST action of form) is handled by {@link WorkoutsCreateController}
 */
public class WorkoutsNewController implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("workout", new Workout("",""));
        ctx.render("/views/workouts/new.html", model);
    }
}
