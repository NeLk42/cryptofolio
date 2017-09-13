package nelk.io.crypton.utils;

import org.junit.Test;

import java.util.List;

import nelk.io.crypton.retrofit.Bittrex.RexService;
import nelk.io.crypton.retrofit.Bittrex.models.RexData;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class Sha512UtilsTest {

    @Test
    public void test(){
        // Given
        RexService bRex = new RexService();

        // When
        List<RexData> result = bRex.getAccountBalance();

        // Then
        assertNotNull(result);
        assertThat(result.size(), greaterThan(0));
    }
}
