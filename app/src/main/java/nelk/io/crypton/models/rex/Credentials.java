package nelk.io.crypton.models.rex;

public class Credentials {

    private String key;
    private String privateKey;

    Credentials(Credentials credentials){
        this.key = credentials.getKey();
        this.privateKey = credentials.getPrivateKey();
    }

    public Credentials(String key, String privateKey){
        this.key = key;
        this.privateKey = privateKey;
    }

    public String getKey() {
        return key;
    }

    void setKey(String key) {
        this.key = key;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

}

