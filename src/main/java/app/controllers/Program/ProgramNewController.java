package app.controllers.Program;

import app.models.Program;
import app.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Controller to render the New form. The Model creation (POST action of form) is handled by {@link ProgramCreateController}
 */
public class ProgramNewController implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("program", new Program("",""));
        ctx.render("/views/Programs/new.html", model);
    }
}
