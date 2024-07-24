package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

//import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;

    public SocialMediaController(){
        this.messageService = new MessageService();
        this.accountService = new AccountService();
    }
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

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postRegistrationHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);

        Account addedAccount = accountService.addAccount(account);

        if ( addedAccount == null ) {
            context.status(400);
        } else {
            context.json(mapper.writeValueAsString(addedAccount));
        }
    }

    private void postLoginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);

        Account addedAccount = accountService.loginAccount(account);

        if (addedAccount == null) {
            context.status(401); // to explicitly return a 401 status for illegal login
        } else {
            context.json(mapper.writeValueAsString(addedAccount));
        }
    }

    private void postMessagesHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);

        Message addedMessage = messageService.addMessage(message);

        if (addedMessage == null) {
            context.status(400); // to explicitly return a 400 status for unsuccessful registration
        } else {
            context.json(mapper.writeValueAsString(addedMessage));
        }
    }

    private void getMessagesHandler(Context context) {
        context.json(messageService.getAllMessages());
    }

    private void getMessagesUsingIdHandler(Context context) {
        String message_id = context.pathParam("message_id");
        Message returnedMessage = messageService.getMessageById(Integer.parseInt(message_id));
        
        if (returnedMessage == null) {
            context.status(200); // to explicitly return a 200 status as a default status
        } else {
            context.json(returnedMessage);
        }
    }

    private void deleteMessagesUsingIdHandler(Context context) {
        String message_id = context.pathParam("message_id");
        Message returnedMessage = messageService.deleteMessageById(Integer.parseInt(message_id));

        if (returnedMessage == null) {
            context.status(200); // to explicitly return a 200 status if a message is not found
        } else {
            context.json(returnedMessage);
        }
    }

    private void patchMessagesUsingIdHandler(Context context) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(); //to map Context
        Message message = mapper.readValue(context.body(), Message.class); //to get the message object

        String message_id = context.pathParam("message_id");
        Message updatedMessage = messageService.updateMessage(message, Integer.parseInt(message_id));

        if (updatedMessage == null) {
            context.status(400); // to explicitly return a 400 status if update was not successful
        } else {
            context.json(updatedMessage);
        }
    }

    private void getMessagesOfUser(Context context) {
        String account_id = context.pathParam("account_id");

        context.json(messageService.getMessageByUser(Integer.parseInt(account_id)));
    }
}