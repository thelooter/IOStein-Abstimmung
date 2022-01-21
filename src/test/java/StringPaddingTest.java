import de.thelooter.iosteinpolls.util.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StringPaddingTest {

    @Test
    public void testPadLeft() {
        List<String> input = List.of("--------------", "hello", "c213","§4");

        assertThat(StringUtils.pad(input).get(0), equalTo("--------------"));
        assertThat(StringUtils.pad(input).get(1),equalTo("        hello"));
        assertThat(StringUtils.pad(input).get(2),equalTo("        c213"));
        assertThat(StringUtils.pad(input).get(3),equalTo("           §4"));

    }

    @Test
    public void testGetStringWidth() {
        List<String> input = List.of("--------------", "hello", "c213","§4");

        assertThat(StringUtils.MinecraftFontWidthCalculator.getStringWidth(input.get(0)), equalTo(70));
        assertThat(StringUtils.MinecraftFontWidthCalculator.getStringWidth(input.get(1)), equalTo(19));
        assertThat(StringUtils.MinecraftFontWidthCalculator.getStringWidth(input.get(2)), equalTo(20));
        assertThat(StringUtils.MinecraftFontWidthCalculator.getStringWidth(input.get(3)), equalTo(0));
        assertThat(StringUtils.MinecraftFontWidthCalculator.getStringWidth(null), equalTo(0));
    }

    @Test
    public void testEmptyPaddingList() {
        List<String> input = List.of();

        boolean thrown = false;
        try {
            StringUtils.pad(input);
        }catch (Exception e){
            thrown = true;
            e.printStackTrace();
        }

        assertThat(thrown, equalTo(true));

    }



}
