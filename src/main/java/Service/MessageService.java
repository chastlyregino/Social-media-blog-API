package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;


import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO){
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages() {
        return this.messageDAO.getAllMessages();
    }

    public Message addMessage(Message message) {
        if (message.getMessage_text() != ""
            && message.getMessage_text().length() < 256
            && this.accountDAO.getAccountById(message.getPosted_by()) != null) {
                return this.messageDAO.createNewMessage(message);
        }
        return null;
    }

    public Message getMessageById(int id) {
        return this.messageDAO.getMessageById(id);
    }

    public Message deleteMessageById(int id) {
        return this.messageDAO.deleteMessageById(id);
    }

    public Message updateMessage(Message message) {
        if (this.messageDAO.getMessageById(message.getMessage_id()) != null
            && message.getMessage_text() != ""
            && message.getMessage_text().length() < 256) {
                return this.messageDAO.updateMessageById(message);
        }
        return null;
    }

    public List<Message> getMessageByUser(int posted_by) {
        return this.messageDAO.getAllMessagesOfUser(posted_by);
    }
}