package app.controllers.workouts;

import app.dao.WorkoutDao;
import app.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ProgramsEditController implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("workout", WorkoutDao.INSTANCE.get(ctx.pathParam("id", Long.class).get()));
        ctx.render("/views/programs/edit.html", model);
    }
}
