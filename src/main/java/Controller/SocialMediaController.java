package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegistrationHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessagesHandler);
        app.get("/messages", this::getMessagesHandler);
        app.get("/messages/{message_id}", this::getMessagesUsingIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessagesUsingIdHandler);
        app.patch("/messages/{message_id}", this::patchMessagesUsingIdHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesOfUser);
        app.start(8080);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postRegistrationHandler(Context context) {
        context.json("sample text");
    }

    private void postLoginHandler(Context context) {
        context.json("sample text");
    }

    private void postMessagesHandler(Context context) {
        context.json("sample text");
    }

    private void getMessagesHandler(Context context) {
        context.json("sample text");
    }

    private void getMessagesUsingIdHandler(Context context) {
        context.json("sample text");
    }

    private void deleteMessagesUsingIdHandler(Context context) {
        context.json("sample text");
    }

    private void patchMessagesUsingIdHandler(Context context) {
        context.json("sample text");
    }

    private void getMessagesOfUser(Context context) {
        context.json("sample text");
    }
}