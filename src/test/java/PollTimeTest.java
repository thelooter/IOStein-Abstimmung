import de.thelooter.iosteinpolls.util.PollTime;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PollTimeTest {

    @Test
    public void pollTimeTest() {
        assertThat(PollTime.TEN_MINUTES,is(notNullValue()));
        assertThat(PollTime.FIVETEEN_MINUTES, is(notNullValue()));
        assertThat(PollTime.THIRTY_MINUTES, is(notNullValue()));
        assertThat(PollTime.FOURTYFIVE_MINUTES, is(notNullValue()));
        assertThat(PollTime.ONE_HOUR, is(notNullValue()));
        assertThat(PollTime.ONE_HOUR_THIRTY_MINUTES, is(notNullValue()));
        assertThat(PollTime.TWO_HOURS, is(notNullValue()));

    }

    @Test
    public void testDuration() {
        assertThat(PollTime.TEN_MINUTES.getTicks(), is(equalTo(12000)));
        assertThat(PollTime.FIVETEEN_MINUTES.getTicks(), is(equalTo(18000)));
        assertThat(PollTime.THIRTY_MINUTES.getTicks(), is(equalTo(36000)));
        assertThat(PollTime.FOURTYFIVE_MINUTES.getTicks(), is(equalTo(54000)));
        assertThat(PollTime.ONE_HOUR.getTicks(), is(equalTo(72000)));
        assertThat(PollTime.ONE_HOUR_THIRTY_MINUTES.getTicks(), is(equalTo(108000)));
        assertThat(PollTime.TWO_HOURS.getTicks(), is(equalTo(142000)));
    }

    @Test
    public void testLoreString() {
        assertThat(PollTime.TEN_MINUTES.getLoreString(), is(equalTo("10 Minuten")));
        assertThat(PollTime.FIVETEEN_MINUTES.getLoreString(), is("15 Minuten"));
        assertThat(PollTime.THIRTY_MINUTES.getLoreString(), is("30 Minuten"));
        assertThat(PollTime.FOURTYFIVE_MINUTES.getLoreString(), is("45 Minuten"));
        assertThat(PollTime.ONE_HOUR.getLoreString(), is("1 Stunde"));
        assertThat(PollTime.ONE_HOUR_THIRTY_MINUTES.getLoreString(), is("1 Stunde 30 Minuten"));
        assertThat(PollTime.TWO_HOURS.getLoreString(), is("2 Stunden"));
    }
}
