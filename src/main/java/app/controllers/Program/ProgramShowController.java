package app.controllers.Program;

import app.dao.ProgramDao;
import app.models.Program;
import app.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
/**
 * @author Xinyu Chen, 2020. email: s3798356@student.rmit.edu.au
 */
/**
 * Controller to handle the display of one program.
 */
public class ProgramShowController implements Handler {
    private static final String TEMPLATE = "/views/programs/show.html";

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Long id = ctx.pathParam("id", Long.class).get();

        Program program = ProgramDao.INSTANCE.get(id);
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("program", program);
        ctx.render(TEMPLATE,model);
    }
}