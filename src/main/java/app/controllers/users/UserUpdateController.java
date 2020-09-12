package app.controllers.users;

import app.dao.ProgramDao;
import app.dao.UserDao;
import app.models.Program;
import app.models.User;
import app.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class UserUpdateController implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        User user = UserDao.INSTANCE.getByEmail(ctx.pathParam("email", String.class).get());
        user.setName(ctx.formParam("name"));
        user.setEmail(ctx.formParam("email"));
        UserDao.INSTANCE.update(user);
        ctx.redirect("/users/" + user.getId());
    }

}
