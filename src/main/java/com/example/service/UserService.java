package com.example.service;

import com.example.dto.UserDto;

import java.util.List;


public interface UserService {

    boolean containsUser(UserDto user);

    List<UserDto> getAllUsers();

    void setSessionId(String login, String sessionId);

    void removeSessionId(String login, String sessionId);

    boolean containsLoginSessionId(String user, String sessionId);
}
