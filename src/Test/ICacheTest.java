import Cache.FileHandler;
import Cache.ICache;
import Exception.KeyNotFoundException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ICacheTest  {
    private ICache cache = new ICache();
    private FileHandler file = new FileHandler();


    @Test
    void insert() throws IOException, KeyNotFoundException {
        cache.addNew("key", "value");
        assertEquals("value", cache.get("key"));
        remove("key");
    }

    @Test
    void put() throws IOException, KeyNotFoundException {
        cache.addNew("key", "value");
        cache.put("key", "value2");
        assertEquals("value2", cache.get("key"));
        remove("key");
    }
    @Test
    void exist() throws IOException, KeyNotFoundException {
        cache.addNew("key", "value");
        assertEquals(true, cache.exists("key"));
        remove("key");
    }
    @Test
    void getOrDefault() throws IOException, KeyNotFoundException {
        cache.addNew("key1", "value");
        assertEquals("value", cache.getOrDefault("key", "value"));
        remove("key1");
    }

    @Test
    void size() throws IOException, KeyNotFoundException {
        cache.addNew("key", "value");
        assertEquals(1, cache.size());
        remove("key");
    }

    @Test
    void remove() throws IOException, KeyNotFoundException {
        cache.addNew("key", "value");
        cache.remove("key");
        assertEquals(false, file.existFile("cache/key"));
    }

    void remove(String key) throws IOException, KeyNotFoundException {
        cache.remove(key);
        assertEquals(false, file.existFile("cache/key"));
    }



}
