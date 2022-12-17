import Exception.DuplicatedKeyException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws DuplicatedKeyException, IOException {
        ICache cache = new ICache();

       try {
           cache.addNew("clave1", "valor1");
       }catch (DuplicatedKeyException e){
           System.out.println(e.getMessage());
       }


    }

}
