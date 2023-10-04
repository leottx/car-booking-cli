package user;

import java.util.UUID;

public class UserDAO {
    private static final User[] users;

    static {
        users = new User[] {new User(UUID.fromString("12345"), "Leonardo"), new User(UUID.fromString("54321"), "Ana"), new User(UUID.fromString("874965"), "Camila")};
    }

    public User[] getAllUsers() {
        return users;
    }
}
