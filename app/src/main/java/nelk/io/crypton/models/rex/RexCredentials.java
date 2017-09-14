package nelk.io.crypton.models.rex;

import nelk.io.crypton.models.Credentials;

public class RexCredentials implements Credentials {

    String key;
    String privateKey;

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

