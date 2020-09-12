package app.controllers.workouts;

import app.dao.ProgramDao;
import app.models.Program;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class ProgramsCreateController implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Program program = new Program(
                ctx.formParam("name"),
                ctx.formParam("description"),
                ctx.formParam("date")
        );



        program = ProgramDao.INSTANCE.create(program);
        ctx.redirect("/programs/" + program.getId());
    }
}
