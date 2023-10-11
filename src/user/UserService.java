package user;

import java.util.UUID;

public class UserService {
    private static final UserDAO userDAO = new UserDAO();

    public User getUserById(UUID id) {
        for (User user:
             getAllUsers()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User[] getAllUsers() {
        return userDAO.getAllUsers();
    }
}
