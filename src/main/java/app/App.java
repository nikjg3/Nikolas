package app;

import app.auth.AccessManager;
import app.auth.LoginController;
import app.controllers.users.UsersMeController;
import app.controllers.welcome.WelcomeController;
import app.controllers.workouts.WorkoutsCreateController;
import app.controllers.workouts.WorkoutsListController;
import app.controllers.workouts.WorkoutsNewController;
import app.controllers.workouts.WorkoutsShowController;
import app.models.Role;
import app.utils.Views;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;

import static io.javalin.core.security.SecurityUtil.roles;

/**
 * Main Application Class.
 * <p>
 * Running this class as regular java application will start the HTTP Server and configure our web application.
 *
 * @author Sebastian Rodriguez, 2020. email: sebastian.rodriguez@rmit.edu.au
 */
public class App {


    public static void main(String[] args) {

        //Create our HTTP server and listen in port 7000
        Javalin app = Javalin.create(config -> {
            config.enableDevLogging();
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));
            config.addStaticFiles("public/");
            config.accessManager(new AccessManager());
        }).start(7000);

        //Register the engine to process html views
        JavalinRenderer.register(JavalinThymeleaf.INSTANCE, ".html");

        //Configure Web Routes
        configureRoutes(app);

    }

    public static void configureRoutes(Javalin app) {

        app.get(WelcomeController.URL, new WelcomeController());

        app.get("/workouts", new WorkoutsListController());
        app.get("/workouts/new", new WorkoutsNewController(), roles(Role.ADMIN)); //Secured for ADMINs only
        app.post("/workouts", new WorkoutsCreateController(), roles(Role.ADMIN)); //Secured for ADMINs only
        app.get("/workouts/:id", new WorkoutsShowController());

        //Auth
        app.get("/login", ctx -> {
            ctx.render("/views/auth/login.html", Views.baseModel(ctx));
        });

        app.post("auth", new LoginController());
        app.get("/logout", ctx -> {
            AccessManager.logout(ctx);
            ctx.redirect("/");
        });

        app.get("/users/me", new UsersMeController(),roles(Role.REGISTERED,Role.ADMIN));
    }


}
