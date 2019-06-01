package in.nimbo;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages user's storage size.
 */
public class UserStorageRepository {

    // Do not change this line!
    private Map<String, Integer> userStorageMap = new HashMap<>();

    /**
     * Increase storage of given user by given size. If given user does not exists, it creates the user.
     */
    public void increaseStorageOfUser(String user, int size) {
        int newAmount = userStorageMap.containsKey(user) ? userStorageMap.get(user) + size : size;
        userStorageMap.put(user, newAmount);
    }

    /**
     * Check if user has at least given storage. If user does not exits false is returned.
     */
    public boolean hasStorage(String user, int size) {
        return userStorageMap.containsKey(user) && userStorageMap.get(user) >= size;
    }

    /**
     * Decrease user storage by given size. If user does not have enough storage, should be deleted.
     */
    public void decreaseStorageOfUser(String user, int size) {
        if (userStorageMap.containsKey(user)) {
            int newAmount = userStorageMap.get(user) - size;
            if (newAmount >0)
                userStorageMap.put(user, newAmount);
            else
                userStorageMap.remove(user);
        }
    }
}
