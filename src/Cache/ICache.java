package Cache;

import Exception.DuplicatedKeyException;
import Exception.KeyNotFoundException;
import Structure.TreeMap;

import java.io.IOException;

public class ICache implements ICacheInterface {
    private String dirname = "cache";
    private TreeMap<String, String> cache;
    private FileHandler fileHandler = new FileHandler();

    /**
     * Constructor of the class ICache
     *
     * @throws IOException
     */
    public ICache() {
        if (!fileHandler.existFile(this.dirname)) {
            fileHandler.createFolder(this.dirname);
            cache = new TreeMap<>();
        } else {
            try {
                this.cache = new TreeMap<>();
                String[] keys = fileHandler.readFolder(this.dirname);
                for (String key : keys) {
                    String value = fileHandler.readFile(getFileName(key));
                    this.cache.put(key, value);
                }
            } catch (IOException e) {
                System.out.println("Exception occurred: " + e);
            }
        }

    }

    /**
     * Returns all the keys of the cache
     *
     * @return String[] with all the keys
     */
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

    /**
     * Returns the value of the key
     *
     * @param key
     * @return
     * @throws KeyNotFoundException
     */
    public String get(String key) throws KeyNotFoundException {
        if (!cache.contains(key)) {
            throw new KeyNotFoundException("Key not found");
        }
        return cache.get(key);
    }

    /**
     * Returns the value of the key or the default value if the key is not found
     *
     * @param key
     * @param defaultValue
     * @return String
     */
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

    /**
     * Returns the file name of the key in the cache
     *
     * @param key
     * @return boolean
     */
    public boolean exists(String key) {
        boolean cacheContains = cache.contains(key);
        boolean fileExists = fileHandler.existFile(getFileName(key));

        if (cacheContains && fileExists) {
            return true;
        }
        return false;
    }

    /**
     * Updates the value of the key in the cache
     *
     * @param key
     * @param value
     */
    public void put(String key, String value) throws IOException {
        cache.put(key, value);
        String fileName = getFileName(key);

        if (fileHandler.existFile(fileName)) {
            fileHandler.writeInFile(fileName, value);
        }
    }

    /**
     * Adds a new key to the cache with the value
     *
     * @param key
     * @param value
     * @throws DuplicatedKeyException
     */
    public void addNew(String key, String value) throws IOException {
        if (cache.contains(key)) {
            throw new DuplicatedKeyException("Key already exists");
        }
        String fileName = getFileName(key);
        cache.put(key, value);
        if (fileHandler.createFile(fileName)) {
            fileHandler.writeInFile(fileName, value);
        }
    }

    /**
     * Removes the key from the cache
     *
     * @param key
     * @throws KeyNotFoundException
     */
    public void remove(String key) throws KeyNotFoundException {
        if (!cache.contains(key)) {
            throw new KeyNotFoundException("Key not found");
        }
        cache.remove(key);
        fileHandler.deleteFile(getFileName(key));

    }

    /**
     * Shows the size of the cache
     *
     * @return String
     */
    public int size() {
        return cache.size();
    }

    /**
     * Returns the file name of the key in the cache
     *
     * @param file
     * @return String
     */
    private String getFileName(String file) {
        return this.dirname + "/" + file + ".txt";
    }

}