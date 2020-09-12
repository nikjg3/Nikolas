package app.controllers.Program;

import app.dao.ProgramDao;
import app.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author Xinyu Chen, 2020. email: s3798356@student.rmit.edu.au
 */
/**
 * Controller to handle requests to list all programs.
 */
public class ProgramListController implements Handler {
    private static final String TEMPLATE = "/views/programs/list.html";

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("program", ProgramDao.INSTANCE.getAll());
        ctx.render(TEMPLATE, model);
    }
}

