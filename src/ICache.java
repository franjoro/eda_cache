import structure.TreeMap;
import Exception.KeyNotFoundException;
import Exception.DuplicatedKeyException;

public class ICache implements ICacheInterface {

    private TreeMap<String, String> cache;

    public ICache() {
        cache = new TreeMap<>();
    }

    public String[] getAll() {
        if (cache.isEmpty()) {
            return null;
        }
        Object[] keys = cache.keys();
        String[] output = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            output[i] = (String) keys[i];
        }

        return output;

    }

    public String get(String key) throws KeyNotFoundException {
        if (!cache.contains(key)) {
            throw new KeyNotFoundException("Key not found");
        }
        return cache.get(key);
    }

    public String getOrDefault(String key, String defaultValue) {
        try {
            if (!cache.contains(key)) {
                return defaultValue;
            }
            return cache.get(key);
        } catch (KeyNotFoundException e) {
            return defaultValue;
        }

    }

    public boolean exists(String key) {
        return cache.contains(key);
    }


    public void put(String key, String value) {
        cache.put(key, value);
    }

    public void addNew(String key, String value) throws DuplicatedKeyException{
        if (cache.contains(key)) {
            throw new DuplicatedKeyException("Key already exists");
        }
        cache.put(key, value);
    }

    public void remove(String key) throws KeyNotFoundException {
        if (!cache.contains(key)) {
            throw new KeyNotFoundException("Key not found");
        }
        cache.remove(key);
    }

    public int size() {
        return cache.size();
    }


}