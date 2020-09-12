package app.controllers.weight;

import app.dao.WeightDao;
import app.models.Weight;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

/**
 * @author Yuqing Wu, 2020. email: s3808626@student.rmit.edu.au
 */

public class WeightCreatController implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Weight weight = new Weight(
                ctx.formParam("name"),
                ctx.formParam("wtime")
        );

        weight = WeightDao.INSTANCE.create(weight);
        ctx.redirect("/weights/" + weight.getId());
    }




}
