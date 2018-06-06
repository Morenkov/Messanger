package ru.dev.messanger.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dev.messanger.BLL.BLL;
import ru.dev.messanger.dll.Database;
import ru.dev.messanger.entities.ConversationDTO;
import ru.dev.messanger.entities.NewUserDTO;
import ru.dev.messanger.entities.SentMessageDTO;
import ru.dev.messanger.entities.UserDTO;

import java.util.*;

@RestController
public class RESTController {

    @RequestMapping(value = "/authorization", method = RequestMethod.GET, produces = "application/json")
    public String authorization(@RequestParam String login, @RequestParam String password) {
        return BLL.INSTANCE.authorization(login, password);
    }

    @RequestMapping(value = "/emailAlreadyExists", method = RequestMethod.GET, produces = "application/json")
    public String emailAlreadyExists(@RequestParam String email) {
        return BLL.INSTANCE.emailAlreadyExists(email);
    }

    @RequestMapping(value = "/loginAlreadyExists", method = RequestMethod.GET, produces = "application/json")
    public String loginAlreadyExists(@RequestParam String login) {
        return BLL.INSTANCE.loginAlreadyExists(login);
    }

    @RequestMapping(value = "/setUser", method = RequestMethod.GET, produces = "application/json")
    public String setUser(
            @RequestParam String email,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam String sex,
            @RequestParam String status,
            @RequestParam String avatar) {
        return BLL.INSTANCE.setUser(email, login, password, first_name, last_name, sex, status, avatar);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.POST, produces = "application/json")
    public String getUser(@RequestParam int id) {
        return BLL.INSTANCE.getUser(id);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET, produces = "application/json")
    public String updateUser(
            @RequestParam int id,
            @RequestParam String password,
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam String sex,
            @RequestParam String status,
            @RequestParam String avatar
    ) {
        return BLL.INSTANCE.updateUser(id, password, first_name, last_name, sex, status, avatar);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET, produces = "application/json")
    public String deleteUser(@RequestParam int id) {
        return BLL.INSTANCE.deleteUser(id);
    }

    @RequestMapping(value = "/searchUsers", method = RequestMethod.POST, produces = "application/json")
    public String searchUsers(@RequestParam String searchQuery) {
        return BLL.INSTANCE.searchUsers(searchQuery);
    }

    @RequestMapping(value = "/getConversations", method = RequestMethod.POST, produces = "application/json")
    public String getConversations(@RequestParam int id) {
        return BLL.INSTANCE.getConversations(id);
    }

    @RequestMapping(value = "/getDialogs", method = RequestMethod.POST, produces = "application/json")
    public String getDialogs(@RequestParam int id) {
        return BLL.INSTANCE.getDialogs(id);
    }

    @RequestMapping(value = "/setConversation", method = RequestMethod.POST, produces = "application/json")
    public String setConversation(
            @RequestParam int admin_id,
            @RequestParam String title,
            @RequestParam String users
    ) {
        return BLL.INSTANCE.setConversation(admin_id, title, users);
    }

    @RequestMapping(value = "/setMessage", method = RequestMethod.POST, produces = "application/json")
    public String setMessage(
            @RequestParam int from_id,
            @RequestParam int conversation_id,
            @RequestParam String message,
            @RequestParam String attachment_url
    ) {
        return BLL.INSTANCE.setMessage(from_id, conversation_id, message, attachment_url);
    }

    @RequestMapping(value = "/getMessages", method = RequestMethod.POST, produces = "application/json")
    public String getMessages(
            @RequestParam Integer conversation_id,
            @RequestParam Integer id,
            @RequestParam Integer message_id
    ) {
        return BLL.INSTANCE.getMessages(conversation_id, id, message_id);
    }

    @RequestMapping(value = "/searchInConversation", method = RequestMethod.GET, produces = "application/json")
    public String searchInConversation(
            @RequestParam String searchQuery,
            @RequestParam Integer conversation_id
    ) {
        return BLL.INSTANCE.searchInConversation(searchQuery, conversation_id);
    }

    @RequestMapping(value = "/searchConversations", method = RequestMethod.POST, produces = "application/json")
    public String searchConversations(
            @RequestParam String searchQuery
    ) {
        return BLL.INSTANCE.searchConversations(searchQuery);
    }

    @RequestMapping(value = "/joinTheConversation", method = RequestMethod.POST, produces = "application/json")
    public String joinTheConversation(
            @RequestParam Integer conversation_id,
            @RequestParam Integer id
    ) {
        return BLL.INSTANCE.joinTheConversation(conversation_id, id);
    }

    @RequestMapping(value = "/leaveTheConversation", method = RequestMethod.GET, produces = "application/json")
    public String leaveTheConversation(
            @RequestParam Integer conversation_id,
            @RequestParam Integer id
    ) {
        return BLL.INSTANCE.leaveTheConversation(conversation_id, id);
    }

    @RequestMapping(value = "/setUnreadMessages", method = RequestMethod.POST, produces = "application/json")
    public String setUnreadMessages(
            @RequestParam Integer conversation_id,
            @RequestParam Integer id,
            @RequestParam Integer count
    ) {
        return BLL.INSTANCE.setUnreadMessages(conversation_id, id, count);
    }

    @RequestMapping(value = "/deleteConversation", method = RequestMethod.GET, produces = "application/json")
    public String deleteConversation(
            @RequestParam Integer conversation_id,
            @RequestParam Integer id
    ) {
        return BLL.INSTANCE.deleteConversation(conversation_id, id);
    }

    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST, produces = "application/json")
    public String setStatusOnline(
            @RequestParam Integer id,
            @RequestParam Integer status
    ) {
        return BLL.INSTANCE.setStatusOnline(id, status);
    }
}
