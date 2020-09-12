package app.controllers.Program;

import app.dao.ProgramDao;
import app.models.Program;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
/**
 * @author Xinyu Chen, 2020. email: s3798356@student.rmit.edu.au
 */
public class ProgramUpdateController implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Program program = ProgramDao.INSTANCE.get(ctx.pathParam("id", Long.class).get());
        program.setName(ctx.formParam("name"));
        program.setDescription(ctx.formParam("description"));
        ProgramDao.INSTANCE.update(program);
        ctx.redirect("/programs/" + program.getId());
    }
}
