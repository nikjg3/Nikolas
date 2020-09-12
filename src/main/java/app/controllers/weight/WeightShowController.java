package app.controllers.weight;

import app.dao.WeightDao;
import app.models.Weight;
import app.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author Yuqing Wu, 2020. email: s3808626@student.rmit.edu.au
 */

public class WeightShowController implements Handler {
    private static final String TEMPLATE = "/views/weights/show.html";

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Long id = ctx.pathParam("id", Long.class).get();

        Weight weight = WeightDao.INSTANCE.get(id);
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("weight", weight);
        ctx.render(TEMPLATE,model);
    }

}
