package user;

import java.io.FileNotFoundException;
import java.util.UUID;

public class UserService {
    private static final UserArrayDataAccessService userArrayData = new UserArrayDataAccessService();

    public User getUserById(UUID id) throws FileNotFoundException {
        for (User user:
             getAllUsers()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User[] getAllUsers() {
        return userArrayData.getAllUsers();
    }
}
