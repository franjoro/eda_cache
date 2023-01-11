package App;
import Cache.ICache;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import java.io.IOException;
import java.util.concurrent.Callable;
import Exception.KeyNotFoundException;
import Exception.DuplicatedKeyException;


@Command (name = "Cache", version = "Cache 1.0", mixinStandardHelpOptions = true, description = "Cache is a simple database that stores key-value pairs in memory.")
public class CacheDB implements Callable<Integer> {

    @Command(name="getAll", description = "Get all keys")
    public Integer getAll() throws Exception {
        ICache cache = new ICache();
        try {
            String[] keys = cache.getAll();
            for (String key : keys) {
                System.out.println(key);
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }


    @Command(name="get", description = "Get a value")
    public Integer get(@Parameters(arity = "1", paramLabel = "Key",description = "The key") String key) throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        try {
            System.out.println(cache.get(key));
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

    @Command(name = "add", description = "Add the value associated to a key.")
    public Integer add(
            @Parameters(arity = "1", paramLabel = "Key", description = "Key to use in the command.") String key,
            @Parameters(arity = "1", paramLabel = "Value", description = "Value to use in the command.") String value) throws IOException, DuplicatedKeyException {
        ICache cache = new ICache();
        try {
            cache.addNew(key, value);
            System.out.println(value + " added into " + key);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }



    @Command(name = "put", description = "Updates the value associated to a key. Value must have apostrophes.")
    public Integer put(
            @Parameters(arity = "1", paramLabel = "Key", description = "Key to use in the command.") String key,
            @Parameters(arity = "1", paramLabel = "Value", description = "Value to use in the command.") String value) throws IOException {
        ICache cache = new ICache();
        try {
            cache.put(key, value);
            System.out.println(key + " updated with " + value);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Command(name = "remove", description = "Delete a key")
    public Integer remove(@Parameters(arity = "1", paramLabel = "Key", description = "Key to use in command") String key) throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        try {
            cache.remove(key);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Command(name = "size", description = "Get the size of the cache")
    public Integer size() throws IOException {
        ICache cache = new ICache();
        try {
            System.out.println(cache.size());
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }


    @Command(name="exists", description = "Check if a key exist")
    public Integer exist(@Parameters(arity = "1", paramLabel = "Key",description = "The key") String key) throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        try {
            System.out.println(cache.exists(key));
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Commands: getAll, get, add, put, remove, size, exists");
        return 0;
    }


    public static void main(String[] args) {
        int exitCode = new CommandLine(new CacheDB()).execute(args);
        System.exit(exitCode);
    }

}
