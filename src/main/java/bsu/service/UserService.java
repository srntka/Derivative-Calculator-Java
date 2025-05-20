package bsu.service;

import bsu.dao.UserDao;
import bsu.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private final UserDao dao;
    public UserService(UserDao dao) { this.dao = dao; }

    public boolean userExists(String username) {
        return dao.findByUsername(username) != null;
    }

    public boolean register(String username, String password) {
        if(userExists(username)) return false;
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        dao.save(new User(username, hash));
        return true;
    }

    public boolean authenticate(String username, String password) {
        User user = dao.findByUsername(username);
        if(user == null) return false;
        return BCrypt.checkpw(password, user.getPasswordHash());
    }
}