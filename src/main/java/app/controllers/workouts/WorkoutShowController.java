package app.controllers.workouts;

import app.models.Workout;
import app.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class WorkoutShowController implements Handler {
    private static final String TEMPLATE = "/views/workouts/show.html";

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        //Long id = context.pathParam("id", Long.class).get();

        Workout workout = new Workout("Workout one","#This is the Description\n\n second line.");
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("workout", workout);
        ctx.render(TEMPLATE,model);
    }
}
