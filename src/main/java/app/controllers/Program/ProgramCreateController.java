package app.controllers.Program;

import app.dao.ProgramDao;
import app.dao.WorkoutDao;
import app.models.Program;
import app.models.Workout;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

/**
 * @author Xinyu Chen, 2020. email: s3798356@student.rmit.edu.au
 */
public class ProgramCreateController {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Program program = new Program(
                ctx.formParam("name"),
                ctx.formParam("description")
                ctx.formParam("date")
        );

        program = ProgramDao.INSTANCE.create(program);
        ctx.redirect("/program/" + program.getId());
    }
}
