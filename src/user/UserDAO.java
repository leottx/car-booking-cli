package user;

import java.util.UUID;

public class UserDAO {
    private static final User[] users;

    static {
        users = new User[] {new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "Leonardo"), new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10ab3"), "Ana")};
    }

    public User[] getAllUsers() {
        return users;
    }
}
