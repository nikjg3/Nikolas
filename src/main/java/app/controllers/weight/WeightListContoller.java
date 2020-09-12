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

public class WeightListContoller implements Handler {
    private static final String TEMPLATE = "/views/weights/list.html";

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("weights", WeightDao.INSTANCE.getAll());
        ctx.render(TEMPLATE,model);
    }
}
