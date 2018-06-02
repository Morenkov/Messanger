package ru.dev.messanger.BLL;

import com.google.gson.Gson;
import ru.dev.messanger.dll.Database;
import ru.dev.messanger.entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BLL {

    public static final BLL INSTANCE = new BLL();   // SINGLETONE

    private HashMap<Object, Token> userToken = new HashMap<>(); //Key(Object) is ID of User

    public HashMap<Object, Token> getUserToken() {
        return userToken;
    }

    public void addTokenToUser(TUser user, Token token) {
        this.userToken.put(user.getId(), token);
    }

    public Boolean removeToken(Token token) {
        if (userToken.containsValue(token)) {
            for (Object key : userToken.keySet()) {
                if (userToken.get(key) == token) {
                    userToken.remove(key);
                    return true; //Tokens are unique, no need to continue iteration
                }
            }
        }
        return false;
    }

    public void removeUsersToken(TUser user) {
        this.userToken.remove(user.getId());
    }

    public String authorization(String login, String password) {

        UserDTO user = Database.INSTANCE.authorization(login, password);
        Object o = BLL.INSTANCE; //TODO: remove in prod

        TUser tuser = new TUser(user);
        //return new Gson().toJson(user); //old code
        String str = new Gson().toJson(tuser); //TODO: remove in prod
        return new Gson().toJson(tuser);
    }

    public String emailAlreadyExists(String email) {
        return new Gson().toJson(Database.INSTANCE.emailAlreadyExists(email));
    }

    public String loginAlreadyExists(String login) {
        return new Gson().toJson(Database.INSTANCE.loginAlreadyExists(login));
    }

    public String setUser(
            String email,
            String login,
            String password,
            String first_name,
            String last_name,
            String sex,
            String status,
            String avatar) {
        NewUserDTO user = new NewUserDTO();
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setSex(sex);
        user.setStatus(status);
        user.setAvatar_url(avatar);
        return new Gson().toJson(Database.INSTANCE.setUser(user));
    }

    public String getUser(int id) {
        return new Gson().toJson(Database.INSTANCE.getUser(id));
    }

    public String updateUser(
            int id,
            String password,
            String first_name,
            String last_name,
            String sex,
            String status,
            String avatar
    ) {
        NewUserDTO user = new NewUserDTO();
        user.setId(id);
        user.setPassword(password);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setSex(sex);
        user.setStatus(status);
        user.setAvatar_url(avatar);

        return new Gson().toJson(Database.INSTANCE.updateUser(user, id));
    }

    public String deleteUser(
            int id
    ) {
        NewUserDTO user = new NewUserDTO();
        user.setId(id);
        return new Gson().toJson(Database.INSTANCE.deleteUser(user));
    }

    public String searchUsers(
            String searchQuery
    ) {
        return new Gson().toJson(Database.INSTANCE.searchUsers(searchQuery));
    }

    public String getConversations(
            int id
    ) {
        return new Gson().toJson(Database.INSTANCE.getConversations(id));
    }

    public String getDialogs(
            int id
    ) {
        return new Gson().toJson(Database.INSTANCE.getDialogs(id));
    }

    public String setConversation(
            int admin_id,
            String title,
            String users
    ) {
        ConversationDTO conversation = new ConversationDTO();
        conversation.setAdmin_id(admin_id);
        conversation.setTitle(title);
        String[] userNames = users.split(",");
        List<Integer> userIds = new ArrayList<Integer>(userNames.length);
        //TODO: validation
        try {
            for (int i = 0; i < userNames.length; i++) {
                userIds.add(Integer.parseInt(userNames[i]));
            }
        } catch (Exception e) {
            System.out.println("/setConversation parse users error");
        }
        conversation.setParticipants_id(userIds);

        return new Gson().toJson(Database.INSTANCE.setConversation(conversation));
    }

    public String setMessage(
            int from_id,
            int conversation_id,
            String message,
            String attachment_url
    ) {
        SentMessageDTO messageDTO = new SentMessageDTO();
        messageDTO.setFrom_id(from_id);
        messageDTO.setConversation_id(conversation_id);
        messageDTO.setMessage(message);
        messageDTO.setAttachment_url(attachment_url);

        return new Gson().toJson(Database.INSTANCE.setMessage(messageDTO));
    }

    public String getMessages(
            Integer conversation_id,
            Integer id,
            Integer message_id
    ) {
        return new Gson().toJson(Database.INSTANCE.getMessages(conversation_id, id, message_id));
    }

    public String searchInConversation(
            String searchQuery,
            Integer conversation_id
    ) {
        return new Gson().toJson(Database.INSTANCE.searchInConversation(searchQuery, conversation_id));
    }

    public String searchConversations(
            String searchQuery
    ) {
        return new Gson().toJson(Database.INSTANCE.searchConversations(searchQuery));
    }

    public String joinTheConversation(
            Integer conversation_id,
            Integer id
    ) {
        return new Gson().toJson(Database.INSTANCE.joinTheConversation(conversation_id, id));
    }

    public String leaveTheConversation(
            Integer conversation_id,
            Integer id
    ) {
        return new Gson().toJson(Database.INSTANCE.leaveTheConversation(conversation_id, id));
    }

    public String setUnreadMessages(
            Integer conversation_id,
            Integer id,
            Integer count
    ) {
        return new Gson().toJson(Database.INSTANCE.setUnreadMessages(conversation_id, id, count));
    }

    public String deleteConversation(
            Integer conversation_id,
            Integer id
    ) {
        return new Gson().toJson(Database.INSTANCE.deleteConversation(conversation_id, id));
    }

    public String setStatusOnline(Integer id, Integer status) {
        return new Gson().toJson(Database.INSTANCE.setStatusOnline(id, status));
    }
}