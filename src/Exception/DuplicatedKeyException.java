package Exception;

public class DuplicatedKeyException extends Throwable {
    public DuplicatedKeyException(String key_already_exists) {
        super(key_already_exists);
    }
}


