package nelk.io.crypton.models.rex;

public class Credentials {

    String key;
    String privateKey;

    public Credentials(String key, String privateKey){
        this.key = key;
        this.privateKey = privateKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

}

