package app.controllers.weight;

import app.dao.WeightDao;
import app.models.Weight;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author Yuqing Wu, 2020. email: s3808626@student.rmit.edu.au
 */

public class WeightUpdateController implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Weight weight = WeightDao.INSTANCE.get(ctx.pathParam("id", Long.class).get());
        weight.setName(ctx.formParam("name"));
        weight.setwtime(ctx.formParam("date"));
        WeightDao.INSTANCE.update(weight);
        ctx.redirect("/weights/" + weight.getId());
    }
}
