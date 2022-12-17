
public class ObjectData {
    private String identifier;
    private String value;
    private String hashCode;
    private String payLoad;

    /**
     * constructor
     * @param clave identifier of the object
     * @param valor value of the object
     */
    public ObjectData(String clave, String valor) {
        this.identifier = clave;
        this.value = valor;
        setHashCode(clave);
        setPayLoad(clave, valor);
    }

    /**
     * Getters and Setters
     **/
    public String getHashCode() {
        return hashCode;
    }

    public String getPayLoad() {
        return payLoad;
    }

    /**
     * set hash code
     * @param data
     */
    private void setHashCode(String data){
        String hash = String.format("%6x", data.hashCode());
        this.hashCode = hash;
    }

    /**
     * set payload
     * @param clave
     * @param valor
     */
    private void setPayLoad(String clave, String valor){
        this.payLoad = clave + ":" + valor;
    }


}
