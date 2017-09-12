package nelk.io.crypton.utils;

import org.junit.Test;

import java.util.List;

import nelk.io.crypton.retrofit.BittrexAPI;
import nelk.io.crypton.retrofit.models.Coin;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class SHA512Test {

    @Test
    public void test(){
        // Given
        BittrexAPI bRex = new BittrexAPI();

        // When
        List<Coin> result = bRex.getAccountBalance();

        // Then
        assertNotNull(result);
        assertThat(result.size(), greaterThan(0));
    }
}
