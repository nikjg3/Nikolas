package app.controllers.weight;

import app.models.Weight;
import app.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class WeightNewController implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("weight", new Weight("",""));
        ctx.render("/views/weight/new.html", model);
    }
}
