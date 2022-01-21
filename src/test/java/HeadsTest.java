import de.thelooter.iosteinpolls.util.Heads;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HeadsTest {

    @Test
    public void testHeads() {

        assertThat(Heads.CHECKMARK, is(notNullValue()));
    }


}
