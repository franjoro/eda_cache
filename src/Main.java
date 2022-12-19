import Exception.DuplicatedKeyException;
import Exception.KeyNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws DuplicatedKeyException, KeyNotFoundException, IOException {
        ICache cache = new ICache();

        cache.addNew("clave1", "valor1");
        cache.addNew("clave2", "valor2");
        cache.addNew("clave3", "valor3");
        cache.size();


    }

}
