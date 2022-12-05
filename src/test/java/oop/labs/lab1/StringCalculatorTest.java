package oop.labs.lab1;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class StringCalculatorTest
{
    private StringCalculator testCalculator;

    @Before
    public void prepare()
    {
        testCalculator = new StringCalculator();
    }

    @Test
    public void step_1_basic()
    {
        assertThat(testCalculator.add("")).isEqualTo(0);
        assertThat(testCalculator.add("1")).isEqualTo(1);
        assertThat(testCalculator.add("11")).isEqualTo(11);
        assertThat(testCalculator.add("1,2")).isEqualTo(3);
        assertThat(testCalculator.add("11,22")).isEqualTo(33);
    }

    @Test
    public void step_1_extended()
    {
        assertThat(catchThrowable(() -> testCalculator.add("@"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add(","))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add(",1"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add("1,"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add("1,,"))).isInstanceOf(IllegalArgumentException.class);
    }
}
