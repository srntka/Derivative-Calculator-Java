package bsu.controller;

import bsu.service.UserService;

public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    public boolean login(String u, String p) {
        return userService.authenticate(u, p);
    }
    public boolean register(String u, String p) {
        return userService.register(u, p);
    }
    public boolean userExists(String u) {
        return userService.userExists(u);
    }
}