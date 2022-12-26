import Exception.DuplicatedKeyException;
import Exception.KeyNotFoundException;

import java.io.IOException;

public class ICache {
    private static List<ObjectData> list;
    private String workDirName = "cache";
    private FileHandler fileHandler;

    public ICache() {
        list = initializeCache();
    }

    /**
     * Initialize the cache filling with existant data
     *
     * @return List<ObjectData>
     */
    private List<ObjectData> initializeCache() {
        System.out.println("Initializing cache...");
        boolean isCacheDirCreated = FileHandler.createFolder(workDirName);

        if (isCacheDirCreated) {
            System.out.println("Cache is empty, creating new cache...");
            return new List<ObjectData>();
        }
        // TODO: read cache from files
        System.out.println("Loading cache...");
        return new List<ObjectData>();
    }


    /**
     * Get all keys stored in cache.
     * @return array of stored keys
     */
    //String[] getAll();
    /**
     * Get the value associated with the key passed as argument.
     * @param key Key to look for
     * @return The value associated with the key
     * @throws KeyNotFoundException if key does not exist.
     */
    // String get(String key);
    /**
     * Return the value of key passed as argument. Otherwise, return the
     * default value passed as second argument.
     * @param key Key to look for
     * @param defaultValue Value returned when key does not exist.
     * @return The value associated with the key or the defaultValue if key was not
    found.
     */
    //String getOrDefault(String key, String defaultValue);

    /**
     * Check is a key exists in cache.
     *
     * @param key Key to look for
     * @return True if key exists.
     */
    boolean exists(String key) {
        return fileHandler.existFile(getFileName(key));
    }
    /**
     * Add or update the value associated to a key.
     * @param key Key to be stored.
     * @param value Value to be stored.
     */
    public boolean put(String key, String value){
        String keyIn = setHashCode(key);
        if (exists(keyIn)){
            return false;
        }
        try {
            fileHandler.createFileIn(getFileName(keyIn), workDirName);
            fileHandler.writeInFile(getFileName(keyIn), value);
            return true;
        } catch (IOException e) {
            System.out.println("Exception occurred: " + e);
            return false;
        }
    }

    /**
     * Add a value to a new key. If key already exists, it throws an exception.
     *
     * @param key   Key to be stored.
     * @param value Value to be stored.
     * @throws DuplicatedKeyException the key already exists.
     */
    void addNew(String key, String value) throws DuplicatedKeyException, IOException {
        ObjectData objectData = new ObjectData(key, value);
        String keyIn = getFileName(objectData.getHashCode());

        if (exists(keyIn)) {
            throw new DuplicatedKeyException("Key already exists");
        }
        System.out.println("Adding new key: " + keyIn);
        fileHandler.createFile(keyIn);
        fileHandler.writeInFile(keyIn, objectData.getPayLoad());
        list.insert(objectData, -1);
    }


    /**
     * Remove a key and its value.
     * @param key Key to be stored.
     * @throws KeyNotFoundException if key does not exist.
     */

    public boolean remove(String key) throws KeyNotFoundException{
        try {
            String keyIn = getFileName(setHashCode(key));
            if (!exists(setHashCode(key))) {
                throw new KeyNotFoundException("Key does not exist");
            }
            fileHandler.deleteFile(keyIn);;
            list.removeByKey(key);
            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e);
            return false;
        }

    }

    /**
     * Count the keys (and values) stored in cache.
     *
     * @return Count of keys.
     */
    // TODO: DIEGO
    public static int size(){
        System.out.println("Size: " + list.size());
        return list.size();
    }
    private String getFileName(String key) {
        return workDirName + "\\" + key + ".txt";
    }


    private String setHashCode(String data){
        String hash = String.format("%6x", data.hashCode());
        return hash;
    }


}