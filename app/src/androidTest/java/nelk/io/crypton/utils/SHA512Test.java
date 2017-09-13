package nelk.io.crypton.utils;

import org.junit.Test;

import java.util.List;

import nelk.io.crypton.retrofit.BRexService;
import nelk.io.crypton.retrofit.models.Data;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class SHA512Test {

    @Test
    public void test(){
        // Given
        BRexService bRex = new BRexService();

        // When
        List<Data> result = bRex.getAccountBalance();

        // Then
        assertNotNull(result);
        assertThat(result.size(), greaterThan(0));
    }
}
