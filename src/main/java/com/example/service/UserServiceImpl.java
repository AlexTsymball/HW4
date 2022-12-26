package com.example.service;

import com.example.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    private static final List<UserDto> allUsers = new ArrayList<>();

    static {
        System.out.println("initial");
        allUsers.add(new UserDto("Max", "Max2000", "qwerty"));
        allUsers.add(new UserDto("Masha", "Masha123", "123"));
        allUsers.add(new UserDto("Andrew", "A100", "qwerty123"));
        allUsers.add(new UserDto("John", "J", "test"));
    }


    private static Map<String, List<String>> loginSessionId = new ConcurrentHashMap<>();

    @Override
    public boolean containsUser(UserDto user) {
        return allUsers.stream()
                .anyMatch(a -> a.getLogin().equals(user.getLogin())
                        && a.getPassword().equals(user.getPassword()));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return allUsers;
    }

    @Override
    public void setSessionId(String login, String sessionId) {
        if (loginSessionId.get(login) == null) {
            loginSessionId.put(login, new ArrayList<>() {
                {
                    add(sessionId);
                }
            });
        } else {
            List<String> listId = loginSessionId.get(login);
            listId.add(sessionId);
        }
    }

    @Override
    public void removeSessionId(String login, String sessionId) {
        List<String> listId = loginSessionId.get(login);
        if (listId.size() == 1) {
            loginSessionId.remove(login);
        } else {
            listId.remove(sessionId);
        }
    }

    @Override
    public boolean containsLoginSessionId(String user, String sessionId) {
        List<String> listId = new ArrayList<>();
        if (loginSessionId.get(user) != null) {
            listId = loginSessionId.get(user);
        }
        return listId.contains(sessionId);
    }
}
