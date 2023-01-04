import Exception.DuplicatedKeyException;
import Exception.KeyNotFoundException;
import structure.TreeMap;

import java.io.IOException;

public class ICache implements ICacheInterface {

    private String dirname = "cache";
    private TreeMap<String, String> cache;
    private FileHandler fileHandler = new FileHandler();

    public ICache() throws IOException {
        if(!fileHandler.existFile(this.dirname)){
            fileHandler.createFolder(this.dirname);
            cache = new TreeMap<>();
        }else{
            this.cache = new TreeMap<>();
            String[] keys = fileHandler.readFolder(this.dirname);
            for(String key : keys){
                String value = fileHandler.readFile(getFileName(key));
                this.cache.put(key, value);
            }

        }

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
        boolean cacheContains = cache.contains(key);
        boolean fileExists = fileHandler.existFile(getFileName(key));

        if (cacheContains && fileExists) {
            return true;
        }
        return false;
    }


    public void put(String key, String value) throws IOException {
        cache.put(key, value);
        String fileName = getFileName(key);

        if(fileHandler.existFile(fileName)){
            fileHandler.writeInFile(fileName, value);
        }
    }

    public void addNew(String key, String value) throws IOException {
        if (cache.contains(key)) {
            throw new DuplicatedKeyException("Key already exists");
        }
        String fileName = getFileName(key);
        cache.put(key, value);
        if(fileHandler.createFile(fileName)){
            fileHandler.writeInFile(fileName, value);
        }
    }

    public void remove(String key) throws KeyNotFoundException {
        if (!cache.contains(key)) {
            throw new KeyNotFoundException("Key not found");
        }
        cache.remove(key);
        System.out.println(fileHandler.deleteFile(getFileName(key)));


    }

    public int size() {
        return cache.size();
    }

    private String getFileName(String file) {
        return this.dirname + "/" + file + ".txt";
    }

}